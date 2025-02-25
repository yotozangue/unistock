package com.unistock.dao;

import java.util.ArrayList;

public interface GenericDAO<T> {

    void save(T entity);
    void delete(T entity);
    T getById(int id);
    ArrayList<T> getAll();

}
