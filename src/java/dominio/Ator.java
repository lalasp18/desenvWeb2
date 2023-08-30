package dominio;

public class Ator {
    private int idAtor;
    private String nome;

    public Ator() {
    }

    public Ator(int idAtor, String nome) {
        this.idAtor = idAtor;
        this.nome = nome;
    }

    public int getIdAtor() {
        return idAtor;
    }

    public void setIdAtor(int idAtor) {
        this.idAtor = idAtor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
