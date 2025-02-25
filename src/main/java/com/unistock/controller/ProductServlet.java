package com.unistock.controller;

import com.unistock.dao.product.ProductDAO;
import com.unistock.dao.product.ProductDAOImpl;
import com.unistock.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Product product = productDAO.getById(id);

            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("products/detail.jsp").forward(request, response);
            } else {
                sendAlert(response, "Produto não encontrado.");
            }

        } else {
            int page = parseIntOrDefault(request.getParameter("page"), 1);
            int limit = Math.min(Math.max(parseIntOrDefault(request.getParameter("limit"), 15), 15), 50);

            String orderBy = request.getParameter("orderBy") != null ? request.getParameter("orderBy") : "id";
            String orderDirection = request.getParameter("orderDirection") != null ? request.getParameter("orderDirection") : "ASC";

            int start = (page - 1) * limit;

            List<Product> products = productDAO.getAllPaginated(start, limit, orderBy, orderDirection);

            request.setAttribute("products", products);
            request.setAttribute("page", page);
            request.setAttribute("limit", limit);
            request.setAttribute("orderBy", orderBy);
            request.setAttribute("orderDirection", orderDirection);

            request.getRequestDispatcher("products/index.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getParameter("_method");

        if ("put".equalsIgnoreCase(method)) {
            doPut(request, response);
        } else if ("delete".equalsIgnoreCase(method)) {
            doDelete(request, response);
        } else {
            handleSave(request, response, false);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleSave(request, response, true);
    }

    private void handleSave(HttpServletRequest request, HttpServletResponse response, boolean isUpdate) throws IOException {
        int id = isUpdate ? parseIntOrDefault(request.getParameter("id"), 0) : 0;
        String codigo = request.getParameter("codigo");
        String nome = request.getParameter("nome");

        double preco;
        try {
            preco = Double.parseDouble(request.getParameter("preco"));
        } catch (NumberFormatException e) {
            sendAlert(response, "Erro: Formato de preço inválido.");
            return;
        }

        Product product = new Product(id, codigo, nome, preco);

        try {
            productDAO.save(product);
            sendAlert(response, "Produto salvo.");
        } catch (IllegalArgumentException e) {
            sendAlert(response, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.getById(id);

        if (product == null) {
            sendAlert(response, "Produto não encontrado.");
            return;
        }

        productDAO.delete(product);
        sendAlert(response, "Produto excluído.");
    }

    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private void sendAlert(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript'>");
        out.println("alert('" + message + "');");
        out.println("window.location = '/unistock/products';");
        out.println("</script>");
    }

}