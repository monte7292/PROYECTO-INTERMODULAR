-- Inserciones de usuarios
INSERT INTO usuarios (nombre, apellidos, edad, telefono, dni, correo_electronico, contrasena, tipo_agarre_raton, formato_teclado_favorito) VALUES
('Carlos', 'Gómez', 28, '612345678', '12345678A', 'carlos@gmail.com', 'pass123', 'Claw', '75%'),
('Lucía', 'Martínez', 22, '612345679', '23456789B', 'lucia@gmail.com', 'pass456', 'Fingertip', '60%'),
('David', 'Ruiz', 35, '612345680', '34567890C', 'david@gmail.com', 'pass789', 'Palm', '100%');

-- Inserciones de productos
INSERT INTO productos (nombre, marca, fecha_publicacion, precio, stock, tipo_producto, image) VALUES
('Razer Viper Mini', 'Razer', '2023-01-15', 49.99, 10, 'Raton', 'https://i.ibb.co/LzHQHHqq/33661-1-Photoroom.png'),
('Glorious Model O', 'Glorious', '2022-05-10', 59.90, 5, 'Raton', 'https://i.ibb.co/yFXZYdt6/image-Photoroom-3.png'),
('G Pro X Superlight', 'Logitech', '2023-03-01', 149.99, 7, 'Raton', 'https://i.ibb.co/3ZKvshk/34803-1-Photoroom.png'),
('Skate Pulsar PTFE', 'Pulsar', '2023-07-12', 9.99, 20, 'Skate', NULL),
('Teclado Ducky One 2 Mini', 'Ducky', '2022-10-01', 109.90, 6, 'Teclado', NULL),
('Alfombrilla SteelSeries QcK', 'SteelSeries', '2021-06-20', 29.99, 12, 'Alfombrilla', NULL),
('Auriculares Moondrop Aria', 'Moondrop', '2023-11-25', 89.90, 8, 'Auriculares', NULL);

-- Inserciones de tablas específicas (desde Java)
-- Raton
INSERT INTO raton (idproducto, tipo_agarre, medidas, sensor, peso, switches) VALUES
(1, 'Claw', '118x56x38mm', 'PMW3359', 61.0, 'Omron'),
(2, 'Fingertip', '128x59x37mm', 'Pixart 3360', 67.0, 'Kailh'),
(3, 'Palm', '125x63x40mm', 'HERO 25K', 63.0, 'Omron');

-- Skate
INSERT INTO skate (idproducto, velocidad) VALUES
(4, 'Rapida');

-- Teclado
INSERT INTO teclado (idproducto, formato, medidas, switches) VALUES
(5, '60%', '292x103x40mm', 'Cherry MX Red');

-- Alfombrilla
INSERT INTO alfombrilla (idproducto, velocidad, medidas) VALUES
(6, 'Control', '320x270x2mm');

-- Auriculares
INSERT INTO auriculares (idproducto, tipo_auricular) VALUES
(7, 'IEM');

-- Compras (sin metodo_pago)
INSERT INTO compras (idusuario, idproducto, fecha_compra, precio_final, estado_compra) VALUES
(1, 1, '2024-04-12', 49.99, 'Pagado'),
(2, 5, '2024-04-15', 109.90, 'Pagado'),
(3, 7, '2024-04-20', 89.90, 'Pendiente');

-- Valoraciones
INSERT INTO valoraciones (idusuario, idproducto, puntuacion, comentario) VALUES
(1, 1, 5, 'Ligero y preciso, ideal para Claw grip.'),
(2, 5, 4, 'Muy compacto, excelente para gaming.'),
(3, 7, 5, 'Calidad de sonido impresionante para su precio.');

-- Usuarios adicionales
INSERT INTO usuarios (nombre, apellidos, edad, telefono, dni, correo_electronico, contrasena, tipo_agarre_raton, formato_teclado_favorito) VALUES
('Marta', 'López', 27, '612345681', '45678901D', 'marta@gmail.com', 'pass111', 'Claw', '75%'),
('Javier', 'Santos', 31, '612345682', '56789012E', 'javier@gmail.com', 'pass222', 'Palm', '100%'),
('Ana', 'Pérez', 24, '612345683', '67890123F', 'ana@gmail.com', 'pass333', 'Fingertip', '60%');

