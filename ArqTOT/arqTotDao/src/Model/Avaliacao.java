package Model;

public class Avaliacao {
    private String tpAvaliacao;

    public Avaliacao(String tpAvaliacao) {
        this.tpAvaliacao = tpAvaliacao;
    }

    public Avaliacao() {
    }

    public String getTpAvaliacao() {
        return tpAvaliacao;
    }

    public void setTpAvaliacao(String tpAvaliacao) {
        this.tpAvaliacao = tpAvaliacao;
    }

    @Override
    public String toString() {
        return tpAvaliacao + '\'';
    }
}
