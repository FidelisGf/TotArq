package DAO;

import CONNECTION.ConnectionFactory;
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
        criar_TabelaCategoria();
    }
    public void criar_TabelaCategoria(){
        try {
            String sql = "CREATE TABLE IF not exists Categorias " +
                    "(Idd BIGINT not NULL AUTO_INCREMENT, " +
                    " PRIMARY KEY (Idd) , " +
                    " NomeCategoria VARCHAR(255), " +
                    " IdUnidade INT , " +
                    " DATA TIMESTAMP , " +
                    " FOREIGN KEY (IdUnidade) REFERENCES unidade(idUnidade))";
            Statement statement = connection.createStatement();
            statement.execute(sql);

            statement.close();
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
    public void excluirCategoria(int op){
        try {
            String sql = "DELETE FROM Categorias WHERE Idd = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, op);
            statement.execute();
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public void InsereCategoria(Categoria categoria, int id){
        try {
            String sql = "INSERT INTO Categorias" + "(NomeCategoria, IdUnidade)" + "VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, categoria.getNome());
            statement.setInt(2, id);
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            int ID = 0;
            while (resultSet.next()){
                ID = resultSet.getInt(1);
            }
            JOptionPane.showMessageDialog(null, "Categoria Cadastrada no ID - > " + ID);
            statement.close();

        }catch (SQLException e){
            e.getErrorCode();
        }
    }
    public boolean VerificarNome(String nome){
        try {
            String sql = "SELECT * FROM Categorias WHERE NomeCategoria = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                JOptionPane.showMessageDialog(null, "Já existe uma Categoria com esse nome");
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
   public List<Categoria> listartodos(int id){
        try {
            String sql = "SELECT * FROM Categorias WHERE Categorias.IdUnidade = ?";
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
       if(list.isEmpty()){
           return -1;
       }
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
   public Categoria verCategoria(int idUnidade , int idCategoria){
       Categoria categoria = new Categoria();
       try {
           String sql = "SELECT * FROM Categorias WHERE Categorias.Idd = ?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, idCategoria);
           statement.execute();
           ResultSet resultSet = statement.executeQuery();
           if(resultSet.next()){
               categoria.setId(resultSet.getInt("Idd"));
               categoria.setNome(resultSet.getString("NomeCategoria"));
           }
           return categoria;
       }catch (SQLException e){
           throw new RuntimeException();
       }
   }
}
