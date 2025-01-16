package code.weiz.inventario.infraestructure.services;

import code.weiz.inventario.domain.entities.UsuarioEntity;
import code.weiz.inventario.domain.repositories.UsuarioRepository;
import code.weiz.inventario.util.exceptions.IdNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AppUserService implements UserDetailsService {

    private static final String COLLECTION_NAME = "app_user";

    private final UsuarioRepository userRepository;

    @Override

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = this.userRepository.findByEmail(email).orElseThrow(()-> new IdNotFoundException(COLLECTION_NAME));

        return mapUserToUserDetails(user);
    }

    private static UserDetails mapUserToUserDetails(UsuarioEntity user) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        return new User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
