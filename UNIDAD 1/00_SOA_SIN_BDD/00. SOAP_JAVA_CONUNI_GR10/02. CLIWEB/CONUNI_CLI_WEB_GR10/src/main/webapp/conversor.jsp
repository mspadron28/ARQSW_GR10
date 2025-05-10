<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Conversor de Unidades - CONUNI</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f7f6;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .header {
            background-color: #46535d;
            color: white;
            padding: 20px 20px;
            font-family: Arial, sans-serif;
            font-weight: bold;
            font-size: 18px;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 5px;
            flex-grow: 1;
            margin-top: 50px;
            padding: 20px;
        }
        .left-column, .right-column {
            display: flex;
            justify-content: center;
            align-items: center;
            flex: 1;
        }
        .left-column-content {
            text-align: center;
        }
        .left-column-content h2 {
            font-family: Arial, sans-serif;
            font-weight: bold;
            font-size: 24px;
            margin-bottom: 20px;
        }
        .left-column-content img {
            width: 400px;
            height: 400px;
        }
        .right-column-content {
            background-color: #b1c5c7;
            padding: 20px;
            border: 1px solid black;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 300px;
            text-align: center;
        }
        .right-column-content label {
            font-family: Arial, sans-serif;
            font-size: 14px;
            display: block;
            text-align: left;
            margin-bottom: 5px;
        }
        .right-column-content select, .right-column-content input {
            width: 200px;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid black;
            border-radius: 4px;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
            font-size: 14px;
        }
        .right-column-content button {
            width: 200px;
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
        .right-column-content button:hover {
            background-color: #333;
        }
        .error {
            margin: 10px 0;
            font-family: Arial, sans-serif;
            font-size: 14px;
            color: red;
        }
        .result-label {
            font-family: Arial, sans-serif;
            font-size: 14px;
            display: block;
            text-align: left;
            margin-top: 10px;
            margin-bottom: 5px;
        }
        .result-container {
            width: 175px;
            padding: 10px;
            border: 1px solid black;
            border-radius: 4px;
            background-color: white;
            margin: 0 auto;
            font-family: Arial, sans-serif;
            font-size: 14px;
            text-align: center;
            min-height: 20px; /* Asegura que el contenedor tenga altura incluso si está vacío */
        }
        .resultado {
            color: green;
        }
    </style>
</head>
<body>
    <div class="header">Conversor de Unidades JAVA SOAP</div>
    <div class="container">
        <div class="left-column">
            <div class="left-column-content">
                <h2>¿Qué unidad requieres convertir?</h2>
                <img src="images/sulleyconuni.png" alt="Sulley">
            </div>
        </div>
        <div class="right-column">
            <div class="right-column-content">
                <% if (request.getAttribute("error") != null) { %>
                    <p class="error"><%= request.getAttribute("error") %></p>
                <% } %>
                <form action="convertir" method="post">
                    <label for="tipoConversion">Selecciona el tipo de cambio</label>
                    <select id="tipoConversion" name="accion">
                        <option value="pulgadasACentimetros" <%= "pulgadasACentimetros".equals(request.getAttribute("selectedAccion")) ? "selected" : "" %>>Pulgadas a Centímetros</option>
                        <option value="centimetrosAPulgadas" <%= "centimetrosAPulgadas".equals(request.getAttribute("selectedAccion")) ? "selected" : "" %>>Centímetros a Pulgadas</option>
                        <option value="metrosAPies" <%= "metrosAPies".equals(request.getAttribute("selectedAccion")) ? "selected" : "" %>>Metros a Pies</option>
                        <option value="piesAMetros" <%= "piesAMetros".equals(request.getAttribute("selectedAccion")) ? "selected" : "" %>>Pies a Metros</option>
                        <option value="metrosAYardas" <%= "metrosAYardas".equals(request.getAttribute("selectedAccion")) ? "selected" : "" %>>Metros a Yardas</option>
                        <option value="yardasAMetros" <%= "yardasAMetros".equals(request.getAttribute("selectedAccion")) ? "selected" : "" %>>Yardas a Metros</option>
                    </select>
                    <label for="valor">Ingresa el valor</label>
                    <input type="number" step="any" id="valor" name="valor" placeholder="Ingrese valor" required>
                    <button type="submit">Convertir</button>
                    <label class="result-label">Resultado</label>
                    <div class="result-container">
                        <% if (request.getAttribute("resultado") != null) { %>
                            <span class="resultado"><%= request.getAttribute("resultado") %></span>
                        <% } %>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>