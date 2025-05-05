<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="Cliente_Web_SOAP._Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Conversión de Unidades</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            background-color: #0d47a1; /* Cambio a azul oscuro de fondo */
            padding: 20px;
            position: relative; /* Para posicionamiento relativo */
        }
        .container {
            max-width: 600px;
            margin: auto;
            background-color: #ffffff; /* Fondo blanco para el contenedor principal */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1); /* Sombra ligera */
            position: relative;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .btn-primary {
            transition: all 0.3s ease; /* Animación básica en botones */
        }
        .btn-primary:hover {
            transform: scale(1.1); /* Efecto de escala al pasar el mouse por encima */
        }
        .result-box {
            background-color: #f0f9ff; /* Fondo azul claro para el resultado */
            border: 1px solid #0d47a1; /* Borde azul oscuro */
            padding: 15px;
            margin-top: 20px;
            border-radius: 8px;
            text-align: center;
        }
        .result {
            font-size: 24px; /* Tamaño de fuente grande para el resultado */
            color: #0d47a1; /* Color azul oscuro para el texto del resultado */
            font-weight: bold;
        }
        .result-animation {
            animation: fadeIn 0.5s ease; /* Animación de fadeIn para mostrar el resultado */
        }
        .footer-img {
            display: block;
            margin: 20px auto 0; /* Centrar la imagen y añadir margen superior */
            width: 300px; /* Tamaño ajustable */
        }
        .btn-exit {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #dc3545; /* Color de fondo rojo */
            color: #fff; /* Color de texto blanco */
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .btn-exit:hover {
            background-color: #c82333; /* Cambio de color al pasar el mouse */
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="container">
            <h2 class="text-center text-primary mb-4">Conversión de Unidades</h2>
            <div class="form-group">
                <asp:Label ID="lblInput" runat="server" AssociatedControlID="txtInput" Text="Ingrese el valor: "></asp:Label>
                <asp:TextBox ID="txtInput" runat="server" CssClass="form-control"></asp:TextBox>
            </div>
            <div class="form-group">
                <asp:Label ID="lblConversionType" runat="server" AssociatedControlID="ddlConversionType" Text="Seleccione el tipo de conversión: "></asp:Label>
                <asp:DropDownList ID="ddlConversionType" runat="server" CssClass="form-control">
                    <asp:ListItem Value="CentimetersToFeet">Centímetros a Pies</asp:ListItem>
                    <asp:ListItem Value="FeetToCentimeters">Pies a Centímetros</asp:ListItem>
                    <asp:ListItem Value="MetersToYards">Metros a Yardas</asp:ListItem>
                    <asp:ListItem Value="YardsToMeters">Yardas a Metros</asp:ListItem>
                    <asp:ListItem Value="InchesToCentimeters">Pulgadas a Centímetros</asp:ListItem>
                    <asp:ListItem Value="CentimetersToInches">Centímetros a Pulgadas</asp:ListItem>
                </asp:DropDownList>
            </div>
            <div class="form-group text-center">
                <asp:Button ID="btnConvert" runat="server" Text="Convertir" CssClass="btn btn-primary btn-lg" OnClick="btnConvert_Click" />
            </div>
            <div class="result-box">
                <asp:Label ID="lblResult" runat="server" CssClass="result result-animation"></asp:Label>
            </div>
            <!-- Botón de Salir -->
            <button type="button" class="btn-exit" onclick="location.href='Login.aspx'">Salir</button>
        </div>

        <!-- Imagen de Footer -->
        <img src="imagenes/footer.png" class="footer-img" />

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </form>
</body>
</html>
