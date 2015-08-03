package com.springapp.mvc.contact.service;

import com.springapp.mvc.contact.dao.ContactDao;
import com.springapp.mvc.contact.model.Contact;
import com.springapp.mvc.contact.model.ContactPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;

    @Override
    @Transactional
    public List<Contact> findByUser(int userId) {
        return contactDao.findByUser(userId);
    }

    @Override
    @Transactional
    public void removeContact(Integer userId, Integer contactId) {
        ContactPK contactPK = new ContactPK(userId, contactId);
        Contact contact = contactDao.find(contactPK);
        if (contact != null) {
            contactDao.delete(contact);
        }
    }

    @Override
    @Transactional
    public List<Contact> findByContact(Integer contactId) {
        return contactDao.findByContact(contactId);
    }

    @Override
    @Transactional
    public void delete(Contact contact) {
        contactDao.delete(contact);
    }

    @Override
    @Transactional
    public void addContact(Contact contact) {
        contactDao.create(contact);
    }
}
