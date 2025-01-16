package code.weiz.inventario.api.controllers;

import code.weiz.inventario.api.models.requests.InventarioRequest;
import code.weiz.inventario.api.models.responses.InventarioResponse;
import code.weiz.inventario.api.models.responses.errors.BaseErrorResponse;
import code.weiz.inventario.infraestructure.services.imp.IInventarioService;
import code.weiz.inventario.util.exceptions.InventarioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/user")
@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Inventario", description = "Operaciones relacionadas con el inventario")
public class InventarioController {

    private final IInventarioService inventarioService;

    @ApiResponse(responseCode = "404",
        description = "Devuelve un estado HTTP 404 si se violan las restricciones de validación en el cuerpo de la solicitud, " +
                "junto con un JSON que detalla los errores. " +
                "Si el error ocurre en el procedimiento almacenado, responde con \"No hay suficiente stock\" o \"Uno o más productos no existen en el sistema\"."
    )
    @Operation(summary = "Registrar entradas y salidas", description = "Registra las entradas y salidas de productos en el inventario")
    @PostMapping("/actualizar-inventario")
    public ResponseEntity<BaseErrorResponse> registrarEntradasYSalidas(@Valid @RequestBody InventarioRequest request) {
        inventarioService.registrarEntradasYSalidas(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Consultar el estado actual del inventario", description = "Consulta el estado actual del inventario")
    @GetMapping("/consultar")
    public ResponseEntity<Set<InventarioResponse>> consultarElEstadoActualDelInventario() throws InventarioException {
        return ResponseEntity.ok(inventarioService.consultarElEstadoActualDelInventario());
    }

}
