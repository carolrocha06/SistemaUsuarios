// Modelo de dados que representa o Usuario
package models;

public class Usuario {
    private String nick; 
    private String nome;
    private String senha;
    private String tipo;
    private String status;
    private String genero;

    // Construtores
    public Usuario() {
    }

    public Usuario(String nome, String senha, String tipo, String status, String genero) { // Sem nick 
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.status = status;
        this.genero = genero;
    }

    public Usuario(String nome, String nick, String senha, String tipo, String status, String genero) { // Com nick 
        this.nome = nome;
        this.nick = nick;
        this.senha = senha;
        this.tipo = tipo;
        this.status = status;
        this.genero = genero;
    }

    // Getters e Setters
    public String getNick() {
        return nick; // Retorna o valor armazenado na variavel
    } 

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setIipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // toString
    @Override
    public String toString() {
        return "Usuário [nome=" + nome + ", nick=" + nick + ", senha=" + senha + ", tipo=" + tipo + ", status=" + status + ", genero=" + genero + "]";
    }
}
