package com.springapp.mvc.login;

import com.springapp.mvc.user.model.Role;
import com.springapp.mvc.user.model.User;
import com.springapp.mvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DbAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userService.findByUserName(username);

        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Role role = Role.getRoleById(user.getRoleId());
        if (role == null) {
            throw new BadCredentialsException("Wrong privileges.");
        }

        return new UsernamePasswordAuthenticationToken(user, password, Arrays.asList(role));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
