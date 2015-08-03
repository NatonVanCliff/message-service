package com.springapp.mvc.contact.dao;

import com.springapp.mvc.contact.model.Contact;
import com.springapp.mvc.orm.CrudDao;

import java.util.List;

public interface ContactDao extends CrudDao<Contact> {

    List<Contact> findByUser(Integer userId);

    List<Contact> findByContact(Integer contactId);
}
