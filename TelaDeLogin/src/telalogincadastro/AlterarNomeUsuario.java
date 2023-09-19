package telalogincadastro;
/**
*
* @author mateusSiqueira2004
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
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

public class AlterarNomeUsuario extends JFrame{
 
	private static final long serialVersionUID = 1L;
	private JPanel tela;
    private JPasswordField senhaatual;
    private JTextField nomenova;
    private String Novonome;
    private String tipo_usuario;
    
    @SuppressWarnings("deprecation")
	public AlterarNomeUsuario(){
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Alterar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(520, 260, 450, 200);

        tela = new JPanel();
        tela.setBackground(new Color(0,0,0));
        setContentPane(tela);
        tela.setLayout(null);
    
        JLabel lblIdentificacao = new JLabel("Alterar");
        lblIdentificacao.setBounds(144, 0, 139, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        lblIdentificacao.setForeground(new Color(255,255,255));
        tela.add(lblIdentificacao);

        JLabel lblUsuario = new JLabel("Senha Atual");
        lblUsuario.setForeground(new Color(255,255,255));
        lblUsuario.setBounds(15, 65, 150, 15);
        tela.add(lblUsuario);

        JLabel lblsenha = new JLabel("Novo Nome");
        lblsenha.setBounds( 15, 92, 150, 15);
        lblsenha.setForeground(new Color(255,255,255));
        tela.add(lblsenha);
        
        senhaatual = new JPasswordField();
        senhaatual.setBounds(170, 63, 219, 19);
        tela.add(senhaatual);

        nomenova = new JTextField();
        nomenova.setBounds(170, 90, 219, 19);
        tela.add(nomenova);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(270, 130, 117, 25);
        tela.add(btnAlterar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 130, 117, 25);
        tela.add(btnCancelar);
        
        Conexao banco = new Conexao();
                       
        btnCancelar.addActionListener((ActionEvent e) -> { 
            try{
                banco.AbrirConexao();
                banco.stmt= banco.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                banco.resultset = banco.stmt.executeQuery("select *, 'normal' tipo from usuario union select *, 'admin' tipo from adm");
                
                int parada = 0;
                
                while(banco.resultset.next() && parada ==0){
                        tipo_usuario = banco.resultset.getString("tipo");
                        if(telalogin.carregaNomeUsuario.equals(banco.resultset.getString("nome"))){
                            System.out.println(tipo_usuario);
                            if (tipo_usuario.equals("admin")){
                                System.out.println("admin");
                                parada = 2;                                
                            }
                            else{
                                System.out.println("normal");
                                parada = 1;                                
                            } 
                        }                        
                    }
                
                if (parada == 1) {
                    new TelaConfig().setVisible(true);
                    dispose();
                }else if(parada == 2){
                    new TelaConfig().setVisible(true);
                    dispose();
                }else { JOptionPane.showMessageDialog(null, "Senha incorreta");
                        }
                        banco.FecharConexao();
                }catch(HeadlessException | SQLException ec) {
                    System.out.println("Erro ao Alterar senha do Usuário " + ec.getMessage());
                }
        });
        
        
        btnAlterar.addActionListener((ActionEvent e) -> {
            senhaatual.getText();
            Novonome = nomenova.getText();
            
            if ((senhaatual.getText().isEmpty()) || (nomenova.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");}
            
            else{
                try{
                    banco.AbrirConexao();
                    banco.stmt= banco.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    banco.resultset = banco.stmt.executeQuery("select *, 'normal' tipo from usuario union select *, 'admin' tipo from adm");                   
                    int parada = 0;
                
                    while(banco.resultset.next() && parada == 0){
                        tipo_usuario = banco.resultset.getString("tipo");
                        if(telalogin.carregaNomeUsuario.equals(banco.resultset.getString("nome")) 
                       		 && senhaatual.getText().equals(banco.resultset.getString("senha"))){
	                        if (tipo_usuario.equals("admin")){
	                                 System.out.println("admin");
	                                 parada = 2;                                
	                        }else{
	                                 System.out.println("normal");
	                                 parada = 1;
	                        }
                        }
                    }
                             if (parada == 1) {
                                      try{
                                               PreparedStatement ps = banco.con.prepareStatement("update usuario set nome = ? where nome = ?");
                            
                                               ps.setString(1, Novonome);
                                               ps.setString(2, telalogin.carregaNomeUsuario);
                                               ps.execute();
                                               ps.close();
                                               JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso! ");
                                               new telalogin().setVisible(true);
                                               dispose();
                            
                                      }catch(HeadlessException | SQLException ec){
                                               System.out.println("Erro ao Alterar nome do Usuário "+ec.getMessage());
                                               JOptionPane.showMessageDialog(null, "Nome de Usuario já cadastrado");
                                      }
                        
                            }else if(parada == 2){
                                     try{
                                               PreparedStatement ps = banco.con.prepareStatement("update adm set nome = ? where nome = ?");
                            
                                               ps.setString(1, Novonome);
                                               ps.setString(2, telalogin.carregaNomeUsuario);
                                               ps.execute();
                                               ps.close();
                                               JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso! ");
                                               new telalogin().setVisible(true);
                                               dispose();
                            
                                    }catch(HeadlessException | SQLException ec){
                                               System.out.println("Erro ao Alterar nome do Usuário "+ec.getMessage());
                                               JOptionPane.showMessageDialog(null, "Nome de Usuario já cadastrado");
                                      }
                            }
                             banco.FecharConexao();
                }catch(HeadlessException | SQLException ec) {
                    System.out.println("Erro ao Alterar Usuário " + ec.getMessage());
                }
            }
        });
    }    
}