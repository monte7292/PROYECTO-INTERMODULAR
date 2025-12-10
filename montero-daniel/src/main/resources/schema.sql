-- 🧍‍♂️ Tabla de usuarios
CREATE TABLE usuarios (
    idusuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    apellidos VARCHAR(50),
    edad INT,
    telefono VARCHAR(20),
    dni VARCHAR(15) UNIQUE,
    correo_electronico VARCHAR(100) UNIQUE,
    contrasena VARCHAR(100),
    tipo_agarre_raton ENUM('Fingertip', 'Claw', 'Palm'),
    formato_teclado_favorito ENUM('100%', '75%', '68%', '60%')
);

-- 🛒 Tabla de productos base
CREATE TABLE productos (
    idproducto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    marca VARCHAR(50),
    fecha_publicacion DATE,
    precio DECIMAL(10,2),
    stock INT,
    tipo_producto ENUM('Raton', 'Teclado', 'Skate', 'Alfombrilla', 'Auriculares'),
    image VARCHAR(255)
);

-- 🖱️ Tabla específica de ratones
CREATE TABLE raton (
    idproducto INT PRIMARY KEY,
    tipo_agarre ENUM('Fingertip', 'Claw', 'Palm'),
    medidas VARCHAR(50),
    sensor VARCHAR(50),
    peso DECIMAL(5,2),
    switches VARCHAR(50),
    image VARCHAR(255),
    FOREIGN KEY (idproducto) REFERENCES productos(idproducto) ON DELETE CASCADE
);

-- 🛹 Tabla específica de skates
CREATE TABLE skate (
    idproducto INT PRIMARY KEY,
    velocidad ENUM('Lenta', 'Media', 'Rapida'),
    image VARCHAR(255),
    FOREIGN KEY (idproducto) REFERENCES productos(idproducto) ON DELETE CASCADE
);

-- ⌨️ Tabla específica de teclados
CREATE TABLE teclado (
    idproducto INT PRIMARY KEY,
    formato ENUM('100%', '75%', '68%', '60%'),
    medidas VARCHAR(50),
    switches VARCHAR(50),
    image VARCHAR(255),
    FOREIGN KEY (idproducto) REFERENCES productos(idproducto) ON DELETE CASCADE
);

-- 🖼️ Tabla específica de alfombrillas
CREATE TABLE alfombrilla (
    idproducto INT PRIMARY KEY,
    velocidad ENUM('Control', 'Fast', 'Hybrid'),
    medidas VARCHAR(50),
    image VARCHAR(255),
    FOREIGN KEY (idproducto) REFERENCES productos(idproducto) ON DELETE CASCADE
);

-- 🎧 Tabla específica de auriculares
CREATE TABLE auriculares (
    idproducto INT PRIMARY KEY,
    tipo_auricular ENUM('IEM', 'Headphones'),
    image VARCHAR(255),
    FOREIGN KEY (idproducto) REFERENCES productos(idproducto) ON DELETE CASCADE
);

-- 🧾 Tabla de compras
CREATE TABLE compras (
    idcompra INT PRIMARY KEY AUTO_INCREMENT,
    idusuario INT,
    idproducto INT,
    fecha_compra DATE,
    precio_final DECIMAL(10,2),
    estado_compra ENUM('Pendiente', 'Pagado', 'Cancelado'),
    FOREIGN KEY (idusuario) REFERENCES usuarios(idusuario),
    FOREIGN KEY (idproducto) REFERENCES productos(idproducto)
);

-- 🌟 Tabla de valoraciones
CREATE TABLE valoraciones (
    idvaloracion INT PRIMARY KEY AUTO_INCREMENT,
    idusuario INT,
    idproducto INT,
    puntuacion INT CHECK (puntuacion BETWEEN 1 AND 5),
    comentario TEXT,
    FOREIGN KEY (idusuario) REFERENCES usuarios(idusuario),
    FOREIGN KEY (idproducto) REFERENCES productos(idproducto)
);

-- 🎫 Tabla de tickets
CREATE TABLE tickets (
    idticket INT PRIMARY KEY AUTO_INCREMENT,
    idusuario INT,
    numero_ticket VARCHAR(20) UNIQUE,        -- Número de ticket único
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2),
    impuestos DECIMAL(10,2),
    metodo_pago ENUM('Tarjeta', 'PayPal', 'Transferencia'),
    FOREIGN KEY (idusuario) REFERENCES usuarios(idusuario)
);

-- 📃 Tabla de líneas de ticket
CREATE TABLE lineas_ticket (
    idlinea INT PRIMARY KEY AUTO_INCREMENT,
    idticket INT,
    idproducto INT,
    cantidad INT,
    precio_unitario DECIMAL(10,2),
    FOREIGN KEY (idticket) REFERENCES tickets(idticket) ON DELETE CASCADE,
    FOREIGN KEY (idproducto) REFERENCES productos(idproducto)
);


