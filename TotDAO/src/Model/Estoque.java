package Model;

public class Estoque {
    private long idInsumo;
    private String nomeInsumo;
    private int qntdInsumo;
    private Float precoInsumo;
    private String Validade;
    private Unidade unidade;

    public Estoque() {
    }

    public Estoque(long idInsumo, String nomeInsumo, int qntdInsumo, Float precoInsumo, String validade) {
        this.idInsumo = idInsumo;
        this.nomeInsumo = nomeInsumo;
        this.qntdInsumo = qntdInsumo;
        this.precoInsumo = precoInsumo;
        Validade = validade;
    }

    public Estoque(String nomeInsumo, int qntdInsumo, Float precoInsumo, String validade, Unidade unidade) {
        this.nomeInsumo = nomeInsumo;
        this.qntdInsumo = qntdInsumo;
        this.precoInsumo = precoInsumo;
        Validade = validade;
        this.unidade = unidade;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(long idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNomeInsumo() {
        return nomeInsumo;
    }

    public void setNomeInsumo(String nomeInsumo) {
        this.nomeInsumo = nomeInsumo;
    }

    public int getQntdInsumo() {
        return qntdInsumo;
    }

    public void setQntdInsumo(int qntdInsumo) {
        this.qntdInsumo = qntdInsumo;
    }

    public Float getPrecoInsumo() {
        return precoInsumo;
    }

    public void setPrecoInsumo(Float precoInsumo) {
        this.precoInsumo = precoInsumo;
    }

    public String getValidade() {
        return Validade;
    }

    public void setValidade(String validade) {
        Validade = validade;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "idInsumo=" + idInsumo +
                ", nomeInsumo='" + nomeInsumo + '\'' +
                ", qntdInsumo=" + qntdInsumo +
                ", precoInsumo=" + precoInsumo +
                ", Validade='" + Validade + '\'' +
                '}';
    }
}
