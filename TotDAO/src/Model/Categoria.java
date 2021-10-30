package Model;

public class Categoria {
    private int Id;
    private String nome;
    private int IdEmpresa;
    private float SalarioDoCargo;

    public float getSalarioDoCargo() {
        return SalarioDoCargo;
    }

    public void setSalarioDoCargo(float salarioDoCargo) {
        SalarioDoCargo = salarioDoCargo;
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        IdEmpresa = idEmpresa;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria(int id, String nome) {
        Id = id;
        this.nome = nome;
    }


    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(int id, String nome, int idEmpresa) {
        Id = id;
        this.nome = nome;
        IdEmpresa = idEmpresa;
    }

    public Categoria() {
    }

    public Categoria(String nome, int idEmpresa) {
        this.nome = nome;
        IdEmpresa = idEmpresa;
    }


    @Override
    public String toString() {
        return "Categoria{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", IdEmpresa=" + IdEmpresa +
                '}';
    }
}
