<%@page import="java.util.List"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.CarroDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.ClienteDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.FuncionarioDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.dao.VendaDao"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Carro"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Cliente"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Funcionario"%>
<%@page import="com.mycompany.crud_concessionaria.modelo.entidade.Venda"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Vendas</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    </head>

    <body>
        <h1>Cadastro de Vendas</h1>
        <table>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="venda_id" value="${venda_id}" />
                
                <p><label>Carro:</label>
                    <select name="carroVenda">
                        <c:forEach var="carro" items="${carros}">
                            <c:choose> 
                                <c:when test="${carro.carro_id eq carroVenda}">
                                    <option selected value="${carro.carro_id}">${carro.modelo}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${carro.carro_id}">${carro.modelo}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                
                <p><label>Cliente:</label>
                    <select name="clienteVenda">
                        <c:forEach var="cliente" items="${clientes}">
                            <c:choose> 
                                <c:when test="${cliente.cliente_id eq clienteVenda}">
                                    <option selected value="${cliente.cliente_id}">${cliente.nome}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${cliente.cliente_id}">${cliente.nome}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                
                <p><label>Funcionário:</label>
                    <select name="funcionarioVenda">
                        <c:forEach var="funcionario" items="${funcionarios}">
                            <c:choose> 
                                <c:when test="${funcionario.funcionario_id eq funcionarioVenda}">
                                    <option selected value="${funcionario.funcionario_id}">${funcionario.nome}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${funcionario.funcionario_id}">${funcionario.nome}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                
                <p><label>Data da Venda:</label> <input type="date" name="data_venda" value="${data_venda}" required /> </p>
                <p><label>Valor da Venda:</label> <input type="text" name="valor_venda" value="${valor_venda}" required /> </p>
                <p><label>Comissão do Vendedor:</label> <input type="text" name="comissao_vendedor" value="${comissao_vendedor}" required /> </p>
                <p><label>Desconto no Carro:</label> <input type="text" name="desconto_carro" value="${desconto_carro}" required /> </p>
                
                <td>
                    <input type="submit" value="Salvar" name="Salvar" />
                </td>
            </form>

            <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador" method="get">
                <td>
                    <input type="submit" value="Cancelar" name="Cancelar" />
                </td>
                <input type="hidden" name="opcao" value="cancelar" />
            </form>
        </table>
        
        ${mensagem}

        <table border="1">
            <c:if test="${not empty vendas}">
                <tr>
                    <th>Código</th>
                    <th>Carro</th>
                    <th>Cliente</th>
                    <th>Funcionário</th>
                    <th>Data da Venda</th>
                    <th>Valor da Venda</th>
                    <th>Comissão do Vendedor</th>
                    <th>Desconto no Carro</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr> 
            </c:if>

            <c:forEach var="venda" items="${vendas}">
                <tr>
                    <td>${venda.venda_id}</td>
                    <td>${venda.carroVenda.modelo}</td>
                    <td>${venda.clienteVenda.nome}</td>
                    <td>${venda.funcionarioVenda.nome}</td>
                    <td>${venda.data_venda_formatada}</td>
                    <td>${venda.valor_venda}</td>
                    <td>${venda.comissao_vendedor}</td>
                    <td>${venda.desconto_carro}</td>
                    
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador" method="get">
                            <input type="hidden" name="venda_id" value="${venda.venda_id}" />
                            <input type="hidden" name="carroVenda" value="${venda.carroVenda.carro_id}" />
                            <input type="hidden" name="clienteVenda" value="${venda.clienteVenda.cliente_id}" />
                            <input type="hidden" name="funcionarioVenda" value="${venda.funcionarioVenda.funcionario_id}" />
                            <input type="hidden" name="data_venda" value="${venda.data_venda_formatada}" />
                            <input type="hidden" name="valor_venda" value="${venda.valor_venda}" />
                            <input type="hidden" name="comissao_vendedor" value="${venda.comissao_vendedor}" />
                            <input type="hidden" name="desconto_carro" value="${venda.desconto_carro}" />
                            <input type="hidden" name="opcao" value="editar" />
                            <button type="submit">Editar</button>
                        </form>    
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador" method="get">
                            <input type="hidden" name="venda_id" value="${venda.venda_id}" />
                            <input type="hidden" name="carroVenda" value="${venda.carroVenda.carro_id}" />
                            <input type="hidden" name="clienteVenda" value="${venda.clienteVenda.cliente_id}" />
                            <input type="hidden" name="funcionarioVenda" value="${venda.funcionarioVenda.funcionario_id}" />
                            <input type="hidden" name="data_venda" value="${venda.data_venda_formatada}" />
                            <input type="hidden" name="valor_venda" value="${venda.valor_venda}" />
                            <input type="hidden" name="comissao_vendedor" value="${venda.comissao_vendedor}" />
                            <input type="hidden" name="desconto_carro" value="${venda.desconto_carro}" />
                            <input type="hidden" name="opcao" value="excluir" />
                            <button type="submit">Excluir</button>
                        </form>    
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
