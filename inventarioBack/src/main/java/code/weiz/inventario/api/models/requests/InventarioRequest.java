package code.weiz.inventario.api.models.requests;

import code.weiz.inventario.util.enums.TipoInventarioEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventarioRequest {
    @NotNull(message = "El ID de usuario es requerido")
    private Long idUsuario;
    @NotNull(message = "El tipo de inventario es requerido")
    //@Pattern(regexp = "^(ENTRADA|SALIDA)$", message = "El tipo de inventario debe ser ENTRADA o SALIDA")
    private TipoInventarioEnum tipoInventario;
    private String observaciones;
    @NotEmpty(message = "Debe incluir al menos un detalle")
    private List<DetalleInventarioRequest> detallesInventario;
}
