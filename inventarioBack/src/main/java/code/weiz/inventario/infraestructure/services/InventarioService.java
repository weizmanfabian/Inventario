package code.weiz.inventario.infraestructure.services;

import code.weiz.inventario.api.models.requests.DetalleInventarioRequest;
import code.weiz.inventario.api.models.requests.InventarioRequest;
import code.weiz.inventario.api.models.responses.InventarioResponse;
import code.weiz.inventario.domain.entities.InventarioEntity;
import code.weiz.inventario.domain.repositories.InventarioRepository;
import code.weiz.inventario.infraestructure.services.imp.IInventarioService;
import code.weiz.inventario.mapper.InventarioRowMapper;
import code.weiz.inventario.util.exceptions.InventarioException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class InventarioService implements IInventarioService {

    private final JdbcTemplate jdbcTemplate;
    private final InventarioRepository inventarioRepository;


    @Override
    public Set<InventarioResponse> consultarElEstadoActualDelInventario() {
        try {
            String sql = "SELECT * FROM fn_consultar_el_estado_actual_del_inventario()";

            List<InventarioResponse> inventarios = jdbcTemplate.query(
                    sql,
                    new InventarioRowMapper()
            );

            return new HashSet<>(inventarios);

        } catch (DataAccessException e) {
            log.error("Error execute FN consultarElEstadoActualDelInventario", e);
            String errorMessage = e.getMostSpecificCause() != null && e.getMostSpecificCause().getMessage() != null
                    ? e.getMostSpecificCause().getMessage()
                    : "Error desconocido";
            throw new InventarioException(errorMessage);
        }
    }

    @Override
    public void registrarEntradasYSalidas(InventarioRequest request) {
        try {
            // Convierto los detalles a sql array
            java.sql.Array detallesArray = convertirDetallesAArray(request.getDetallesInventario());

            // determino los tipos de parametros
            SqlParameterValue userIdParam = new SqlParameterValue(Types.INTEGER, request.getIdUsuario());
            SqlParameterValue inventoryTypeParam = new SqlParameterValue(Types.VARCHAR, request.getTipoInventario().name());
            SqlParameterValue observationsParam = new SqlParameterValue(Types.VARCHAR, request.getObservaciones());
            SqlParameterValue detailsParam = new SqlParameterValue(Types.ARRAY, detallesArray);

            // Ejecuto el procedimiento almacenado
            jdbcTemplate.update("CALL sp_actualizar_inventario(?, ?, ?, ?)",
                    userIdParam,
                    inventoryTypeParam,
                    observationsParam,
                    detailsParam);

        } catch (DataAccessException e) {
            log.error("Error execute SP registrarEntradasYSalidas", e);
            String errorMessage = e.getMostSpecificCause() != null && e.getMostSpecificCause().getMessage() != null
                    ? e.getMostSpecificCause().getMessage()
                    : "Error desconocido";
            throw new InventarioException(getExceptionMessage(errorMessage));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getExceptionMessage(String errorMessage) {
        if (errorMessage.contains("El usuario no existe")) {
            return "Usuario no encontrado";
        } else if (errorMessage.contains("No hay suficiente stock")) {
            return "Stock insuficiente para algunos productos";
        } else if (errorMessage.contains("Uno o más productos no existen")) {
            return "Uno o más productos no existen en el sistema";
        }
        return "Error al actualizar inventario: " + errorMessage;
    }

    private java.sql.Array convertirDetallesAArray(List<DetalleInventarioRequest> detalles) throws SQLException {
        // Convierte cada detalle a un registro compuesto en PostgreSQL
        String[] registros = detalles.stream()
                .map(detalle -> String.format("(%d,%d)", detalle.getIdProducto(), detalle.getCantidad()))
                .toArray(String[]::new);

        // Crea un array SQL compatible con PostgreSQL
        return jdbcTemplate.getDataSource().getConnection()
                .createArrayOf("detalle_inventario_type", registros);
    }


    private java.sql.Array convertirDetallesAArray4(List<DetalleInventarioRequest> detalles) throws SQLException, SQLException {
        // Convierte los detalles a un array de objetos compatibles
        Object[] arrayDetalles = detalles.stream()
                .map(detalle -> new Object[]{detalle.getIdProducto(), detalle.getCantidad()})
                .toArray(Object[]::new);


        // Crea un array SQL compatible con PostgreSQL
        return jdbcTemplate.getDataSource().getConnection()
                .createArrayOf("detalle_inventario_type", arrayDetalles);
    }


    private String convertirDetallesAArray3(List<DetalleInventarioRequest> detalles) {
        return Arrays.stream(detalles.toArray(new DetalleInventarioRequest[0]))
                .map(detalle -> String.format("(%d, %s)", detalle.getIdProducto(), detalle.getCantidad()))
                .collect(Collectors.joining(",", "{", "}"));
    }

    private String convertirDetallesAArray2(List<DetalleInventarioRequest> detalles) {
        return detalles.stream()
                .map(detalle -> String.format("(%d, %s)", detalle.getIdProducto(), detalle.getCantidad()))
                .collect(Collectors.joining("::detalle_inventario_type, ", "ARRAY[", "::detalle_inventario_type]"));
    }

    private Object[] convertirDetallesAArray1(List<DetalleInventarioRequest> detalles) {
        return detalles.stream()
                .map(detalle -> new Object[]{detalle.getIdProducto(), detalle.getCantidad()})
                .toArray(Object[]::new);
    }


    private String convertirDetallesAJson2(List<DetalleInventarioRequest> detalles) {
        return detalles.stream()
                .map(detalle -> String.format("(%d,%d)", detalle.getIdProducto(), detalle.getCantidad()))
                .collect(Collectors.joining(",", "[(", ")]"));
    }


    private String convertirDetallesAJson1(List<DetalleInventarioRequest> detalles) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (DetalleInventarioRequest detalle : detalles) {
            sb.append("{");
            sb.append("\"idProducto\": ").append(detalle.getIdProducto()).append(",");
            sb.append("\"cantidad\": ").append(detalle.getCantidad()).append(",");
            sb.append("},");
        }
        if (detalles.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

}
