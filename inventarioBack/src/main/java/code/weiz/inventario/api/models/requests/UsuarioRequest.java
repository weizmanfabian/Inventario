package code.weiz.inventario.api.models.requests;

import code.weiz.inventario.util.enums.EstadoEnum;
import code.weiz.inventario.util.enums.TipoDocumentoEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsuarioRequest {
    private String nombre;
    private String email;
    private String password;
    private TipoDocumentoEnum tipoDocumento;
    private String numeroDocumento;
    private EstadoEnum estado;
}
