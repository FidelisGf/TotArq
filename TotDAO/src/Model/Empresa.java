package Model;

public class Empresa {
    private long id;
    private String Nome;
    private int cnpj;
    private String endereco;

    public Empresa(long id, String nome, int cnpj, String endereco) {
        this.id = id;
        Nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public Empresa(String nome, int cnpj, String endereco) {
        Nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public Empresa() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", Nome='" + Nome + '\'' +
                ", cnpj=" + cnpj +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
