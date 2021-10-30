package DAO;

import CONNECTION.ConnectionFactory;
import Model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;
    public CategoriaDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void InsereCategoria(Categoria categoria, int id){
        try {
            String sql = "INSERT INTO teste22" + "(NomeCategoria, IdEmpresa)" + "VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, categoria.getNome());
            statement.setInt(2, id);
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            int ID = 0;
            while (resultSet.next()){
                ID = resultSet.getInt(1);
            }
            System.out.println("Essa categoria possui o codigo: " + ID);
            statement.close();

        }catch (SQLException e){
            e.getErrorCode();
        }
    }
   public List<Categoria> listartodos(int id){
        try {
            String sql = "SELECT * FROM teste22 WHERE teste22.IdEmpresa = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            Categoria categoria;
            List<Categoria> lista = new ArrayList<>();
            while (resultSet.next()){
                categoria = new Categoria();
                categoria.setId(resultSet.getInt("Idd"));
                categoria.setNome(resultSet.getString("NomeCategoria"));
                lista.add(categoria);
            }
            return lista;
        }catch (SQLException e){
            throw  new RuntimeException();
        }
   }
}
