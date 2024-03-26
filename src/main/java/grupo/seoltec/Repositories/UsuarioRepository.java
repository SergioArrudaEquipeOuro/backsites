package grupo.seoltec.Repositories;

import grupo.seoltec.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByTel(String tel);
    Optional<Usuario> findByToken(String token);
    Optional<Usuario> findByCpf(String cpf);

    List<Usuario> findAll();
}