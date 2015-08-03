package com.springapp.mvc.message.service;

import com.springapp.mvc.message.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> findBySender(Integer id);

    List<Message> findByRecipient(Integer id);

    void delete(Integer messageId);

    void send(Message message);
}
