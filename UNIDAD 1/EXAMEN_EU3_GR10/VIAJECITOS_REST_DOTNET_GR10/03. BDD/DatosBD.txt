USE vuelos_db;

-- Inserción de registros en Vuelo
INSERT INTO Vuelo (ciudad_origen, ciudad_destino, valor, hora_salida) VALUES
('Bogotá', 'Medellín', 150.50, '2025-06-01 08:00:00'),
('Medellín', 'Bogotá', 145.75, '2025-06-01 10:30:00'),
('Buenos Aires', 'Córdoba', 200.00, '2025-06-02 14:00:00'),
('Córdoba', 'Buenos Aires', 190.25, '2025-06-02 16:45:00'),
('Quito', 'Guayaquil', 180.00, '2025-06-03 09:15:00'),
('Guayaquil', 'Quito', 175.50, '2025-06-03 11:30:00'),
('Cali', 'Cartagena', 160.00, '2025-06-04 12:00:00'),
('Cartagena', 'Cali', 155.75, '2025-06-04 15:20:00'),
('Buenos Aires', 'Quito', 210.30, '2025-06-05 07:45:00'),
('Bogotá', 'Mendoza', 205.00, '2025-06-05 10:00:00');

-- Inserción de registros en Cliente
INSERT INTO Cliente (nombre, email, documento_identidad) VALUES
('Juan Pérez', 'juan.perez@email.com', '123456789'),
('María Gómez', 'maria.gomez@email.com', '987654321'),
('Carlos López', 'carlos.lopez@email.com', '456789123');

-- Inserción de registros en Usuario
INSERT INTO Usuario (id_cliente, nombre_usuario, clave_usuario, estado_usuario) VALUES
(1, 'juanperez', 'MONSTER9', 'Activo'),
(2, 'mariagomez', 'MONSTER9', 'Activo'),
(3, 'carloslopez', 'MONSTER9', 'Inactivo');

-- Inserción de registros en Compra
INSERT INTO Compra (id_vuelo, id_cliente, fecha_compra) VALUES
(1, 1, '2025-05-29 08:00:00'),
(3, 1, '2025-05-29 09:15:00'),
(5, 2, '2025-05-29 10:30:00'),
(7, 2, '2025-05-29 11:45:00'),
(9, 3, '2025-05-29 12:00:00');