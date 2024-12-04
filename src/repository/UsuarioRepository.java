// Responsável por interagir com o banco de dados
package repository;

import models.Usuario; // importa o modelo usuario
import config.DbConnection; // importa a classe de conexao com o banco de dados

import java.sql.*; // importa tudo para manipulação de sql
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    // método para criar um novo usuario
    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, nick, senha, tipo, status, genero) VALUES (?, ?, ?, ?, ?, ?)";

        System.out.println(usuario.getSenha()); // exibe a senha do usuário no console (usa para debug)
        try (Connection conn = DbConnection.getConnection(); // obtém a conexão
                PreparedStatement stmt = conn.prepareStatement(sql)) { // prepara a query
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getNick());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.setString(5, usuario.getStatus());
            stmt.setString(6, usuario.getGenero());

            int linhasAfetadas = stmt.executeUpdate(); // executa a query e verifica quantas linhas foram afetadas
            if (linhasAfetadas > 0) {
                System.out.println("Usuário adicionado com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar usuário.");
            e.printStackTrace();  // exibe o rastreamento da exceção
        }
    }

    // método para obter todos os usuarios do banco de dados
    public List<Usuario> obterTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>(); // cria uma lista para armazenar os usuários
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DbConnection.getConnection();
                Statement stmt = conn.createStatement();  // cria um statement simples
                ResultSet rs = stmt.executeQuery(sql)) { // executa a consulta e obtém os resultados

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("nome"),
                    rs.getString("nick"),
                    rs.getString("senha"),
                    rs.getString("tipo"),
                    rs.getString("status"),
                    rs.getString("genero")
                    );
                usuarios.add(usuario);  // adiciona o usuário à lista
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter contatos.");
            e.printStackTrace();
        }

        return usuarios; // retorna a lista de usuários
    }

    // método para obter usuario por nick
    public Usuario obterUsuarioPorNick(String nick) {
        String sql = "SELECT * FROM usuarios WHERE nick = ?";
        Usuario usuario = null;

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nick); // Parâmetro da interrogação
            ResultSet rs = stmt.executeQuery(); // executa a query

            if (rs.next()) { // verifica se existe um resultado
                usuario = new Usuario(
                    rs.getString("nome"),
                    rs.getString("nick"),
                    rs.getString("senha"),
                    rs.getString("tipo"),
                    rs.getString("status"),
                    rs.getString("genero"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter usuário por nick.");
            e.printStackTrace();
        }

        return usuario; // retorna 
    }

    // método para atualizar um usuario
    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, senha = ?, tipo = ?, status = ?, genero = ? WHERE nick = ?";

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTipo());
            stmt.setString(4, usuario.getStatus());
            stmt.setString(5, usuario.getGenero());
            stmt.setString(6, usuario.getNick());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Usuário não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário.");
            e.printStackTrace();
        }
    }

    // método para deletar um usuário
    public void deletarUsuario(String nick) {
        String sql = "DELETE FROM usuarios WHERE nick = ?"; // o parametro que será usado

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nick); // define o nick a ser deletado

            int linhasAfetadas = stmt.executeUpdate(); // executa a deleção ao atualizar
            if (linhasAfetadas > 0) {
                System.out.println("Usuário deletado com sucesso!");
            } else {
                System.out.println("Usuário não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário.");
            e.printStackTrace();
        }
    }
}
