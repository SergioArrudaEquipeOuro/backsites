package grupo.seoltec.Controller;

import grupo.seoltec.Models.Usuario;
import grupo.seoltec.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioLogado = usuarioService.login(usuario.getEmail(), usuario.getSenha());
            return ResponseEntity.ok().body(Map.of("token", usuarioLogado.getToken()));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: " + e.getMessage());
        }
    }

    @PostMapping("/verificaToken")
    public ResponseEntity<Boolean> verificaToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        boolean tokenExiste = usuarioService.verificaTokenExistente(token);
        return ResponseEntity.ok(tokenExiste);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/usuarioPorToken")
    public ResponseEntity<?> buscarUsuarioPorToken(@RequestHeader("Authorization") String token) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorToken(token);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/porEmail/{email}")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/porTel/{tel}")
    public ResponseEntity<Usuario> buscarUsuarioPorTel(@PathVariable String tel) {
        Usuario usuario = usuarioService.buscarUsuarioPorTel(tel);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/porCpf/{cpf}")
    public ResponseEntity<Usuario> buscarUsuarioPorCpf(@PathVariable String cpf) {
        Usuario usuario = usuarioService.buscarUsuarioPorCpf(cpf);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.atualizarUsuario(usuario);
        return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/atualizarCursos")
    public ResponseEntity<Usuario> atualizarCursos(@PathVariable Long id, @RequestBody Map<String, Boolean> atualizacoes) {
        Usuario usuarioAtualizado = usuarioService.atualizarCursos(id, atualizacoes);
        return ResponseEntity.ok(usuarioAtualizado);
    }



}
