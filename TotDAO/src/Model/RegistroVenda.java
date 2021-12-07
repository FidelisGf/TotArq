package Model;

public class RegistroVenda {
    private long IdRegistro;
    private Produto produto;
    private int numVendas;

    public RegistroVenda(long idRegistro, Produto produto, int numVendas) {
        IdRegistro = idRegistro;
        this.produto = produto;
        this.numVendas = numVendas;
    }

    public RegistroVenda(Produto produto, int numVendas) {
        this.produto = produto;
        this.numVendas = numVendas;
    }

    public RegistroVenda() {

    }

    public long getIdRegistro() {
        return IdRegistro;
    }

    public void setIdRegistro(long idRegistro) {
        IdRegistro = idRegistro;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }
}
