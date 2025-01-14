CREATE TYPE detalle_inventario_type AS (
    producto_id INTEGER,
    cantidad NUMERIC(10,2)
);

CREATE OR REPLACE PROCEDURE sp_actualizar_inventario(
    usuario_id INTEGER,
    tipo_inventario VARCHAR(50),
    observaciones TEXT,
    detalles detalle_inventario_type[]
) language plpgsql as $$
DECLARE
    inventario_id INTEGER;
BEGIN
    -- Validar usuario
    IF usuario_id IS NULL OR usuario_id <= 0 THEN
        RAISE EXCEPTION 'El id del usuario no puede ser nulo y debe ser un id válido';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM USUARIOS WHERE USU_ID = usuario_id) THEN
        RAISE EXCEPTION 'El usuario no existe';
    END IF;

	-- Validamos el tipo_inventario para que no sea inválido
	IF tipo_inventario NOT IN ('ENTRADA', 'SALIDA') THEN
        RAISE EXCEPTION 'El tipo de inventario debe ser ENTRADA o SALIDA';
    END IF;

    -- Validar productos y cantidades, para ello creamos una tabla temporal unnest y le nombramos
	-- sus columnas d(producto_id, cantidad) para poder acceder a ella
    IF EXISTS (
        SELECT 1
        FROM unnest(detalles) d(producto_id, cantidad)
        WHERE d.producto_id IS NULL
           OR d.cantidad IS NULL
           OR d.cantidad <= 0
    ) THEN
        RAISE EXCEPTION 'Existen productos con ID nulo o cantidades inválidas';
    END IF;

    -- Verificar existencia de productos
    IF EXISTS (
        SELECT 1
        FROM unnest(detalles) d(producto_id, cantidad)
        LEFT JOIN PRODUCTOS p ON p.PRO_ID = d.producto_id
        WHERE p.PRO_ID IS NULL
    ) THEN
        RAISE EXCEPTION 'Uno o más productos no existen';
    END IF;

    -- Para SALIDAS: Verificar stock suficiente
    IF tipo_inventario = 'SALIDA' AND EXISTS (
        SELECT 1
        FROM unnest(detalles) d(producto_id, cantidad)
        JOIN PRODUCTOS p ON p.PRO_ID = d.producto_id
        WHERE p.PRO_STOCK < d.cantidad
    ) THEN
        RAISE EXCEPTION 'No hay suficiente stock para uno o más productos';
    END IF;

    -- Insertar inventario
    INSERT INTO INVENTARIOS (
        INV_FECHA_HORA,
        INV_TOTAL,
        INV_USU_ID,
        INV_TIPO,
        INV_OBSERVACIONES
    ) VALUES (
        CURRENT_TIMESTAMP,
        (SELECT COALESCE(SUM(d.cantidad), 0) FROM unnest(detalles) d(producto_id, cantidad)),
        usuario_id,
        tipo_inventario,
        observaciones
    ) RETURNING INV_ID INTO inventario_id;

    -- Insertar todos los detalles
    INSERT INTO DETALLE_INVENTARIOS (
        DET_INV_INV_ID,
        DET_INV_PRO_ID,
        DET_INV_CANTIDAD
    )
    SELECT
        inventario_id,
        d.producto_id,
        d.cantidad
    FROM unnest(detalles) d(producto_id, cantidad);

    -- Actualizar stock de productos
    IF tipo_inventario = 'ENTRADA' THEN
        UPDATE PRODUCTOS p
        SET
            PRO_STOCK = p.PRO_STOCK + d.cantidad,
            PRO_FECHA_ACTUALIZACION = CURRENT_TIMESTAMP
        FROM unnest(detalles) d(producto_id, cantidad)
        WHERE p.PRO_ID = d.producto_id;
    ELSE
        UPDATE PRODUCTOS p
        SET
            PRO_STOCK = p.PRO_STOCK - d.cantidad,
            PRO_FECHA_ACTUALIZACION = CURRENT_TIMESTAMP
        FROM unnest(detalles) d(producto_id, cantidad)
        WHERE p.PRO_ID = d.producto_id;
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        RAISE EXCEPTION 'Error en sp_actualizar_inventario: %', SQLERRM;
END;$$;