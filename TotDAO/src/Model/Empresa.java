package Model;

public class Empresa {
    private String nomeEmpresa;
    private String enderecoEmpresa;
    private String cnpjEmpresa;

    public Empresa() {
    }

    public Empresa(String nomeEmpresa, String enderecoEmpresa, String cnpjEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        this.enderecoEmpresa = enderecoEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEnderecoEmpresa() {
        return enderecoEmpresa;
    }

    public void setEnderecoEmpresa(String enderecoEmpresa) {
        this.enderecoEmpresa = enderecoEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    @Override
    public String toString() {
        return nomeEmpresa + " | " + enderecoEmpresa + " | " + cnpjEmpresa;
    }
}
