package Model;

public class Funcionario {
    private int id;
    private String nome;
    private Integer idade;
    private String endereco;
    private int idCargo;
    private String cargo;
    private float salario;
    private String senha;


    public Funcionario(int id, String nome, int idade, String endereco, int idCargo, String cargo, float salario) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.idCargo = idCargo;
        this.cargo = cargo;
        this.salario = salario;
    }


    public Funcionario(String nome, int idade, String endereco, int idCargo, String cargo, float salario) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.idCargo = idCargo;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario(String nome, int idade, String endereco, String cargo, float salario) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario() {
    }

    public Funcionario(String nome, int idade, String endereco, int idCargo) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.idCargo = idCargo;
    }

    public Funcionario(String nome, int idade, String endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public Funcionario(int id, String nome, int idade, String endereco, int idCargo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.idCargo = idCargo;
    }

    public Funcionario(String nome, int idade, String endereco, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.senha = senha;
    }

    public Funcionario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Funcionario(String nome, int idade, String endereco, int idCargo, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.idCargo = idCargo;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario : " +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", endereco='" + endereco + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario
                ;
    }
}
