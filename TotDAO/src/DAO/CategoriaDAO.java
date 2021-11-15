package DAO;

import CONNECTION.ConnectionFactory;
import Model.Cargos;
import Model.Categoria;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
   public int escolhe_Categoria(int id){
       List<Categoria> list = this.listartodos(id);
       int tamanho = list.size();
       String[] object = new String[list.size()];
       JFrame frame = new JFrame();
       int op = 0;
       frame.setAlwaysOnTop(true);
       int i = 0;
       try {
           for (Categoria categoria : list) {
               object[i] = String.valueOf(categoria.getId()) + "|" + categoria.getNome().toString();
               i++;
           }
           Object selectionObject = (String) JOptionPane.showInputDialog(frame, "Escolha a Categoria desejada : ", "Categoria", JOptionPane.QUESTION_MESSAGE, null, object, object[0]);
           String tmp = selectionObject.toString();
           StringTokenizer st = new StringTokenizer(tmp);
           op = Integer.valueOf(st.nextToken("|"));

       }catch (Exception e){
           e.printStackTrace();
       }
       return op;
   }
}
