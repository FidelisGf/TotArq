package DAO;

import Model.Empresa;
import CONNECTION.ConnectionFactory;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpresaDAO {
    private File caminhoEmpresa = new File("./Config/empresa.txt");
    private Connection connection;

    public EmpresaDAO() {
        this.connection = new ConnectionFactory().getConnection();
        createTableEmpresa();
    }

    public void createTableEmpresa() {
        String query = "CREATE TABLE IF NOT EXISTS empresa (" +
                "nomeEmpresa VARCHAR(50) NOT NULL," +
                "enderecoEmpresa VARCHAR(50) NOT NULL," +
                "cnpjEmpresa VARCHAR(50) NOT NULL)";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();
            stmt.close();
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrarEmpresaDAO(Empresa empresa) {
        String emp = verificarEmpresa();
        String query = "INSERT INTO empresa (" +
                "nomeEmpresa, enderecoEmpresa, cnpjEmpresa) VALUES (" +
                "'" + empresa.getNomeEmpresa() + "','" + empresa.getEnderecoEmpresa() + "','" + empresa.getCnpjEmpresa() + "')";

        if (emp.equals("nao ha empresa cadastrada!")) {
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Empresa Cadastrada com sucesso" );
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Não foi Possivel Cadastrar a empresa" );;
        }
    }

    public String editarEmpresaDAO(Empresa empresa){
        String emp = verificarEmpresa();

        String query = "UPDATE empresa SET nomeEmpresa = '"+empresa.getNomeEmpresa()+"', " + "enderecoEmpresa = '" + empresa.getEnderecoEmpresa() + "', " + "cnpjEmpresa = '"+empresa.getCnpjEmpresa()+"'";

        //if (emp.equals("nao ha empresa cadastrada!")) {
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.execute();
                stmt.close();

                return empresa.getNomeEmpresa() + " foi alterada com sucesso!";
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        //}
    }

    public Empresa visualizarEmpresaDAO(){
        String query = "SELECT * FROM empresa";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultset = stmt.executeQuery();
            Empresa emp = new Empresa();
            if (resultset.next()) {
                emp.setNomeEmpresa(resultset.getString("nomeEmpresa"));
                emp.setEnderecoEmpresa(resultset.getString("enderecoEmpresa"));
                emp.setCnpjEmpresa(resultset.getString("cnpjEmpresa"));
            }
            return emp;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public String verificarEmpresa(){
        String query = "SELECT * FROM empresa";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultset = stmt.executeQuery();

            if (!resultset.next()) {
                return "nao ha empresa cadastrada!";
            }else{
                return "ha empresas";
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
