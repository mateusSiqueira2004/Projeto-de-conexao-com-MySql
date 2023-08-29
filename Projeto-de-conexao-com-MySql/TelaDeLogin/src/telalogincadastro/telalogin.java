package telalogincadastro;

/**
 *
 * @author mateus.ssalomao
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class telalogin extends JFrame{
    private final JPanel tela;
    private JTextField textinho;
    private JPasswordField passenha;
    private String tipo_usuario;
    
    public static String carregaNomeUsuario;
    public static String senhaadm;

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            try {
                telalogin login = new telalogin();
                login.setVisible(true);
            } catch(Exception e) {
            }
        });
    }
        
    @SuppressWarnings("deprecation")
	public telalogin() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(555, 260, 426, 212); /*configurações do JFrame*/
        
        tela = new JPanel();
        tela.setBackground(new Color(20,2,1));
        setContentPane(tela);
        tela.setLayout(null); /*configurações do JPanel*/
        
        JLabel lblIdentificacao = new JLabel("indentificacao");
        lblIdentificacao.setBounds(144, 0, 139, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        lblIdentificacao.setForeground(new Color(255,255,255));
        tela.add(lblIdentificacao); /*configurações do Titulo*/

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setForeground(new Color(255,255,255));
        lblUsuario.setBounds(24, 65, 70, 15);
        tela.add(lblUsuario); /*configurações do JLabel "Usuario"*/
        
        JLabel lblsenha = new JLabel("senha");
        lblsenha.setForeground(new Color(255,255,255));
        lblsenha.setBounds( 24, 92, 70, 15);
        tela.add(lblsenha); /*configurações do JLabel "senha"*/
        
        textinho = new JTextField();
        textinho.setBounds(112, 63, 219, 19);
        tela.add(textinho);
        textinho.setColumns(10); 
        
        passenha = new JPasswordField();
        passenha.setBounds(112, 90, 219, 19);
        tela.add(passenha);
        
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(260, 136, 100, 25);
        tela.add(btnEntrar);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(150, 136, 100, 25);
        tela.add(btnCadastrar);
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(40, 136, 100, 25);
        tela.add(btnSair);
         
        
        
        btnCadastrar.addActionListener((ActionEvent e) -> {
            new cadastro().setVisible(true);
            dispose();
        });
        
        btnSair.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        Conexao banco = new Conexao();
        
        btnEntrar.addActionListener((ActionEvent e) -> {
            
            if ((textinho.getText().isEmpty()) || (passenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");}
            
            else{
                
                
                int parada = 0;

                try{
                	carregaNomeUsuario = textinho.getText();
                    banco.AbrirConexao();
                    banco.stmt= banco.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    banco.resultset = banco.stmt.executeQuery("select *, 'normal' tipo from usuario union select *, 'admin' tipo from adm");
                                        
                    while(banco.resultset.next() && parada ==0){
                        tipo_usuario = banco.resultset.getString("tipo");
                        if(textinho.getText().equals(banco.resultset.getString("nome")) && 
                           passenha.getText().equals(banco.resultset.getString("senha"))){
                            System.out.println(tipo_usuario);
                            if (tipo_usuario.equals("admin")){
                                System.out.println("admin");
                                parada = 2;                                
                            }else{
                                System.out.println("normal");
                                parada = 1;                                
                            } 
                        }                        
                    }
                    System.out.println(parada);
                    
                    if (parada == 1) {
                        new TelaInicial().setVisible(true);
                        dispose();                    
                    }
                    else if(parada == 2){
                       senhaadm = passenha.getText();
                       new config().setVisible(true);
                       dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "usuario ou senha incorretos");
                    }
                    
                }catch(HeadlessException | SQLException ec) {
                    System.out.println("Erro ao consultar usuário " + ec.getMessage());
                }
            }        
        });
    }
}
