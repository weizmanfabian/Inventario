package code.weiz.inventario.api.models.responses;

import code.weiz.inventario.util.enums.EstadoEnum;
import code.weiz.inventario.util.enums.TipoDocumentoEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private TipoDocumentoEnum tipoDocumento;
    private String numeroDocumento;
    private EstadoEnum estado;
}
