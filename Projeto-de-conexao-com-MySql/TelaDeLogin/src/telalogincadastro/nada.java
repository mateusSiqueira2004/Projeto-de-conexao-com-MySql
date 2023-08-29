package telalogincadastro;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class nada extends JFrame{
    private final JPanel tela;
    
    public nada() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(" ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, 800, 350);
        
        tela = new JPanel();
        tela.setBackground(new Color(0,0,0));
        setContentPane(tela);
        tela.setLayout(null);
               
               
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(40, 250, 150, 25);
        tela.add(btnSair);
        
        btnSair.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
        } });
        
        JButton btnInteressante = new JButton("Aqui é mais interessante");
        btnInteressante.setBounds(450, 250, 250, 25);
        tela.add(btnInteressante);
        
        btnInteressante.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new TelaInicial().setVisible(true);
                dispose();
        } });        
    }
}