package code.weiz.inventario;

import code.weiz.inventario.domain.repositories.ProductoRepository;
import code.weiz.inventario.domain.repositories.UsuarioRepository;
import code.weiz.inventario.util.exceptions.IdNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootApplication
public class InventarioApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;


    public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//this.usuarioRepository.findAll().forEach(user -> log.info(user.getEmail() + " - " + this.bCryptPasswordEncoder.encode(user.getPassword())));
	}

}
