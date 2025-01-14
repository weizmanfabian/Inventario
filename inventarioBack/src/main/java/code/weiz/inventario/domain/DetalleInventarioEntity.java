package code.weiz.inventario.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "DETALLE_INVENTARIOS")
public class DetalleInventarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DET_INV_ID")
    private Long id;

    @Column(name = "DET_INV_CANTIDAD")
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DET_INV_PRO_ID", nullable = false)
    private ProductoEntity producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DET_INV_INV_ID", nullable = false)
    private InventarioEntity inventario;
}
