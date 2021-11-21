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
    private String nome;
    private String cpf;
    private String endereco;

    public Usuario() {

    }

    public Usuario(long idUsuario, String nomeUsuario, String senhaUsuario, String acessoUsuario, String uniadeUsuario, String nome, String cpf, String endereco) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.acessoUsuario = acessoUsuario;
        this.unidadeUsuario = uniadeUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
        if (acessoUsuario.equals("1") || acessoUsuario.equals("administrador") ) {
            this.acessoUsuario = "administrador";
        }
        else if (acessoUsuario.equals("2")|| acessoUsuario.equals("supervisor") ) {
            this.acessoUsuario = "supervisor";
        }
        else if (acessoUsuario.equals("3")|| acessoUsuario.equals("funcionario") ) {
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
        return idUsuario + " | " +
                nomeUsuario + " | " +
                senhaUsuario + " | " +
                acessoUsuario + " | " +
                unidadeUsuario + " | " +
                nome + " | " +
                cpf + " | " +
                endereco + " | ";
    }
}
