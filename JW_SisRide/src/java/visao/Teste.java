package visao;

import controle.EncodeDecode;
import controle.UsuarioDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

public class Teste {

    public static void main(String[] args) {
        
        UsuarioDAO uDao = new UsuarioDAO();
        
        Usuario joao = new Usuario("emailjoao","abracadabra","joao");
        Usuario maria = new Usuario("emailmaria","1234","maria","cajazeiras",new Date(2000,2,8),"nada","f");
        Usuario joao2 = new Usuario("emailjoao","itaquaquecetuba","joao");
        Usuario carlos = new Usuario("emailcarlos","abcd","carlos");
        try {
            uDao.add(joao);
            uDao.add(maria);
            uDao.update(joao, joao2);
            uDao.add(carlos);
            String scarlos = uDao.read("emailcarlos").toString();
            uDao.delete(carlos);
            System.out.println(scarlos);            
        } catch (SQLException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
