package code.weiz.inventario.api.models.responses;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DetalleInventarioResponse {
    private ProductoResponse producto;
    private Integer cantidad;
}
