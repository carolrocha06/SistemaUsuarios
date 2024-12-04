// Arquivo para gerenciamento da lógica de negócio, controlando as ações de CRUD para o modelo Usuario
package controllers; // onde o arquivo está (pasta controllers)

// importes necessários
import models.Usuario;
import repository.UsuarioRepository;
import views.UsuarioForm;
import views.UsuarioTableView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsuarioController { // classe que gerencia as interações
    private UsuarioRepository repository; // acessa os dados dos usuarios
    private UsuarioTableView tableView; // interface gráfica para exibição da tabela

    public UsuarioController() {
        repository = new UsuarioRepository();
        tableView = new UsuarioTableView();
        inicializar(); // inicializa tudo
    }

    private void inicializar() {
        // Atualizar a tabela com os usuarios existentes
        atualizarTabela();

        // Criar a barra de ferramentas (toolbar) com botões das ações CRUD
        JToolBar toolBar = new JToolBar();
        JButton adicionarButton = new JButton("Adicionar"); // botão para adicionar usuário
        JButton editarButton = new JButton("Editar"); // botão para editar usuário
        JButton deletarButton = new JButton("Deletar"); // botão para deletar usuário
        toolBar.add(adicionarButton);
        toolBar.add(editarButton);
        toolBar.add(deletarButton);

        tableView.add(toolBar, java.awt.BorderLayout.NORTH); // ferramentas na parte superior da tabela

        // Ações dos botões de adicionar
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarUsuario();
            }
        });

        // ações para o botão de editar
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        // ações para o botão de deletar
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarUsuario();
            }
        });

        // configurações da interface de tabela
        tableView.setVisible(true); // janela visível: verdadeiro
        tableView.setResizable(false); // impede que o usuário ajuste o tamanho da janela
    }

    // método para atualizar os dados da tabela
    private void atualizarTabela() {
        List<Usuario> usuarios = repository.obterTodosUsuarios();// obtém os usuários do repositório
        tableView.atualizarTabela(usuarios);
    }

    // método para adicionar um novo usuário
    private void adicionarUsuario() {
        UsuarioForm form = new UsuarioForm(tableView, "Adicionar Usuário"); // formulário para inserir um novo usuário
        form.setVisible(true);
        Usuario novoUsuario = form.getUsuario(); // obtém os dados do formulário
        if (novoUsuario != null) { // verifica se o formulário retornou um novo usuário, ou seja, é diferente de
                                   // nulo
            repository.adicionarUsuario(novoUsuario); // add no repositório
            atualizarTabela(); // atualiza a tabela para exibir o novo usuario
        }
    }

    // método para editar um usuário selecionado
    private void editarUsuario() {
        String selectedNick = tableView.getSelectedUsuarioNick(); // obtém o nick do usuário selecionado na tabela
        if (selectedNick != null) { // Trocar -1 por null pois é String (se usuario selecionado for diferente de
                                    // nulo)
            Usuario usuario = repository.obterUsuarioPorNick(selectedNick); // procura o usuario pelo nick (primary key)
            if (usuario != null) {
                UsuarioForm form = new UsuarioForm(tableView,
                        "Editar Usuário", usuario); // formulário preenchido com os dados do usuário
                form.setVisible(true);
                Usuario usuarioAtualizado = form.getUsuario(); // dados atualizados do formulário
                if (usuarioAtualizado != null) {
                    usuarioAtualizado = new Usuario(
                            usuarioAtualizado.getNome(),
                            usuarioAtualizado.getNick(),
                            usuarioAtualizado.getSenha(),
                            usuarioAtualizado.getTipo(),
                            usuarioAtualizado.getStatus(),
                            usuarioAtualizado.getGenero());
                    repository.atualizarUsuario(usuarioAtualizado); // atualiza o usuário no repositório
                    atualizarTabela(); // atualiza a tabela com os novos dados
                }
            } else {
                JOptionPane.showMessageDialog(tableView,
                        "Usuário não encontrado.", // mensagem de erro caso o usuário não seja encontrado
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(tableView,
                    "Selecione um usuário para editar.", // mensagem de aviso caso nenhum usuário seja selecionado
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    // método para deletar um usuário selecionado
    private void deletarUsuario() {
        String selectedNick = tableView.getSelectedUsuarioNick(); // obtém o nickname do usuario selecionado na tabela
        if (selectedNick != null) { // Como se perguntasse "a String SelectedNick é diferente de nulo?"
            // se fosse int seria != -1
            int confirm = JOptionPane.showConfirmDialog(
                    tableView,
                    "Tem certeza que deseja deletar este usuario?",
                    "Confirmar Deleção",
                    JOptionPane.YES_NO_OPTION); // caixa de confirmação
            if (confirm == JOptionPane.YES_OPTION) {
            repository.deletarUsuario(selectedNick); // se for confirmada a ação, deleta usuário do repositório
                atualizarTabela();// atualiza a tabela para remover o usuário deletado
            } 
        } else { // mensagem de aviso caso nenhum usuário seja selecionado
            JOptionPane.showMessageDialog(
                    tableView,
                    "Selecione um usuario para deletar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void iniciar() { // método para iniciar o controlador
        // porém as ações já são inicializadas no construtor
    }

}
