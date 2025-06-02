CREATE DATABASE IF NOT EXISTS viajecitos_db;
USE viajecitos_db;

-- Creación de la tabla Vuelo
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
    documento_identidad VARCHAR(10) NOT NULL,
    UNIQUE (email),
    UNIQUE (documento_identidad)
);

-- Creación de la tabla Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL,
    clave_usuario VARCHAR(255) NOT NULL,
    estado_usuario VARCHAR(20) NOT NULL,
    UNIQUE (nombre_usuario)
);

-- Creación de la tabla Empleado
CREATE TABLE IF NOT EXISTS Empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    documento_identidad VARCHAR(10) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    UNIQUE (email),
    UNIQUE (documento_identidad)
);

-- Creación de la tabla Metodo_Pago
CREATE TABLE IF NOT EXISTS Metodo_Pago (
    id_metodo_pago INT AUTO_INCREMENT PRIMARY KEY,
    nombre_metodo VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100) NOT NULL
);

-- Creación de la tabla Factura
CREATE TABLE IF NOT EXISTS Factura (
    id_factura INT AUTO_INCREMENT PRIMARY KEY,
    numero_factura VARCHAR(20) NOT NULL,
    fecha_emision DATETIME NOT NULL,
    id_empleado INT NOT NULL,
    id_cliente INT NOT NULL,
    id_metodo_pago INT NOT NULL,
    subtotal NUMERIC(9,2) NOT NULL,
    descuento NUMERIC(9,2) NOT NULL DEFAULT 0.00,
    iva NUMERIC(9,2) NOT NULL,
    total NUMERIC(9,2) NOT NULL,
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_metodo_pago) REFERENCES Metodo_Pago(id_metodo_pago),
    UNIQUE (numero_factura)
);
a 
-- Creación de la tabla Detalle_Factura
CREATE TABLE IF NOT EXISTS Detalle_Factura (
    id_detalle_factura INT AUTO_INCREMENT PRIMARY KEY,
    id_factura INT NOT NULL,
    id_vuelo INT NOT NULL,
    cantidad INT NOT NULL,
    valor_unitario NUMERIC(7,2) NOT NULL,
    total NUMERIC(9,2) NOT NULL,
    FOREIGN KEY (id_factura) REFERENCES Factura(id_factura),
    FOREIGN KEY (id_vuelo) REFERENCES Vuelo(id_vuelo)
);

-- Índices para mejorar el rendimiento en búsquedas frecuentes
CREATE INDEX IF NOT EXISTS idx_vuelo_ciudades ON Vuelo (ciudad_origen, ciudad_destino);
CREATE INDEX IF NOT EXISTS idx_factura_numero ON Factura (numero_factura);
CREATE INDEX IF NOT EXISTS idx_factura_empleado ON Factura (id_empleado);
CREATE INDEX IF NOT EXISTS idx_factura_cliente ON Factura (id_cliente);
CREATE INDEX IF NOT EXISTS idx_factura_metodo_pago ON Factura (id_metodo_pago);
CREATE INDEX IF NOT EXISTS idx_detalle_factura_factura ON Detalle_Factura (id_factura);
CREATE INDEX IF NOT EXISTS idx_detalle_factura_vuelo ON Detalle_Factura (id_vuelo);
CREATE INDEX IF NOT EXISTS idx_usuario_nombre ON Usuario (nombre_usuario);
CREATE INDEX IF NOT EXISTS idx_empleado_usuario ON Empleado (id_usuario);