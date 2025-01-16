package code.weiz.inventario.domain.entities;

import code.weiz.inventario.api.models.requests.UsuarioRequest;
import code.weiz.inventario.api.models.responses.UsuarioResponse;
import code.weiz.inventario.util.enums.EstadoEnum;
import code.weiz.inventario.util.enums.TipoDocumentoEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "USUARIOS")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USU_ID")
    private Long id;
    @Column(name = "USU_NOMBRE")
    private String nombre;
    @Column(name = "USU_EMAIL")
    private String email;
    @Column(name = "USU_PASSWORD")
    private String password;
    @Column(name = "USU_TIPO_DOCUMENTO")
    @Enumerated(EnumType.STRING)
    private TipoDocumentoEnum tipoDocumento;
    @Column(name = "USU_NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Enumerated(EnumType.STRING)
    @Column(name = "USU_ESTADO")
    private EstadoEnum estado;

    public static UsuarioResponse entityToResponse(UsuarioEntity entity) {
        return UsuarioResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .tipoDocumento(entity.getTipoDocumento())
                .numeroDocumento(entity.getNumeroDocumento())
                .estado(entity.getEstado())
                .build();
    }

    public static UsuarioEntity requestToEntity(UsuarioRequest request) {
        return UsuarioEntity.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(request.getPassword())
                .tipoDocumento(request.getTipoDocumento())
                .numeroDocumento(request.getNumeroDocumento())
                .estado(request.getEstado())
                .build();
    }
}
