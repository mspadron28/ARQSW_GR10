<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Viajecitos SA - Resultado</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <header class="header">
        <img src="images/travel-agency.jpg" alt="Viajecitos SA">
        <div class="header-text">
            <h1>Viajecitos SA</h1>
            <p>Encuentra y compra tus boletos de avión de forma fácil y segura</p>
        </div>
    </header>
    <div class="container">
        <div class="result-container">
            <h2>Resultado de la Búsqueda</h2>
            <c:choose>
                <c:when test="${vuelo != null}">
                    <div class="flight-info">
                        <p><strong>Vuelo ID:</strong> ${vuelo.idVuelo}</p>
                        <p><strong>Origen:</strong> ${vuelo.ciudadOrigen}</p>
                        <p><strong>Destino:</strong> ${vuelo.ciudadDestino}</p>
                        <p><strong>Valor:</strong> $${String.format("%.2f", vuelo.valor)}</p>
                        <p><strong>Hora de Salida:</strong> ${vuelo.horaSalida}</p>
                        <c:if test="${sessionScope.usuario != null}">
                            <form action="CompraServlet" method="post">
                                <input type="hidden" name="idVuelo" value="${vuelo.idVuelo}">
                                <input type="hidden" name="idCliente" value="${sessionScope.usuario.idCliente}">
                                <button type="submit">Comprar Boleto</button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.usuario == null}">
                            <p class="error"><a href="login.jsp">Inicie sesión para comprar</a></p>
                        </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="error">Vuelo no Disponible</p>
                </c:otherwise>
            </c:choose>
            <div class="nav-links">
                <a href="index.jsp">Volver a Buscar</a>
            </div>
        </div>
    </div>
</body>
</html>