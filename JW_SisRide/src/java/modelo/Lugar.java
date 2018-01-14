package modelo;

public class Lugar {
    
    //id = -1 para gerar um id automatico
    private int id;
    
    private String estado;
    private String cidade;
    private String rua;
    private int numero;
    
    private String nome;
    private String descricao;

    public Lugar(int id, String estado, String cidade, String rua, int numero) {
        this(id, estado, cidade, rua, numero, null, null);
    }

    public Lugar(int id, String estado, String cidade, String rua, int numero, String nome, String descricao) {
        this.id = id;        
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.nome = nome;
        this.descricao = descricao;
    }
        
    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }    
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Lugar{" + "id=" + id + ", estado=" + estado + ", cidade=" + cidade + ", rua=" + rua + ", numero=" + numero + ", nome=" + nome + ", descricao=" + descricao + '}';
    }
    
    
    
}
