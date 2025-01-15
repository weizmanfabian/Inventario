package code.weiz.inventario.domain.repositories;

import code.weiz.inventario.domain.entities.DetalleInventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleInventarioRepository extends JpaRepository<DetalleInventarioEntity, Long> {
}
