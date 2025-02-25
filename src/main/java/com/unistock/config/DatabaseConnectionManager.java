package com.unistock.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static final String DB_URL = System.getenv("DB_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado", e);
        }

        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}