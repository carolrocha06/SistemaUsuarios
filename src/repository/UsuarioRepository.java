// Responsável por interagir com o banco de dados
package repository;

import models.Usuario;
import config.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    // Criar um novo usuario
    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, nick, senha, tipo, status, genero) VALUES (?, ?, ?, ?, ?, ?)";

        System.out.println(usuario.getSenha());
        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getNick());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.setString(5, usuario.getStatus());
            stmt.setString(6, usuario.getGenero());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário adicionado com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar usuário.");
            e.printStackTrace();
        }
    }

    // Obter todos os usuarios
    public List<Usuario> obterTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DbConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("nome"),
                    rs.getString("nick"),
                    rs.getString("senha"),
                    rs.getString("tipo"),
                    rs.getString("status"),
                    rs.getString("genero")
                    );
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter contatos.");
            e.printStackTrace();
        }

        return usuarios;
    }

    // Obter usuario por nick
    public Usuario obterUsuarioPorNick(String nick) {
        String sql = "SELECT * FROM usuarios WHERE nick = ?";
        Usuario usuario = null;

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nick); // Parâmetro da interrogação
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
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

        return usuario;
    }

    // Atualizar um usuario
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

    // Deletar um usuário
    public void deletarUsuario(String nick) {
        String sql = "DELETE FROM usuarios WHERE nick = ?";

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nick);

            int linhasAfetadas = stmt.executeUpdate();
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
