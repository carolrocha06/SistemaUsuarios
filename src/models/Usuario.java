// Modelo de dados que representa o Usuario
package models;

public class Usuario { // classe usuario com os atributos e comportamentos do modelo
    private String nick; // primary key- identificador único
    private String nome; // nome do usuário
    private String senha; // senha do usuário
    private String tipo; // tipo de usuário (administrador e comum)
    private String status; // status do usuário (ativo e inativo)
    private String genero;  // gênero do usuário (masculino e feminino)

    // Construtores
    public Usuario() { // o padrão sem argumentos
    }

    // construtor que inicializa sem o atributo nick
    public Usuario(String nome, String senha, String tipo, String status, String genero) { // Sem nickanem
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.status = status;
        this.genero = genero;
    }

    // construtor que inicializa um usuário com todos os atributos, incluindo nick
    public Usuario(String nome, String nick, String senha, String tipo, String status, String genero) { // Com nick 
        this.nome = nome;
        this.nick = nick;
        this.senha = senha;
        this.tipo = tipo;
        this.status = status;
        this.genero = genero;
    }

    // Getters e Setters (métodos para todos os atributos)
    public String getNick() {
        return nick; // Retorna o valor armazenado na variavel
    } 

    public void setNick(String nick) {
        this.nick = nick; // Define o valor do atributo nick
    }

    // O mesmo ocorre aos outros nessas funções
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

    // toString-  // método que retorna uma representação textual do objeto
    @Override
    public String toString() {
        return "Usuário [nome=" + nome + ", nick=" + nick + ", senha=" + senha + ", tipo=" + tipo + ", status=" + status + ", genero=" + genero + "]";
    }
}
