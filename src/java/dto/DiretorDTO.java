package dto;

public class DiretorDTO {
    private int id;
    private String nome;

    public DiretorDTO() {
    }

    public DiretorDTO(String nome) {
        this.nome = nome;
    }

    public DiretorDTO(int id, String nome) {
        this.id = id;
        this.nome = nome;
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
    
}
