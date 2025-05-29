CREATE DATABASE IF NOT EXISTS vuelos_db;
USE vuelos_db;

-- Creación de la tabla Vuelo (modificada para nombres completos de ciudades)
CREATE TABLE IF NOT EXISTS Vuelo (
    id_vuelo INT AUTO_INCREMENT PRIMARY KEY,
    ciudad_origen VARCHAR(100) NOT NULL,
    ciudad_destino VARCHAR(100) NOT NULL,
    valor NUMERIC(7,2) NOT NULL,
    hora_salida DATETIME NOT NULL
);

-- Creación de la tabla Cliente
CREATE TABLE IF NOT EXISTS Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    documento_identidad VARCHAR(20) NOT NULL,
    UNIQUE (email),
    UNIQUE (documento_identidad)
);

-- Creación de la tabla Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    nombre_usuario VARCHAR(50) NOT NULL,
    clave_usuario VARCHAR(255) NOT NULL,
    estado_usuario VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    UNIQUE (id_cliente),
    UNIQUE (nombre_usuario)
);

-- Creación de la tabla Compra
CREATE TABLE IF NOT EXISTS Compra (
    id_compra INT AUTO_INCREMENT PRIMARY KEY,
    id_vuelo INT NOT NULL,
    id_cliente INT NOT NULL,
    fecha_compra DATETIME NOT NULL,
    FOREIGN KEY (id_vuelo) REFERENCES Vuelo(id_vuelo),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

-- Índices para mejorar el rendimiento en búsquedas frecuentes
CREATE INDEX IF NOT EXISTS idx_vuelo_ciudades ON Vuelo (ciudad_origen, ciudad_destino);
CREATE INDEX IF NOT EXISTS idx_compra_vuelo ON Compra (id_vuelo);
CREATE INDEX IF NOT EXISTS idx_compra_cliente ON Compra (id_cliente);
CREATE INDEX IF NOT EXISTS idx_usuario_nombre ON Usuario (nombre_usuario);