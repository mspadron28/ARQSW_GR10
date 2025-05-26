<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>EurekaBank Cliente</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
    <div class="header">EurekaBank Cliente</div>
    <div class="container">
        <h2 style="text-align: center;">Menú Principal</h2>
        <form>
            <button type="button" onclick="window.location.href='consulta.jsp'">Consulta de Movimientos</button><br><br>
            <button type="button" onclick="window.location.href='deposito.jsp'">Registrar Depósito</button><br><br>
            <button type="button" onclick="window.location.href='retiro.jsp'">Registrar Retiro</button><br><br>
            <button type="button" onclick="window.location.href='transferencia.jsp'">Realizar Transferencia</button><br><br>
            <button type="button" onclick="window.location.href='saldo.jsp'">Verificar Saldo</button>
        </form>
    </div>
</body>
</html>