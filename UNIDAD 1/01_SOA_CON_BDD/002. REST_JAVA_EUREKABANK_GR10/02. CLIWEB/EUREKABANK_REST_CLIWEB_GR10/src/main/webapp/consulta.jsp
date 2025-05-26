<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, ec.edu.monster.modelo.Movimiento"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Movimientos</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
    <div class="header">Consulta de Movimientos</div>
    <div class="container">
        <form action="ConsultaServlet" method="post">
            <div class="form-group">
                <label for="cuenta">Cuenta:</label>
                <input type="text" id="cuenta" name="cuenta" required>
            </div>
            <button type="submit">Consultar</button>
            <button type="button" onclick="window.location.href='main.jsp'">Volver al Menú</button>
        </form>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        <% if (request.getAttribute("mensaje") != null) { %>
            <p class="success"><%= request.getAttribute("mensaje") %></p>
        <% } %>
        <% List<Movimiento> movimientos = (List<Movimiento>) request.getAttribute("movimientos");
           if (movimientos != null && !movimientos.isEmpty()) { %>
           <div class="table-responsive">
            <table>
                <tr>
                    <th>CUENTA</th>
                    <th>NROMOV</th>
                    <th>FECHA</th>
                    <th>TIPO</th>
                    <th>ACCIÓN</th>
                    <th>IMPORTE</th>
                </tr>
                <% for (Movimiento mov : movimientos) { %>
                    <tr>
                        <td><%= mov.getCuenta() %></td>
                        <td><%= mov.getNroMov() %></td>
                        <td><%= mov.getFecha() %></td>
                        <td><%= mov.getTipo() %></td>
                        <td><%= mov.getAccion() %></td>
                        <td><%= String.format("%.2f", mov.getImporte()) %></td>
                    </tr>
                <% } %>
            </table>
            </div>
        <% } %>
    </div>
</body>
</html>