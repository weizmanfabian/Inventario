package code.weiz.inventario.api.models.requests;

import code.weiz.inventario.util.enums.EstadoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductoRequest {
    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    @NotNull(message = "El stock es requerido")
    private Integer stock;
    @NotNull(message = "El precio es requerido")
    private Double precio;
    @NotNull(message = "El stock minimo es requerido")
    private Integer stockMinimo;
    @NotBlank(message = "Las caracteristicas son requeridas")
    private String caracteristicas;
    @NotNull(message = "El estado es requerido")
    private EstadoEnum estado;
}
