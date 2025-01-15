package code.weiz.inventario.domain.entities;

import code.weiz.inventario.api.models.responses.InventarioResponse;
import code.weiz.inventario.util.enums.TipoInventarioEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "INVENTARIOS")
public class InventarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INV_ID")
    private Long id;
    @Column(name = "INV_FECHA_HORA")
    private LocalDateTime fechaHora;
    @Column(name = "INV_TOTAL")
    private Integer total;

    //@Column(name = "INV_USU_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INV_USU_ID", nullable = false)
    private UsuarioEntity usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "INV_TIPO")
    private TipoInventarioEnum tipo;
    @Column(name = "INV_OBSERVACIONES")
    private String observaciones;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "inventario"
    )
    private Set<DetalleInventarioEntity> detallesInventario;

    public static InventarioResponse entityToResponse(InventarioEntity inventario) {
        return InventarioResponse.builder()
                .id(inventario.getId())
                .fechaHora(inventario.getFechaHora())
                .total(inventario.getTotal())
                .usuario(UsuarioEntity.entityToResponse(inventario.getUsuario()))
                .tipo(inventario.getTipo())
                .observaciones(inventario.getObservaciones())
                .detallesInventario(inventario.getDetallesInventario().stream().map(DetalleInventarioEntity::entityToResponse).collect(Collectors.toSet()))
                .build();
    }

}
