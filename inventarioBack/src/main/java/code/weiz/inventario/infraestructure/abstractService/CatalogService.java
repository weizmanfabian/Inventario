package code.weiz.inventario.infraestructure.abstractService;

import java.util.Set;

public interface CatalogService<Req, Res> {
    Set<Res> consultarElEstadoActualDelInventario();
    void registrarEntradasYSalidas(Req request);
}
