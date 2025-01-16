
CREATE OR REPLACE FUNCTION sp_actualizar_inventario1(
    usuario_id INTEGER,
    tipo_inventario VARCHAR(50),
    observaciones TEXT,
    detalles detalle_inventario_type[]
) RETURNS void AS $$
DECLARE
    inventario_id INTEGER;
    detalle detalle_inventario_type;
    stock_actual NUMERIC(10,3);
BEGIN
    -- Validamos usuario
    IF usuario_id IS NULL THEN
        RAISE EXCEPTION 'El id del usuario no puede ser nulo';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM USUARIOS WHERE USU_ID = usuario_id) THEN
        RAISE EXCEPTION 'El usuario no existe';
    END IF;

    -- Insertamos inventario
    INSERT INTO INVENTARIOS (
        INV_FECHA_HORA,
        INV_TOTAL,
        INV_USU_ID,
        INV_TIPO,
        INV_OBSERVACIONES
    ) VALUES (
        CURRENT_TIMESTAMP,
        0,
        usuario_id,
        tipo_inventario,
        observaciones
    ) RETURNING INV_ID INTO inventario_id;

    -- Procesamos cada detalle
    FOREACH detalle IN ARRAY detalles LOOP
        -- Validamos el producto
        IF detalle.producto_id IS NULL OR detalle.cantidad IS NULL OR detalle.cantidad <= 0 THEN
            RAISE EXCEPTION 'El productoId y la cantidad no pueden ser nulos o cero';
        END IF;

        IF NOT EXISTS (SELECT 1 FROM PRODUCTOS WHERE PRO_ID = detalle.producto_id) THEN
            RAISE EXCEPTION 'El producto % no existe', detalle.producto_id;
        END IF;

        -- Obtener stock actual
        SELECT PRO_STOCK INTO stock_actual
        FROM PRODUCTOS
        WHERE PRO_ID = detalle.producto_id;

        -- Procesar según tipo de inventario
        IF tipo_inventario = 'ENTRADA' THEN
            UPDATE PRODUCTOS
            SET PRO_STOCK = stock_actual + detalle.cantidad,
                PRO_FECHA_ACTUALIZACION = CURRENT_TIMESTAMP
            WHERE PRO_ID = detalle.producto_id;

            UPDATE INVENTARIOS
            SET INV_TOTAL = INV_TOTAL + detalle.cantidad
            WHERE INV_ID = inventario_id;

        ELSIF tipo_inventario = 'SALIDA' THEN
            IF stock_actual < detalle.cantidad THEN
                RAISE EXCEPTION 'No hay suficiente stock para el producto %', detalle.producto_id;
            END IF;

            UPDATE PRODUCTOS
            SET PRO_STOCK = stock_actual - detalle.cantidad,
                PRO_FECHA_ACTUALIZACION = CURRENT_TIMESTAMP
            WHERE PRO_ID = detalle.producto_id;

            UPDATE INVENTARIOS
            SET INV_TOTAL = INV_TOTAL + detalle.cantidad
            WHERE INV_ID = inventario_id;
        END IF;

        -- Insertar detalle
        INSERT INTO DETALLE_INVENTARIOS (
            DET_INV_INV_ID,
            DET_INV_PRO_ID,
            DET_INV_CANTIDAD
        ) VALUES (
            inventario_id,
            detalle.producto_id,
            detalle.cantidad
        );
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        RAISE EXCEPTION 'Error en sp_actualizar_inventario1: %', SQLERRM;
END;
$$ LANGUAGE plpgsql;