package Model;

import java.util.List;

public class Carrinho {
    private int IdCarrinho;
    private List<Produto> lista_do_Carrinho;
    private Float Valor_Total_Carrinho;
    private Produto produto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Carrinho(List<Produto> lista_do_Carrinho, Float valor_Total_Carrinho) {
        this.lista_do_Carrinho = lista_do_Carrinho;
        Valor_Total_Carrinho = valor_Total_Carrinho;
    }

    public Carrinho() {
    }

    public int getIdCarrinho() {
        return IdCarrinho;
    }

    public void setIdCarrinho(int idCarrinho) {
        IdCarrinho = idCarrinho;
    }

    public List<Produto> getLista_do_Carrinho() {
        return lista_do_Carrinho;
    }

    public void setLista_do_Carrinho(List<Produto> lista_do_Carrinho) {
        this.lista_do_Carrinho = lista_do_Carrinho;
    }


    public Float getValor_Total_Carrinho() {
        return Valor_Total_Carrinho;
    }

    public void setValor_Total_Carrinho(Float valor_Total_Carrinho) {
        Valor_Total_Carrinho = valor_Total_Carrinho;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "IdCarrinho=" + IdCarrinho +
                ", lista_do_Carrinho=" + lista_do_Carrinho +
                ", Valor_Total_Carrinho=" + Valor_Total_Carrinho +
                '}';
    }
}
