package com.ebsl.data.dao;

import java.util.List;

public interface Dao<E> {
    void persist(E entity);
    void remove(E entity);
    E findById(long id);
    List<E> findAll();
    List<E> findByProperty(String propertyName, String value);
	void update(E entity);
    
}
