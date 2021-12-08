package DAO;

import CONNECTION.ConnectionFactory;
import Controller.EstoqueController;
import Model.Carrinho;
import Model.Estoque;
import Model.Produto;
import Model.RegistroVenda;
import com.mysql.cj.jdbc.CallableStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroVendaDAO {
    private Connection connection;

    public RegistroVendaDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void criarTabelaRegistroVenda(){
        try {
            String sql = "CREATE TABLE IF not exists RegistroVenda " +
                    "(idRegistroV BIGINT not NULL AUTO_INCREMENT, " +
                    " PRIMARY KEY (idRegistroV) , " +
                    " Produto VARCHAR(255), " +
                    " Vendidos INTEGER not NULL , " +
                    " DATA TIMESTAMP )";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public void RegistraVendaProduto(Carrinho carrinho){
        EstoqueController estoqueController = new EstoqueController();
        try {
            for(Produto produto : carrinho.getLista_do_carrinho()){
                if(!procuraProdutoNaTabela(produto)){
                    String sql = "INSERT INTO RegistroVenda " + "(Produto, Vendidos)" + "VALUES(?,?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, produto.getNome());
                    statement.setInt(2, 1);
                    statement.execute();
                    statement.close();
                }else{
                    String sql = "UPDATE RegistroVenda SET Vendidos = Vendidos + 1 WHERE Produto = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, produto.getNome());
                    statement.execute();
                    statement.close();
                }
            }
            for(Produto produto : carrinho.getLista_do_carrinho()){
                for(Estoque estoque : produto.getInsumos()){
                    estoqueController.descontarInsumo(estoque);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public boolean procuraProdutoNaTabela(Produto produto){
        try {
            String sql = "SELECT Produto FROM RegistroVenda WHERE Produto = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,produto.getNome());
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return false;
    }
    public List<RegistroVenda> PegaVendas(){
        Produto produto;
        RegistroVenda registroVenda = new RegistroVenda();
        List<RegistroVenda> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM RegistroVenda ORDER BY Vendidos DESC ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                produto = new Produto();
                registroVenda = new RegistroVenda();
                produto.setNome(resultSet.getString("Produto"));
                registroVenda.setNumVendas(resultSet.getInt("Vendidos"));
                registroVenda.setProduto(produto);
                list.add(registroVenda);
            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
