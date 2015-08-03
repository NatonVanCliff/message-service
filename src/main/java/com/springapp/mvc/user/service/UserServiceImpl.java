package com.springapp.mvc.user.service;

import com.springapp.mvc.contact.dao.ContactDao;
import com.springapp.mvc.contact.service.ContactService;
import com.springapp.mvc.message.service.MessageService;
import com.springapp.mvc.user.dao.UserDao;
import com.springapp.mvc.user.model.Role;
import com.springapp.mvc.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ContactDao contactDao;

    @Override
    @Transactional
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    @Transactional
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    @Transactional
    public List<User> findAllExcept(User user) {
        return userDao.findAllExcept(user);
    }

    @Override
    @Transactional
    public void deleteByUser(Integer id) {
        User deletedUser = userDao.find(id);
        if (deletedUser != null) {
            userDao.delete(deletedUser);
        }
    }

    @Override
    @Transactional
    public void setRole(Integer userId, Role role) {
        User user = userDao.find(userId);
        if (user != null) {
            user.setRoleId(role.getId());
            userDao.update(user);
        }
    }

    @Override
    @Transactional
    public List<User> findAvailableUsers(User user) {
        StringBuilder builder = new StringBuilder();
        builder.append(" id <> ? and id not in (select contactId from Contact where userId = ?)");
        List<User> userList = userDao.findWhereSql(builder.toString(), user.getId(), user.getId());
        return userList;
    }
}
