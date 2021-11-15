package DAO;

import CONNECTION.ConnectionFactory;
import Controller.ProdutoController;
import Model.Categoria;
import Model.Funcionario;
import Model.Produto;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class ProdutoDAO {
    private Connection connection;
    public ProdutoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    public void insereProduto(Produto produto){
        try {
            String sql = "INSERT INTO produtos" + "(Produto, Valor,Quantidade,IdCategoria)" + "VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            verificaSeExiste(produto);
            if(verificaSeExiste(produto) == false){
                statement.setString(1, produto.getNome());
                statement.setFloat(2, produto.getPreco());
                statement.setInt(3, produto.getQuantidade());
                statement.setInt(4, produto.getIdCategoria());
                statement.execute();
                ResultSet rs = statement.getGeneratedKeys();
                int id = 0;
                while (rs.next()){
                    id = rs.getInt(1);
                }
                fazerLogAdicionar(produto);
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
    public List<Produto> listaProdutosporCategoria(int id, int id2) {
        try {
            List<Produto> lista = new ArrayList<>();
            String sql = "SELECT * FROM produtos, teste22, empresa WHERE produtos.IdCategoria = ? && produtos.IdCategoria = teste22.Idd AND empresa.IDEmpresa = ? &&  teste22.IdEmpresa = empresa.IDEmpresa  ";
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
           String sql = "SELECT * FROM produtos, teste22, empresa WHERE empresa.IDEmpresa = ? && teste22.IdEmpresa = empresa.IDEmpresa && produtos.IdCategoria = teste22.Idd";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, id);
           statement.execute();
           ResultSet resultSet = statement.executeQuery();
           return listar(resultSet);

       }catch (SQLException e){
           throw new RuntimeException();
       }
    }
    public void EditarProduto(Produto produto, int op){
        try {
            String sql = "";
            switch (op) {
                case 1:
                    sql = "Update produtos SET Produto = ? WHERE Codigo = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    if(verificaSeExiste(produto)==false){
                        statement.setString(1, produto.getNome().toUpperCase());
                        statement.setInt(2, produto.getId());
                        statement.executeUpdate();
                        statement.close();
                        JOptionPane.showMessageDialog(null,"Nome alterado com sucesso");
                    }else{
                        JOptionPane.showMessageDialog(null,"Já existe um produto com esse nome");
                    }
                    break;
                case 2:
                    sql = "Update produtos SET Valor = ? WHERE  Codigo = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setFloat(1,produto.getPreco());
                    statement.setInt(2, produto.getId());
                    statement.executeUpdate();
                    statement.close();
                    break;
                case 3:
                    sql = "Update produtos SET IdCategoria = ? WHERE Codigo = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, produto.getIdCategoria());
                    statement.setInt(2, produto.getId());
                    statement.executeUpdate();
                    statement.close();
                    break;
                case 4:
                    adicionarQuantidadeProduto(produto);
                    break;
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public List<Produto> listar(ResultSet resultSet) throws SQLException{
        List<Produto> list = new ArrayList<>();
        Produto produto;
        while (resultSet.next()){
            produto = new Produto();
            produto.setId(resultSet.getInt("Codigo"));
            produto.setNome(resultSet.getString("Produto"));
            produto.setPreco(resultSet.getFloat("Valor"));
            produto.setIdCategoria(resultSet.getInt("IdCategoria"));
            list.add(produto);
        }
        return list;
    }
    public int VerificaLogin(){
        try {
            int id = 0;
            File file = new File("C:\\Users\\Fifo\\Desktop\\TotDAo\\ControleLog\\Logado.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()){
                id = Integer.valueOf(bufferedReader.readLine());
            }
            bufferedReader.close();
            fileReader.close();
            return id;
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
    public void fazerLogAdicionar(Produto produto){
        int id = VerificaLogin();
        System.out.println(id);
        try {
            String sql = "SELECT * FROM funcionarios WHERE funcionarios.IdFuncionario = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            Funcionario funcionario = new Funcionario();
            Date date = new Date();
            while (resultSet.next()){
                funcionario.setNome(resultSet.getString("Nome"));
            }
            statement.close();
            File file = new File("C:\\Users\\Fifo\\Desktop\\TotDAo\\ControleLog\\log.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            try {
                if(!file.exists()){
                    file.createNewFile();
                }
                printWriter.println("USUARIO : " + funcionario.getNome() + " INSERIU : " + produto.getNome() + " Na CATEGORIA : " + produto.getIdCategoria() + " no HORARIO : " + new Timestamp(date.getTime()));
                printWriter.close();
                fileWriter.close();
            }catch (IOException e){
                throw new RuntimeException();
            }
        }catch (SQLException | IOException e){
            throw new RuntimeException();
        }
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
    public int escolher_produto( int idCategoria, int idEmpresa){
        int i = 0;
        List<Produto> list = listaProdutosporCategoria(idEmpresa, idCategoria);
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
}
