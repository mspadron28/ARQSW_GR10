<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Depósito</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
    <div class="header">Registrar Depósito</div>
    <div class="container">
        <form action="DepositoServlet" method="post">
            <div class="form-group">
                <label for="codEmp">Código de Empleado:</label>
                <input type="text" id="codEmp" name="codEmp" required>
            </div>
            <div class="form-group">
                <label for="cuenta">Cuenta:</label>
                <input type="text" id="cuenta" name="cuenta" required>
            </div>
            <div class="form-group">
                <label for="importe">Importe:</label>
                <input type="text" id="importe" name="importe" required>
            </div>
            <button type="submit">Depositar</button>
            <button type="button" onclick="window.location.href='main.jsp'">Volver al Menú</button>
        </form>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        <% if (request.getAttribute("mensaje") != null) { %>
            <p class="success"><%= request.getAttribute("mensaje") %></p>
        <% } %>
    </div>
</body>
</html>