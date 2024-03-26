package grupo.seoltec.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;


@Entity
public class Usuario {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Role tipo;

        private Boolean cursosSelecionados;
        private Planos planoContratado;
        @Column(nullable = false)
        private String nome;
        @Column(unique = true, nullable = false)
        private String email;
        @Column(nullable = false)
        private String senha;
        @Column(unique = true)
        private String cpf;
        @Column(unique = true)
        private String tel;
        private String token;
        private Boolean pagamenteRealizado;
        private Boolean cursoCripto;
        private Boolean cursoForex;
        private Boolean cursoCommodities;
        private Boolean cursoIndices;
        private Boolean cursoAcoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getTipo() {
        return tipo;
    }

    public void setTipo(Role tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getPagamenteRealizado() {
        return pagamenteRealizado;
    }

    public void setPagamenteRealizado(Boolean pagamenteRealizado) {
        this.pagamenteRealizado = pagamenteRealizado;
    }

    public Boolean getCursoCripto() {
        return cursoCripto;
    }

    public void setCursoCripto(Boolean cursoCripto) {
        this.cursoCripto = cursoCripto;
    }

    public Boolean getCursoForex() {
        return cursoForex;
    }

    public void setCursoForex(Boolean cursoForex) {
        this.cursoForex = cursoForex;
    }

    public Boolean getCursoCommodities() {
        return cursoCommodities;
    }

    public void setCursoCommodities(Boolean cursoCommodities) {
        this.cursoCommodities = cursoCommodities;
    }

    public Boolean getCursoIndices() {
        return cursoIndices;
    }

    public void setCursoIndices(Boolean cursoIndices) {
        this.cursoIndices = cursoIndices;
    }

    public Boolean getCursoAcoes() {
        return cursoAcoes;
    }

    public void setCursoAcoes(Boolean cursoAcoes) {
        this.cursoAcoes = cursoAcoes;
    }

    public Planos getPlanoContratado() {
        return planoContratado;
    }

    public void setPlanoContratado(Planos planoContratado) {
        this.planoContratado = planoContratado;
    }

    public Boolean getCursosSelecionados() {
        return cursosSelecionados;
    }

    public void setCursosSelecionados(Boolean cursosSelecionados) {
        this.cursosSelecionados = cursosSelecionados;
    }
}
