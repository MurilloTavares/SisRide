package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.DAOIF;
import modelo.Lugar;

public class LugarDAO implements DAOIF<Lugar>{
    
    Connection connection;
    
    public LugarDAO(){
        connection = new Conexao().getConn();
    }
    
    public LugarDAO(String url, String user, String pass){
        connection = new Conexao(url,user,pass).getConn();
    }

    public int getLastID(){
        try{
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("select MAX(id) from Lugar");
            rs.next();
            int id = rs.getInt("MAX")+1;
            stat.close();
            return id;
        } catch (SQLException ex) {
            System.out.println("Erro na consulta");
            return -1;
        } 
    }
    
    private PreparedStatement setLugar(Lugar l, PreparedStatement stat) throws SQLException {
        try {
            if(l.getId()<0){
                l.setId(getLastID());                
            }
            stat.setInt(1, l.getId());
            stat.setString(2, l.getEstado());
            stat.setString(3, l.getCidade());
            stat.setString(4, l.getRua());
            stat.setInt(5, l.getNumero());
            stat.setString(6, l.getNome());
            stat.setString(7, l.getDescricao());
            
            return stat;
            
        } catch (SQLException ex) {
            l.setId(-1);
            throw new SQLException(ex);
        }
    }
    
    @Override
    public void add(Lugar l) throws SQLException {
        String sql = "INSERT INTO Lugar(id, estado, cidade, rua, numero,"
                   + "nome, descricao)"
                   + "Values(?,?,?,?,?,?,?)";
        PreparedStatement stat = connection.prepareStatement(sql);
        
        try {
            setLugar(l, stat);
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    @Override
    public void update(Lugar l, Lugar novo) throws SQLException {
        String sql = "UPDATE Lugar SET id = ?, estado = ?, cidade = ?, rua = ?,"
                   + "numero = ?, nome = ?, descricao =?"
                   + "WHERE id = ?";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            setLugar(novo, stat);
            stat.setInt(8, l.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    @Override    
    public void delete(Lugar  l) throws SQLException {
        String sql = "DELETE FROM Lugar WHERE id = ?";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            stat.setInt(1, l.getId());
            stat.executeUpdate();
            stat.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    public Lugar read(int id) throws SQLException {
        Lugar l = null;
        
        String sql = "SELECT * FROM Lugar WHERE id = ?";
        PreparedStatement stat = connection.prepareStatement(sql);
        try {
            stat.setInt(1, id);            
            ResultSet rs = stat.executeQuery();            
            if(rs.next()){
                l = new Lugar(
                        rs.getInt("id"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getString("nome"),
                        rs.getString("descricao"));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
            return l;
        }
    
}
}
