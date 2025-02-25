package com.unistock.dao;

import com.unistock.config.DatabaseConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO<T> implements GenericDAO<T> {

    protected Connection getConnection() throws SQLException {
        return DatabaseConnectionManager.getConnection();
    }

}
