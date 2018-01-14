package modelo;

import controle.LugarDAO;
import controle.UsuarioDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class Viagem {
    LugarDAO lDAO = new LugarDAO();
    UsuarioDAO uDAO = new UsuarioDAO();
    
    private int id;
    private Usuario motorista;
    
    private Lugar saida;
    private Lugar chegada;
    
    private Date data;
    private Time horario;    
    private Float valor;
    private int vagas;
    
    private String descCarro;
    private String descViagem;

    public Viagem(int id, String motoristaEmail, int saidaID, int chegadaID) throws SQLException {
        this(id, motoristaEmail, saidaID, chegadaID, null, null, 0f, 0, null, null);        
    }

    public Viagem(int id, String motoristaEmail, int saidaID, int chegadaID, Date data, Time horario, Float valor, int vagas, String descCarro, String descViagem) throws SQLException {
        this.id = id;
        this.motorista = uDAO.read(motoristaEmail);
        this.saida = lDAO.read(saidaID);
        this.chegada = lDAO.read(chegadaID);
        this.data = data;
        this.horario = horario;
        this.valor = valor;
        this.vagas = vagas;
        this.descCarro = descCarro;
        this.descViagem = descViagem;
    }

    public int getId() {
        return id;
    }

    public Usuario getMotorista() {
        return motorista;
    }

    public Lugar getSaida() {
        return saida;
    }

    public Lugar getChegada() {
        return chegada;
    }

    public Date getData() {
        return data;
    }

    public Time getHorario() {
        return horario;
    }

    public Float getValor() {
        return valor;
    }

    public int getVagas() {
        return vagas;
    }

    public String getDescCarro() {
        return descCarro;
    }

    public String getDescViagem() {
        return descViagem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMotorista(Usuario motorista) {
        this.motorista = motorista;
    }

    public void setSaida(Lugar saida) {
        this.saida = saida;
    }

    public void setChegada(Lugar chegada) {
        this.chegada = chegada;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public void setDescCarro(String descCarro) {
        this.descCarro = descCarro;
    }

    public void setDescViagem(String descViagem) {
        this.descViagem = descViagem;
    }

    @Override
    public String toString() {
        return "Viagem{" + "id=" + id + ", motorista=" + motorista + ", saida=" + saida + ", chegada=" + chegada + ", data=" + data + ", horario=" + horario + ", valor=" + valor + ", vagas=" + vagas + ", descCarro=" + descCarro + ", descViagem=" + descViagem + '}';
    }
        
}
