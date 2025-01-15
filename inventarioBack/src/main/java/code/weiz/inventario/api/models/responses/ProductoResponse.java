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
    private Long id;
    private String nombre;
    private Integer stock;
    private Double precio;
    private Integer stockMinimo;
    private String caracteristicas;
    private EstadoEnum estado;
}
