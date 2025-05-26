<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Verificar Saldo</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
    <div class="header">Verificar Saldo</div>
    <div class="container">
        <form action="SaldoServlet" method="post">
            <div class="form-group">
                <label for="cuenta">Cuenta:</label>
                <input type="text" id="cuenta" name="cuenta" required>
            </div>
            <button type="submit">Verificar</button>
            <button type="button" onclick="window.location.href='main.jsp'">Volver al Menú</button>
        </form>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        <% if (request.getAttribute("saldo") != null) { %>
            <p class="success">Saldo: <%= request.getAttribute("saldo") %></p>
        <% } %>
    </div>
</body>
</html>