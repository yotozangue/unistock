package com.unistock.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GenericFetcher<T> {
    T getById(Connection connection, int id) throws SQLException;
    ArrayList<T> getAll(Connection connection) throws SQLException;
}
