package com.springapp.mvc.contact.model;

import java.io.Serializable;

public class ContactPK implements Serializable {

    private Integer userId;
    private Integer contactId;

    public ContactPK() {
    }

    public ContactPK(Integer userId, Integer contactId) {
        this.userId = userId;
        this.contactId = contactId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
}