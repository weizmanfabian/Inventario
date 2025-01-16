package code.weiz.inventario.mapper;

import code.weiz.inventario.api.models.responses.InventarioResponse;
import code.weiz.inventario.util.enums.EstadoEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventarioRowMapper implements RowMapper<InventarioResponse> {
    @Override
    public InventarioResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return InventarioResponse.builder()
                .productoId(rs.getLong("producto_id"))
                .nombre(rs.getString("nombre"))
                .stockActual(rs.getInt("stock_actual"))
                .precio(rs.getBigDecimal("precio"))
                .caracteristicas(rs.getString("caracteristicas"))
                .estado(EstadoEnum.valueOf(rs.getString("estado")))
                .ultimaEntrada(rs.getTimestamp("ultima_entrada") != null ?
                        rs.getTimestamp("ultima_entrada").toLocalDateTime() : null)
                .ultimaSalida(rs.getTimestamp("ultima_salida") != null ?
                        rs.getTimestamp("ultima_salida").toLocalDateTime() : null)
                .valorTotal(rs.getBigDecimal("valor_total"))
                .stockMinimo(rs.getInt("stock_minimo"))
                .build();
    }
}
