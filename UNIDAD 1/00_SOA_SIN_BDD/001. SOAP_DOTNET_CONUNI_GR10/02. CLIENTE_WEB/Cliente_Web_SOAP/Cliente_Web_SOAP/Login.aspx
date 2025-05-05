<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="Cliente_Web_SOAP.Login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Iniciar Sesión</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            background-color: #0d47a1; /* Azul oscuro de fondo */
            padding: 20px;
        }
        .container {
            max-width: 350px; /* Reducir el ancho del contenedor */
            margin: auto;
            margin-top: 50px;
            text-align: center;
            background-color: #ffffff; /* Fondo blanco para el formulario */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1); /* Sombra ligera */
        }
        .login-image {
            max-width: 100%;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            font-weight: bold; /* Negrita para las etiquetas */
            color: #0d47a1; /* Color azul oscuro para las etiquetas */
        }
    </style>
</head>
<body>
    <form id="formLogin" runat="server">
        <div class="container">
            <img src="imagenes/LOGIN_CLIWEB.png" alt="Login Image" class="login-image" />
            <h2 class="text-center">Iniciar Sesión</h2>
            <div class="form-group">
                <asp:Label ID="lblUsername" runat="server" AssociatedControlID="txtUsername" Text="Usuario:" CssClass="control-label"></asp:Label>
                <asp:TextBox ID="txtUsername" runat="server" CssClass="form-control"></asp:TextBox>
            </div>
            <div class="form-group">
                <asp:Label ID="lblPassword" runat="server" AssociatedControlID="txtPassword" Text="Contraseña:" CssClass="control-label"></asp:Label>
                <asp:TextBox ID="txtPassword" runat="server" TextMode="Password" CssClass="form-control"></asp:TextBox>
            </div>
            <div class="form-group">
                <asp:Button ID="btnLogin" runat="server" Text="Iniciar Sesión" CssClass="btn btn-primary btn-block" OnClick="btnLogin_Click" />
            </div>
            <asp:Label ID="lblMessage" runat="server" Text="" ForeColor="Red"></asp:Label>
        </div>

        <!-- Modal de validación -->
        <div class="modal fade" id="validationModal" tabindex="-1" role="dialog" aria-labelledby="validationModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="validationModalLabel">Error de Validación</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

        <script type="text/javascript">
            function showValidationModal() {
                $('#validationModal').modal('show');
            }
        </script>
    </form>
</body>
</html>
