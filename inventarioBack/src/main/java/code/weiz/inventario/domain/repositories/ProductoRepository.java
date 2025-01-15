package code.weiz.inventario.domain.repositories;

import code.weiz.inventario.domain.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
