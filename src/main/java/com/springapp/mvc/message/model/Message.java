package com.springapp.mvc.message.model;

import com.springapp.mvc.user.model.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ms_messages")
public class Message implements Serializable {
    private Integer id;
    private Integer senderId;
    private Integer recipientId;
    private User sender;
    private User recipient;
    private Date date;
    private String subject;
    private String text;

    public Message() {
    }

    public Message(Integer id, User sender, User recipient, Date date, String subject, String text) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.date = date;
        this.subject = subject;
        this.text = text;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "senderId")
    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    @Column(name = "recipientId")
    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    @ManyToOne
    @JoinColumn(name = "senderId", nullable = false, insertable = false, updatable = false)
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne
    @JoinColumn(name = "recipientId", nullable = false, insertable = false, updatable = false)
    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "subject", nullable = false)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}