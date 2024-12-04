package views;

import models.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UsuarioForm extends JDialog { // ao invés de JFrame, extende JDialog para criar uma janela modal
    // declarando tudo que será usado:
    private JButton salvarButton, cancelarButton;
    private JComboBox<String> comboBox; // Especificando o tipo de dado entre <>
    private JPanel panelButton, panel0, panel1, panel2, panel4;
    private JLabel labelNome, labelNick, labelSenha, labelStatus, labelTipo, labelGen;
    private JTextField tFNome, tFNick;
    private JPasswordField pFSenha;
    private JCheckBox checkBox;
    private JRadioButton radio1, radio2;
    private ButtonGroup radioGroup;

    private Usuario usuario; // objeto de usuário que será manipulado
    private boolean isEditMode;  // define se o formulário está em modo de edição

    // construtor para modo de criação de usuário
    public UsuarioForm(Frame parent, String title) {
        super(parent, title, true); // janela modal
        this.isEditMode = false;
        initializeComponents();
        setResizable(false); // impede que o usuário ajuste o tamanho da janela
    }

    // construtor para modo de edição de usuário
    public UsuarioForm(Frame parent, String title, Usuario usuario) {
        super(parent, title, true); // janela modal
        this.usuario = usuario;
        this.isEditMode = true; // define que é modo de edição
        initializeComponents();  // inicializa os componentes da interface
        preencherCampos();
        setResizable(false); // impede que o usuário ajuste o tamanho da janela
    }

    private void initializeComponents() {
        // inicializa os labels e campos de texto
        labelNome = new JLabel(" Nome Completo: ");
        tFNome = new JTextField(20);
        labelNick = new JLabel(" Nickname: ");
        tFNick = new JTextField(20);
        labelSenha = new JLabel(" Senha: ");
        pFSenha = new JPasswordField(20);

        // ComboBox para selecionar o tipo de usuário
        labelTipo = new JLabel(" Tipo: ");
        comboBox = new JComboBox<>(); // criação do JComboBox
        comboBox.addItem(" Comum"); // adiciona o texto
        comboBox.addItem(" Administrador"); // adiciona o texto

        // Checkbox para o status do usuário
        labelStatus = new JLabel(" Status: ");
        checkBox = new JCheckBox("Ativo");

        // Configuração dos botões de rádio para o gênero
        // Criando painel para os RadioButtons
        labelGen = new JLabel(" Gênero: ");
        panel4 = new JPanel(); // criação do painel
        panel4.setLayout(new GridLayout(1, 2)); // mudando o layout
        panel4.add(radio1 = new JRadioButton("Feminino", false));
        panel4.add(radio2 = new JRadioButton("Masculino", true));

        // Grupo de Radio Buttons- agrupa os botões
        radioGroup = new ButtonGroup();
        radioGroup.add(radio1);
        radioGroup.add(radio2);

        // Botões de ação
        salvarButton = new JButton("Salvar"); // criação do botão salvar
        cancelarButton = new JButton("Cancelar"); // criação do outro botão
        panelButton = new JPanel(); // criação de um painel para botão
        panelButton.add(salvarButton); // adiciona cada botão no painel
        panelButton.add(cancelarButton);

        // Painéis para organizar os labels e campos de entrada
        panel1 = new JPanel(); // criação de um painel
        panel1.add(labelNome);
        panel1.add(labelNick);
        panel1.add(labelSenha);
        panel1.add(labelTipo);
        panel1.add(labelStatus);
        panel1.add(labelGen);
        panel1.setLayout(new GridLayout(6, 1));// 6 linhas e 1 coluna

        // Painel
        panel2 = new JPanel();
        panel2.add(tFNome);
        panel2.add(tFNick);
        panel2.add(pFSenha);
        panel2.add(comboBox); // adicionando jComboBox no painel
        panel2.add(checkBox); // adicionando jCkeckBox no painel
        panel2.add(panel4);
        panel2.setLayout(new GridLayout(6, 1, 3, 3));// 6 linhas, 1 coluna e lacunas de 3

        // Criando painel principal- panel0
        panel0 = new JPanel();
        panel0.setLayout(new BorderLayout());
        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.EAST);
        add(panelButton, BorderLayout.SOUTH);

        // setSize(450, 300); // set frame size

        // Adicionando uma margem de 10 pixels nas bordas laterais e verticais
        panel0.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Define as ações dos botões
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {  // Verifica se todos os campos foram preenchidos corretamente
                    if (isEditMode) {
                        atualizarUsuario(); // Atualiza o usuário existente
                    } else {
                        adicionarUsuario(); // Cria um novo usuário
                    }
                    dispose();  // Fecha o formulário
                }
            }
        });

        cancelarButton.addActionListener(e -> dispose()); // Fecha o formulário ao clicar em "Cancelar"

        this.add(panel0);
        this.pack(); // Ajusta o tamanho da janela aos componentes
        // this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // this.setResizable(false); // impede que o usuário ajuste o tamanho da janela
        this.setLocationRelativeTo(getParent()); // posiciona a janela no centro da tela

    } // fim do construtor

    // Preenche os campos com os dados do usuário em modo de edição
    private void preencherCampos() {
        if (usuario != null) {
            tFNome.setText(usuario.getNome());
            tFNick.setText(usuario.getNick());
            pFSenha.setText(usuario.getSenha().toString());
            if (usuario.getTipo().equalsIgnoreCase("Administrador")) {
                comboBox.setSelectedItem(1);
            } else {
                comboBox.setSelectedItem(0); // Igual ao comum
            }
            //JOptionPane.showMessageDialog(null, usuario.toString());
            if (usuario.getStatus().equalsIgnoreCase("Ativo")){
                checkBox.setSelected(true); // Ativo
            } else {
                checkBox.setSelected(false); // Inativo
            }
            if (usuario.getGenero().equalsIgnoreCase("Feminino")) {
                radio1.setSelected(true);
            } else {
                radio2.setSelected(false);
            }
        }
    }

    // Valida se todos os campos obrigatórios foram preenchidos
    private boolean validarCampos() {
        if (tFNome.getText().trim().isEmpty() || tFNick.getText().trim().isEmpty() 
        || pFSenha.getPassword().toString().isEmpty() || String.valueOf(comboBox.getSelectedItem()).trim().isEmpty() 
        || (!radio1.isSelected() && !radio2.isSelected())) { // a exclamação seria "se não for selecionado" = estaria vazio
        // || !checkBox.isSelected() // Status é ativo quando selecionado senão é inativo
            JOptionPane.showMessageDialog(
                    this,
                    "Todos os campos são obrigatórios, preencha corretamente.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Cria um novo objeto de usuário com os dados preenchidos
    private void adicionarUsuario() {
        String genero = "";
        if (radio1.isSelected()) {
            genero = "Feminino";
        } else if (radio2.isSelected()) {
            genero = "Masculino";
        } else {
            genero = "Indefinido";
        }
        usuario = new Usuario(
                tFNome.getText().trim(),
                tFNick.getText().trim(), // Tira espaço em branco da String
                String.valueOf(pFSenha.getPassword()), // Transforma em String igual ao arquivo Usuario (modelo)
                String.valueOf(comboBox.getSelectedItem()).trim(),
                checkBox.isSelected() ? "Ativo" : "Inativo",
                genero // Definiu na linha de cima
        );
    }

    // Atualiza os dados do objeto de usuário
    private void atualizarUsuario( ) {
        if (usuario != null) {
            usuario.setNome(tFNome.getText().trim());
            usuario.setNick(tFNick.getText().trim());
            usuario.setSenha(String.valueOf(pFSenha.getPassword()));
            usuario.setIipo(String.valueOf(comboBox.getSelectedItem()));
            
            usuario.setStatus(checkBox.isSelected() ? "Ativo" : "Inativo");
            
            usuario.setGenero(radio1.isSelected() ? "Feminino" : "Masculino");
        }
    }

    public Usuario getUsuario() {
        return usuario; // Retorna o objeto (usuário)
    } 

}// Fim da classe
