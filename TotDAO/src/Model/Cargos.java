package Model;

public class Cargos {
    private String nomeCargo;
    private int id;
    private float SalarioDoCargo;

    public Cargos(int id, String nomeCargo,float salarioDoCargo) {
        this.nomeCargo = nomeCargo;
        this.id = id;
        SalarioDoCargo = salarioDoCargo;
    }

    public Cargos(String nomeCargo, float salarioDoCargo) {
        this.nomeCargo = nomeCargo;
        SalarioDoCargo = salarioDoCargo;
    }

    public float getSalarioDoCargo() {
        return SalarioDoCargo;
    }

    public void setSalarioDoCargo(float salarioDoCargo) {
        SalarioDoCargo = salarioDoCargo;
    }

    public Cargos(String nomeCargo, int id) {
        this.nomeCargo = nomeCargo;
        this.id = id;
    }

    public Cargos() {
    }


    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cargos{" +
                ", id=" + id +
                "nomeCargo='" + nomeCargo + '\'' +
                ", SalarioDoCargo=" + SalarioDoCargo +
                '}';
    }
}
