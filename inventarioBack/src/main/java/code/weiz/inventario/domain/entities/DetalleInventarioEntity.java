package code.weiz.inventario.domain.entities;

import code.weiz.inventario.api.models.responses.DetalleInventarioResponse;
import code.weiz.inventario.api.models.responses.ProductoResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
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

    public static DetalleInventarioResponse entityToResponse(DetalleInventarioEntity detalleInventario) {
        return DetalleInventarioResponse.builder()
                .producto(ProductoEntity.entityToResponse(detalleInventario.getProducto()))
                .cantidad(detalleInventario.getCantidad())
                .build();
    }
}
