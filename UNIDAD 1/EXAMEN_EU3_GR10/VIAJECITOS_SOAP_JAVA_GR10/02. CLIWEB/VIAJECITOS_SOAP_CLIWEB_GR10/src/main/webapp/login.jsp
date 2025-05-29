<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Viajecitos SA - Iniciar Sesión</title>
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
            <h2>Iniciar Sesión</h2>
            <form action="LoginServlet" method="post" accept-charset="UTF-8">
                <div class="form-group">
                    <label for="nombreUsuario">Usuario:</label>
                    <input type="text" id="nombreUsuario" name="nombreUsuario" required>
                </div>
                <div class="form-group">
                    <label for="claveUsuario">Contraseña:</label>
                    <input type="password" id="claveUsuario" name="claveUsuario" required>
                </div>
                <button type="submit">Iniciar Sesión</button>
            </form>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
            <div class="nav-links">
                <a href="register.jsp">Registrarse</a>
                <a href="index.jsp">Volver</a>
            </div>
        </div>
    </div>
</body>
</html>