package code.weiz.inventario;

import code.weiz.inventario.domain.repositories.ProductoRepository;
import code.weiz.inventario.util.exceptions.IdNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class InventarioApplication implements CommandLineRunner {

	private final ProductoRepository productoRepository;

    public InventarioApplication(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//var producto = productoRepository.findById(1L).orElseThrow(() -> new IdNotFoundException("Producto"));
		//log.info("Producto Weiz: " + String.valueOf(producto.toString()));
	}

}
