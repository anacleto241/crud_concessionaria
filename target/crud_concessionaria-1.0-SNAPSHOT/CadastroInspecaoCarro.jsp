<%@page import="java.util.List"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.CarroDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.InspecaoCarroDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.InspecaoCarro"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Carro"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Inspeção de Carro</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    </head>

    <body>
        <h1>Cadastro Inspeção de Carro</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/InspecaoCarroControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="inspecao_id" value="${inspecao_id}" />
                <p><label>Carro:</label>
                    <select name="carroInspecao">
                     <c:forEach var="carro" items="${carros}">
                         <c:choose> 
                            <c:when test="${carro.carro_id eq carroInspecao}">
                                <option selected value="${carro.carro_id}">${carro.modelo}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${carro.carro_id}">${carro.modelo}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                <p><label>Data da Inspeção:</label> <input type="date" name="data_inspecao" value="${data_inspecao}" size="40" required/> </p>
                <p><label>Condição Geral:</label> <input type="text" name="condicao_geral" value="${condicao_geral}"  /> </p>
                <p><label>Observações:</label> <input type="text" name="observacoes" value="${observacoes}"  /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/InspecaoCarroControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty inspecoes}">
                <tr>
                    <th>Código</th>
                    <th>Carro</th>
                    <th>Data da Inspeção</th>
                    <th>Condição Geral</th>
                    <th>Observações</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="inspecao" items="${inspecoes}">
                <tr>
                    <td>${inspecao.inspecao_id}</td>
                    <td>${inspecao.carroInspecao.modelo}</td>
                    <td>${inspecao.data_inspecao_formatada}</td>
                    <td>${inspecao.condicao_geral}</td>
                    <td>${inspecao.observacoes}</td>
                    
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/InspecaoCarroControlador" method="get">
                            <input type="hidden" name="inspecao_id" value="${inspecao.inspecao_id}" >
                            <input type="hidden" name="carroInspecao" value="${inspecao.carroInspecao.carro_id}" >
                            <input type="hidden" name="data_inspecao" value="${inspecao.data_inspecao_formatada}" >
                            <input type="hidden" name="condicao_geral" value="${inspecao.condicao_geral}" >
                            <input type="hidden" name="observacoes" value="${inspecao.observacoes}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/InspecaoCarroControlador" method="get">
                            <input type="hidden" name="inspecao_id" value="${inspecao.inspecao_id}" >
                            <input type="hidden" name="carroInspecao" value="${inspecao.carroInspecao.carro_id}" >
                            <input type="hidden" name="data_inspecao" value="${inspecao.data_inspecao_formatada}" >
                            <input type="hidden" name="condicao_geral" value="${inspecao.condicao_geral}" >
                            <input type="hidden" name="observacoes" value="${inspecao.observacoes}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>

        </table>

    </body>
</html>
