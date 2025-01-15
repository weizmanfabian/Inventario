package code.weiz.inventario.infraestructure.services.imp;

import code.weiz.inventario.api.models.requests.InventarioRequest;
import code.weiz.inventario.api.models.responses.InventarioResponse;
import code.weiz.inventario.infraestructure.abstractService.CatalogService;
import code.weiz.inventario.infraestructure.abstractService.CrudService;

public interface IInventarioService extends CatalogService<InventarioRequest, InventarioResponse>, CrudService<InventarioRequest, InventarioResponse, Long> {
}
