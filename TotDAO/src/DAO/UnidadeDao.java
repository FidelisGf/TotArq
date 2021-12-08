package DAO;
import CONNECTION.ConnectionFactory;
import Model.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UnidadeDao {
    private Connection connection;

    public UnidadeDao() {
        this.connection = new ConnectionFactory().getConnection();
        createTable();
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS unidade (" +
                "idUnidade INT PRIMARY KEY AUTO_INCREMENT," +
                "nomeUnidade VARCHAR(50) NOT NULL," +
                "enderecoUnidade VARCHAR(50) NOT NULL," +
                "ufUnidade VARCHAR(10));";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastraUnidade(Unidade u1) {
        String sql = "INSERT INTO unidade" +
                " (nomeUnidade, enderecoUnidade, ufUnidade) " +
                "VALUES ('"+ u1.getNomeUnidade() +"', '" + u1.getEnderecoUnidade() + "', '" + u1.getUfUnidade() + "')";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Unidade " + u1.getNomeUnidade().toUpperCase() + " cadastrada com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Unidade> listarUnidade(){
        String sql = "SELECT * from unidade";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery(sql);
            List<Unidade> listaUnidade = new ArrayList<>();
            Unidade unidade;
            while(resultSet.next()){
                unidade = new Unidade(resultSet.getInt("idUnidade"),resultSet.getString("nomeUnidade"), resultSet.getString("enderecoUnidade"), resultSet.getString("ufUnidade"));
                listaUnidade.add(unidade);
            }
            return listaUnidade;
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
    public int escolheUnidade(){
        int i = 0;
        List<Unidade> list = listarUnidade();
        if(list.isEmpty()){
            return -1;
        }
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String[] tmp = new String[list.size()];
        String opc = "";
        String output = "";
        for (Unidade unidade : list) {
            tmp[i] =  i + "| " + "NOME : " + unidade.getNomeUnidade() + " Registrada no Endereço : " + unidade.getEnderecoUnidade() +  "  No Estado de : " + unidade.getUfUnidade();
            i++;
        }
        Object selectionObject = (String) JOptionPane.showInputDialog(frame,"Select Unidade : ","Produtos",JOptionPane.QUESTION_MESSAGE,null, tmp, tmp[0]);
        Produto produto = new Produto();
        String pegaop = selectionObject.toString();
        StringTokenizer st = new StringTokenizer(pegaop);
        int id1 = Integer.parseInt(st.nextToken("|"));
        return id1;
    }

    public Unidade buscarUnidadeById(int idUnidade){
        String sql = "SELECT * FROM unidade WHERE idUnidade = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUnidade);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Unidade und = new Unidade();
                und.setIdUnidade(resultSet.getInt("idUnidade"));
                und.setNomeUnidade(resultSet.getString("nomeUnidade"));
                und.setEnderecoUnidade(resultSet.getString("enderecoUnidade"));
                und.setUfUnidade(resultSet.getString("ufUnidade"));
                return und;
            }
        }catch (SQLException e){
            throw  new RuntimeException();
        }
        return null;
    }

    public void excluirUnidade(Unidade unid){
        String sql = "DELETE FROM unidade WHERE idUnidade = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, unid.getIdUnidade());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editarUnidade(Unidade u1, int id){
        String sql = "UPDATE unidade SET nomeUnidade = ?, enderecoUnidade = ?, ufUnidade = ? WHERE idUnidade =" + id + "";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, u1.getNomeUnidade());
            stmt.setString(2, u1.getEnderecoUnidade());
            stmt.setString(3, u1.getUfUnidade());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> visualizarFuncionariosDaUnidade(String id) {
        String query = "SELECT * FROM usuarios WHERE unidadeUsuario = ?";
        List<String> listaFuncs = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            int convertInt = Integer.parseInt(id);
            stmt.setInt(1, convertInt);
            ResultSet resultset = stmt.executeQuery();
            Usuario usuario;
            while (resultset.next()) {
                usuario = new Usuario();
                usuario.setNomeUsuario(resultset.getString("nomeUsuario"));
                usuario.setAcessoUsuario(resultset.getString("acessoUsuario"));
                listaFuncs.add("Nome do Funcionário: " + usuario.getNomeUsuario() + " | " + "Acesso: " +usuario.getAcessoUsuario());
            }
            return listaFuncs;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaExiste(Unidade unidade){
        try {
            String sql = "SELECT * FROM unidade";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if(resultSet.getString("nomeUnidade").equals(unidade.getNomeUnidade())){
                    return true;
                }
            }
            return false;
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }
}
