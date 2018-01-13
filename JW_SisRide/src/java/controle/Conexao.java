package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/**
 *
 * @author Murillo
 */
public class Conexao {

    String url;
    String user;
    String pass;
    Connection conn;
    RowSetFactory factory;

    public Conexao(String url, String user, String pass) {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);

            factory = RowSetProvider.newFactory();
            
            System.out.println("Conexao feita com sucesso");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro na Conexao.");
        }
    }
    
    public Conexao() {
        url = "jdbc:postgresql://localhost:5432/SisRide";
        user = "postgres";
        pass = "1234";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);

            factory = RowSetProvider.newFactory();
            
            System.out.println("Conexao feita com sucesso");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro na Conexao.");
        }
        
    }
    
    public int executaUpdate(String sql){
        try {
            Statement stat = conn.createStatement();
            int result = stat.executeUpdate(sql);
            stat.close();
            conn.close();
            System.out.println("Tuplas afetadas: "+result);
            return result;
        } catch (SQLException ex) {
            System.out.println("Erro no update");
            return 0;
        }
    }
    
    public ResultSet executaConsulta(String sql){
        try{
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(sql);
            conn.close();
            return result;
        } catch (SQLException ex) {
            System.out.println("Erro na consulta");
            return null;
        }
    }
    
    public Connection getConn() {
        return conn;
    }
    
}
