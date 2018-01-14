package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import modelo.DAOIF;
import modelo.Viagem;

public class ViagemDAO implements DAOIF<Viagem>{
    
    Connection connection;
    UsuarioDAO uDAO;
    LugarDAO lDAO;
    
    public ViagemDAO(){
        connection = new Conexao().getConn();
        uDAO = new UsuarioDAO();
        lDAO = new LugarDAO();
    }
    
    public ViagemDAO(String url, String user, String pass){
        connection = new Conexao(url,user,pass).getConn();
        uDAO = new UsuarioDAO(url,user,pass);
        lDAO = new LugarDAO(url,user,pass);
    }

    public int getLastID(){
        try{
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("select MAX(id) from Viagem");
            rs.next();
            int id = rs.getInt("MAX")+1;
            stat.close();
            return id;
        } catch (SQLException ex) {
            System.out.println("Erro na consulta");
            return -1;
        } 
    }
    
    private PreparedStatement setViagem(Viagem v, PreparedStatement stat) throws SQLException {
        
        try {
            if(v.getId()<0){v.setId(getLastID());}
            
            stat.setInt(1, v.getId());
            stat.setString(2, v.getMotorista().getEmail());
            stat.setInt(3, v.getSaida().getId());
            stat.setInt(4, v.getChegada().getId());
            stat.setDate(5, v.getData());
            stat.setTime(6, v.getHorario());
            stat.setFloat(7, v.getValor());
            stat.setInt(8, v.getVagas());
            stat.setString(9, v.getDescCarro());
            stat.setString(10, v.getDescViagem());
            
            return stat;
            
        } catch (SQLException ex) {
            v.setId(-1);
            throw new SQLException(ex);
        }
    }
    
    @Override
    public void add(Viagem v) throws SQLException {
        String sql = "INSERT INTO Viagem(id, motorista, saida, chegada, data, horario,"
                   + "valor, vagas, descCarro, descViagem)"
                   + "Values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stat = connection.prepareStatement(sql);
        
        try {
            setViagem(v, stat);
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    @Override
    public void update(Viagem v, Viagem novo) throws SQLException {
        String sql = "UPDATE Viagem SET id = ?, motorista = ?, saida = ?, chegada = ?,"
                   + " data = ?, horario = ?, valor = ?, vagas = ?, descCarro = ?, descViagem = ? "
                   + "WHERE id = ?";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            setViagem(novo, stat);
            stat.setInt(11, v.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }
    
    public void update(int i, Viagem novo) throws SQLException {
        String sql = "UPDATE Viagem SET id = ?, motorista = ?, saida = ?, chegada = ?,"
                   + " data = ?, horario = ?, valor = ?, vagas = ?, descCarro = ?, descViagem = ? "
                   + "WHERE id = ?";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            setViagem(novo, stat);
            stat.setInt(11, i);
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    @Override    
    public void delete(Viagem  v) throws SQLException {
        String sql = "DELETE FROM Viagem WHERE id = ?";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            stat.setInt(1, v.getId());
            stat.executeUpdate();
            stat.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    public Viagem read(int id) throws SQLException {
        Viagem v = null;
                
        String sql = "SELECT * FROM Viagem WHERE id = ?";
        PreparedStatement stat = connection.prepareStatement(sql);
        try {
            stat.setInt(1, id);            
            ResultSet rs = stat.executeQuery();            
            if(rs.next()){
                v = new Viagem(
                        rs.getInt("id"),
                        rs.getString("motorista"),
                        rs.getInt("saida"),
                        rs.getInt("chegada"),
                        rs.getDate("data"),
                        rs.getTime("horario"),
                        rs.getFloat("valor"),
                        rs.getInt("vagas"),
                        rs.getString("descCarro"),
                        rs.getString("descViagem"));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
            return v;
        }
    
    }    
    
}
