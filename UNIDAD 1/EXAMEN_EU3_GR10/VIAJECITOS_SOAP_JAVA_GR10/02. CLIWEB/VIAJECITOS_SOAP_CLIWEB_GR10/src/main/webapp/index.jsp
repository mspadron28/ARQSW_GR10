<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Viajecitos SA - Buscar Vuelos</title>
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
        <div class="form-container">
            <div class="form-header">
                <h2>Buscar Vuelos</h2>
                <% if (session.getAttribute("usuario") != null) { %>
                    <a href="ComprasClienteServlet" class="view-purchases-btn">Ver Mis Compras</a>
                <% } %>
            </div>
            <form action="VueloServlet" method="post">
                <div class="form-group form-group-inline">
                    <div class="select-group">
                        <label for="ciudadOrigen">Ciudad Origen:</label>
                        <select id="ciudadOrigen" name="ciudadOrigen" required>
                            <option value="" disabled selected>Selecciona una ciudad</option>
                            <option value="Bogotá" data-code="BOG">BOG - Bogotá</option>
                            <option value="Medellín" data-code="MDE">MDE - Medellín</option>
                            <option value="Buenos Aires" data-code="BUE">BUE - Buenos Aires</option>
                            <option value="Córdoba" data-code="COR">COR - Córdoba</option>
                            <option value="Quito" data-code="UIO">UIO - Quito</option>
                            <option value="Guayaquil" data-code="GYE">GYE - Guayaquil</option>
                            <option value="Cali" data-code="CLO">CLO - Cali</option>
                            <option value="Cartagena" data-code="CTG">CTG - Cartagena</option>
                            <option value="Mendoza" data-code="MDZ">MDZ - Mendoza</option>
                        </select>
                    </div>
                    <span class="dash">-</span>
                    <div class="select-group">
                        <label for="ciudadDestino">Ciudad Destino:</label>
                        <select id="ciudadDestino" name="ciudadDestino" required>
                            <option value="" disabled selected>Selecciona una ciudad</option>
                            <option value="Bogotá" data-code="BOG">BOG - Bogotá</option>
                            <option value="Medellín" data-code="MDE">MDE - Medellín</option>
                            <option value="Buenos Aires" data-code="BUE">BUE - Buenos Aires</option>
                            <option value="Córdoba" data-code="COR">COR - Córdoba</option>
                            <option value="Quito" data-code="UIO">UIO - Quito</option>
                            <option value="Guayaquil" data-code="GYE">GYE - Guayaquil</option>
                            <option value="Cali" data-code="CLO">CLO - Cali</option>
                            <option value="Cartagena" data-code="CTG">CTG - Cartagena</option>
                            <option value="Mendoza" data-code="MDZ">MDZ - Mendoza</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="fecha">Fecha de Viaje:</label>
                    <input type="date" id="fecha" name="fecha" required>
                </div>
                <button type="submit">Buscar</button>
            </form>
        </div>
        <div class="nav-links">
            <a href="login.jsp">Iniciar Sesión</a> | <a href="register.jsp">Registrarse</a>
        </div>
    </div>
</body>
</html>