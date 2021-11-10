package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;


public class Usuario {
    private long idUsuario;
    private String nomeUsuario;
    private String senhaUsuario;
    private String acessoUsuario;
    private String unidadeUsuario;

    public Usuario() {

    }

    public Usuario(long idUsuario, String nomeUsuario, String senhaUsuario, String acessoUsuario, String uniadeUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.acessoUsuario = acessoUsuario;
        this.unidadeUsuario = uniadeUsuario;
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
        if (acessoUsuario.equals("1")) {
            this.acessoUsuario = "administrador";
        }
        else if (acessoUsuario.equals("2")) {
            this.acessoUsuario = "supervisor";
        }
        else if (acessoUsuario.equals("3")) {
            this.acessoUsuario = "funcionario";
        }
    }

    public String getUnidadeUsuario() {
        return unidadeUsuario;
    }

    public void setUnidadeUsuario(String unidadeUsuario) {
        this.unidadeUsuario = unidadeUsuario;
    }

    @Override
    public String toString() {
        return idUsuario + " | " + nomeUsuario + " | " + senhaUsuario + " | " + acessoUsuario + " | " + unidadeUsuario;
    }

//    @Override
//    public String toString() {
//        return "Usuarios{" +
//                "idUsuario=" + idUsuario +
//                ", nomeUsuario='" + nomeUsuario + '\'' +
//                ", senhaUsuario='" + senhaUsuario + '\'' +
//                ", acessoUsuario='" + acessoUsuario + '\'' +
//                ", uniadeUsuario='" + uniadeUsuario + '\'' +
//                '}';
//    }
}
