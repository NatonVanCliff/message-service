package com.springapp.mvc.contact.model;

import com.springapp.mvc.user.model.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "ms_contacts")
@IdClass(ContactPK.class)
public class Contact implements Serializable {

    private Integer userId;
    private Integer contactId;
    private User user;
    private User contact;

    public Contact() {
    }

    public Contact(Integer userId, Integer contactId) {
        this.userId = userId;
        this.contactId = contactId;
    }

    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Id
    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "contactId", nullable = false, insertable = false, updatable = false)
    public User getContact() {
        return contact;
    }

    public void setContact(User contact) {
        this.contact = contact;
    }
}
