<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Viajecitos SA - Resultado</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 600px; margin: auto; }
        .flight-info { border: 1px solid #ccc; padding: 15px; margin-top: 20px; }
        .error { color: red; }
        button { padding: 8px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Resultado de la Búsqueda</h2>
        <c:choose>
            <c:when test="${vuelo != null}">
                <div class="flight-info">
                    <p><strong>Vuelo ID:</strong> ${vuelo.idVuelo}</p>
                    <p><strong>Origen:</strong> ${vuelo.ciudadOrigen}</p>
                    <p><strong>Destino:</strong> ${vuelo.ciudadDestino}</p>
                    <p><strong>Valor:</strong> ${vuelo.valor}</p>
                    <p><strong>Hora de Salida:</strong> ${vuelo.horaSalida}</p>
                    <c:if test="${sessionScope.usuario != null}">
                        <form action="CompraServlet" method="post">
                            <input type="hidden" name="idVuelo" value="${vuelo.idVuelo}">
                            <input type="hidden" name="idCliente" value="${sessionScope.usuario.idCliente}">
                            <button type="submit">Comprar Boleto</button>
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.usuario == null}">
                        <p><a href="login.jsp">Inicie sesión para comprar</a></p>
                    </c:if>
                </div>
            </c:when>
            <c:otherwise>
                <p class="error">Vuelo no Disponible</p>
            </c:otherwise>
        </c:choose>
        <p><a href="index.jsp">Volver a Buscar</a></p>
    </div>
</body>
</html>