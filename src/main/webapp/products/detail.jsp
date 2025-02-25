<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.unistock.model.Product" %>
<jsp:useBean id="product" type="com.unistock.model.Product" scope="request" />

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Unistock</title>
        <link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%2210 0 100 100%22><text y=%22.90em%22 font-size=%2290%22>📦</text></svg>"></link>


        <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/table.css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/button.css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/detalhes.css">
    </head>
    <body>


    <jsp:include page="/partials/header.jsp" />


    <main id="home">

        <article id="detalhes">


            <h2>Visualizar Produto:</h2>


            <table>
                <tr>
                    <th>ID</th>
                    <td><%= product.getId() %></td>
                </tr>
                <form action="/unistock/products" method="POST" id="formulario">
                    <input type="hidden" name="_method" value="put" />
                    <input type="hidden" name="id" value="<%= product.getId() %>" />

                    <tr>
                        <th>Código</th>
                        <td><input type="text" value="<%= product.getCode() %>" name="codigo" disabled></td>
                    </tr>
                    <tr>
                        <th>Nome</th>
                        <td><input type="text" value="<%= product.getName() %>" name="nome" disabled></td>
                    </tr>
                    <tr>
                        <th>Preço (R$)</th>
                        <td><input type="number" value="<%= String.format("%.2f", product.getPrice()) %>" name="preco" disabled></td>
                    </tr>

                </form>
            </table>



            <div class="agrupamento-botoes">

                <form action="/unistock/products" method="POST">

                    <input type="hidden" name="_method" value="delete" />

                    <input type="hidden" name="id" value="<%= product.getId() %>">

                    <button type="submit" class="btn btn-danger">Excluir</button>

                </form>

                <button id="btnEditar" class="btn btn-warning">Editar</button>

                <button id="btnSalvar" class="btn btn-success hidden">Salvar</button>

            </div>

            <a id="voltar-texto" href="/unistock/products">Voltar para a lista de produtos</a>

        </article>
    </main>

    <jsp:include page="/partials/footer.jsp" />

    <script>
        document.addEventListener("DOMContentLoaded", () => {

            const btnEditar = document.querySelector("#btnEditar");
            const btnSalvar = document.querySelector("#btnSalvar");
            const inputs = document.querySelectorAll("input");
            const form = document.querySelector("#formulario");

            btnEditar.addEventListener("click", () => {
                inputs.forEach(input => {
                    input.disabled = false;
                });
                btnSalvar.classList.remove("hidden");
                btnEditar.classList.add("hidden");
            });


            btnSalvar.addEventListener("click", () => {
                form.submit();
            });
        });
    </script>
    </body>
</html>