// Arquivo responsável pela tabela visual que exibe os usuários cadastrados
package views;

import models.Usuario; //importa o modelo de outra pasta

// importa as classes necessárias para a interface gráfica
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioTableView extends JFrame {
    private JTable table; // tabela para exibir os dados dos usuários
    private DefaultTableModel tableModel; // modelo de dados para a tabela

    public UsuarioTableView() {
        super("Gerenciamento de Usuarios"); // define o título da janela
        initializeComponents(); // inicializa os componentes da interface
    }

    private void initializeComponents() {
        String[] columnNames = { "Nome", "Nick", "Senha", "Tipo", "Status", "Genero" }; // define os nomes das colunas da tabela
        tableModel = new DefaultTableModel(columnNames, 0); // cria um modelo vazio com as colunas definidas
        table = new JTable(tableModel); // cria a tabela baseada no modelo
        JScrollPane scrollPane = new JScrollPane(table); // adiciona rolagem à tabela

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));
                // adiciona um espaçamento ao redor da tabela

        this.setLayout(new BorderLayout()); // define o layout da janela
        this.add(scrollPane, BorderLayout.CENTER); // adiciona o painel de rolagem ao centro da janela

        this.setSize(600, 400); // define o tamanho da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra o programa ao fechar a janela
        this.setLocationRelativeTo(null); // posiciona a janela no centro da tela
    }

    public void atualizarTabela(List<Usuario> usuarios) {
        tableModel.setRowCount(0); // Limpa a tabela
        for (Usuario usuario : usuarios) { //percorre a lista de usuários
            Object[] row = { // cria uma linha com os dados do usuário
                    usuario.getNome(),
                    usuario.getNick(),
                    usuario.getSenha(),
                    usuario.getTipo(),
                    usuario.getStatus(),
                    usuario.getGenero()
            };
            tableModel.addRow(row); // adiciona a linha ao modelo da tabela
        }
    }

    public String getSelectedUsuarioNick() {
        String selectedRow = Integer.toString(table.getSelectedRow()); // Converte de String para int
        if (!selectedRow.equals("-1") ) { // Como se fizesse uma pergunta: "selectedRow é igual a -1?"
        // Linha selecionada >= 0, caso não tenha sido nenhuma é -1 (indica não encontrado ou inválido)
        // Sendo uma linha válida vai acessar os dados
            return (String) tableModel.getValueAt(table.getSelectedRow(), 1); 
            // Nick é a primeira coluna, portanto, o número zero
        }
        return null; // retorna null caso nenhuma linha tenha sido selecionada
    } 
}
