package modelo;

import controle.EncodeDecode;
import java.sql.Date;
import javax.swing.ImageIcon;

public class Usuario {

    private final String email;
    private String senha;

    private String nome;
    private ImageIcon foto;

    private String cidade;
    private Date nascimento;
    private String profissao;
    private String sexo;
    private Float nota;

    public Usuario(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
                
        foto = new ImageIcon("/imgPerfil/default.png");
        nota = 0f;
    }

    public Usuario(String email, String senha, String nome, String cidade, Date nascimento, String profissao, String sexo) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cidade = cidade;
        this.nascimento = nascimento;
        this.profissao = profissao;
        this.sexo = sexo;
        
        foto = new ImageIcon("/imgPerfil/default.png");
        nota = 0f;
    }

    public Usuario(String email, String senha, String nome, ImageIcon foto, String cidade, Date nascimento, String profissao, String sexo, Float nota) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.foto = foto;
        this.cidade = cidade;
        this.nascimento = nascimento;
        this.profissao = profissao;
        this.sexo = sexo;
        this.nota = nota;
    }  

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getSenhaEncoded() {
        return EncodeDecode.encode(senha);
    }

    public String getNome() {
        return nome;
    }

    public ImageIcon getFoto() {
        return foto;
    }
    
    public String getFotoPath(){
        return foto.getDescription();
    }

    public String getCidade() {
        return cidade;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public String getProfissao() {
        return profissao;
    }

    public String getSexo() {
        return sexo;
    }

    public Float getNota() {
        return nota;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", senha=" + senha + ", nome=" + nome + ", foto=" + foto + ", cidade=" + cidade + ", nascimento=" + nascimento + ", profissao=" + profissao + ", sexo=" + sexo + ", nota=" + nota + '}';
    }

    
    
}
