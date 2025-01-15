package code.weiz.inventario.domain.repositories;

import code.weiz.inventario.domain.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
