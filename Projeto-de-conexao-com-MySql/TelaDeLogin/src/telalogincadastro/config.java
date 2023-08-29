package telalogincadastro;
/**
 *
 * @author mateus.ssalomao
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class config extends JFrame {
    private JPanel tela;
    private String tipo_usuario;

public config() {
    setLocationRelativeTo(null);
    setResizable(false);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    

    tela = new JPanel();
    tela.setBackground(new Color(0,0,0));
    setContentPane(tela);
    tela.setLayout(null);

    Conexao banco = new Conexao();
    
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
    setTitle("Configurações");                
    setBounds(600, 200, 250, 300);
    
    
    JLabel lblTitulo = new JLabel("Configurações");
    lblTitulo.setBounds(55, 20, 139, 39);
    lblTitulo.setFont(new Font("Arial", 3, 19));
    lblTitulo.setForeground(new Color(255,255,255));
    tela.add(lblTitulo);
    
    JButton btnAlterarUsuario = new JButton ("Mudar Usuario");
    btnAlterarUsuario.setBounds(50, 75, 150, 25);
    tela.add(btnAlterarUsuario);
    btnAlterarUsuario.addActionListener((ActionEvent e) -> {
        new Usuario().setVisible(true);
        dispose();
    });
    
    JButton btnAlterarSenha = new JButton ("Mudar Senha");
    btnAlterarSenha.setBounds(50, 150, 150, 25);
    tela.add(btnAlterarSenha);
    
    btnAlterarSenha.addActionListener((ActionEvent e) -> {
        new Alterar().setVisible(true);
        dispose();
        
    });
       
    JButton btnVoltar = new JButton("voltar");
    btnVoltar.setBounds(50, 225, 150, 25);
    tela.add(btnVoltar);
    btnVoltar.addActionListener((ActionEvent e) -> {
        new TelaInicial().setVisible(true);
        dispose();
        });
                }
else if(parada == 2){
    setTitle("Configurações de ADM");
    setBounds(600, 200, 250, 450);                    
                    
    JLabel lblTitulo = new JLabel("APENAS ADM");
    lblTitulo.setBounds(55, 20, 139, 39);
    lblTitulo.setFont(new Font("Arial", 3, 19));
    lblTitulo.setForeground(new Color(255,255,255));
    tela.add(lblTitulo);
    
    JButton btnAlterarUsuario = new JButton ("Mudar Usuario");
    btnAlterarUsuario.setBounds(50, 75, 150, 25);
    tela.add(btnAlterarUsuario);
    btnAlterarUsuario.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        new Usuario().setVisible(true);
        dispose();
        }
    });
    
    JButton btnAlterarSenha = new JButton ("Mudar Senha");
    btnAlterarSenha.setBounds(50, 150, 150, 25);
    tela.add(btnAlterarSenha);
    
    btnAlterarSenha.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        new Alterar().setVisible(true);
        dispose();
        }
    });
    
    JButton btnExclusao = new JButton ("Excluir um Usuario");
    btnExclusao.setBounds(50, 225, 150, 25);
    tela.add(btnExclusao);
    
    btnExclusao.addActionListener((ActionEvent e) -> {
        new exclusao().setVisible(true);
        dispose();
    });
    
    JButton btnCadastro = new JButton("Cadastrar ADM");
    btnCadastro.setBounds(50, 300, 150, 25);
    tela.add(btnCadastro);
    btnCadastro.addActionListener((ActionEvent e) -> {
        new cadastraradm().setVisible(true);
        dispose();
    });
    
    JButton btnVoltar = new JButton("voltar");
    btnVoltar.setBounds(50, 375, 150, 25);
    tela.add(btnVoltar);
    btnVoltar.addActionListener((ActionEvent e) -> {
        new telalogin().setVisible(true);
        dispose();
            
        });
                }else { JOptionPane.showMessageDialog(null, "Senha incorreta");
                        }
                        banco.FecharConexao();
                }catch(HeadlessException | SQLException ec) {
                    System.out.println("Erro ao Alterar senha do Usuário " + ec.getMessage());
                }
    
           
    }
}
