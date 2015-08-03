package com.springapp.mvc.message.dao;

import com.springapp.mvc.message.model.Message;
import com.springapp.mvc.orm.CrudDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl extends CrudDaoImpl<Message> implements MessageDao {
    private static final  String SENDER_COLUMN = "senderId";
    private static final  String RECIPIENT_COLUMN = "recipientId";

    @Autowired
    public MessageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class getModelClass() {
        return Message.class;
    }

    @Override
    protected String getTableName() {
        return Message.class.getSimpleName();
    }

    @Override
    public List<Message> findBySender(Integer id) {
        return findByColumn(SENDER_COLUMN, id);
    }

    @Override
    public List<Message> findByRecipient(Integer id) {
        return findByColumn(RECIPIENT_COLUMN, id);
    }
}
