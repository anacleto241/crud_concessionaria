<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <title>Concessionária Ultra Veículos - Página Inicial</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <header>
        <h1>Concessionária Ultra Veículos</h1>
        <p>Bem-vindo à melhor concessionária da cidade. Encontre seu carro dos sonhos com a qualidade e confiança que você merece!</p>
    </header>
    
    <div class="container">
        <section class="highlight">
            <h2>Ofertas Especiais</h2>
            <p>Confira nossas ofertas especiais e encontre o carro perfeito para você com condições imperdíveis!</p>
            <button onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">Ver Ofertas</button>
        </section>

        <section class="about">
            <h2>Sobre Nós</h2>
            <p>Na Ultra Veículos, oferecemos uma vasta gama de carros novos e usados, todos com a qualidade garantida. Nossa equipe está pronta para ajudá-lo a encontrar o veículo ideal que atenda às suas necessidades e orçamento.</p>
            <button onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">Saiba Mais</button>
        </section>

        <section class="highlight">
            <h2>Carros em Destaque</h2>
            <p>Conheça alguns dos nossos veículos mais populares. Qualidade e desempenho garantidos!</p>
            <button onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">Ver Carros</button>
        </section>
    </div>

    <footer>
        <p>&copy; 2024 Concessionária Ultra Veículos. Todos os direitos reservados.</p>
        <p>Endereço: Rua Jatobá, 123 - Machado, Minas Gerais</p>
        <p>Telefone: (35) 9234-5678 | Email: contato@ultraveiculos.com.br</p>
    </footer>
</body>
</html>
