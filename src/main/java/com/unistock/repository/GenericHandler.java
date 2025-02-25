package com.unistock.repository;

import com.unistock.model.Product;

import java.sql.Connection;
import java.sql.SQLException;

public interface GenericHandler<T> {
    void insert(Connection connection, T entity) throws SQLException;
    void update(Connection connection, T entity) throws SQLException;
    void delete(Connection connection, int entityId) throws SQLException;
}
