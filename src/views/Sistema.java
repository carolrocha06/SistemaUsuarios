package views; // Onde o arquivo está (em qual pasta)

// importações necessárias para criar o JFrame
import java.awt.*; // manipula layout e componentes graficos
import javax.swing.*; // componentes swing como JLabel, JButton e outros
import controllers.UsuarioController; // cria uma instancia posteriormente
import java.awt.event.ActionEvent; // cliques de botão, por exemplo
import java.awt.event.ActionListener; // necessário para o ActionListener- trata eventos
import java.net.URL; // importa a URL para carregar imagens

public class Sistema extends JFrame {
    // declarando os componentes da interface gráfica:
    private JLabel imgLabel; // exibe uma imagem
    private JButton cadButton; // Botão de cadastro
    private JPanel panelB; // painel que organiza os botões

    public Sistema() { // Construtor da classe
        super("Tela Principal- Sistema de Usuarios"); // título da janela
        
        imgLabel = new JLabel();

        URL imgUrl = getClass().getResource("/assets/imagem.jpeg"); 
        // carrega  a imagem pelo caminho que sai do diretorio atual e vai para o "assets"
        
        if(imgUrl != null){ // Verifica se a imagem foi encontrada pelo caminho dado
            Icon iconImagem = new ImageIcon(imgUrl);  // cria um icone com a imagem
            imgLabel.setIcon(iconImagem); // icone é adicionado ao label
        } else { // senao dá a mensagem de erro
            System.out.println("Não foi possível carregar a imagem");
            
        }

        cadButton = new JButton("Cadastro de usuario"); // Criação de um botão
        //tabelaButton = new JButton("Gerenciamento de usuário");

        panelB = new JPanel(); 
        panelB.add(cadButton); // adiciona o botão ao painelB
        //panelB.add(tabelaButton); 

        // principal
        add(imgLabel, BorderLayout.CENTER); // imagem (ou melhor, o label) ficará no centro
        add(panelB, BorderLayout.SOUTH); // painelB no sul da janela
        
        // Adicionando ação aos dois botões
        cadButton.addActionListener(new ActionListener() { // Quando um botão ou outro componente interativo é clicado ou ativado, um evento é gerado
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioController controller = new UsuarioController(); // instancia do controlador de usuarios
                controller.iniciar();
            }
        });

        // Configurações da janela principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // encerra a aplicação ao fechar a janela
        setSize(1100, 600); // set frame size
        setVisible(true); // display frame
        setResizable(false); // impede que o usuário ajuste o tamanho da janela
        setLocationRelativeTo(null); // deixa o JFrame centralizada na tela do computador
    } // fim do construtor

} // fim da classe
