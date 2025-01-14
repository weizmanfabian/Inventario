package code.weiz.inventario.api.models.requests;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DetalleInventarioRequest {
    private Long idProducto;
    private Double cantidad;
}
