<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Conversor de Unidades - CONUNI</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #d9f2ff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .converter-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 400px;
            text-align: center;
        }
        .converter-container h2 {
            margin-bottom: 20px;
        }
        .converter-container input {
            width: 60%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .converter-container button {
            padding: 10px 20px;
            background-color: #007bff;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
            margin: 10px;
        }
        .converter-container button:hover {
            background-color: #0056b3;
        }
        .error, .resultado {
            margin: 10px 0;
        }
        .error {
            color: red;
        }
        .resultado {
            color: green;
        }
    </style>
</head>
<body>
    <div class="converter-container">
        <h2>Conversor de Unidades</h2>
        <% if (request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        <% if (request.getAttribute("resultado") != null) { %>
            <p class="resultado"><%= request.getAttribute("resultado") %></p>
        <% } %>
        <form action="convertir" method="post">
            <h3>Pulgadas a Centímetros</h3>
            <input type="number" step="any" name="pulgadas" placeholder="Ingrese pulgadas" required>
            <button type="submit" name="accion" value="pulgadasACentimetros">Convertir</button>
        </form>
        <form action="convertir" method="post">
            <h3>Centímetros a Pulgadas</h3>
            <input type="number" step="any" name="centimetros" placeholder="Ingrese centímetros" required>
            <button type="submit" name="accion" value="centimetrosAPulgadas">Convertir</button>
        </form>
    </div>
</body>
</html>