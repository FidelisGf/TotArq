package DAO;

import CONNECTION.ConnectionFactory;
import Model.Funcionario;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    Connection connection;
    public FuncionarioDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    public void cadastrarFuncionario(Funcionario funcionario){
        try {
            String sql = "INSERT INTO funcionarios" + "(Nome,Senha, Idade,Endereco,IdCargo) " +"VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getSenha());
            statement.setInt(3, funcionario.getIdade());
            statement.setString(4, funcionario.getEndereco());
            statement.setInt(5, funcionario.getIdCargo());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            int id = 0;
            while(resultSet.next()){
                id = resultSet.getInt(1);
            }
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso  " + id);
            statement.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Falha no cadastro !");
            throw new RuntimeException();
        }
    }
   public List<Funcionario> listaTodos(int id){
        try {
            List<Funcionario> list = new ArrayList<>();
            String sql = "SELECT * from funcionarios, cargos, empresa WHERE empresa.IDempresa = ? &&  cargos.IdEmpresa = empresa.IDempresa && funcionarios.IdCargo = cargos.IdCargo";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            Funcionario funcionario;
            while (resultSet.next()){
                funcionario = new Funcionario();
                funcionario.setNome(resultSet.getString("Nome"));
                funcionario.setId(resultSet.getInt("IdFuncionario"));
                funcionario.setEndereco(resultSet.getString("Endereco"));
                funcionario.setSalario(resultSet.getFloat("Salario"));
                funcionario.setCargo(resultSet.getString("NomeCargo"));
                funcionario.setIdade(resultSet.getInt("Idade"));
                funcionario.setIdCargo(resultSet.getInt("IdCargo"));
                list.add(funcionario);
            }
            return list;
        }catch (SQLException e){
            throw  new RuntimeException();
        }
   }
   public boolean iniciarSessao(Funcionario funcionario){
        try {
            String sql = "SELECT *  FROM funcionarios WHERE  funcionarios.nome = ? AND funcionarios.senha = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getSenha());
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                funcionario.setId(resultSet.getInt("IdFuncionario"));
                controleLogin(funcionario);
                return true;
            }else{
                return false;
            }
        }catch (SQLException | IOException e){
            throw new RuntimeException();
        }
   }
   public void controleLogin(Funcionario funcionario) throws IOException {
        File file = new File("C:\\Users\\Fifo\\Desktop\\TotDAo\\ControleLog\\Logado.txt");
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            printWriter.println(funcionario.getId());
            printWriter.close();
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
   }
   public int verificaCargo(Funcionario funcionario){
        try {
            String sql = "SELECT * FROM funcionarios WHERE funcionarios.Nome = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, funcionario.getNome());
            statement.execute();

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
               funcionario.setIdCargo(resultSet.getInt("IdCargo"));
            }
            System.out.println(funcionario.getIdCargo());
            sql = "SELECT * from cargos WHERE cargos.IdCargo = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql);
            statement2.setInt(1, funcionario.getIdCargo());
            statement2.execute();
            resultSet = statement2.executeQuery();
            if(resultSet.next()){
                funcionario.setCargo(resultSet.getString("NomeCargo"));
            }
            System.out.println(funcionario.getCargo());
            statement.close();
            resultSet.close();
            if(funcionario.getCargo().contains("Patrao")){
                return 1;
            }else{
                return 0;
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

}
