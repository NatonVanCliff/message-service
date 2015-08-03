package com.springapp.mvc.message.dao;

import com.springapp.mvc.message.model.Message;
import com.springapp.mvc.orm.CrudDao;

import java.util.List;

public interface MessageDao extends CrudDao<Message> {
    List<Message> findBySender(Integer id);

    List<Message> findByRecipient(Integer id);
}
