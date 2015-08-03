package com.springapp.mvc.message.service;

import com.springapp.mvc.message.dao.MessageDao;
import com.springapp.mvc.message.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    @Override
    @Transactional
    public List<Message> findBySender(Integer id) {
        return messageDao.findBySender(id);
    }

    @Override
    @Transactional
    public List<Message> findByRecipient(Integer id) {
        return messageDao.findByRecipient(id);
    }

    @Override
    @Transactional
    public void delete(Integer messageId) {
        Message message = messageDao.find(messageId);
        if (message != null) {
            messageDao.delete(message);
        }
    }

    @Override
    public void send(Message message) {
        message.setDate(new Date());
        messageDao.create(message);
    }
}
