package com.unistock.repository.product.handlers;

import com.unistock.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductHandlerImpl implements ProductHandler {

    @Override
    public void insert(Connection connection, Product entity) throws SQLException {
        String sql = "INSERT INTO product (code, name, price) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getCode());
            stmt.setString(2, entity.getName());
            stmt.setDouble(3, entity.getPrice());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Connection connection, Product entity) throws SQLException {
        String sql = "UPDATE product SET code = ?, name = ?, price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getCode());
            stmt.setString(2, entity.getName());
            stmt.setDouble(3, entity.getPrice());
            stmt.setInt(4, entity.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Connection connection, int entityId) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entityId);
            stmt.executeUpdate();
        }
    }
}
