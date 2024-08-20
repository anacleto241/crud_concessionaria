<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8">
        <title>Página Principal</title>
    </head>
    
    <body>
       
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/CarroControlador?opcao=cancelar">Carro</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador?opcao=cancelar">Funcionário</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar">Cliente</a></li>
                 <li><a href="${pageContext.request.contextPath}${URL_BASE}/PreparacaoControlador?opcao=cancelar">Preparação</a></li>
                 <li><a href="${pageContext.request.contextPath}${URL_BASE}/AgendamentoControlador?opcao=cancelar">Agendamento</a></li>
                 <li><a href="${pageContext.request.contextPath}${URL_BASE}/TestDriveControlador?opcao=cancelar">Test Drive</a></li>
                 <li><a href="${pageContext.request.contextPath}${URL_BASE}/InspecaoCarroControlador?opcao=cancelar">Inspeção</a></li>
                 <li><a href="${pageContext.request.contextPath}${URL_BASE}/SeguroCarroControlador?opcao=cancelar">Seguros</a></li>
                 <li><a href="${pageContext.request.contextPath}${URL_BASE}/GarantiaControlador?opcao=cancelar">Garantia</a></li>
                  <li><a href="${pageContext.request.contextPath}${URL_BASE}/VendaControlador?opcao=cancelar">Venda</a></li>
                <li><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/LoginControlador">Logout</a></li>
                
            </ul>
        </nav>
    </body>
</html>


