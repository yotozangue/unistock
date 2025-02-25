package com.unistock.repository.product.fetchers;

import com.unistock.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductFetcherImpl implements ProductFetcher {

    @Override
    public Product getById(Connection connection, int id) throws SQLException {
        String sql = "SELECT id, code, name, price FROM product WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getDouble("price")
                );
            }
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAll(Connection connection) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT id, code, name, price FROM product";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getDouble("price")
                ));
            }
        }
        return products;
    }

    @Override
    public ArrayList<Product> getAllWithLimit(Connection connection, int start, int total, String orderBy, String orderDirection) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT id, code, name, price FROM product ORDER BY " + orderBy + " " + orderDirection + " LIMIT ? OFFSET ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, total);
            stmt.setInt(2, start);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getDouble("price")
                ));
            }
        }
        return products;
    }

    @Override
    public boolean existsByCode(Connection connection, String code) throws SQLException {
        String sql = "SELECT COUNT(*) FROM product WHERE code = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
