package telalogincadastro;
/**
 *
 * @author mateus.ssalomao
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaInicial extends JFrame{
    private final JPanel tela;
    

    public TelaInicial() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, 800, 350);

        tela = new JPanel();
        tela.setBackground(new Color(0,0,0));
        setContentPane(tela);
        tela.setLayout(null);

        JLabel lblTitulo = new JLabel("Ablú");
        lblTitulo.setBounds(144, 0, 139, 39);
        lblTitulo.setFont(new Font("Arial", 3, 19));
        lblTitulo.setForeground(new Color(255,255,255));
        tela.add(lblTitulo);

        JLabel lblTextinho = new JLabel("O sabio sabia que o sabiá sabia assobia");
        lblTextinho.setForeground(new Color(255,255,255));
        lblTextinho.setBounds(15, 65, 450, 15);
        tela.add(lblTextinho);

        JLabel lblTextinho2 = new JLabel("Um ninho de mafagafos tinha sete mafagafinhos. Quem desmafagar o ninho de mafagafos bom desmafagafador será.");
        lblTextinho2.setForeground(new Color(255,255,255));
        lblTextinho2.setBounds(15, 100, 800, 15);
        tela.add(lblTextinho2);

        JLabel lblTextinho3 = new JLabel("A aranha arranha a rã. A rã arranha a aranha. Nem a aranha arranha a rã. Nem a rã arranha a aranha.");
        lblTextinho3.setForeground(new Color(255,255,255));
        lblTextinho3.setBounds(15, 135, 800, 15);
        tela.add(lblTextinho3);

        JLabel lblTextinho4 = new JLabel("Se percebeste, percebeste. Se não percebeste, faz que percebeste para que eu perceba que tu percebeste. Percebeste?");
        lblTextinho4.setForeground(new Color(255,255,255));
        lblTextinho4.setBounds(15, 170, 800, 15);
        tela.add(lblTextinho4);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(40, 250, 150, 25);
        tela.add(btnSair);

        btnSair.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        JButton btnVoltar = new JButton("voltar");
        btnVoltar.setBounds(300, 250, 150, 25);
        tela.add(btnVoltar);
        
        btnVoltar.addActionListener((ActionEvent e) -> { 
            new telalogin().setVisible(true);
            dispose();
        });

        JButton btnProximo = new JButton("Nada");
        btnProximo.setBounds(550, 250, 150, 25);
        tela.add(btnProximo);

        btnProximo.addActionListener((ActionEvent e) -> {
            new nada().setVisible(true);
            dispose();
        });
        
        JButton btnConfig = new JButton("Config");
        btnConfig.setBounds(650, 20, 100, 25);
        tela.add(btnConfig);  
               
        btnConfig.addActionListener((ActionEvent e) -> {
            new config().setVisible(true);
            dispose();
        });
        
    }  
}
