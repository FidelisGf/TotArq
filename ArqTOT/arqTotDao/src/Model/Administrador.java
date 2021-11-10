package Model;

public class Administrador {
    private long idUsuario;
    private String nomeUsuario;
    private String senhaUsuario;
    private String acessoUsuario;
    private String unidadeUsuario;

    public Administrador() {
    }

    public Administrador(long idUsuario, String nomeUsuario, String senhaUsuario, String acessoUsuario, String unidadeUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.acessoUsuario = acessoUsuario;
        this.unidadeUsuario = unidadeUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getAcessoUsuario() {
        return acessoUsuario;
    }

    public void setAcessoUsuario(String acessoUsuario) {
        this.acessoUsuario = acessoUsuario;
    }

    public String getUnidadeUsuario() {
        return unidadeUsuario;
    }

    public void setUnidadeUsuario(String unidadeUsuario) {
        this.unidadeUsuario = unidadeUsuario;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "idUsuario=" + idUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", senhaUsuario='" + senhaUsuario + '\'' +
                ", acessoUsuario='" + acessoUsuario + '\'' +
                ", unidadeUsuario='" + unidadeUsuario + '\'' +
                '}';
    }
}
