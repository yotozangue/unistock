package com.unistock.dao.product;

import com.unistock.dao.GenericDAO;
import com.unistock.model.Product;

import java.util.ArrayList;

public interface ProductDAO extends GenericDAO<Product> {
    ArrayList<Product> getAllPaginated(int start, int total, String orderBy, String orderDirection);
    boolean existsByCode(String code);
}
