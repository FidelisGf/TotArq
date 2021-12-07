package DAO;

import CONNECTION.ConnectionFactory;
import Controller.RelatorioController;
import Model.Categoria;

import Model.Produto;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
import java.util.StringTokenizer;

public class ProdutoDAO {
    private Connection connection;
    public ProdutoDAO() {
        this.connection = new ConnectionFactory().getConnection();
        criarTabelaProduto();
    }
    public void insereProduto(Produto produto){
        try {
            RelatorioController relatorioController = new RelatorioController();
            String sql = "INSERT INTO produtos" + "(Produto, Valor,Quantidade,IdCategoria,Descricao)" + "VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            verificaSeExiste(produto);
            if(verificaSeExiste(produto) == false){
                statement.setString(1, produto.getNome());
                statement.setFloat(2, produto.getPreco());
                statement.setInt(3, produto.getQuantidade());
                statement.setInt(4, produto.getCategoria().getId());
                statement.setString(5, produto.getDesc());
                statement.execute();
                ResultSet rs = statement.getGeneratedKeys();
                int id = 0;
                while (rs.next()){
                    id = rs.getInt(1);
                }
                relatorioController.fazerLogAdicionar(produto);
                JOptionPane.showMessageDialog(null, "Cadastro do produto " + produto.getNome() +" realizado com sucesso no id -> " + id);
                statement.close();
            }else{
                JOptionPane.showMessageDialog(null, "Produto Já existente");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Falha no Cadastro  ");
            e.getErrorCode();
        }
    }
    public void criarTabelaProduto(){
        try {
            String sql = "CREATE TABLE IF not exists Produtos " +
                    "(Codigo BIGINT not NULL AUTO_INCREMENT, " +
                    " PRIMARY KEY (Codigo) , " +
                    " Produto VARCHAR(255), " +
                    " Valor FLOAT , " +
                    " Quantidade INTEGER , " +
                    " Descricao VARCHAR(255), " +
                    " Insumos VARCHAR(455), " +
                    " IdCategoria BIGINT not NULL , " +
                    " DATA TIMESTAMP , " +
                    " FOREIGN KEY (IdCategoria) REFERENCES Categorias(Idd) ON DELETE CASCADE )";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
    public List<Produto> listaProdutosporCategoria(int id, int id2) {
        try {
            List<Produto> lista = new ArrayList<>();
            String sql = "SELECT * FROM produtos, Categorias, unidade WHERE produtos.IdCategoria = ? && produtos.IdCategoria = Categorias.Idd AND unidade.idUnidade = ? &&  Categorias.IdUnidade = unidade.idUnidade";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id2);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            return listar(resultSet);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public List<Produto> listarTodosProdutos(int id){
       try {
           String sql = "SELECT * FROM produtos, Categorias, unidade WHERE unidade.idUnidade = ? && Categorias.IdUnidade = unidade.IdUnidade && produtos.IdCategoria = Categorias.Idd";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, id);
           statement.execute();
           ResultSet resultSet = statement.executeQuery();
           return listar(resultSet);

       }catch (SQLException e){
           throw new RuntimeException();
       }
    }
    public void EditarProduto(Produto produto){
        try {
            String sql = "";
            sql = "Update produtos SET Produto = ?, Valor = ?, IdCategoria = ?, Descricao = ? WHERE Codigo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, produto.getNome().toUpperCase());
            statement.setFloat(2, produto.getPreco());
            statement.setInt(3, produto.getCategoria().getId());
            statement.setString(4, produto.getDesc());
            statement.setInt(5, produto.getId());
            statement.executeUpdate();
            statement.close();
            JOptionPane.showMessageDialog(null,"Alteração Realizada com sucesso");
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public List<Produto> listar(ResultSet resultSet) throws SQLException{
        List<Produto> list = new ArrayList<>();
        Categoria categoria = new Categoria();
        Produto produto;
        while (resultSet.next()){
            produto = new Produto();
            produto.setId(resultSet.getInt("Codigo"));
            produto.setNome(resultSet.getString("Produto"));
            produto.setPreco(resultSet.getFloat("Valor"));
            categoria.setId(resultSet.getInt("IdCategoria"));
            produto.setCategoria(categoria);
            produto.setDesc(resultSet.getString("Descricao"));
            list.add(produto);
        }
        return list;
    }
    public boolean verificaSeExiste(Produto produto){
        try {
            String sql = "SELECT * FROM produtos";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if(resultSet.getString("Produto").equals(produto.getNome())){
                    return true;
                }
            }
            return false;
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
    public void adicionarQuantidadeProduto(Produto produto){
        try {
            String sql = "Update produtos SET Quantidade = Quantidade + ? WHERE Produto = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, produto.getQuantidade());
            statement.setString(2,produto.getNome());
            statement.execute();
            statement.close();
            JOptionPane.showMessageDialog(null,"Quantidade Adicionada com sucesso !");
        }catch (SQLException e){
            throw new RuntimeException();
        }

    }
    public int escolher_produto(int idCategoria, int idEmpresa){
        int i = 0;
        List<Produto> list = listaProdutosporCategoria(idEmpresa, idCategoria);
        if(list.isEmpty()){
            return -1;
        }
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String[] tmp = new String[list.size()];
        String opc = "";
        String output = "";
        for (Produto produto : list) {
            tmp[i] =  i + "| " + "NOME : " + produto.getNome() + " Com o VALOR de : R$" + String.valueOf(produto.getPreco());
            i++;
        }
        Object selectionObject = (String) JOptionPane.showInputDialog(frame,"Select Product","Produtos",JOptionPane.QUESTION_MESSAGE,null, tmp, tmp[0]);
        Produto produto = new Produto();
        String pegaop = selectionObject.toString();
        StringTokenizer st = new StringTokenizer(pegaop);
        int id1 = Integer.parseInt(st.nextToken("|"));
        return id1;
    }
    public void Excluir_produto(String nomeProduto){
        try {
            String sql = "DELETE FROM produtos WHERE Produto = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nomeProduto);
            statement.execute();
            statement.close();
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
}
