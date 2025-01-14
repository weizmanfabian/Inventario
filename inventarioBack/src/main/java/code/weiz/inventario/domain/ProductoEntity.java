package code.weiz.inventario.domain;

import code.weiz.inventario.util.enums.EstadoEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PRODUCTOS")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Integer id;
    @Column(name = "PRO_NOMBRE")
    private String nombre;
    @Column(name = "PRO_STOCK")
    private Integer stock;
    @Column(name = "PRO_PRECIO")
    private Double precio;
    @Column(name = "PRO_CARACTERISTICAS")
    private String caracteristicas;
    @Column(name = "PRO_ESTADO")
    private EstadoEnum estado;
}
