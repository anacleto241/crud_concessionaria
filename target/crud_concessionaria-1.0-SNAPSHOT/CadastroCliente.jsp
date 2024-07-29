<%-- 
    Document   : CadastroCliente
    Created on : 28 de jul. de 2024, 23:52:24
    Author     : fabri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <script>

        function submitForm(opcaoValue) {

            document.getElementById("opcao").value = opcaoValue;
            document.getElementById("cadastroForm").submit();
        }


    </script>


    <body>
        <h1>Cadastro Cliente</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="cliente_id" value="${cliente_id}" />
                <p><label>Nome</label> <input type="text" name="nome" value="${nome}" size="30" /> </p>
                <p><label>Endereco</label> <input type="text" name="endereco" value="${endereco}" size="30" /> </p>
                <p><label>Telefone</label> <input type="text" name="telefone" value="${telefone}" size="8" /> </p>
                <p><label>Email</label> <input type="text" name="email" value="${email}" size="15" /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty cliente}">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Endereco</th>
                    <th>Telefone</th>
                    <th>Email</th>

                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="cliente" items="${cliente}">
                <tr>
                    <td>${cliente.cliente_id}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.endereco}</td>
                    <td>${cliente.telefone}</td>
                    <td>${cliente.email}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                            <input type="hidden" name="cliente_id" value="${cliente.cliente_id}" >
                            <input type="hidden" name="nome" value="${cliente.nome}" >
                            <input type="hidden" name="endereco" value="${cliente.endereco}" >
                            <input type="hidden" name="telefone" value="${cliente.telefone}" >
                            <input type="hidden" name="email" value="${cliente.email}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                           <input type="hidden" name="cliente_id" value="${cliente.cliente_id}" >
                            <input type="hidden" name="nome" value="${cliente.nome}" >
                            <input type="hidden" name="endereco" value="${cliente.endereco}" >
                            <input type="hidden" name="telefone" value="${cliente.telefone}" >
                            <input type="hidden" name="email" value="${cliente.email}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>

