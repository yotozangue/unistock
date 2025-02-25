<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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


            <h2>Novo Produto:</h2>


            <table>
                <form action="/unistock/products" method="POST" id="formulario">

                    <tr>
                        <th>Código</th>
                        <td><input type="text" name="codigo" required></td>
                    </tr>
                    <tr>
                        <th>Nome</th>
                        <td><input type="text" name="nome" required></td>
                    </tr>
                    <tr>
                        <th>Preço (R$)</th>
                        <td><input type="number" step="0.01" value="0,0" min="0" name="preco" required></td>
                    </tr>
                </form>
            </table>



            <div class="agrupamento-botoes">

                <button id="btnSalvar" class="btn btn-success">Salvar</button>

            </div>

            <a id="voltar-texto" href="/unistock/products">Voltar para a lista de produtos</a>

        </article>
    </main>

    <jsp:include page="/partials/footer.jsp" />

    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const form = document.querySelector("#formulario");
            const btnSalvar = document.querySelector("#btnSalvar");

            btnSalvar.addEventListener("click", (event) => {
                if (form.checkValidity()) {
                    form.submit();
                } else {
                    alert("Por favor, preencha todos os campos obrigatórios.");
                }
            });
        });
    </script>
    </body>
</html>