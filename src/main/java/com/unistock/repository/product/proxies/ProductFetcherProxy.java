package com.unistock.repository.product.proxies;

import com.unistock.repository.product.fetchers.ProductFetcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class ProductFetcherProxy implements InvocationHandler {
    private final ProductFetcher target;
    private static final ArrayList<String> ALLOWED_COLUMNS = new ArrayList<>();
    private static final ArrayList<String> ALLOWED_DIRECTIONS = new ArrayList<>();

    static {
        ALLOWED_COLUMNS.add("id");
        ALLOWED_COLUMNS.add("code");
        ALLOWED_COLUMNS.add("name");
        ALLOWED_COLUMNS.add("price");

        ALLOWED_DIRECTIONS.add("ASC");
        ALLOWED_DIRECTIONS.add("DESC");
    }

    private ProductFetcherProxy(ProductFetcher target) {
        this.target = target;
    }

    public static ProductFetcher create(ProductFetcher target) {
        return (ProductFetcher) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{ProductFetcher.class},
                new ProductFetcherProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("getAllWithLimit")) {
            validateInputs(args);
        }

        return method.invoke(target, args);
    }

    private void validateInputs(Object[] args) {
        if (args.length != 5) {
            throw new IllegalArgumentException("Invalid number of arguments for getAllWithLimit.");
        }

        int start = (int) args[1];
        int total = (int) args[2];
        String orderBy = (String) args[3];
        String orderDirection = (String) args[4];

        if (start < 0 || total <= 0) {
            throw new IllegalArgumentException("Invalid pagination values.");
        }

        if (!ALLOWED_COLUMNS.contains(orderBy.toLowerCase())) {
            throw new IllegalArgumentException("Invalid order by column.");
        }

        if (!ALLOWED_DIRECTIONS.contains(orderDirection.toUpperCase())) {
            throw new IllegalArgumentException("Invalid order direction.");
        }
    }
}
