package Dao;

import Model.Empresa;
import Factory.*;
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

    public String cadastrarEmpresaDAO(Empresa empresa) {
        String emp = visualizarEmpresaDAO();
        String query = "INSERT INTO empresa (" +
                "nomeEmpresa, enderecoEmpresa, cnpjEmpresa) VALUES (" +
                "'" + empresa.getNomeEmpresa() + "','" + empresa.getEnderecoEmpresa() + "','" + empresa.getCnpjEmpresa() + "')";

        if (emp.equals("nao ha empresa cadastrada!")) {
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.execute();
                stmt.close();
                return empresa.getNomeEmpresa() + " foi cadastrada com sucesso!";
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "nao foi possivel cadastrar a empresa";
    }

    public String editarEmpresaDAO(Empresa empresa){
        String emp = visualizarEmpresaDAO();

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

    public String visualizarEmpresaDAO(){
        String query = "SELECT * FROM empresa";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultset = stmt.executeQuery();
            Empresa emp = new Empresa();

            if (resultset.next()) {
                emp.setNomeEmpresa(resultset.getString("nomeEmpresa"));
                emp.setEnderecoEmpresa(resultset.getString("enderecoEmpresa"));
                emp.setCnpjEmpresa(resultset.getString("cnpjEmpresa"));

                return emp.toString();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "nao ha empresa cadastrada!";
    }
}
