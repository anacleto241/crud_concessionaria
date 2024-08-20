<%-- 
    Document   : CadastroAgendamento
    Author     : fabricio
--%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.ClienteDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.CarroDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.AgendamentoDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Agendamento"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Cliente"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Carro"%>

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

    <body>
        <h1>Cadastro Agendamento</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/AgendamentoControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="agendamento_id" value="${agendamento_id}" />
                 <p><label>Cliente:</label>
                    <select name="clienteAgendamento">
                     <c:forEach var="cliente" items="${clientes}">
                         <c:choose> 
                            
                            <c:when test="${cliente.cliente_id eq clienteAgendamento}">
                                <option selected value="${cliente.cliente_id}">${cliente.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${cliente.cliente_id}">${cliente.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                <p><label>Carro:</label>
                    <select name="carroAgendamento">
                     <c:forEach var="carro" items="${carros}">
                         <c:choose> 
                            
                            <c:when test="${carro.carro_id eq carroAgendamento}">
                                <option selected value="${carro.carro_id}">${carro.modelo}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${carro.carro_id}">${carro.modelo}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                <p><label>Data do Agendamento:</label> <input type="date" name="data_agendamento" value="${data_agendamento}" size="40" required/> </p>
                <p><label>Descrição:</label> <input type="text" name="descricao" value="${descricao}"  /> </p>
                <p><label>Status:</label> <input type="text" name="status" value="${status}"  /> </p>
                <td> 
                    <input type="submit" value="Salvar" name="Salvar"  /> 
                </td>
            </form>

            <form  name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/AgendamentoControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar"  />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty agendamentos}">
                <tr>
                    <th>Código</th>
                    <th>Cliente</th>
                    <th>Carro</th>
                    <th>Data do Agendamento</th>
                    <th>Descrição</th>
                    <th>Status</th>
                    
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="agendamento" items="${agendamentos}">
                <tr>
                    <td>${agendamento.agendamento_id}</td>
                    <td>${agendamento.clienteAgendamento.nome}</td>
                    <td>${agendamento.carroAgendamento.modelo}</td>
                    <td>${agendamento.dataAgendamentoFormatada}</td>
                    <td>${agendamento.descricao}</td>
                    <td>${agendamento.status}</td>
                    
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/AgendamentoControlador" method="get">
                            <input type="hidden" name="agendamento_id" value="${agendamento.agendamento_id}" >
                            <input type="hidden" name="clienteAgendamento" value="${agendamento.clienteAgendamento.cliente_id}" >
                            <input type="hidden" name="carroAgendamento" value="${agendamento.carroAgendamento.carro_id}" >
                            <input type="hidden" name="data_agendamento" value="${agendamento.dataAgendamentoFormatada}" >
                            <input type="hidden" name="descricao" value="${agendamento.descricao}" >
                            <input type="hidden" name="status" value="${agendamento.status}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/AgendamentoControlador" method="get">
                            <input type="hidden" name="agendamento_id" value="${agendamento.agendamento_id}" >
                            <input type="hidden" name="clienteAgendamento" value="${agendamento.clienteAgendamento.cliente_id}" >
                            <input type="hidden" name="carroAgendamento" value="${agendamento.carroAgendamento.carro_id}" >
                            <input type="hidden" name="data_agendamento" value="${agendamento.dataAgendamentoFormatada}" >
                            <input type="hidden" name="descricao" value="${agendamento.descricao}" >
                            <input type="hidden" name="status" value="${agendamento.status}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>

        </table>

    </body>
</html>
