package DAO;

import CONNECTION.ConnectionFactory;
import Model.Cargos;
import Model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargosDAO {
    private Connection connection;
    public CargosDAO() {
        this.connection  = new ConnectionFactory().getConnection();
    }
    public void InserirCargo(Cargos cargos, int id){
        try {
            System.out.println(cargos);
            String sql = "INSERT INTO cargos" + "(NomeCargo, Salario, IdEmpresa)" + "VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cargos.getNomeCargo());
            statement.setFloat(2, cargos.getSalarioDoCargo());
            statement.setInt(3, id);
            statement.execute();
            int id2 = 0;
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                id2 = resultSet.getInt(1);
            }
            System.out.println("Id do Novo Cargo é : " + id2);
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public List<Cargos> ListarTodas(int id){
        try {
            List<Cargos> list = new ArrayList<>();
            String sql = "SELECT * FROM cargos WHERE  cargos.IdEmpresa = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            Cargos cargos;
            while (resultSet.next()){
                cargos = new Cargos(resultSet.getInt("IdCargo"), resultSet.getString("NomeCargo"), resultSet.getFloat("Salario"));
                list.add(cargos);
            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
