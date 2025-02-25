package com.unistock.repository.product.proxies;

import com.unistock.model.Product;
import com.unistock.repository.product.handlers.ProductHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProductHandlerProxy implements InvocationHandler {
    private final ProductHandler target;

    private ProductHandlerProxy(ProductHandler target) {
        this.target = target;
    }

    public static ProductHandler create(ProductHandler target) {
        return (ProductHandler) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{ProductHandler.class},
                new ProductHandlerProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        switch (method.getName()) {
            case "insert":
            case "update":
                validateProduct((Product) args[1]);
                break;
            case "delete":
                validateProductId((int) args[1]);
                break;
        }

        return method.invoke(target, args);
    }

    private void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (product.getCode() == null || product.getCode().isEmpty()) {
            throw new IllegalArgumentException("Product code cannot be null or empty.");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero.");
        }
    }


    private void validateProductId(int productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero.");
        }
    }
}