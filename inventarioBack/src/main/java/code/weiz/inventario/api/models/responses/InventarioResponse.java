package code.weiz.inventario.api.models.responses;

import code.weiz.inventario.domain.DetalleInventarioEntity;
import code.weiz.inventario.domain.UsuarioEntity;
import code.weiz.inventario.util.enums.TipoInventarioEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventarioResponse {
    private Long id;
    private LocalDateTime fechaHora;
    private Double total;
    private UsuarioResponse usuario;
    private TipoInventarioEnum tipo;
    private String observaciones;
    private Set<DetalleInventarioResponse> detallesInventario;
}
