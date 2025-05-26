<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Retiro</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
    <div class="header">Registrar Retiro</div>
    <div class="container">
        <form action="RetiroServlet" method="post">
            <div class="form-group">
                <label for="cuenta">Cuenta:</label>
                <input type="text" id="cuenta" name="cuenta" required>
            </div>
            <div class="form-group">
                <label for="importe">Importe:</label>
                <input type="text" id="importe" name="importe" required>
            </div>
            <div class="form-group">
                <label for="codEmp">Código de Empleado:</label>
                <select id="codEmp" name="codEmp" required>
                    <% for (int i = 1; i <= 14; i++) { %>
                        <option value="<%= String.format("%04d", i) %>"><%= String.format("%04d", i) %></option>
                    <% } %>
                </select>
            </div>
            <button type="submit">Retirar</button>
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