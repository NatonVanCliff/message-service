package com.springapp.mvc.contact.service;

import com.springapp.mvc.contact.model.Contact;

import java.util.Collection;
import java.util.List;

public interface ContactService {

    List<Contact> findByUser(int userId);

    void removeContact(Integer userId, Integer contactId);

    List<Contact> findByContact(Integer contactId);

    void delete(Contact contact);

    void addContact(Contact contact);
}
