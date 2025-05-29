<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Viajecitos SA - Registrarse</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 400px; margin: auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input, button { padding: 8px; width: 100%; }
        button { background-color: #4CAF50; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #45a049; }
        .error { color: red; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Registrarse</h2>
        <form action="RegisterServlet" method="post">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="documentoIdentidad">Documento de Identidad:</label>
                <input type="text" id="documentoIdentidad" name="documentoIdentidad" required>
            </div>
            <div class="form-group">
                <label for="nombreUsuario">Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" required>
            </div>
            <div class="form-group">
                <label for="claveUsuario">Contraseña:</label>
                <input type="password" id="claveUsuario" name="claveUsuario" required>
            </div>
            <button type="submit">Registrarse</button>
        </form>
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <p><a href="login.jsp">Iniciar Sesión</a> | <a href="index.jsp">Volver</a></p>
    </div>
</body>
</html>