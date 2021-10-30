package DAO;

import CONNECTION.ConnectionFactory;
import Model.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {
    private Connection connection;

    public EmpresaDAO() {
        this.connection = new ConnectionFactory().getConnection();
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
