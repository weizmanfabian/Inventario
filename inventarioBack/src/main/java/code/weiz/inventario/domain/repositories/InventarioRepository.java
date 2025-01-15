package code.weiz.inventario.domain.repositories;

import code.weiz.inventario.domain.entities.InventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<InventarioEntity, Long> {
}
