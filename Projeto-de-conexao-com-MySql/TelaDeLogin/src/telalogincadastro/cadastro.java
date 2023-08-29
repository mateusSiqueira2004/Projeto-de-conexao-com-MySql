package telalogincadastro;
/**
 *
 * @author mateus.ssalomao
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cadastro extends JFrame{
    private final JPanel tela;
    private JTextField textinho;
    private JTextField nome;
    private JTextField senha;
    private JPasswordField passenha;
    private JPasswordField confirsenha;
    private String nomeUsuario;
    private String senhaUsuario;
    private String confsenhaUsuario;

    public cadastro() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 450, 250);

        tela = new JPanel();
        tela.setBackground(new Color(0,0,0));
        setContentPane(tela);
        tela.setLayout(null);

        JLabel lblIdentificacao = new JLabel("Cadastrar");
        lblIdentificacao.setBounds(144, 0, 139, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        lblIdentificacao.setForeground(new Color(255,255,255));
        tela.add(lblIdentificacao);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setForeground(new Color(255,255,255));
        lblUsuario.setBounds(15, 65, 70, 15);
        tela.add(lblUsuario);

        JLabel lblsenha = new JLabel("senha");
        lblsenha.setBounds( 15, 92, 70, 15);
        lblsenha.setForeground(new Color(255,255,255));
        tela.add(lblsenha);

        JLabel lblconfirsenha = new JLabel("confirme a senha");
        lblconfirsenha.setForeground(new Color(255,255,255));
        lblconfirsenha.setBounds( 15, 119, 110, 15);
        tela.add(lblconfirsenha);

        textinho = new JTextField();
        textinho.setBounds(150, 63, 219, 19);
        tela.add(textinho);
        textinho.setColumns(10);

        passenha = new JPasswordField();
        passenha.setBounds(150, 90, 219, 19);
        tela.add(passenha);

        confirsenha = new JPasswordField();
        confirsenha.setBounds(150, 117, 219, 19);
        tela.add(confirsenha);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(270, 170, 117, 25);
        tela.add(btnCadastrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 170, 117, 25);
        tela.add(btnCancelar);

        btnCancelar.addActionListener((ActionEvent e) -> {
            new telalogin().setVisible(true);
            dispose();
        });

        Conexao banco = new Conexao();

        btnCadastrar.addActionListener((ActionEvent e) -> {
            nomeUsuario = textinho.getText();
            senhaUsuario = passenha.getText();
            confsenhaUsuario = confirsenha.getText();
            int ehigual = senhaUsuario.compareTo(confsenhaUsuario);
            
            if ((textinho.getText().isEmpty()) || (passenha.getText().isEmpty()) || (confirsenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");}
            if(ehigual != 0){
                JOptionPane.showMessageDialog(null, "senhas não conferem");
            }
            else {
                try{
                    banco.AbrirConexao();
                    try (PreparedStatement pa = banco.con.prepareStatement("insert into usuario(nome,senha) value(?,?)")) {
                        pa.setString(1, nomeUsuario);
                        pa.setString(2, senhaUsuario);
                        pa.execute();
                    }
                    JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso! ");
                    banco.FecharConexao();
                    new telalogin().setVisible(true);
                    dispose();
                }catch(HeadlessException | SQLException ec){
                    JOptionPane.showMessageDialog(null, "Nome de Usuario já cadastrado");
                }
            }
        });
    }
}
