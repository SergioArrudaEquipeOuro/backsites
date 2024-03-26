package grupo.seoltec.Services;

import grupo.seoltec.Models.Role;
import grupo.seoltec.Models.Usuario;
import grupo.seoltec.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;



    public Usuario criarUsuario(Usuario usuario) {
        try {
            Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
            if (usuarioExistente.isPresent()) {
                throw new RuntimeException("Email já cadastrado");
            }

            usuarioExistente = usuarioRepository.findByTel(usuario.getTel());
            if (usuarioExistente.isPresent()) {
                throw new RuntimeException("Telefone já cadastrado");
            }

            // Verifica se o nome do usuário começa com o código de administrador
            if (usuario.getNome().startsWith("!#adm#")) {
                // Define o papel do usuário como ADMINISTRADOR
                usuario.setTipo(Role.ADMINISTRADOR);
                // Define todos os cursos como true
                usuario.setCursoCripto(true);
                usuario.setCursoForex(true);
                usuario.setCursoCommodities(true);
                usuario.setCursoIndices(true);
                usuario.setCursoAcoes(true);
                // Remove o código do nome do usuário
                usuario.setNome(usuario.getNome().substring(6));
            } else {
                // Caso não seja administrador, configura os cursos como false por padrão
                usuario.setTipo(Role.CLIENTE);
                usuario.setPagamenteRealizado(false);
                usuario.setCursoCripto(false);
                usuario.setCursoForex(false);
                usuario.setCursoCommodities(false);
                usuario.setCursoIndices(false);
                usuario.setCursoAcoes(false);
            }

            return usuarioRepository.save(usuario);
        } catch (RuntimeException e) {
            // Se a mensagem de erro for diferente de "Email já cadastrado" ou "Telefone já cadastrado",
            // lança uma nova RuntimeException com a mensagem "Ops, algo deu errado."
            if (!e.getMessage().equals("Email já cadastrado") && !e.getMessage().equals("Telefone já cadastrado")) {
                throw new RuntimeException("Ops, algo deu errado.");
            }
            // Caso contrário, propaga a exceção original.
            throw e;
        }
    }


    public Usuario login(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
            String token = gerarTokenUnico();
            Usuario usuarioLogado = usuario.get();
            usuarioLogado.setToken(token);
            usuarioRepository.save(usuarioLogado);
            return usuarioLogado;
        } else {
            throw new RuntimeException("Credenciais inválidas");
        }
    }


    private String gerarTokenUnico() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 71; i++) {
            token.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return token.toString();
    }

    public boolean verificaTokenExistente(String token) {
        return usuarioRepository.findByToken(token).isPresent();
    }



    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setSenha(null);
        usuario.setToken(null);
        return usuario;
    }

    public Usuario buscarUsuarioPorToken(String token) {
        if (!verificaTokenExistente(token)) {
            throw new RuntimeException("Usuário inválido. Por favor, faça login novamente.");
        }
        Usuario usuario = usuarioRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setSenha(null);
        usuario.setToken(null);
        return usuario;
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado pelo e-mail: " + email));
    }

    public Usuario buscarUsuarioPorTel(String tel) {
        return usuarioRepository.findByTel(tel)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado pelo telefone: " + tel));
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado pelo CPF: " + cpf));
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        // Aqui, você pode incluir lógica adicional antes de salvar o usuário
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarCursos(Long usuarioId, Map<String, Boolean> atualizacoes) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + usuarioId));

        usuario.setCursoCripto(atualizacoes.getOrDefault("cursoCripto", usuario.getCursoCripto()));
        usuario.setCursoForex(atualizacoes.getOrDefault("cursoForex", usuario.getCursoForex()));
        usuario.setCursoIndices(atualizacoes.getOrDefault("cursoIndices", usuario.getCursoIndices()));
        usuario.setCursoAcoes(atualizacoes.getOrDefault("cursoAcoes", usuario.getCursoAcoes()));

        usuario.setCursosSelecionados(true);

        return usuarioRepository.save(usuario);
    }



}