<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <title>Concession�ria Ultra Ve�culos - P�gina Inicial</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <header>
        <h1>Concession�ria Ultra Ve�culos</h1>
        <p>Bem-vindo � melhor concession�ria da cidade. Encontre seu carro dos sonhos com a qualidade e confian�a que voc� merece!</p>
    </header>
    
    <div class="container">
        <section class="highlight">
            <h2>Ofertas Especiais</h2>
            <p>Confira nossas ofertas especiais e encontre o carro perfeito para voc� com condi��es imperd�veis!</p>
            <button onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">Ver Ofertas</button>
        </section>

        <section class="about">
            <h2>Sobre N�s</h2>
            <p>Na Ultra Ve�culos, oferecemos uma vasta gama de carros novos e usados, todos com a qualidade garantida. Nossa equipe est� pronta para ajud�-lo a encontrar o ve�culo ideal que atenda �s suas necessidades e or�amento.</p>
            <button onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">Saiba Mais</button>
        </section>

        <section class="highlight">
            <h2>Carros em Destaque</h2>
            <p>Conhe�a alguns dos nossos ve�culos mais populares. Qualidade e desempenho garantidos!</p>
            <button onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">Ver Carros</button>
        </section>
    </div>

    <footer>
        <p>&copy; 2024 Concession�ria Ultra Ve�culos. Todos os direitos reservados.</p>
        <p>Endere�o: Rua Jatob�, 123 - Machado, Minas Gerais</p>
        <p>Telefone: (35) 9234-5678 | Email: contato@ultraveiculos.com.br</p>
    </footer>
</body>
</html>
