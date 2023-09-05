package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Diretor {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private int idDiretor;
    
    @Column (length = 50, nullable = false)
    private String nome;

    public Diretor() {
    }

    public Diretor(String nome) {
        this.nome = nome;
    }

    public Diretor(int idDiretor, String nome) {
        this.idDiretor = idDiretor;
        this.nome = nome;
    }

    public int getIdDiretor() {
        return idDiretor;
    }

    public void setIdDiretor(int idDiretor) {
        this.idDiretor = idDiretor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
