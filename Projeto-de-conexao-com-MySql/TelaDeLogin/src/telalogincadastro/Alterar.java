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

public class Alterar extends JFrame{
    private JPanel tela;
    private JPasswordField senhaatual;
    private JPasswordField senhanova;
    private JPasswordField confirsenha;
    private String Confirm;
    private String Novasenha;
    private String Novaconfsenha;
    private String tipo_usuario;

     
    public Alterar(){
        
        
        
        
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Alterar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 450, 250);

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

        JLabel lblsenha = new JLabel("Nova Senha");
        lblsenha.setBounds( 15, 92, 150, 15);
        lblsenha.setForeground(new Color(255,255,255));
        tela.add(lblsenha);

        JLabel lblconfirsenha = new JLabel("Confirme Nova Senha");
        lblconfirsenha.setForeground(new Color(255,255,255));
        lblconfirsenha.setBounds( 15, 119, 150, 15);
        tela.add(lblconfirsenha);

        senhaatual = new JPasswordField();
        senhaatual.setBounds(170, 63, 219, 19);
        tela.add(senhaatual);

        senhanova = new JPasswordField();
        senhanova.setBounds(170, 90, 219, 19);
        tela.add(senhanova);

        confirsenha = new JPasswordField();
        confirsenha.setBounds(170, 117, 219, 19);
        tela.add(confirsenha);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(270, 170, 117, 25);
        tela.add(btnAlterar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 170, 117, 25);
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
                            }else{
                                System.out.println("normal");
                                parada = 1;                                
                            } 
                        }                        
                    }
                if (parada == 1) {
                    new config().setVisible(true);
                    dispose();
                }else if(parada == 2){
                    
                    new config().setVisible(true);
                    dispose();
                }else { JOptionPane.showMessageDialog(null, "Senha incorreta");
                        }
                        banco.FecharConexao();
                }catch(HeadlessException | SQLException ec) {
                    System.out.println("Erro ao Alterar senha do Usuário " + ec.getMessage());
                }
        });
        
        
        btnAlterar.addActionListener((ActionEvent e) -> {
        Confirm = senhaatual.getText();
        Novasenha = senhanova.getText();
        Novaconfsenha = confirsenha.getText();
            
            if ((senhaatual.getText().isEmpty()) || (senhanova.getText().isEmpty()) || (confirsenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");}
            
            else{
                try{
                              
                banco.AbrirConexao();
                banco.stmt= banco.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                banco.resultset = banco.stmt.executeQuery("select *, 'normal' tipo from usuario union select *, 'admin' tipo from adm");
               
                int parada = 0;
                
                while(banco.resultset.next() && parada ==0){
                        tipo_usuario = banco.resultset.getString("tipo");
                        if(telalogin.carregaNomeUsuario.equals(banco.resultset.getString("nome")) && 
                           senhaatual.getText().equals(banco.resultset.getString("senha"))){
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
                if (parada == 1) {
                    int ehigual = Novasenha.compareTo(Novaconfsenha);
                    if(ehigual != 0){
                        JOptionPane.showMessageDialog(null, "senhas não conferem");
                    }else {
                        try{                       
                            PreparedStatement ps = banco.con.prepareStatement("update usuario set senha = ? where senha = ? and nome = ?");
                                                                                    
                            ps.setString(1, Novasenha);
                            ps.setString(2, Confirm);
                            ps.setString(3, telalogin.carregaNomeUsuario);
                            ps.execute();
                            ps.close();
                            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso! ");
                            new telalogin().setVisible(true);
                            dispose();
                        
                        
                        }catch(HeadlessException | SQLException ec){
                            System.out.println("Erro ao Alterar senha do Usuário "+ec.getMessage());
                    }
                }
                }else if(parada == 2){
                    int ehigual = Novasenha.compareTo(Novaconfsenha);
                    if(ehigual != 0){
                        JOptionPane.showMessageDialog(null, "senhas não conferem");
                    }else {
                        try{                       
                            PreparedStatement ps = banco.con.prepareStatement("update adm set senha = ? where senha = ? and nome = ?");
                                                                                    
                            ps.setString(1, Novasenha);
                            ps.setString(2, Confirm);
                            ps.setString(3, telalogin.carregaNomeUsuario);
                            ps.execute();
                            ps.close();
                            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso! ");
                            new telalogin().setVisible(true);
                            dispose();
                        
                        
                        }catch(HeadlessException | SQLException ec){
                            System.out.println("Erro ao Alterar senha do Usuário "+ec.getMessage());
                    }
                }
                
                }else { JOptionPane.showMessageDialog(null, "Senha incorreta");
                        }
                        banco.FecharConexao();
                }catch(HeadlessException | SQLException ec) {
                    System.out.println("Erro ao Alterar senha do Usuário " + ec.getMessage());
                }
            }
        });
    }    
}
