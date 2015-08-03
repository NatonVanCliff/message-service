package com.springapp.mvc.user.service;

import com.springapp.mvc.user.model.Role;
import com.springapp.mvc.user.model.User;

import java.util.List;

public interface UserService {

    User findByUserName(String userName);

    void create(User user);

    List<User> findAllExcept(User user);

    void deleteByUser(Integer id);

    void setRole(Integer userId, Role role);

    List<User> findAvailableUsers(User user);
}
