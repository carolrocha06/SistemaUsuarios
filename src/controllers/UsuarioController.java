// Arquivo para gerenciamento da lógica de negócio, controlando as ações de CRUD para o modelo Usuario
package controllers;

import models.Usuario;
import repository.UsuarioRepository;
import views.UsuarioForm;
import views.UsuarioTableView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsuarioController {
    private UsuarioRepository repository;
    private UsuarioTableView tableView;

    public UsuarioController() {
        repository = new UsuarioRepository();
        tableView = new UsuarioTableView();
        inicializar();
    }

    private void inicializar() {
        // Atualizar a tabela com os usuarios existentes
        atualizarTabela();

        // Criar a barra de ferramentas (toolbar) com botões
        JToolBar toolBar = new JToolBar();
        JButton adicionarButton = new JButton("Adicionar");
        JButton editarButton = new JButton("Editar");
        JButton deletarButton = new JButton("Deletar");
        toolBar.add(adicionarButton);
        toolBar.add(editarButton);
        toolBar.add(deletarButton);

        tableView.add(toolBar, java.awt.BorderLayout.NORTH);

        // Ações dos botões
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarUsuario();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarUsuario();
            }
        });

        tableView.setVisible(true);
        tableView.setResizable(false); // impede que o usuário ajuste o tamanho da janela
    }

    private void atualizarTabela() {
        List<Usuario> usuarios = repository.obterTodosUsuarios();
        tableView.atualizarTabela(usuarios);
    }

    private void adicionarUsuario() {
        UsuarioForm form = new UsuarioForm(tableView, "Adicionar Usuário");
        form.setVisible(true);
        Usuario novoUsuario = form.getUsuario();
        if (novoUsuario != null) {
            repository.adicionarUsuario(novoUsuario);
            atualizarTabela();
        }
    }

    private void editarUsuario() {
        String selectedNick = tableView.getSelectedUsuarioNick();
        if (selectedNick != null) { // Trocar -1 por null é ideal
            Usuario usuario = repository.obterUsuarioPorNick(selectedNick);
            if (usuario != null) {
                UsuarioForm form = new UsuarioForm(tableView,
                        "Editar Usuário", usuario);
                form.setVisible(true);
                Usuario usuarioAtualizado = form.getUsuario();
                if (usuarioAtualizado != null) {
                    usuarioAtualizado = new Usuario(
                            usuarioAtualizado.getNome(),
                            usuarioAtualizado.getNick(),
                            usuarioAtualizado.getSenha(),
                            usuarioAtualizado.getTipo(),
                            usuarioAtualizado.getStatus(),
                            usuarioAtualizado.getGenero());
                    repository.atualizarUsuario(usuarioAtualizado);
                    atualizarTabela();
                }
            } else {
                JOptionPane.showMessageDialog(tableView,
                        "Usuário não encontrado.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(tableView,
                    "Selecione um usuário para editar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    private void deletarUsuario() {
        String selectedNick = tableView.getSelectedUsuarioNick();
        if (selectedNick != null) { // Como se perguntasse "a String SelectedNick é diferente de nulo?"
            // se fosse int seria != -1
            int confirm = JOptionPane.showConfirmDialog(
                    tableView,
                    "Tem certeza que deseja deletar este contato?",
                    "Confirmar Deleção",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                repository.deletarUsuario(selectedNick);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(
                    tableView,
                    "Selecione um usuário para deletar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void iniciar() {
        // Ações já são inicializadas no construtor
    }

}
