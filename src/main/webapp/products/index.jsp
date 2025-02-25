<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.unistock.model.Product" %>
<jsp:useBean id="products" type="java.util.List" scope="request" />

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unistock</title>
    <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%2210 0 100 100%22><text y=%22.90em%22 font-size=%2290%22>📦</text></svg>"></link>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/table.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/button.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/index.css">
</head>
    <body>

    <jsp:include page="/partials/header.jsp" />

    <main id="home">

        <article id="estoque">

            <h2>Meu Estoque:</h2>


            <table>
                <thead>
                <tr>
                    <th onclick="changeOrderBy('id')">
                        ID
                        <%= "id".equals(request.getParameter("orderBy")) ? (request.getParameter("orderDirection").equals("ASC") ? " ↑" : " ↓") : "" %>
                    </th>
                    <th onclick="changeOrderBy('code')">
                        Código
                        <%= "code".equals(request.getParameter("orderBy")) ? (request.getParameter("orderDirection").equals("ASC") ? " ↑" : " ↓") : "" %>
                    </th>
                    <th onclick="changeOrderBy('name')">
                        Nome
                        <%= "name".equals(request.getParameter("orderBy")) ? (request.getParameter("orderDirection").equals("ASC") ? " ↑" : " ↓") : "" %>
                    </th>
                    <th onclick="changeOrderBy('price')">
                        Preço
                        <%= "price".equals(request.getParameter("orderBy")) ? (request.getParameter("orderDirection").equals("ASC") ? " ↑" : " ↓") : "" %>
                    </th>
                    <th>Detalhes</th>
                </tr>
                </thead>

                <tbody>
                <%
                    List<Product> productList = (List<Product>) request.getAttribute("products");
                    if (productList != null) {
                        for (Product product : productList) {
                %>
                <tr>
                    <td><%= product.getId() %></td>
                    <td><%= product.getCode() %></td>
                    <td><%= product.getName() %></td>
                    <td>R$ <%= String.format("%.2f", product.getPrice()) %></td>
                    <td>
                        <a class="btn btn-info" href="products?id=<%= product.getId() %>">Detalhes</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>

            <form id="itens" action="products" method="get">

                <input type="hidden" name="orderBy" id="orderBy" value="<%= request.getParameter("orderBy") != null ? request.getParameter("orderBy") : "id" %>">
                <input type="hidden" name="orderDirection" id="orderDirection" value="<%= request.getParameter("orderDirection") != null ? request.getParameter("orderDirection") : "ASC" %>">

                <div class="filter">
                    <p>Itens por página: </p>
                    <select name="limit" onchange="submitForm()">
                        <option value="15" <%= "15".equals(request.getParameter("limit")) ? "selected" : "" %>>15</option>
                        <option value="20" <%= "20".equals(request.getParameter("limit")) ? "selected" : "" %>>20</option>
                        <option value="30" <%= "30".equals(request.getParameter("limit")) ? "selected" : "" %>>30</option>
                        <option value="50" <%= "50".equals(request.getParameter("limit")) ? "selected" : "" %>>50</option>
                    </select>
                </div>

                <div class="filter">
                    <button type="button" class="btn" onclick="alterarPagina(-1)"><</button>
                    <input type="number" name="page" id="page" min="1" value="<%= request.getParameter("page") != null ? request.getParameter("page") : "1" %>" onchange="submitForm()">
                    <button type="button" class="btn" onclick="alterarPagina(1)">></button>
                </div>

            </form>


            <div>
                <button onclick="window.location='/unistock/products/new.jsp';" class="btn btn-success btn-novo">Novo Produto</button>
            </div>


        </article>
    </main>

    <jsp:include page="/partials/footer.jsp" />

    <script>
        function alterarPagina(delta) {
            let input = document.getElementById("page");
            let paginaAtual = parseInt(input.value) || 1;
            let novaPagina = Math.max(1, paginaAtual + delta);

            input.value = novaPagina;
            submitForm();
        }

        function changeOrderBy(column) {
            let orderByInput = document.getElementById("orderBy");
            let orderDirectionInput = document.getElementById("orderDirection");

            if (orderByInput.value === column) {
                orderDirectionInput.value = orderDirectionInput.value === "ASC" ? "DESC" : "ASC";
            } else {
                orderByInput.value = column;
                orderDirectionInput.value = "ASC";
            }

            submitForm();
        }

        function submitForm() {
            document.getElementById("itens").submit();
        }
    </script>
    </body>
</html>
