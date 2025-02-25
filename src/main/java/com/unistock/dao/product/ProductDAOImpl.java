package com.unistock.dao.product;

import com.unistock.repository.product.handlers.ProductHandler;
import com.unistock.repository.product.handlers.ProductHandlerImpl;
import com.unistock.repository.product.proxies.ProductFetcherProxy;
import com.unistock.dao.AbstractDAO;
import com.unistock.model.Product;
import com.unistock.repository.product.fetchers.ProductFetcher;
import com.unistock.repository.product.fetchers.ProductFetcherImpl;
import com.unistock.repository.product.proxies.ProductHandlerProxy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl extends AbstractDAO<Product> implements ProductDAO {

    private final ProductFetcher productFetcher;
    private final ProductHandler productHandler;

    public ProductDAOImpl() {
        ProductFetcher fetcher = new ProductFetcherImpl();
        this.productFetcher = ProductFetcherProxy.create(fetcher);
        ProductHandler handler = new ProductHandlerImpl();
        this.productHandler = ProductHandlerProxy.create(handler);
    }


    @Override
    public void save(Product entity) {
        try (Connection conn = getConnection()) {
            if (entity.getId() == 0) {
                if (existsByCode(entity.getCode())) {
                    throw new IllegalArgumentException("Product code is already in use.");
                }
                productHandler.insert(conn, entity);
            } else {
                productHandler.update(conn, entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Product entity) {
        try (Connection conn = getConnection()) {
            productHandler.delete(conn, entity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getById(int id) {
        try (Connection conn = getConnection()) {
            return productFetcher.getById(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Product> getAll() {
        try (Connection conn = getConnection()) {
            return productFetcher.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<Product> getAllPaginated(int start, int total, String orderBy, String orderDirection) {
        try (Connection conn = getConnection()) {

            return productFetcher.getAllWithLimit(conn, start, total, orderBy, orderDirection);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean existsByCode(String code) {
        try (Connection conn = getConnection()) {
            return productFetcher.existsByCode(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
