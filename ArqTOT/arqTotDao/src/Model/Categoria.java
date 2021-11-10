package Model;

public class Categoria {
    private int IdCategoria;
    private String NomeCategoria;

    public Categoria(String nomeCategoria) {
        NomeCategoria = nomeCategoria;
    }

    public Categoria() {
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        IdCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return NomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        NomeCategoria = nomeCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "IdCategoria=" + IdCategoria +
                ", NomeCategoria='" + NomeCategoria + '\'' +
                '}';
    }
}
