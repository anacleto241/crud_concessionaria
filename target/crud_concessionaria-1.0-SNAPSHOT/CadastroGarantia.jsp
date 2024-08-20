<%@page import="java.util.List"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.CarroDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.GarantiaDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Garantia"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Carro"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Garantia</title>
    </head>

    <body>
        <h1>Cadastro Garantia</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/GarantiaControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="garantia_id" value="${garantia_id}" />
                <p><label>Carro:</label>
                    <select name="carroGarantia">
                     <c:forEach var="carro" items="${carros}">
                         <c:choose> 
                            <c:when test="${carro.carro_id eq carroGarantia}">
                                <option selected value="${carro.carro_id}">${carro.modelo}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${carro.carro_id}">${carro.modelo}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                <p><label>Data de Início:</label> <input type="date" name="data_inicio" value="${data_inicio}" size="40" required/> </p>
                <p><label>Data de Fim:</label> <input type="date" name="data_fim" value="${data_fim}" size="40" required/> </p>
                <p><label>Descrição:</label> <input type="text" name="descricao" value="${descricao}"  /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/GarantiaControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty garantias}">
                <tr>
                    <th>Código</th>
                    <th>Carro</th>
                    <th>Data de Início</th>
                    <th>Data de Fim</th>
                    <th>Descrição</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="garantia" items="${garantias}">
                <tr>
                    <td>${garantia.garantia_id}</td>
                    <td>${garantia.carroGarantia.modelo}</td>
                    <td>${garantia.data_inicio_formatada}</td>
                    <td>${garantia.data_fim_formatada}</td>
                    <td>${garantia.descricao}</td>
                    
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/GarantiaControlador" method="get">
                            <input type="hidden" name="garantia_id" value="${garantia.garantia_id}" >
                            <input type="hidden" name="carroGarantia" value="${garantia.carroGarantia.carro_id}" >
                            <input type="hidden" name="data_inicio" value="${garantia.data_inicio_formatada}" >
                            <input type="hidden" name="data_fim" value="${garantia.data_fim_formatada}" >
                            <input type="hidden" name="descricao" value="${garantia.descricao}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/GarantiaControlador" method="get">
                            <input type="hidden" name="garantia_id" value="${garantia.garantia_id}" >
                            <input type="hidden" name="carroGarantia" value="${garantia.carroGarantia.carro_id}" >
                            <input type="hidden" name="data_inicio" value="${garantia.data_inicio_formatada}" >
                            <input type="hidden" name="data_fim" value="${garantia.data_fim_formatada}" >
                            <input type="hidden" name="descricao" value="${garantia.descricao}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>

        </table>

    </body>
</html>
