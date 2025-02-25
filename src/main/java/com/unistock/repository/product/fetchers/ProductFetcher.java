package com.unistock.repository.product.fetchers;


import com.unistock.model.Product;
import com.unistock.repository.GenericFetcher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductFetcher extends GenericFetcher<Product> {
    ArrayList<Product> getAllWithLimit(Connection connection, int start, int total, String orderBy, String orderDirection) throws SQLException;
    boolean existsByCode(Connection connection, String code) throws SQLException;
}
