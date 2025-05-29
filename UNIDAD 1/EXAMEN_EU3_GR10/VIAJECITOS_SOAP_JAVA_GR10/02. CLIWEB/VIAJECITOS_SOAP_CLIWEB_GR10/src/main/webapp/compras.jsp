<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ec.edu.monster.ws.Compra, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Viajecitos SA - Mis Compras</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <header class="header">
        <img src="images/travel-agency.jpg" alt="Viajecitos SA">
        <div class="header-text">
            <h1>Viajecitos SA</h1>
            <p>Encuentra y compra tus boletos de avión de forma fácil y segura</p>
        </div>
    </header>
    <div class="container">
        <div class="table-container">
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
            <div class="nav-links">
                <a href="index.jsp">Volver al Inicio</a>
            </div>
        </div>
    </div>
</body>
</html>