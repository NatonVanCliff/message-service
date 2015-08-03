package com.springapp.mvc.user.dao;

import com.springapp.mvc.orm.CrudDao;
import com.springapp.mvc.user.model.User;
import java.util.List;

public interface UserDao extends CrudDao<User>{

    User findByUserName(String userName);

    List<User> findAllExcept(User user);
}
