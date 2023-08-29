package telalogincadastro;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author mateus.ssalomao
 */
public class exclusao extends JFrame{
    
    private final JPanel tela;
    private JTextField coitado;
    private JPasswordField senhaadm;
    private String coitadinho;
    private String admconfirm;
    
public exclusao(){
    setLocationRelativeTo(null);
    setResizable(false);
    setTitle("excluir");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(520, 260, 450, 200);

    tela = new JPanel();
    tela.setBackground(new Color(0,0,0));
    setContentPane(tela);
    tela.setLayout(null);
    
    JLabel lblTitulo = new JLabel("Exclusão");
    lblTitulo.setBounds(144, 0, 139, 39);
    lblTitulo.setFont(new Font("Arial", 3, 19));
    lblTitulo.setForeground(new Color(255,255,255));
    tela.add(lblTitulo);

    JLabel lblUsuario = new JLabel("Usuario");
    lblUsuario.setForeground(new Color(255,255,255));
    lblUsuario.setBounds(15, 65, 150, 15);
    tela.add(lblUsuario);

    JLabel lblsenha = new JLabel("senha ADM");
    lblsenha.setBounds( 15, 92, 150, 15);
    lblsenha.setForeground(new Color(255,255,255));
    tela.add(lblsenha);

    coitado = new JTextField();
    coitado.setBounds(170, 63, 219, 19);
    tela.add(coitado);

    senhaadm = new JPasswordField();
    senhaadm.setBounds(170, 90, 219, 19);
    tela.add(senhaadm);


    JButton btnAlterar = new JButton("Excluir");
    btnAlterar.setBounds(270, 130, 117, 25);
    tela.add(btnAlterar);

    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.setBounds(50, 130, 117, 25);
    tela.add(btnCancelar);
        
    btnCancelar.addActionListener((ActionEvent e) -> { 
        new config().setVisible(true);
        dispose();
    });
        
    Conexao banco = new Conexao();
    btnAlterar.addActionListener((ActionEvent e) -> {
        coitadinho = coitado.getText();
            
            if ((coitado.getText().isEmpty()) || (senhaadm.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");}
            
            else{
                try{
                admconfirm = senhaadm.getText();
                banco.AbrirConexao();
                banco.stmt= banco.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                banco.resultset = banco.stmt.executeQuery("select * from usuario");

                int erigual = admconfirm.compareTo(telalogin.senhaadm);
          
                if (erigual == 0) {
                    try{                       
                            PreparedStatement ps = banco.con.prepareStatement("delete from usuario where nome = ?"); 
                            ps.setString(1, coitadinho);
                            ps.execute();
                            ps.close();
                            JOptionPane.showMessageDialog(null, "Usuario excluído!");
                            coitado.setText("");
                            senhaadm.setText("");
                        
                        }catch(HeadlessException | SQLException ec){
                            System.out.println("Erro ao Alterar senha do Usuário "+ec.getMessage());
                    }
                }
                else { JOptionPane.showMessageDialog(null, "Senha de ADM incorreta");
                        }
                        banco.FecharConexao();
                }catch(HeadlessException | SQLException ec) {
                    System.out.println("Erro ao Alterar senha do Usuário " + ec.getMessage());
                }
            }
        });
    }    
}