-- Productos adicionales
INSERT INTO productos (nombre, marca, fecha_publicacion, precio, stock, tipo_producto, image) VALUES
('Razer DeathAdder V3', 'Razer', '2023-06-10', 79.99, 15, 'Raton', 'https://i.ibb.co/pjhwdzJX/image-Photoroom-2.png'),
('Logitech G303 Shroud', 'Logitech', '2022-11-05', 99.99, 9, 'Raton', 'https://i.ibb.co/zhCc6ytv/33717-1-Photoroom.png'),
('Pulsar X2', 'Pulsar', '2023-02-18', 89.90, 12, 'Raton', 'https://i.ibb.co/LzHQHHqq/33661-1-Photoroom.png'),
('Lamzu Atlantis', 'Lamzu', '2023-04-22', 89.00, 10, 'Raton', 'https://i.ibb.co/yFXZYdt6/image-Photoroom-3.png'),
('Vaxee Zygen NP-01S', 'Vaxee', '2021-12-01', 64.90, 7, 'Raton', 'https://i.ibb.co/3ZKvshk/34803-1-Photoroom.png'),
('Finalmouse Starlight-12', 'Finalmouse', '2022-08-15', 199.99, 3, 'Raton', 'https://i.ibb.co/zhCc6ytv/33717-1-Photoroom.png'),
('Cooler Master MM710', 'Cooler Master', '2021-03-12', 39.99, 25, 'Raton', 'https://i.ibb.co/pjhwdzJX/image-Photoroom-2.png'),
('Endgame XM1r', 'Endgame', '2022-02-07', 59.99, 14, 'Raton', 'https://i.ibb.co/LzHQHHqq/33661-1-Photoroom.png'),
('BenQ Zowie EC2-C', 'Zowie', '2023-09-09', 74.99, 11, 'Raton', 'https://i.ibb.co/yFXZYdt6/image-Photoroom-3.png'),
('SteelSeries Aerox 3', 'SteelSeries', '2022-01-20', 59.99, 18, 'Raton', 'https://i.ibb.co/3ZKvshk/34803-1-Photoroom.png'),
('Keychron K6', 'Keychron', '2022-12-10', 79.99, 20, 'Teclado', NULL),
('Logitech G Pro Keyboard', 'Logitech', '2021-07-07', 129.90, 6, 'Teclado', NULL),
('Ducky One 3 TKL', 'Ducky', '2023-05-03', 139.90, 8, 'Teclado', NULL),
('Glorious XXXL Mousepad', 'Glorious', '2022-03-03', 49.99, 30, 'Alfombrilla', NULL),
('Artisan Zero XSoft', 'Artisan', '2023-08-21', 79.90, 5, 'Alfombrilla', NULL),
('Pulsar Superglide', 'Pulsar', '2023-07-01', 24.99, 40, 'Alfombrilla', NULL),
('Skate Tiger ICE PTFE', 'Tiger', '2022-04-11', 7.99, 50, 'Skate', NULL),
('Skate Corepad Skatez', 'Corepad', '2021-09-19', 8.99, 60, 'Skate', NULL),
('Moondrop Starfield', 'Moondrop', '2022-02-22', 109.90, 10, 'Auriculares', NULL),
('Sennheiser HD560S', 'Sennheiser', '2021-10-10', 179.90, 4, 'Auriculares', NULL);

-- Tablas específicas para nuevos productos
-- NOTA: IDs asumidos de forma incremental comenzando en 8 según inserciones previas
-- Raton (ids 8-17)
INSERT INTO raton (idproducto, tipo_agarre, medidas, sensor, peso, switches) VALUES
(8, 'Claw', '128x62x42mm', 'Focus Pro 30K', 59.0, 'Razer Optical'),
(9, 'Fingertip', '120x62x39mm', 'HERO 25K', 75.0, 'Omron'),
(10, 'Claw', '120x63x38mm', 'PAW3395', 58.0, 'Kailh GM8.0'),
(11, 'Fingertip', '123x65x40mm', 'PAW3395', 55.0, 'Kailh GM8.0'),
(12, 'Palm', '122x64x40mm', 'Pixart 3389', 70.0, 'Huano'),
(13, 'Fingertip', '120x60x37mm', 'Pixart 3360', 47.0, 'Omron'),
(14, 'Claw', '122x58x38mm', 'Pixart 3389', 53.0, 'Kailh'),
(15, 'Palm', '123x66x40mm', 'Pixart 3389', 70.0, 'Huano'),
(16, 'Claw', '120x58x39mm', 'TrueMove Air', 57.0, 'SteelSeries'),
(17, 'Palm', '123x64x41mm', 'Pixart 3360', 72.0, 'Omron');

-- Teclado (ids 18-20)
INSERT INTO teclado (idproducto, formato, medidas, switches) VALUES
(18, '60%', '293x102x40mm', 'Gateron Brown'),
(19, '100%', '440x140x35mm', 'GX Blue'),
(20, '75%', '350x120x40mm', 'Cherry MX Brown');

-- Alfombrilla (ids 21-23)
INSERT INTO alfombrilla (idproducto, velocidad, medidas) VALUES
(21, 'Fast', '1200x550x3mm'),
(22, 'Control', '490x420x3mm'),
(23, 'Hybrid', '480x400x3mm');

-- Skate (ids 24-25)
INSERT INTO skate (idproducto, velocidad) VALUES
(24, 'Rapida'),
(25, 'Media');

-- Auriculares (ids 26-27)
INSERT INTO auriculares (idproducto, tipo_auricular) VALUES
(26, 'IEM'),
(27, 'Headphones');

-- Compras adicionales
INSERT INTO compras (idusuario, idproducto, fecha_compra, precio_final, estado_compra) VALUES
(4, 8, '2024-05-10', 79.99, 'Pagado'),
(5, 18, '2024-06-12', 79.99, 'Pagado'),
(6, 26, '2024-07-01', 109.90, 'Pendiente'),
(1, 19, '2024-07-15', 129.90, 'Pagado'),
(2, 22, '2024-08-03', 79.90, 'Pagado');

-- Valoraciones adicionales
INSERT INTO valoraciones (idusuario, idproducto, puntuacion, comentario) VALUES
(4, 8, 5, 'Preciso y muy cómodo para claw.'),
(5, 18, 4, 'Formato 60% perfecto para escritorio pequeño.'),
(6, 26, 5, 'Muy buen detalle y escena sonora.'),
(1, 10, 5, 'Pesado pero el sensor es excelente.'),
(2, 21, 4, 'Superficie rápida, ideal para sensores modernos.');
