package com.springapp.mvc.user.dao;

import com.springapp.mvc.orm.CrudDaoImpl;
import com.springapp.mvc.user.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends CrudDaoImpl<User> implements UserDao {
    private static final  String USER_NAME_COLUMN = "userName";

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class getModelClass() {
        return User.class;
    }

    @Override
    protected String getTableName() {
        return User.class.getSimpleName();
    }

    @Override
    public User findByUserName(String userName) {
        List<User> userList = findByColumn(USER_NAME_COLUMN, userName);
        return userList.size() > 0? userList.get(0): null;
    }

    @Override
    public List<User> findAllExcept(User user) {
        return findWhereSql(" id in (select id from " + getTableName() + "  where id <> ?)", user.getId());
    }
}
