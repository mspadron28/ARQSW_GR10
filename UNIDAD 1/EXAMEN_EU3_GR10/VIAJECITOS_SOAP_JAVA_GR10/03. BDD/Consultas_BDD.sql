USE vuelos_db;

-- Consulta 1: Listar todos los vuelos
SELECT id_vuelo, ciudad_origen, ciudad_destino, valor, hora_salida
FROM Vuelo;

-- Consulta 2: Listar todos los clientes
SELECT id_cliente, nombre, email, documento_identidad
FROM Cliente;

-- Consulta 3: Listar todos los usuarios
SELECT id_usuario, id_cliente, nombre_usuario, estado_usuario
FROM Usuario;

-- Consulta 4: Listar todas las compras con detalles de vuelo y cliente
SELECT c.id_compra, v.ciudad_origen, v.ciudad_destino, v.valor, v.hora_salida, 
       cl.nombre AS cliente_nombre, c.fecha_compra
FROM Compra c
JOIN Vuelo v ON c.id_vuelo = v.id_vuelo
JOIN Cliente cl ON c.id_cliente = cl.id_cliente;

-- Consulta 5: Buscar vuelos por ciudad de origen y destino
SELECT id_vuelo, ciudad_origen, ciudad_destino, valor, hora_salida
FROM Vuelo
WHERE ciudad_origen = 'Bogotá' AND ciudad_destino = 'Medellín';

-- Consulta 6: Obtener el vuelo de mayor valor para una combinación de ciudades
SELECT id_vuelo, ciudad_origen, ciudad_destino, valor, hora_salida
FROM Vuelo
WHERE ciudad_origen = 'Buenos Aires' AND ciudad_destino = 'Quito'
ORDER BY valor DESC
LIMIT 1;

-- Consulta 7: Verificar el estado del usuario para un cliente específico
SELECT c.nombre, u.nombre_usuario, u.estado_usuario
FROM Cliente c
JOIN Usuario u ON c.id_cliente = u.id_cliente
WHERE c.email = 'juan.perez@email.com';

-- Consulta 8: Contar las compras por cliente
SELECT cl.nombre, COUNT(c.id_compra) AS total_compras
FROM Cliente cl
LEFT JOIN Compra c ON cl.id_cliente = c.id_cliente
GROUP BY cl.id_cliente, cl.nombre;

-- Consulta 9: Listar vuelos disponibles en una fecha específica
SELECT id_vuelo, ciudad_origen, ciudad_destino, valor, hora_salida
FROM Vuelo
WHERE DATE(hora_salida) = '2025-06-01';

-- Consulta 10: Obtener detalles de compras de un cliente específico
SELECT c.id_compra, v.ciudad_origen, v.ciudad_destino, v.valor, c.fecha_compra
FROM Compra c
JOIN Vuelo v ON c.id_vuelo = v.id_vuelo
WHERE c.id_cliente = (SELECT id_cliente FROM Cliente WHERE email = 'maria.gomez@email.com');