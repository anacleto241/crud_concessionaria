<%-- 
    Document   : CadastroPreparacao
    Author     : fabricio
--%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.CarroDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.PreparacaoDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Preparacao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Carro"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>


    <body>
        <h1>Cadastro Preparação</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PreparacaoControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="preparacao_id" value="${preparacao_id}" />
                 <p><label>Carro:</label>
                    <select name="carroPreparacao">
                     <c:forEach var="carro" items="${carros}">
                         <c:choose> 
                            
                            <c:when test="${carro.carro_id eq carroPreparacao}">
                                <option selected value="${carro.carro_id}">${carro.modelo}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${carro.carro_id}">${carro.modelo}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                <p><label>Data Inicial:</label> <input type="date" name="data_inicio" value="${data_inicio}" size="40" required/> </p>
                <p><label>Data Final:</label> <input type="date" name="data_fim" value="${data_fim}" size="10" required/> </p>
                <p><label>Descrição:</label> <input type="text" name="descricao" value="${descricao}"  /> </p>
                <p><label>Custo:</label> <input type="text" name="custo" value="${custo}"  /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PreparacaoControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty preparacaos}">
                <tr>
                    <th>Código</th>
                    <th>Carro</th>
                    <th>Data Inicial</th>
                    <th>Data Final</th>
                    <th>Descrição</th>
                    <th>Custo</th>
                    
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="preparacao" items="${preparacaos}">
                <tr>
                    <td>${preparacao.preparacao_id}</td>
                    <td>${preparacao.carroPreparacao.modelo}</td>
                    <td>${preparacao.data_inicio_formatada}</td>
                    <td>${preparacao.data_fim_formatada}</td>
                    <td>${preparacao.descricao}</td>
                    <td>${preparacao.custo}</td>
                    
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PreparacaoControlador" method="get">
                            <input type="hidden" name="preparacao_id" value="${preparacao.preparacao_id}" >
                            <input type="hidden" name="carroPreparacao" value="${preparacao.carroPreparacao.carro_id}" >
                            <input type="hidden" name="data_inicio" value="${preparacao.data_inicio_formatada}" >
                            <input type="hidden" name="data_fim" value="${preparacao.data_fim_formatada}">
                            <input type="hidden" name="descricao" value="${preparacao.descricao}" >
                            <input type="hidden" name="custo" value="${preparacao.custo}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PreparacaoControlador" method="get">
                            <input type="hidden" name="preparacao_id" value="${preparacao.preparacao_id}" >
                            <input type="hidden" name="carroPreparacao" value="${preparacao.carroPreparacao.carro_id}" >
                            <input type="hidden" name="data_inicio" value="${preparacao.data_inicio_formatada}" >
                            <input type="hidden" name="data_fim" value="${preparacao.data_fim_formatada}">
                            <input type="hidden" name="descricao" value="${preparacao.descricao}" >
                            <input type="hidden" name="custo" value="${preparacao.custo}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
