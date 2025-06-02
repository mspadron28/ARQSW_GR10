USE viajecitos_db;
GO

-- Inserción de vuelos
INSERT INTO Vuelo (ciudad_origen, ciudad_destino, valor, hora_salida) VALUES
('Quito', 'Guayaquil', 182.50, '2025-06-13 06:15:00'),
('Quito', 'Guayaquil', 179.90, '2025-06-13 08:45:00'),
('Quito', 'Guayaquil', 184.20, '2025-06-13 11:00:00'),
('Quito', 'Guayaquil', 181.00, '2025-06-13 13:30:00'),
('Quito', 'Guayaquil', 183.75, '2025-06-13 16:10:00'),
('Quito', 'Guayaquil', 180.60, '2025-06-13 18:40:00'),
('Guayaquil', 'Quito', 176.00, '2025-06-13 07:00:00'),
('Guayaquil', 'Quito', 173.25, '2025-06-13 09:30:00'),
('Guayaquil', 'Quito', 177.90, '2025-06-13 12:00:00'),
('Guayaquil', 'Quito', 174.80, '2025-06-13 14:30:00'),
('Guayaquil', 'Quito', 176.60, '2025-06-13 17:00:00'),
('Guayaquil', 'Quito', 172.95, '2025-06-13 19:20:00'),
('Lima', 'Buenos Aires', 320.00, '2025-06-15 06:00:00'),
('Lima', 'Buenos Aires', 325.50, '2025-06-15 08:30:00'),
('Lima', 'Buenos Aires', 318.75, '2025-06-15 11:00:00'),
('Lima', 'Buenos Aires', 322.40, '2025-06-15 13:45:00'),
('Lima', 'Buenos Aires', 319.90, '2025-06-15 16:20:00'),
('Lima', 'Buenos Aires', 327.10, '2025-06-15 19:10:00'),
('Buenos Aires', 'Lima', 310.80, '2025-06-15 07:15:00'),
('Buenos Aires', 'Lima', 315.00, '2025-06-15 09:45:00'),
('Buenos Aires', 'Lima', 312.20, '2025-06-15 12:30:00'),
('Buenos Aires', 'Lima', 316.40, '2025-06-15 15:00:00'),
('Buenos Aires', 'Lima', 311.75, '2025-06-15 17:30:00'),
('Buenos Aires', 'Lima', 318.90, '2025-06-15 20:15:00');
GO

-- Inserción de un cliente
INSERT INTO Cliente (nombre, email, documento_identidad) VALUES
('Ana Torres', 'ana.torres@email.com', '1710474758');
GO

-- Inserción de un usuario
INSERT INTO Usuario (nombre_usuario, clave_usuario, estado_usuario) VALUES
('MONSTER', 'MONSTER9', 'Activo');
GO

-- Inserción de un empleado
INSERT INTO Empleado (id_usuario, nombre, email, documento_identidad, estado) VALUES
(1, 'Viajecitos SA', 'viajecitossa@email.com', '1727858693', 'Activo');
GO

-- Inserción de métodos de pago
INSERT INTO Metodo_Pago (nombre_metodo, descripcion) VALUES
('Tarjeta Crédito', 'Pago con tarjeta de crédito'),
('Efectivo', 'Pago en efectivo');
GO

-- Inserción de facturas
INSERT INTO Factura (numero_factura, fecha_emision, id_empleado, id_cliente, id_metodo_pago, subtotal, descuento, iva, total) VALUES
('FAC001', '2025-06-02 12:30:00', 1, 1, 2, 1532.55, 10.00, 228.38, 1750.93);
GO

-- Inserción de detalles de factura
INSERT INTO Detalle_Factura (id_factura, id_vuelo, cantidad, valor_unitario, total) VALUES
(1, 5, 3, 183.75, 551.25),
(1, 18, 3, 327.10, 981.30);
GO