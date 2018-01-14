package visao;

import controle.Conexao;
import controle.EncodeDecode;
import controle.LugarDAO;
import controle.UsuarioDAO;
import controle.ViagemDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Lugar;
import modelo.Usuario;
import modelo.Viagem;

public class Teste {

    public static void main(String[] args) {
        Conexao con = new Conexao();
        
//        UsuarioDAO uDao = new UsuarioDAO();
//        
//        Usuario joao = new Usuario("emailjoao","abracadabra","joao");
//        Usuario maria = new Usuario("emailmaria","1234","maria","cajazeiras",new Date(2000,2,8),"nada","f");
//        Usuario joao2 = new Usuario("emailjoao","itaquaquecetuba","joao");
//        Usuario carlos = new Usuario("emailcarlos","abcd","carlos");
//        try {
//            uDao.add(joao);
//            uDao.add(maria);
//            uDao.update(joao, joao2);
//            uDao.add(carlos);
//            String scarlos = uDao.read("emailcarlos").toString();
//            uDao.delete(carlos);
//            System.out.println(scarlos);            
//        } catch (SQLException ex) {
//            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        LugarDAO lDao = new LugarDAO();
//                
//        try {
//            Lugar lugarA = new Lugar(-1, "estado","cidade2","ruas2",1);
//            Lugar lugarB = new Lugar(2,"estadoB","cidadeB","ruaB",2);
//            Lugar lugarC = new Lugar(3,"estadoC","cidadeC","ruaC",3);
//            Lugar lugarD = new Lugar(4,"estadoD","cidadeD","ruaD",4);
//            System.out.println(lugarA);
//            lDao.add(lugarA);
//            System.out.println(lugarA);
//            lDao.add(lugarB);
//            lDao.update(lugarA, lugarC);
//            lDao.add(lugarD);
//            String sLugarD = lDao.read(lugarD.getId()).toString();
//            lDao.delete(lugarD);
//            System.out.println(sLugarD); 
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        UsuarioDAO uDAO = new UsuarioDAO();
        LugarDAO lDAO= new LugarDAO();
        ViagemDAO vDAO = new ViagemDAO();
        
        Usuario joao = new Usuario("emailJoao","senhaJoao","Joao Alves");
        Lugar saida = new Lugar(-1,"paraiba","cajazeiras", "rua dos trouxas", 0);
        Lugar chegada = new Lugar(-1,"Tokyo", "konoha", "praça da liberdade", 666);
        Viagem novaViagem = null;
        
        try {
            uDAO.add(joao);
            lDAO.add(saida);
            lDAO.add(chegada);
            Viagem viagem = new Viagem(-1,joao.getEmail(),saida.getId(),chegada.getId());
            vDAO.add(viagem);
            novaViagem = vDAO.read(viagem.getId());
            System.out.println("novaViagem: " + novaViagem);
            
        } catch (SQLException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
}
