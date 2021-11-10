package Model;

public class Produto {
    private int IdProduto;
    private String nomeProduto;
    private Float ValorProduto;

    public Produto(String nomeProduto, Float valorProduto) {
        this.nomeProduto = nomeProduto;
        ValorProduto = valorProduto;
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

    @Override
    public String toString() {
        return "Produto{" +
                "IdProduto=" + IdProduto +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", ValorProduto=" + ValorProduto +
                '}';
    }
}
