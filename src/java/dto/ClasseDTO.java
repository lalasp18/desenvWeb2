package dto;

import java.util.Date;

public class ClasseDTO {
    private int id;
    private String nome;
    private double valor;
    private Date data;

    public ClasseDTO() {
    }

    public ClasseDTO(String nome, double valor, Date dataDevolucao) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }

    public ClasseDTO(int id, String nome, double valor, Date dataDevolucao) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
