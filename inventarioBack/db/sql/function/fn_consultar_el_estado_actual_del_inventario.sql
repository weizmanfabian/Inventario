CREATE OR REPLACE FUNCTION fn_consultar_el_estado_actual_del_inventario()
RETURNS TABLE (
    producto_id INTEGER,
    nombre VARCHAR(50),
    stock_actual INTEGER,
    precio NUMERIC(10,2),
    caracteristicas TEXT,
    estado VARCHAR(50),
    ultima_entrada TIMESTAMP,
    ultima_salida TIMESTAMP,
    valor_total NUMERIC(12,2),
    stock_minimo INTEGER
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    WITH ultimos_movimientos AS (
        SELECT
            det.DET_INV_PRO_ID,
            MAX(CASE WHEN inv.INV_TIPO = 'ENTRADA' THEN inv.INV_FECHA_HORA END) as ultima_entrada,
            MAX(CASE WHEN inv.INV_TIPO = 'SALIDA' THEN inv.INV_FECHA_HORA END) as ultima_salida
        FROM DETALLE_INVENTARIOS det
        JOIN INVENTARIOS inv ON inv.INV_ID = det.DET_INV_INV_ID
        GROUP BY det.DET_INV_PRO_ID
    )
    SELECT
        p.PRO_ID,
        p.PRO_NOMBRE,
        p.PRO_STOCK,
        p.PRO_PRECIO,
        p.PRO_CARACTERISTICAS,
        p.PRO_ESTADO,
        um.ultima_entrada,
        um.ultima_salida,
        (p.PRO_STOCK * p.PRO_PRECIO),
        p.PRO_STOCK_MINIMO
    FROM PRODUCTOS p
    LEFT JOIN ultimos_movimientos um ON um.DET_INV_PRO_ID = p.PRO_ID
    ORDER BY
        CASE WHEN p.PRO_STOCK <= p.PRO_STOCK_MINIMO THEN 0 ELSE 1 END,
        p.PRO_NOMBRE;
END;
$$;