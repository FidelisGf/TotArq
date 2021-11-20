package DAO;

import CONNECTION.ConnectionFactory;
import Model.Avaliacao;
import Model.Carrinho;
import Model.Pagamento;
import Model.Produto;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CarrinhoDAO {

    private Connection connection;
    public CarrinhoDAO() {
        this.connection = new ConnectionFactory().getConnection();
        criarTabelaCarrinho();
    }
    public void criarTabelaCarrinho(){
        try {
            String sql = "CREATE TABLE IF not exists carrinho " +
                    "(idCarrinho BIGINT not NULL AUTO_INCREMENT, " +
                    " PRIMARY KEY (idCarrinho) , " +
                    " Produtos VARCHAR(255), " +
                    " Valor_Total FLOAT , " +
                    " DATA TIMESTAMP )";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            if(!verificaTabelaVazia()){
                Finalizar_Carrinho(); // tem o papel de criar o primeiro registro do carrinho
            }
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
    public boolean verificaTabelaVazia(){
        try {
            String sql = "SELECT * FROM carrinho";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
    public void insereNoCarrinho(Carrinho carrinho, boolean exluir){// uso boolean excluir para reutilizar esse codigo em duas funções
        try {
            String command = "";
            if(!exluir){
                command = "UPDATE carrinho SET Produtos = ?, Valor_Total = Valor_Total + ? WHERE idCarrinho = ?";
            }else{
                command = "UPDATE carrinho SET Produtos = ?, Valor_Total = Valor_Total - ? WHERE idCarrinho = ?";
            }
            String sql = command;
            PreparedStatement statement = connection.prepareStatement(sql);
            int id = pegaCarrinhoAtual();
            List<Produto> lista = carrinho.getLista_do_carrinho();
            if(!exluir){
                lista = ConcatenarProdutos(id, carrinho.getLista_do_carrinho());
            }
            carrinho.setLista_do_carrinho(lista);
            String lista_Carrinho = "";
            for(Produto produto : lista){
                lista_Carrinho += produto.getNome();
            }
            statement.setString(1, lista_Carrinho);
            statement.setFloat(2, carrinho.getValor_Total());
            statement.setInt(3, id);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public int pegaCarrinhoAtual(){
        int id = 0;
        try {
            String sql = "SELECT MAX(idCarrinho) FROM carrinho";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                id = resultSet.getInt(1);
            }
            statement.close();
        }catch (SQLException e){
            throw  new RuntimeException();
        }
        return id;
    }
    public void Finalizar_Carrinho(){
        try {
            String sql = "INSERT INTO carrinho (Produtos, Valor_Total) VALUES (' ', ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, Float.valueOf(0));
            statement.execute();
            statement.close();
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
    public List<Produto> ConcatenarProdutos(int id, List<Produto> list){
        try {
            String sql = "SELECT Produtos FROM carrinho WHERE idCarrinho = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet =statement.executeQuery();
            if(resultSet.next()){
                do{
                    Produto produto = new Produto();
                    produto.setNome(resultSet.getString("Produtos"));
                    list.add(produto);
                }while (resultSet.next());
            }
            statement.close();
        }catch (SQLException e){
            throw  new RuntimeException();
        }
        return list;
    }
    public Carrinho listarCarrinho(){
        Carrinho carrinho = new Carrinho();
        List<Produto> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM carrinho WHERE  idCarrinho = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pegaCarrinhoAtual());
            StringTokenizer myTokens;
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            String tmp = "";
            if(resultSet.next()){
                myTokens = new StringTokenizer(resultSet.getString("Produtos"), ",");
                if(resultSet.getString("Produtos").isEmpty() || resultSet.getString("Produtos") == null){
                    return carrinho;
                }
                while (myTokens.hasMoreTokens()) {
                    Produto produto = new Produto();
                    produto.setNome(myTokens.nextToken());
                    produto.setPreco(pegaValorProdutoDoCarrinho(produto.getNome()));
                    if(produto.getPreco() != 0){
                        list.add(produto);
                    }
                }
                if(resultSet.getFloat("Valor_Total") != 0){
                    carrinho.setValor_Total(resultSet.getFloat("Valor_Total"));
                }
                if(!list.isEmpty()){
                    carrinho.setLista_do_carrinho(list);
                }
            }
            return carrinho;
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
    public void excluirDoCarrinho(Carrinho carrinho, int op){
        try{
            carrinho.setValor_Total(pegaValorProdutoDoCarrinho(carrinho.getLista_do_carrinho().get(op).getNome()));
            carrinho.getLista_do_carrinho().remove(op);
            adicionarVirgula(carrinho.getLista_do_carrinho());
            insereNoCarrinho(carrinho, true);
        }catch (Exception e){
            throw  new RuntimeException();
        }
    }
    public Float pegaValorProdutoDoCarrinho(String nomeProduto){
        Float Valor_Total = Float.valueOf(0);
        try {
            String sql = "SELECT * FROM produtos WHERE produtos.Produto = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nomeProduto);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getFloat("Valor") == 0){
                    return Valor_Total;
                }
                Valor_Total = resultSet.getFloat("Valor");
            }
            statement.close();
            return Valor_Total;
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
    public int escolher_ProdutoDoCarrinho(){
        int i  = 0;
        List<Produto> list = listarCarrinho().getLista_do_carrinho();
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        if(list == null){
            return -1;
        }
        String[] tmp = new String[list.size()];
        String opc = "";
        String output = "";
        for (Produto produto : list) {
            tmp[i] =  i + "| " + "NOME : " + produto.getNome() + " Com o VALOR de : R$" + String.valueOf(produto.getPreco());
            i++;
        }
        Object selectionObject = (String) JOptionPane.showInputDialog(frame,"Selecione o Produto do Carrinho :","ListaDoCarrinho",JOptionPane.QUESTION_MESSAGE,null, tmp, tmp[0]);
        String pegaop = selectionObject.toString();
        StringTokenizer st = new StringTokenizer(pegaop);
        int id1 = Integer.parseInt(st.nextToken("|"));
        return id1;
    }
    public List<Produto> adicionarVirgula(List<Produto> list){
        String tmp ="";
        for(Produto produto : list){
            tmp += produto.getNome() + " ,";
            produto.setNome(tmp);
        }
        return list;
    }
    public void logPedidos(Carrinho carrinho, Pagamento pagamento, Avaliacao avaliacao){
        try {
            //int id = set_N_Pedido();
            int id = set_N_Pedido();
            File file = new File("C:\\Users\\Fifo\\Desktop\\TotDAo\\pedidos.txt");
            FileWriter fileWriter = new FileWriter(file,true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            if(!file.exists()){
                file.createNewFile();
            }
            printWriter.println("Pedido Nº - >  " + id + "  Com os seguintes itens :");
            for(Produto produto : carrinho.getLista_do_carrinho()){
                printWriter.println("Produto - > " + produto.getNome() + " No Valor de - > " + produto.getPreco());
            }
            printWriter.println("Valor Total do Pedido - > " + carrinho.getValor_Total());
            printWriter.println("----------------");
            printWriter.close();
            fileWriter.close();
            Finalizar_Carrinho();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
    File file = new File("C:\\Users\\Fifo\\Desktop\\TotDAo\\idPedidos.txt");
    public int get_N_Pedido(){
        int id = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            if(!file.exists()){
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("1");
                pw.close();
                fw.close();
            }
            if(bufferedReader.ready()){
                id = Integer.valueOf(bufferedReader.readLine());
            }
            fileReader.close();
        }catch (IOException e){
            throw  new RuntimeException();
        }
        return id;
    }
    public int set_N_Pedido(){
        int id = get_N_Pedido();
        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(id + 1);
            pw.close();
            fw.close();
        }catch (IOException e){
            throw new RuntimeException();
        }
        return id;
    }
}
