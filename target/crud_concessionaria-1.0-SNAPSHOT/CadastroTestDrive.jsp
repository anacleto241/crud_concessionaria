<%-- 
    Document   : CadastroTestDrive
    Author     : fabri
--%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.CarroDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.ClienteDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.TestDriveDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.TestDrive"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Carro"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Cliente"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Test Drive</title>
    </head>

    <body>
        <h1>Cadastro Test Drive</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/TestDriveControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="test_drive_id" value="${test_drive_id}" />
                
                <p><label>Cliente:</label>
                    <select name="clienteTestDrive">
                        <c:forEach var="cliente" items="${clientes}">
                            <c:choose> 
                                <c:when test="${cliente.cliente_id eq clienteTestDrive}">
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
                    <select name="carroTestDrive">
                        <c:forEach var="carro" items="${carros}">
                            <c:choose> 
                                <c:when test="${carro.carro_id eq carroTestDrive}">
                                    <option selected value="${carro.carro_id}">${carro.modelo}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${carro.carro_id}">${carro.modelo}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                
                <p><label>Data do Test Drive:</label> <input type="date" name="data_test_drive" value="${data_test_drive}" size="40" required/> </p>
                <p><label>Resultado:</label> <input type="text" name="resultado" value="${resultado}" /> </p>
                
                <td> 
                    <input type="submit" value="Salvar" name="Salvar" /> 
                </td>
            </form>

            <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/TestDriveControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar" />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        ${mensagem}

        <table border="1">
            <c:if test="${not empty testDrives}">
                <tr>
                    <th>Código</th>
                    <th>Cliente</th>
                    <th>Carro</th>
                    <th>Data do Test Drive</th>
                    <th>Resultado</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="testDrive" items="${testDrives}">
                <tr>
                    <td>${testDrive.test_drive_id}</td>
                    <td>${testDrive.cliente.nome}</td>
                    <td>${testDrive.carro.modelo}</td>
                    <td>${testDrive.data_test_drive_formatada}</td>
                    <td>${testDrive.resultado}</td>
                    
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/TestDriveControlador" method="get">
                            <input type="hidden" name="test_drive_id" value="${testDrive.test_drive_id}" >
                            <input type="hidden" name="clienteTestDrive" value="${testDrive.cliente.cliente_id}" >
                            <input type="hidden" name="carroTestDrive" value="${testDrive.carro.carro_id}" >
                            <input type="hidden" name="data_test_drive" value="${testDrive.data_test_drive_formatada}" >
                            <input type="hidden" name="resultado" value="${testDrive.resultado}" >
                            <input type="hidden" name="opcao" value="editar" >
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/TestDriveControlador" method="get">
                            <input type="hidden" name="test_drive_id" value="${testDrive.test_drive_id}" >
                            <input type="hidden" name="clienteTestDrive" value="${testDrive.cliente.cliente_id}" >
                            <input type="hidden" name="carroTestDrive" value="${testDrive.carro.carro_id}" >
                            <input type="hidden" name="data_test_drive" value="${testDrive.data_test_drive_formatada}" >
                            <input type="hidden" name="resultado" value="${testDrive.resultado}" >
                            <input type="hidden" name="opcao" value="excluir" >
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
