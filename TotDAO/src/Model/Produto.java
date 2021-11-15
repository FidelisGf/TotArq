package Model;

import java.time.Instant;

public class Produto {
    private int Id;
    private String Nome;
    private float Preco;
    private int IdCategoria;
    private Instant Data;
    private int Quantidade;

    public Instant getData() {
        return Data;
    }

    public void setData(Instant data) {
        Data = data;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public int getId() {
        return Id;
    }

    public Produto(String nome, float preco, int idCategoria, int quantidade) {
        Nome = nome;
        Preco = preco;
        IdCategoria = idCategoria;
        Quantidade = quantidade;
    }

    public Produto(int id, String nome, float preco, int idCategoria) {
        Id = id;
        Nome = nome;
        Preco = preco;
        IdCategoria = idCategoria;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        IdCategoria = idCategoria;
    }

    public Produto(String nome) {
        Nome = nome;
    }

    public Produto(String nome, float preco) {
        Nome = nome;
        Preco = preco;
    }

    public Produto(String nome, float preco, int idCategoria) {
        Nome = nome;
        Preco = preco;
        IdCategoria = idCategoria;
    }

    public Produto(float preco) {
        Preco = preco;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public float getPreco() {
        return Preco;
    }

    public void setPreco(float preco) {
        Preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "Id=" + Id +
                ", Nome='" + Nome + '\'' +
                ", Preco=" + Preco +
                ", IdCategoria=" + IdCategoria +
                '}';
    }

    public Produto() {
    }
}
