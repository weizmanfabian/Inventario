package code.weiz.inventario.api.models.responses;

import code.weiz.inventario.domain.entities.DetalleInventarioEntity;
import code.weiz.inventario.domain.entities.InventarioEntity;
import code.weiz.inventario.domain.entities.UsuarioEntity;
import code.weiz.inventario.util.enums.TipoInventarioEnum;
import lombok.*;

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
    private Long id;
    private LocalDateTime fechaHora;
    private Integer total;
    private UsuarioResponse usuario;
    private TipoInventarioEnum tipo;
    private String observaciones;
    private Set<DetalleInventarioResponse> detallesInventario;

    public static InventarioResponse entityToResponse(InventarioEntity entity) {
        return InventarioResponse.builder()
                .id(entity.getId())
                .fechaHora(entity.getFechaHora())
                .total(entity.getTotal())
                .usuario(UsuarioEntity.entityToResponse(entity.getUsuario()))
                .tipo(entity.getTipo())
                .observaciones(entity.getObservaciones())
                .detallesInventario(entity.getDetallesInventario().stream().map(DetalleInventarioEntity::entityToResponse).collect(Collectors.toSet()))
                .build();
    }
}
