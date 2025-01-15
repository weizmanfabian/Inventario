package code.weiz.inventario.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DetalleInventarioRequest {
    @NotNull(message = "El ID del producto es requerido")
    private Long idProducto;

    @NotNull(message = "la cantidad es requerida")
    private Integer cantidad;
}
