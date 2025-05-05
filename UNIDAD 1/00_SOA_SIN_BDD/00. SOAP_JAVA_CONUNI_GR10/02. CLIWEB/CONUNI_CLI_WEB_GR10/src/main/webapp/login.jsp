<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Iniciar Sesión - CONUNI</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f7f6;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .header {
            background-color: #46535d;
            color: white;
            padding: 10px 10px;
            font-family: Arial, sans-serif;
            font-weight: bold;
            font-size: 18px;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
        }
        .title-header{
         padding-left: 15px;  
        }
        .login-container {
            background-color: #b1c5c7;
            padding: 20px;
            border: 1px solid black;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 300px;
            text-align: center;
            margin-top: 60px; /* Espacio para el encabezado fijo */
        }
        .login-container h2 {
            margin-bottom: 20px;
            font-family: Arial, sans-serif;
            font-weight: bold;
            font-size: 24px;
        }
        .login-container img {
            width: 150px;
            height: 150px;
            margin-bottom: 20px;
        }
        .login-container label {
            font-family: Arial, sans-serif;
            font-size: 14px;
            display: block;
            text-align: left;
            margin-bottom: 5px;
        }
        .login-container input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #b1c5c7;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: #202224;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: bold;
        }
        .login-container button:hover {
            background-color: #333;
        }
        .error {
            color: red;
            margin-bottom: 10px;
            font-family: Arial, sans-serif;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="header"><p class="title-header">Inicio de Sesión</p></div>
    <div class="login-container">
        <h2>Bienvenido</h2>
        <img src="images/sulley.png" alt="Sulley Avatar">
        <p style="font-family: Arial, sans-serif; font-size: 14px; color: #666666;">Por favor, ingresa tus credenciales</p>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        <form action="login" method="post">
            <label for="usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" placeholder="Usuario" required>
            <label for="contrasena">Contraseña</label>
            <input type="password" id="contrasena" name="contrasena" placeholder="Contraseña" required>
            <button type="submit">Iniciar Sesión</button>
        </form>
    </div>
</body>
</html>