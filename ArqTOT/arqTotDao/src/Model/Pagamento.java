package Model;

public class Pagamento {
    private String formaPagamento;

    public Pagamento() {}

    public Pagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "formaPagamento='" + formaPagamento + '\'' +
                '}';
    }
}
