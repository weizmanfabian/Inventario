package code.weiz.inventario.api.models.responses;

import code.weiz.inventario.domain.entities.DetalleInventarioEntity;
import code.weiz.inventario.domain.entities.InventarioEntity;
import code.weiz.inventario.domain.entities.UsuarioEntity;
import code.weiz.inventario.util.enums.EstadoEnum;
import code.weiz.inventario.util.enums.TipoInventarioEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventarioResponse {
    private Long productoId;
    private String nombre;
    private Integer stockActual;
    private BigDecimal precio;
    private String caracteristicas;
    private EstadoEnum estado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private LocalDateTime ultimaEntrada;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private LocalDateTime ultimaSalida;
    private BigDecimal valorTotal;
    private Integer stockMinimo;
}
