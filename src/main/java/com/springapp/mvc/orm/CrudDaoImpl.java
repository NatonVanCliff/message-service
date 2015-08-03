package com.springapp.mvc.orm;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class CrudDaoImpl<T extends Serializable> implements CrudDao<T>{
    private SessionFactory sessionFactory;

    public CrudDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(T object) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(object);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public T find(Serializable primaryKey) {
        Session session = getSession();
        T object = (T) session.get(getModelClass(), primaryKey);
        return object;
    }

    @Override
    public List<T> findAll() {
        Session session = getSession();
        List<T> list;
        try {
            list = session.createQuery("from " + getTableName()).list();

        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void update(T object) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(object);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(T object) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(object);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    protected List<T> findByColumn(String columnName, Object object) {
        Session session = getSession();
        List<T> list;
        StringBuilder builder = new StringBuilder();
        builder.append("from ")
                .append(getTableName())
                .append(" where ")
                .append(columnName)
                .append(" = :")
                .append(columnName);
        try {
            list = session.createQuery(builder.toString())
                    .setParameter(columnName, object)
                    .list();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<T> findWhereSql(String whereSql, Object...parameters) {
        Session session = getSession();
        List<T> list;
        StringBuilder builder = new StringBuilder();
        builder.append("from ")
                .append(getTableName())
                .append(" where ")
                .append(whereSql);
        try {
            Query query = session.createQuery(builder.toString());
            int offset = 0;
            for (Object parameter: parameters) {
                query.setParameter(offset, parameter);
                ++offset;
            }
            list = query.list();
        } finally {
            session.close();
        }
        return list;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    protected abstract Class getModelClass();

    protected abstract String getTableName();
}
