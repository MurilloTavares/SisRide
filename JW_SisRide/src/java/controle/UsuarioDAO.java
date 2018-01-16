package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import modelo.DAOIF;
import modelo.Usuario;

public class UsuarioDAO implements DAOIF<Usuario>{

    Connection connection;
    
    public UsuarioDAO(){
        connection = new Conexao().getConn();
    }
    
    public UsuarioDAO(String url, String user, String pass){
        connection = new Conexao(url,user,pass).getConn();
    }

    private PreparedStatement setUsuario(Usuario u, PreparedStatement stat) throws SQLException {
            stat.setString(1, u.getEmail());
            stat.setString(2, u.getSenhaEncoded());
            stat.setString(3, u.getNome());
            stat.setString(4, u.getFotoPath());
            stat.setString(5, u.getCidade());
            stat.setDate  (6, u.getNascimento());
            stat.setString(7, u.getProfissao());
            stat.setString(8, u.getSexo());
            stat.setFloat (9, u.getNota());
            
            return stat;
    }
    
    
    @Override
    public void add(Usuario u) throws SQLException {
        String sql = "INSERT INTO Usuario(email, senha, nome, foto, cidade,"
                   + "nascimento, profissao, sexo, nota)"
                   + "Values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stat = connection.prepareStatement(sql);
        
        try {
            setUsuario(u, stat);
            stat.executeUpdate();
        } finally {
            stat.close();
        }
    }

    @Override
    public void update(Usuario u, Usuario novo) throws SQLException {
        String sql = "UPDATE Usuario SET email = ?, senha = ?, nome = ?, foto = ?,"
                   + "cidade = ?, nascimento = ?, profissao = ?, sexo = ?, nota = ?"
                   + "WHERE email = ?";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            setUsuario(novo, stat);
            stat.setString(10, u.getEmail());
            stat.executeUpdate();
        } finally {
            stat.close();
        }
    }

    @Override    
    public void delete(Usuario u) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE email = ? and senha = ?";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            stat.setString(1, u.getEmail());
            stat.setString(2, u.getSenhaEncoded());
            stat.executeUpdate();
            stat.close();
        } finally {
            stat.close();
        }
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException{
        Usuario u = null;
        if(rs.next()){
                u = new Usuario(
                        rs.getString("email"),
                        EncodeDecode.decode(rs.getString("senha")),
                        rs.getString("nome"),
                        new ImageIcon(rs.getString("foto")),
                        rs.getString("cidade"),
                        rs.getDate("nascimento"),
                        rs.getString("profissao"),
                        rs.getString("sexo"),
                        rs.getFloat ("nota"));
            }
        return u;
    }
    
    public Usuario read(String email) throws SQLException {      
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        PreparedStatement stat = connection.prepareStatement(sql);
        
        try {
            stat.setString(1, email);            
            ResultSet rs = stat.executeQuery();            
            return getUsuario(rs);
            
        } finally {
            stat.close();
        }
    }
    
    public Usuario autentica(String email, String senha) throws SQLException{        
        String sql = "SELECT * FROM Usuario WHERE email = ? and senha = ?";
        PreparedStatement stat = connection.prepareStatement(sql);
        
        try{
            stat.setString(1, email);
            stat.setString(2, EncodeDecode.encode(senha));
            ResultSet rs = stat.executeQuery();
            return getUsuario(rs);
        } finally {
            stat.close();
        }
        
    }
    
}
