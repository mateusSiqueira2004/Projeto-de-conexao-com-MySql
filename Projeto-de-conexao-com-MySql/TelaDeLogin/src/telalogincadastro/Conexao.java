package telalogincadastro;
/**
 *
 * @author mateus.ssalomao
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexao{

    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;
    String servidor = "jdbc:mysql://127.0.0.1:3306/Banco";
    String usuario  = "root";
    String senha = "";
    String driver = "com.mysql.jdbc.Driver";

    public Connection AbrirConexao() {
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection( servidor, usuario, senha);
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("erro ao acessar banco de dados");
            e.printStackTrace();
        }
        return con;
    }

    public void FecharConexao() {
        try {
            con.close();
        } catch (Exception e ) {
            System.out.println("Erro ao encerrar conexão " + e.getMessage());
        }
    }
}
