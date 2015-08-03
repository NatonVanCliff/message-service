package com.springapp.mvc.orm;

import java.io.Serializable;
import java.util.List;

public interface CrudDao<T extends Serializable> {

    void create(T object);

    T find(Serializable primaryKey);

    List<T> findAll();

    void update(T object);

    void delete(T object);

    List<T> findWhereSql(String whereSql, Object...parameters);
}
