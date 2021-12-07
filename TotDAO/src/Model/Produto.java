package Model;

import java.time.Instant;
import java.util.List;

public class Produto {
    private int Id;
    private String Nome;
    private float Preco;
    private Categoria categoria;
    private Instant Data;
    private int Quantidade;
    private String Desc;
    private List<Estoque> insumos;


    public Produto(String nome, float preco, Categoria IdCategoria, int quantidade, String desc) {
        Nome = nome;
        Preco = preco;
        this.categoria = IdCategoria;
        Quantidade = quantidade;
        Desc = desc;
    }

    public Produto(int id, String nome, float preco, Categoria IdCategoria) {
        Id = id;
        Nome = nome;
        Preco = preco;
        this.categoria = IdCategoria;
    }


    public void setId(int id) {
        Id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Produto(String nome) {
        Nome = nome;
    }

    public Produto(String nome, float preco) {
        Nome = nome;
        Preco = preco;
    }

    public Produto(String nome, float preco, Categoria idCategoria) {
        Nome = nome;
        Preco = preco;
        this.categoria = idCategoria;
    }

    public Produto(String nome, float preco, String desc, Categoria idCategoria) {
        Nome = nome;
        Preco = preco;
        this.categoria = idCategoria;
        Desc = desc;
    }

    public Produto(String nome, float preco, Categoria categoria, int quantidade, String desc, List<Estoque> insumos) {
        Nome = nome;
        Preco = preco;
        this.categoria = categoria;
        Quantidade = quantidade;
        Desc = desc;
        this.insumos = insumos;
    }

    public List<Estoque> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<Estoque> insumos) {
        this.insumos = insumos;
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

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "Id=" + Id +
                ", Nome='" + Nome + '\'' +
                ", Preco=" + Preco +
                ", categoria=" + categoria +
                ", Data=" + Data +
                ", Quantidade=" + Quantidade +
                ", Desc='" + Desc + '\'' +
                '}';
    }




    public Produto() {
    }
}
