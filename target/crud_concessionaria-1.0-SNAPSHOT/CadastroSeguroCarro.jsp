<%@page import="java.util.List"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.CarroDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.SeguroCarroDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.SeguroCarro"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Carro"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Seguro de Carro</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    </head>

    <body>
        <h1>Cadastro Seguro de Carro</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/SeguroCarroControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="seguro_id" value="${seguro_id}" />
                <p><label>Carro:</label>
                    <select name="carroSeguro">
                     <c:forEach var="carro" items="${carros}">
                         <c:choose> 
                            <c:when test="${carro.carro_id eq carroSeguro}">
                                <option selected value="${carro.carro_id}">${carro.modelo}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${carro.carro_id}">${carro.modelo}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                <p><label>Número da Apólice:</label> <input type="text" name="numero_polis" value="${numero_polis}" size="40" required/> </p>
                <p><label>Data de Início:</label> <input type="date" name="data_inicio" value="${data_inicio}" size="40" required/> </p>
                <p><label>Data de Fim:</label> <input type="date" name="data_fim" value="${data_fim}" size="40" required/> </p>
                <p><label>Nome da Seguradora:</label> <input type="text" name="nome_seguradora" value="${nome_seguradora}"  /> </p>
                <p><label>Valor do Prêmio:</label> <input type="text" name="valor_premio" value="${valor_premio}"  /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/SeguroCarroControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty seguros}">
                <tr>
                    <th>Código</th>
                    <th>Carro</th>
                    <th>Número da Apólice</th>
                    <th>Data de Início</th>
                    <th>Data de Fim</th>
                    <th>Nome da Seguradora</th>
                    <th>Valor do Prêmio</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="seguro" items="${seguros}">
                <tr>
                    <td>${seguro.seguro_id}</td>
                    <td>${seguro.carroSeguro.modelo}</td>
                    <td>${seguro.numero_polis}</td>
                    <td>${seguro.data_inicio_formatada}</td>
                    <td>${seguro.data_fim_formatada}</td>
                    <td>${seguro.nome_seguradora}</td>
                    <td>${seguro.valor_premio}</td>

                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/SeguroCarroControlador" method="get">
                            <input type="hidden" name="seguro_id" value="${seguro.seguro_id}" >
                            <input type="hidden" name="carroSeguro" value="${seguro.carroSeguro.carro_id}" >
                            <input type="hidden" name="numero_polis" value="${seguro.numero_polis}" >
                            <input type="hidden" name="data_inicio" value="${seguro.data_inicio_formatada}" >
                            <input type="hidden" name="data_fim" value="${seguro.data_fim_formatada}" >
                            <input type="hidden" name="nome_seguradora" value="${seguro.nome_seguradora}" >
                            <input type="hidden" name="valor_premio" value="${seguro.valor_premio}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/SeguroCarroControlador" method="get">
                            <input type="hidden" name="seguro_id" value="${seguro.seguro_id}" >
                            <input type="hidden" name="carroSeguro" value="${seguro.carroSeguro.carro_id}" >
                            <input type="hidden" name="numero_polis" value="${seguro.numero_polis}" >
                            <input type="hidden" name="data_inicio" value="${seguro.data_inicio_formatada}" >
                            <input type="hidden" name="data_fim" value="${seguro.data_fim_formatada}" >
                            <input type="hidden" name="nome_seguradora" value="${seguro.nome_seguradora}" >
                            <input type="hidden" name="valor_premio" value="${seguro.valor_premio}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>

        </table>

    </body>
</html>