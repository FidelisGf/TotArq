package Model;

public class Produto {
    private int IdProduto;
    private String nomeProduto;
    private Float ValorProduto;
    private int quantidadeProduto;

    public Produto(String nomeProduto, Float valorProduto, int quantidadeProduto) {
        this.nomeProduto = nomeProduto;
        ValorProduto = valorProduto;
        this.quantidadeProduto = quantidadeProduto;
    }

    public Produto() {
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int idProduto) {
        IdProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Float getValorProduto() {
        return ValorProduto;
    }

    public void setValorProduto(Float valorProduto) {
        ValorProduto = valorProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "IdProduto=" + IdProduto +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", ValorProduto=" + ValorProduto +
                ", quantidadeProduto=" + quantidadeProduto +
                '}';
    }
}