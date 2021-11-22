package Model;

public class Unidade {
    private int idUnidade;
    private String nomeUnidade;
    private String enderecoUnidade;
    private String ufUnidade;

    public Unidade() {
    }

    public Unidade(String nomeUnidade, String enderecoUnidade, String ufUnidade) {
        this.nomeUnidade = nomeUnidade;
        this.enderecoUnidade = enderecoUnidade;
        this.ufUnidade = ufUnidade;
    }

    public Unidade(int idUnidade, String nomeUnidade, String enderecoUnidade, String ufUnidade) {
        this.idUnidade = idUnidade;
        this.nomeUnidade = nomeUnidade;
        this.enderecoUnidade = enderecoUnidade;
        this.ufUnidade = ufUnidade;
    }

    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getEnderecoUnidade() {
        return enderecoUnidade;
    }

    public void setEnderecoUnidade(String enderecoUnidade) {
        this.enderecoUnidade = enderecoUnidade;
    }

    public String getUfUnidade() {
        return ufUnidade;
    }

    public void setUfUnidade(String ufUnidade) {
        this.ufUnidade = ufUnidade;
    }

    @Override
    public String toString() {
        return "ID unidade: " + idUnidade + "\n" +
                "Nome Unidade: " + nomeUnidade + '\n'+
                "Endereço: " + enderecoUnidade + '\n'+
                "UF: " + ufUnidade + '\n';
    }
}
