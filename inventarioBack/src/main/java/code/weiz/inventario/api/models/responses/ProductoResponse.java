package code.weiz.inventario.api.models.responses;

import code.weiz.inventario.util.enums.EstadoEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductoResponse {
    private Integer id;
    private String nombre;
    private Double stock;
    private Double precio;
    private String caracteristicas;
    private EstadoEnum estado;
}
