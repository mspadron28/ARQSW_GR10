IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'vuelos_db')
BEGIN
    CREATE DATABASE vuelos_db;
END
USE vuelos_db;

-- Usar la base de datos
USE vuelos_db;

-- Crear la tabla Vuelo
CREATE TABLE Vuelo (
    id_vuelo INT IDENTITY(1,1) PRIMARY KEY,
    ciudad_origen VARCHAR(100) NOT NULL,
    ciudad_destino VARCHAR(100) NOT NULL,
    valor DECIMAL(7,2) NOT NULL,
    hora_salida DATETIME NOT NULL
);

-- Crear la tabla Cliente
CREATE TABLE Cliente (
    id_cliente INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    documento_identidad VARCHAR(20) NOT NULL,
    UNIQUE (email),
    UNIQUE (documento_identidad)
);

-- Crear la tabla Usuario
CREATE TABLE Usuario (
    id_usuario INT IDENTITY(1,1) PRIMARY KEY,
    id_cliente INT NOT NULL,
    nombre_usuario VARCHAR(50) NOT NULL,
    clave_usuario VARCHAR(255) NOT NULL,
    estado_usuario VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    UNIQUE (id_cliente),
    UNIQUE (nombre_usuario)
);

-- Crear la tabla Compra
CREATE TABLE Compra (
    id_compra INT IDENTITY(1,1) PRIMARY KEY,
    id_vuelo INT NOT NULL,
    id_cliente INT NOT NULL,
    fecha_compra DATETIME NOT NULL,
    FOREIGN KEY (id_vuelo) REFERENCES Vuelo(id_vuelo),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

-- Crear índices para mejorar el rendimiento
CREATE INDEX idx_vuelo_ciudades ON Vuelo (ciudad_origen, ciudad_destino);
CREATE INDEX idx_compra_vuelo ON Compra (id_vuelo);
CREATE INDEX idx_compra_cliente ON Compra (id_cliente);
CREATE INDEX idx_usuario_nombre ON Usuario (nombre_usuario);