package DAO;

import CONNECTION.ConnectionFactory;
import Model.Estoque;
import Model.Produto;
import Model.Unidade;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EstoqueDAO {
    private Connection connection;

    public EstoqueDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    public void criar_TabelaEstoque(){
        try {
            String sql = "CREATE TABLE IF not exists Estoque " +
                    "(idInsumo BIGINT not NULL AUTO_INCREMENT, " +
                    " PRIMARY KEY (idInsumo) , " +
                    " nomeInsumo VARCHAR(255), " +
                    " qntInsumo INTEGER  not NULL , " +
                    " precoInsumo FLOAT not NULL , " +
                    " dataValidade VARCHAR(11), " +
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
    public void cadastrarEstoque(Estoque estoque){
        try{
            String sql = "INSERT INTO insumos (nomeInsumo, qntInsumo, precoInsumo, dataValidade) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, estoque.getNomeInsumo());
            preparedStatement.setInt(2, estoque.getQntdInsumo());
            preparedStatement.setDouble(3, estoque.getPrecoInsumo());
            preparedStatement.setString(4, estoque.getValidade());
            preparedStatement.execute();
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Estoque> listarEstoque(){
        List<Estoque> list = new ArrayList<>();
        Estoque estoque;
        Unidade unidade;
        try {
            String sql = "SELECT * FROM Estoque  ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                estoque = new Estoque();
                estoque.setIdInsumo(resultSet.getInt("idInsumo"));
                estoque.setNomeInsumo(resultSet.getString("nomeInsumo"));
                estoque.setPrecoInsumo(resultSet.getFloat("precoInsumo"));
                estoque.setQntdInsumo(resultSet.getInt("qntInsumo"));
                estoque.setValidade(resultSet.getString("dataValidade"));
                unidade = new Unidade();
                unidade.setIdUnidade(resultSet.getInt("IdUnidade"));
                estoque.setUnidade(unidade);
                list.add(estoque);
            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public int escolheInsumoEstoque(){
        int i = 0;
        List<Estoque> list = listarEstoque();
        if(list.isEmpty()){
            return -1;
        }
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String[] tmp = new String[list.size()];
        String opc = "";
        String output = "";
        for (Estoque estoque : list) {
            tmp[i] =  i + "| " + "NOME : " + estoque.getNomeInsumo() + "  Com Preço de :  " + estoque.getPrecoInsumo() +  " Por Unidade ";
            i++;
        }
        Object selectionObject = (String) JOptionPane.showInputDialog(frame,"Select Unidade : ","Produtos",JOptionPane.QUESTION_MESSAGE,null, tmp, tmp[0]);
        Produto produto = new Produto();
        String pegaop = selectionObject.toString();
        StringTokenizer st = new StringTokenizer(pegaop);
        int id1 = Integer.parseInt(st.nextToken("|"));
        return id1;
    }
}
