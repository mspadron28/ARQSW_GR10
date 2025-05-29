<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ec.edu.monster.modelo.Compra, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Viajecitos SA - Mis Compras</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 800px; margin: auto; }
        .table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        .table th, .table td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        .table th { background-color: #4CAF50; color: white; }
        .table tr:nth-child(even) { background-color: #f2f2f2; }
        .table tr:hover { background-color: #ddd; }
        .error { color: red; }
        .back-link { margin-top: 20px; display: inline-block; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Mis Compras</h2>
        <% 
            List<Compra> compras = (List<Compra>) request.getAttribute("compras");
            String error = (String) request.getAttribute("error");
            if (error != null) { 
        %>
            <p class="error"><%= error %></p>
        <% } %>
        <% if (compras != null && !compras.isEmpty()) { %>
            <table class="table">
                <tr>
                    <th>ID Compra</th>
                    <th>Vuelo</th>
                    <th>Valor</th>
                    <th>Fecha Compra</th>
                    <th>Hora Salida</th>
                </tr>
                <% for (Compra compra : compras) { %>
                    <tr>
                        <td><%= compra.getIdCompra() %></td>
                        <td><%= compra.getVuelo().getCiudadOrigen() %> a <%= compra.getVuelo().getCiudadDestino() %></td>
                        <td>$<%= String.format("%.2f", compra.getVuelo().getValor()) %></td>
                        <td><%= compra.getFechaCompra() %></td>
                        <td><%= compra.getVuelo().getHoraSalida() %></td>
                    </tr>
                <% } %>
            </table>
        <% } else { %>
            <p>No tienes compras registradas.</p>
        <% } %>
        <p class="back-link"><a href="index.jsp">Volver al Inicio</a></p>
    </div>
</body>
</html>