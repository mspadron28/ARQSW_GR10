<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Realizar Transferencia</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
    <div class="header">Realizar Transferencia</div>
    <div class="container">
        <form action="TransferenciaServlet" method="post">
            <div class="form-group">
                <label for="cuentaOrigen">Cuenta Origen:</label>
                <input type="text" id="cuentaOrigen" name="cuentaOrigen" required>
            </div>
            <div class="form-group">
                <label for="cuentaDestino">Cuenta Destino:</label>
                <input type="text" id="cuentaDestino" name="cuentaDestino" required>
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
            <button type="submit">Transferir</button>
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