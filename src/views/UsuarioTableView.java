// Arquivo responsável pela tabela visual que exibe os usuários cadastrados
package views;

import models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioTableView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public UsuarioTableView() {
        super("Gerenciamento de Usuários");
        initializeComponents();
    }

    private void initializeComponents() {
        String[] columnNames = { "Nome", "Nick", "Senha", "Tipo", "Status", "Genero" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);

        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void atualizarTabela(List<Usuario> usuarios) {
        tableModel.setRowCount(0); // Limpa a tabela
        for (Usuario usuario : usuarios) {
            Object[] row = { // Cada linha
                    usuario.getNome(),
                    usuario.getNick(),
                    usuario.getSenha(),
                    usuario.getTipo(),
                    usuario.getStatus(),
                    usuario.getGenero()
            };
            tableModel.addRow(row);
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
        return null; 
    }
}
