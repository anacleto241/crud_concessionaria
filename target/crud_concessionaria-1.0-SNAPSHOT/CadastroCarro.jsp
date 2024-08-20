<%-- 
    Document   : CadastroCarro
    Created on : 8 de jul. de 2024, 21:42:09
    Author     : fabricio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    </head>

    <script>

        function submitForm(opcaoValue) {

            document.getElementById("opcao").value = opcaoValue;
            document.getElementById("cadastroForm").submit();
        }


    </script>


    <body>
        <h1>Cadastro Carro</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/CarroControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="carro_id" value="${carro_id}" />
                <p><label>Marca:</label> <input type="text" name="marca" value="${marca}" size="30" /> </p>
                <p><label>Modelo</label> <input type="text" name="modelo" value="${modelo}" size="30" /> </p>
                <p><label>Ano</label> <input type="text" name="ano" value="${ano}" size="8" /> </p>
                <p><label>Cor:</label> <input type="text" name="cor" value="${cor}" size="15" /> </p>
                <p><label>Preço:</label> <input type="text" name="preco" value="${preco}" size="8" /> </p>
                <p><label>Status</label> <input type="text" name="status" value="${status}" size="15" /> </p>
                 <p><label>Percentual de comissão:</label> <input type="text" name="percentual_comissao" value="${percentual_comissao}" size="5" /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/CarroControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty carro}">
                <tr>
                    <th>ID</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Ano</th>
                    <th>Cor</th>
                    <th>Preço</th>
                    <th>Status</th>
                    <th>Percentual de comissão</th>

                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="carro" items="${carro}">
                <tr>
                    <td>${carro.carro_id}</td>
                    <td>${carro.marca}</td>
                    <td>${carro.modelo}</td>
                    <td>${carro.ano}</td>
                    <td>${carro.cor}</td>
                    <td>${carro.preco}</td>
                    <td>${carro.status}</td>
                    <td>${carro.percentual_comissao}
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/CarroControlador" method="get">
                            <input type="hidden" name="carro_id" value="${carro.carro_id}" >
                            <input type="hidden" name="marca" value="${carro.marca}" >
                            <input type="hidden" name="modelo" value="${carro.modelo}" >
                            <input type="hidden" name="ano" value="${carro.ano}" >
                            <input type="hidden" name="cor" value="${carro.cor}" >
                            <input type="hidden" name="preco" value="${carro.preco}" >
                            <input type="hidden" name="status" value="${carro.status}" >
                            <input type="hidden" name="percentual_comissao" value="${carro.percentual_comissao}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/CarroControlador" method="get">
                           <input type="hidden" name="carro_id" value="${carro.carro_id}" >
                            <input type="hidden" name="marca" value="${carro.marca}" >
                            <input type="hidden" name="modelo" value="${carro.modelo}" >
                            <input type="hidden" name="ano" value="${carro.ano}" >
                            <input type="hidden" name="cor" value="${carro.cor}" >
                            <input type="hidden" name="preco" value="${carro.preco}" >
                            <input type="hidden" name="status" value="${carro.status}" >
                            <input type="hidden" name="percentual_comissao" value="${carro.percentual_comissao}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
