package Model;

import java.util.List;

public class Carrinho {
    private int IdCarrinho;
    private List<Produto> lista_do_carrinho;
    private Float Valor_Total;

    public Carrinho(List<Produto> lista_do_carrinho, Float valor_Total) {
        this.lista_do_carrinho = lista_do_carrinho;
        Valor_Total = valor_Total;
    }


    public Carrinho() {
    }

    public int getIdCarrinho() {
        return IdCarrinho;
    }

    public void setIdCarrinho(int idCarrinho) {
        IdCarrinho = idCarrinho;
    }

    public List<Produto> getLista_do_carrinho() {
        return lista_do_carrinho;
    }

    public void setLista_do_carrinho(List<Produto> lista_do_carrinho) {
        this.lista_do_carrinho = lista_do_carrinho;
    }

    public Float getValor_Total() {
        return Valor_Total;
    }

    public void setValor_Total(Float valor_Total) {
        Valor_Total = valor_Total;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "IdCarrinho=" + IdCarrinho +
                ", lista_do_carrinho=" + lista_do_carrinho +
                ", Valor_Total=" + Valor_Total +
                '}';
    }
}
