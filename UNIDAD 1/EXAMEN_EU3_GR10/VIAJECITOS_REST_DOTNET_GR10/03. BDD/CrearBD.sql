-- Creación de la base de datos en SQL Server
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'viajecitos_db')
BEGIN
    CREATE DATABASE viajecitos_db;
END
GO

USE viajecitos_db;
GO

-- Creación de la tabla Vuelo
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Vuelo]') AND type in (N'U'))
BEGIN
    CREATE TABLE Vuelo (
        id_vuelo INT IDENTITY(1,1) PRIMARY KEY,
        ciudad_origen VARCHAR(100) NOT NULL,
        ciudad_destino VARCHAR(100) NOT NULL,
        valor NUMERIC(7,2) NOT NULL,
        hora_salida DATETIME NOT NULL
    );
END
GO

-- Creación de la tabla Cliente
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Cliente]') AND type in (N'U'))
BEGIN
    CREATE TABLE Cliente (
        id_cliente INT IDENTITY(1,1) PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL,
        documento_identidad VARCHAR(10) NOT NULL,
        CONSTRAINT UK_Cliente_email UNIQUE (email),
        CONSTRAINT UK_Cliente_documento_identidad UNIQUE (documento_identidad)
    );
END
GO

-- Creación de la tabla Usuario
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Usuario]') AND type in (N'U'))
BEGIN
    CREATE TABLE Usuario (
        id_usuario INT IDENTITY(1,1) PRIMARY KEY,
        nombre_usuario VARCHAR(50) NOT NULL,
        clave_usuario VARCHAR(255) NOT NULL,
        estado_usuario VARCHAR(20) NOT NULL,
        CONSTRAINT UK_Usuario_nombre_usuario UNIQUE (nombre_usuario)
    );
END
GO

-- Creación de la tabla Empleado
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Empleado]') AND type in (N'U'))
BEGIN
    CREATE TABLE Empleado (
        id_empleado INT IDENTITY(1,1) PRIMARY KEY,
        id_usuario INT NOT NULL,
        nombre VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL,
        documento_identidad VARCHAR(10) NOT NULL,
        estado VARCHAR(20) NOT NULL,
        CONSTRAINT FK_Empleado_Usuario FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
        CONSTRAINT UK_Empleado_email UNIQUE (email),
        CONSTRAINT UK_Empleado_documento_identidad UNIQUE (documento_identidad)
    );
END
GO

-- Creación de la tabla Metodo_Pago
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Metodo_Pago]') AND type in (N'U'))
BEGIN
    CREATE TABLE Metodo_Pago (
        id_metodo_pago INT IDENTITY(1,1) PRIMARY KEY,
        nombre_metodo VARCHAR(50) NOT NULL,
        descripcion VARCHAR(100) NOT NULL
    );
END
GO

-- Creación de la tabla Factura
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Factura]') AND type in (N'U'))
BEGIN
    CREATE TABLE Factura (
        id_factura INT IDENTITY(1,1) PRIMARY KEY,
        numero_factura VARCHAR(20) NOT NULL,
        fecha_emision DATETIME NOT NULL,
        id_empleado INT NOT NULL,
        id_cliente INT NOT NULL,
        id_metodo_pago INT NOT NULL,
        subtotal NUMERIC(9,2) NOT NULL,
        descuento NUMERIC(9,2) NOT NULL DEFAULT 0.00,
        iva NUMERIC(9,2) NOT NULL,
        total NUMERIC(9,2) NOT NULL,
        CONSTRAINT FK_Factura_Empleado FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado),
        CONSTRAINT FK_Factura_Cliente FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
        CONSTRAINT FK_Factura_Metodo_Pago FOREIGN KEY (id_metodo_pago) REFERENCES Metodo_Pago(id_metodo_pago),
        CONSTRAINT UK_Factura_numero_factura UNIQUE (numero_factura)
    );
END
GO

-- Creación de la tabla Detalle_Factura
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Detalle_Factura]') AND type in (N'U'))
BEGIN
    CREATE TABLE Detalle_Factura (
        id_detalle_factura INT IDENTITY(1,1) PRIMARY KEY,
        id_factura INT NOT NULL,
        id_vuelo INT NOT NULL,
        cantidad INT NOT NULL,
        valor_unitario NUMERIC(7,2) NOT NULL,
        total NUMERIC(9,2) NOT NULL,
        CONSTRAINT FK_Detalle_Factura_Factura FOREIGN KEY (id_factura) REFERENCES Factura(id_factura),
        CONSTRAINT FK_Detalle_Factura_Vuelo FOREIGN KEY (id_vuelo) REFERENCES Vuelo(id_vuelo)
    );
END
GO

-- Índices para mejorar el rendimiento en búsquedas frecuentes
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'idx_vuelo_ciudades' AND object_id = OBJECT_ID(N'[dbo].[Vuelo]'))
BEGIN
    CREATE INDEX idx_vuelo_ciudades ON Vuelo (ciudad_origen, ciudad_destino);
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'idx_factura_numero' AND object_id = OBJECT_ID(N'[dbo].[Factura]'))
BEGIN
    CREATE INDEX idx_factura_numero ON Factura (numero_factura);
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'idx_factura_empleado' AND object_id = OBJECT_ID(N'[dbo].[Factura]'))
BEGIN
    CREATE INDEX idx_factura_empleado ON Factura (id_empleado);
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'idx_factura_cliente' AND object_id = OBJECT_ID(N'[dbo].[Factura]'))
BEGIN
    CREATE INDEX idx_factura_cliente ON Factura (id_cliente);
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'idx_factura_metodo_pago' AND object_id = OBJECT_ID(N'[dbo].[Factura]'))
BEGIN
    CREATE INDEX idx_factura_metodo_pago ON Factura (id_metodo_pago);
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'idx_detalle_factura_factura' AND object_id = OBJECT_ID(N'[dbo].[Detalle_Factura]'))
BEGIN
    CREATE INDEX idx_detalle_factura_factura ON Detalle_Factura (id_factura);
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'idx_detalle_factura_vuelo' AND object_id = OBJECT_ID(N'[dbo].[Detalle_Factura]'))
BEGIN
    CREATE INDEX idx_detalle_factura_vuelo ON Detalle_Factura (id_vuelo);
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'idx_usuario_nombre' AND object_id = OBJECT_ID(N'[dbo].[Usuario]'))
BEGIN
    CREATE INDEX idx_usuario_nombre ON Usuario (nombre_usuario);
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'idx_empleado_usuario' AND object_id = OBJECT_ID(N'[dbo].[Empleado]'))
BEGIN
    CREATE INDEX idx_empleado_usuario ON Empleado (id_usuario);
END
GO