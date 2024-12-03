package views;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;

import controllers.UsuarioController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Import necessário para o ActionListener
import java.net.URL;

public class Sistema extends JFrame {
    private JLabel imgLabel;
    private JButton cadButton; // Botão de cadastro e botão de login
    private JPanel panelB;

    public Sistema() {
        super("Tela Principal- Sistema de Usuários");
        
        imgLabel = new JLabel();

        URL imgUrl = getClass().getResource("/assets/imagem.jpeg");
        if(imgUrl != null){
            Icon iconImagem = new ImageIcon(imgUrl); 
            
            imgLabel.setIcon(iconImagem);
        } else {
            System.out.println("Não foi possível carregar a imagem");
            
        }

        cadButton = new JButton("Cadastro de usuário"); // Criação de um botão
        //tabelaButton = new JButton("Gerenciamento de usuário");

        panelB = new JPanel();
        panelB.add(cadButton);
        //panelB.add(tabelaButton); 

        // principal
        add(imgLabel, BorderLayout.CENTER);
        add(panelB, BorderLayout.SOUTH); // Adicionando tudo
        
        // Adicionando ação aos dois botões
        cadButton.addActionListener(new ActionListener() { // Quando um botão ou outro componente interativo é clicado ou ativado, um evento é gerado
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioController controller = new UsuarioController();
                controller.iniciar();
            }
        });

        // Configurações da janela principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600); // set frame size
        setVisible(true); // display frame
        setResizable(false); // impede que o usuário ajuste o tamanho da janela
        setLocationRelativeTo(null); // deixa o JFrame centralizada na tela do computador
    } // fim do construtor

} // fim da classe
