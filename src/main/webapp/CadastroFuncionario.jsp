<%-- 
    Document   : CadastroFuncionario
    Created on : 28 de jul. de 2024, 23:53:04
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">

    </head>

    <script>

        function submitForm(opcaoValue) {

            document.getElementById("opcao").value = opcaoValue;
            document.getElementById("cadastroForm").submit();
        }


    </script>


    <body>
        <h1>Cadastro Funcionario</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="funcionario_id" value="${funcionario_id}" />
                <p><label>Nome</label> <input type="text" name="nome" value="${nome}" size="30" /> </p>
                <p><label>Cargo</label> <input type="text" name="cargo" value="${cargo}" size="30" /> </p>
                <p><label>Telefone</label> <input type="text" name="telefone" value="${telefone}" size="8" /> </p>
                <p><label>Email</label> <input type="text" name="email" value="${email}" size="15" /> </p>
                <p><label>Salário base</label> <input type="text" name="salario_base" value="${salario_base}" size="8" /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty funcionario}">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Cargo</th>
                    <th>Telefone</th>
                    <th>Email</th>
                    <th>Salário Base</th>

                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="funcionario" items="${funcionario}">
                
                <tr>
                    <td>${funcionario.funcionario_id}</td>
                    <td>${funcionario.nome}</td>
                    <td>${funcionario.cargo}</td>
                    <td>${funcionario.telefone}</td>
                    <td>${funcionario.email}</td>
                    <td>${funcionario.salario_base}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                            <input type="hidden" name="funcionario_id" value="${funcionario.funcionario_id}" >
                            <input type="hidden" name="nome" value="${funcionario.nome}" >
                            <input type="hidden" name="cargo" value="${funcionario.cargo}" >
                            <input type="hidden" name="telefone" value="${funcionario.telefone}" >
                            <input type="hidden" name="email" value="${funcionario.email}" >
                            <input type="hidden" name="salario_base" value="${funcionario.salario_base}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                           <input type="hidden" name="funcionario_id" value="${funcionario.funcionario_id}" >
                            <input type="hidden" name="nome" value="${funcionario.nome}" >
                            <input type="hidden" name="cargo" value="${funcionario.cargo}" >
                            <input type="hidden" name="telefone" value="${funcionario.telefone}" >
                            <input type="hidden" name="email" value="${funcionario.email}" >
                            <input type="hidden" name="salario_base" value="${funcionario.salario_base}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>


