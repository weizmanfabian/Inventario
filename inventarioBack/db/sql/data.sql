INSERT INTO USUARIOS (
    USU_NOMBRE, USU_EMAIL, USU_PASSWORD, USU_TIPO_DOCUMENTO, 
    USU_NUMERO_DOCUMENTO, USU_ESTADO, USU_FECHA_CREACION, USU_FECHA_ACTUALIZACION
) VALUES 
    ('Weizman Herreño', 'weizman.herreno@email.com', 'password123', 'CC', '1234567890', 'ACTIVO',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('María González', 'maria.gonzalez@email.com', 'password456', 'CE', '9876543210', 'ACTIVO', 
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Carlos Rodríguez', 'carlos.rodriguez@email.com', 'password789', 'NIT', '8001234567', 'INACTIVO', 
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO PRODUCTOS (
    PRO_NOMBRE, PRO_STOCK, PRO_PRECIO, PRO_CARACTERISTICAS, PRO_ESTADO,
    PRO_FECHA_CREACION, PRO_FECHA_ACTUALIZACION, PRO_STOCK_MINIMO
) VALUES 
    ('Laptop HP', 50, 2500000.000, 'Intel Core i7, 16GB RAM, 512GB SSD', 'ACTIVO',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10.000),
    ('Monitor Dell 27"', 30, 800000.000, 'Monitor LED 27 pulgadas, 1440p', 'ACTIVO',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5.000),
    ('Teclado Mecánico', 100, 250000.000, 'Switches Blue, RGB, Layout ESP', 'ACTIVO',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15.000);

INSERT INTO INVENTARIOS (
    INV_FECHA_HORA, INV_TOTAL, INV_USU_ID, INV_TIPO, INV_OBSERVACIONES
) VALUES 
    (CURRENT_TIMESTAMP, 15.00, 1, 'ENTRADA', 'Entrada de laptops y monitores'),
    (CURRENT_TIMESTAMP, 20.00, 1, 'ENTRADA', 'Entrada de teclados'),
    (CURRENT_TIMESTAMP, 5.00, 2, 'SALIDA', 'Salida de varios productos');

INSERT INTO DETALLE_INVENTARIOS (
    DET_INV_INV_ID, DET_INV_PRO_ID, DET_INV_CANTIDAD
) VALUES 
    -- Primera entrada: total 15 unidades (10 laptops + 5 monitores)
    (1, 1, 10.00),  -- 10 laptops
    (1, 2, 5.00),   -- 5 monitores
    
    -- Segunda entrada: total 20 unidades de teclados
    (2, 3, 20.00),  -- 20 teclados
    
    -- Salida: total 5 unidades (2 laptops + 3 monitores)
    (3, 1, 2.00),   -- 2 laptops
    (3, 2, 3.00);   -- 3 monitores