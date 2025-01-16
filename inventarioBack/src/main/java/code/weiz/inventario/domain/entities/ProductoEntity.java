package code.weiz.inventario.domain.entities;

import code.weiz.inventario.api.models.requests.ProductoRequest;
import code.weiz.inventario.api.models.responses.ProductoResponse;
import code.weiz.inventario.util.enums.EstadoEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "PRODUCTOS")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Long id;
    @Column(name = "PRO_NOMBRE")
    private String nombre;
    @Column(name = "PRO_STOCK")
    private Integer stock;
    @Column(name = "PRO_PRECIO")
    private Double precio;
    @Column(name = "PRO_STOCK_MINIMO")
    private Integer stockMinimo;
    @Column(name = "PRO_CARACTERISTICAS")
    private String caracteristicas;
    @Enumerated(EnumType.STRING)
    @Column(name = "PRO_ESTADO")
    private EstadoEnum estado;

    public static ProductoResponse entityToResponse(ProductoEntity entity) {
        return new ProductoResponse().builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .stock(entity.getStock())
                .precio(entity.getPrecio())
                .stockMinimo(entity.getStockMinimo())
                .caracteristicas(entity.getCaracteristicas())
                .estado(entity.getEstado())
                .build();
    }

    public static ProductoEntity requestToEntity(ProductoRequest request) {
        return new ProductoEntity().builder()
                .nombre(request.getNombre())
                .stock(request.getStock())
                .precio(request.getPrecio())
                .stockMinimo(request.getStockMinimo())
                .caracteristicas(request.getCaracteristicas())
                .estado(request.getEstado())
                .build();
    }
}
