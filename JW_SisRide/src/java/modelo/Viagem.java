package modelo;

import java.sql.Time;
import java.util.Date;

public class Viagem {
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

    public Viagem(Usuario motorista, Lugar saida, Lugar chegada) {
        this.motorista = motorista;
        this.saida = saida;
        this.chegada = chegada;
    }

    public Viagem(Usuario motorista, Lugar saida, Lugar chegada, Date data, Time horario, Float valor, int vagas) {
        this.motorista = motorista;
        this.saida = saida;
        this.chegada = chegada;
        this.data = data;
        this.horario = horario;
        this.valor = valor;
        this.vagas = vagas;
    }

    public Viagem(int id, Usuario motorista, Lugar saida, Lugar chegada, Date data, Time horario, Float valor, int vagas, String descCarro, String descViagem) {
        this.id = id;
        this.motorista = motorista;
        this.saida = saida;
        this.chegada = chegada;
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
    
}
