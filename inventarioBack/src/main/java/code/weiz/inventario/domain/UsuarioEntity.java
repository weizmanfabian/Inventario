package code.weiz.inventario.domain;

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
    private TipoDocumentoEnum tipoDocumento;
    @Column(name = "USU_NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Column(name = "USU_ESTADO")
    private EstadoEnum estado;
}
