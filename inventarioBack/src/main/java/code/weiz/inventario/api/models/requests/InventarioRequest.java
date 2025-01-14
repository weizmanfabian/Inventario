package code.weiz.inventario.api.models.requests;

import code.weiz.inventario.util.enums.TipoInventarioEnum;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventarioRequest {
    private Long idUsuario;
    private TipoInventarioEnum tipoInventario;
    private String observaciones;
    private Set<DetalleInventarioRequest> detallesInventario;
}
