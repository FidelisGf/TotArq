package DAO;

import CONNECTION.ConnectionFactory;
import Model.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {
    private Connection connection;

    public EmpresaDAO() {
        this.connection = new ConnectionFactory().getConnection();
        criarTabelaEmpresa();
    }
    public void criarTabelaEmpresa(){
        try {
            String sql = "CREATE TABLE IF not exists empresa " +
                    "(IDEmpresa BIGINT not NULL AUTO_INCREMENT, " +
                    " PRIMARY KEY (IDEmpresa) , " +
                    " Nome VARCHAR(255), " +
                    " Cnpj BIGINT , " +
                    " Endereco VARCHAR(200), " +
                    " DATA TIMESTAMP )";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
    public void insereEmpresa(Empresa empresa){
        try {
            String sql = "INSERT INTO empresa" + "(Nome, Cnpj, Endereco)" + "VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, empresa.getNome());
            statement.setInt(2, empresa.getCnpj());
            statement.setString(3, empresa.getEndereco());
            statement.execute();
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException();
        }

    }
    public List<Empresa> listarEmpresa(){
        try {
            String sql = "SELECT * FROM empresa";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            List<Empresa> list = new ArrayList<>();
            Empresa empresa;
            while (resultSet.next()){
                empresa = new Empresa(resultSet.getInt("IDEmpresa"), resultSet.getString("Nome"), resultSet.getInt("Cnpj"), resultSet.getString("Endereco"));
                list.add(empresa);
            }
            return list;
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
}
