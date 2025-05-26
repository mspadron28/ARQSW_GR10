<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>EurekaBank - Inicio de Sesión</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
    <div class="header">EurekaBank - Inicio de Sesión</div>
    <div class="login-container">
        <div class="avatar">
            <img src="images/sulley.png" alt="Avatar">
        </div>
        <h2 style="text-align: center;">Bienvenido a EurekaBank</h2>
        <p style="text-align: center; color: #666666;">Ingresa tus credenciales</p>
        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" required>
            </div>
            <div class="form-group">
                <label for="clave">Contraseña:</label>
                <input type="password" id="clave" name="clave" required>
            </div>
            <button type="submit">Iniciar Sesión</button>
        </form>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
    </div>
</body>
</html>