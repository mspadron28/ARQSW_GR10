<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Viajecitos SA - Buscar Vuelos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 600px; margin: auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input, button { padding: 8px; width: 100%; }
        button { background-color: #4CAF50; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Buscar Vuelos</h2>
        <form action="VueloServlet" method="post">
            <div class="form-group">
                <label for="ciudadOrigen">Ciudad Origen:</label>
                <input type="text" id="ciudadOrigen" name="ciudadOrigen" required>
            </div>
            <div class="form-group">
                <label for="ciudadDestino">Ciudad Destino:</label>
                <input type="text" id="ciudadDestino" name="ciudadDestino" required>
            </div>
            <div class="form-group">
                <label for="fecha">Fecha de Viaje:</label>
                <input type="date" id="fecha" name="fecha" required>
            </div>
            <button type="submit">Buscar</button>
        </form>
        <p><a href="login.jsp">Iniciar Sesión</a> | <a href="register.jsp">Registrarse</a></p>
    </div>
</body>
</html>