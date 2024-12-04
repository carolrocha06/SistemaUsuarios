// Arquivo que tem como único objetivo fazer a conexao/ligacao entre o banco de dados SQL e o programa
package config; // Pasta onde o arquivo está

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; // Importações necessárias para gerenciar essas conexoes

public class DbConnection { // Classe responsável por tais gerenciametos
    private static final String URL = "jdbc:mysql://localhost:3306/banco?useSSL=false&serverTimezone=UTC"; // URL de conexão
    private static final String USER = "root"; // mysql -u root -p 
    private static final String PASSWORD = ""; // usuario e senha padroes para autenticacao

    private static Connection connection = null; // armazena a conexao com o banco de dados (bd)

    public static Connection getConnection() throws SQLException { // função/método para obter a conexao
        if (connection == null || connection.isClosed()) { // verifica se a conexão já foi criada ou está fechada
            try{
                Class.forName("com.mysql.cj.jdbc.Driver"); // Carregar o driver do MySQL
                connection = DriverManager.getConnection(URL, USER, PASSWORD); // Estabelece a conexão com o banco de dados
            } catch (Exception e) { // Trata os erros que podem acontecer
                System.out.println("Driver do MySQL não encontrado.");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return connection; // retorna a coenxão ativa
    }
        public static void disconnect(Connection connection) { // função para desconectar do banco de dados
            try {
                connection.close(); // fecha a conexão
            } catch (SQLException e){
                throw new RuntimeException("Erro ao desconectar o banco de dados", e); // exceção em caso de erro (ao desconectar o banco)
            }
        }
}
    