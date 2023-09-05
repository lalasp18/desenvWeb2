package domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Classe {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private int idClasse;
    
    @Column (length = 50, nullable = false)
    private String nome;
    
    @Column (nullable = false)
    private double valor;
    
    @Column (nullable = false)
    private Date dataDevolucao;

    public Classe() {
    }

    public Classe(String nome, double valor, Date dataDevolucao) {
        this.nome = nome;
        this.valor = valor;
        this.dataDevolucao = dataDevolucao;
    }

    public Classe(int idClasse, String nome, double valor, Date dataDevolucao) {
        this.idClasse = idClasse;
        this.nome = nome;
        this.valor = valor;
        this.dataDevolucao = dataDevolucao;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
