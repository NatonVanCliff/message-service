package com.springapp.mvc.contact.dao;

import com.springapp.mvc.contact.model.Contact;
import com.springapp.mvc.orm.CrudDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDaoImpl extends CrudDaoImpl<Contact> implements ContactDao {
    private static final  String USER_ID_COLUMN = "userId";
    private static final  String CONTACT_ID_COLUMN = "contactId";

    @Autowired
    public ContactDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Contact> findByUser(Integer userId) {
        return findByColumn(USER_ID_COLUMN, userId);
    }

    @Override
    public List<Contact> findByContact(Integer contactId) {
        return findByColumn(CONTACT_ID_COLUMN, contactId);
    }

    @Override
    protected Class getModelClass() {
        return Contact.class;
    }

    @Override
    protected String getTableName() {
        return Contact.class.getSimpleName();
    }
}
