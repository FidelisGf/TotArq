package DAO;

import java.io.*;
import Model.*;
import CONNECTION.ConnectionFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void createTableUsuarios() {
        String query = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "idUsuario INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                "nome VARCHAR(50)," +
                "cpf VARCHAR(50)," +
                "endereco VARCHAR(50)," +
                "nomeUsuario VARCHAR(50) NOT NULL," +
                "senhaUsuario VARCHAR(50) NOT NULL," +
                "acessoUsuario VARCHAR(20) NOT NULL," +
                "unidadeUsuario INT,"+
                "FOREIGN KEY (unidadeUsuario) REFERENCES unidade (idUnidade))";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        query = "SELECT * FROM usuarios";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultset = stmt.executeQuery();
            if (! resultset.next()) {
                stmt.close();
                query = "INSERT INTO usuarios (nomeUsuario, senhaUsuario, acessoUsuario)" +
                        "VALUES ('admin', 'admin', 'administrador')" ;
                try {
                    stmt = connection.prepareStatement(query);
                    stmt.execute();
                    stmt.close();
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String cadastroUsuarioDAO(Usuario usuario)  {
        if (! nomeUsuarioExiste(usuario) && cpfValidado(usuario.getCpf())) {
            String query = "INSERT INTO usuarios (" +
                    "nome, cpf, endereco, nomeUsuario, senhaUsuario, acessoUsuario, unidadeUsuario)" +
                    "VALUES ('" + usuario.getNome() + "','" + usuario.getCpf() + "','" + usuario.getEndereco() +
                    "','" + usuario.getNomeUsuario() +
                    "','" + usuario.getSenhaUsuario() + "','" + usuario.getAcessoUsuario() +
                    "','" + usuario.getUnidadeUsuario() + "')";

            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.execute();
                stmt.close();
                return usuario.getNomeUsuario() + " foi cadastrado com sucesso!";
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "Nome de usuario ja existe ou cpf invalido";

    }
    public void controleLogin(Usuario usuario) throws IOException {
        usuario.setIdUsuario(identifica_usuario(usuario));
        File file = new File("C:\\Users\\Fifo\\Desktop\\TotDAo\\ControleLog\\Logado.txt");
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            printWriter.println(usuario.getIdUsuario());
            printWriter.close();
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int  identifica_usuario(Usuario usuario){
        try {
            String query = "SELECT * FROM usuarios WHERE usuarios.nomeUsuario = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, usuario.getNomeUsuario());
            stmt.execute();
            ResultSet resultSet = stmt.executeQuery();
            int id = 0;
            if(resultSet.next()){
                id = resultSet.getInt("idUsuario");
            }
            stmt.close();
            return id;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public String visualizarUsuarioDAO(){
        String query = "SELECT * FROM usuarios";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultset = stmt.executeQuery();
            String usuarios = "ID | NOME | CPF | ENDERECO | NOME_USUARIO | SENHA | ACESSO | UNIDADE\n";
            Usuario usuario;

            while (resultset.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(resultset.getInt("idUsuario"));
                usuario.setNome(resultset.getString("nome"));
                usuario.setCpf(resultset.getString("cpf"));
                usuario.setEndereco(resultset.getString("endereco"));
                usuario.setNomeUsuario(resultset.getString("nomeUsuario"));
                usuario.setSenhaUsuario(resultset.getString("senhaUsuario"));
                usuario.setAcessoUsuario(resultset.getString("acessoUsuario"));
                String idUnidade = resultset.getString("unidadeUsuario");
                String nomeUnidade = getNomeUnidadeById(idUnidade);
                usuario.setUnidadeUsuario(nomeUnidade);


                usuarios += usuario + "\n";
            }
            stmt.close();
            return usuarios;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Usuario> ListaUsuario(){
        List<Usuario> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM usuarios WHERE  idUsuario != 1";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultset = stmt.executeQuery();
            Usuario usuario;
            while (resultset.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(resultset.getInt("idUsuario"));
                usuario.setNome(resultset.getString("nome"));
                usuario.setCpf(resultset.getString("cpf"));
                usuario.setEndereco(resultset.getString("endereco"));
                usuario.setNomeUsuario(resultset.getString("nomeUsuario"));
                usuario.setSenhaUsuario(resultset.getString("senhaUsuario"));
                usuario.setAcessoUsuario(resultset.getString("acessoUsuario"));
                String idUnidade = resultset.getString("unidadeUsuario");
                String nomeUnidade = getNomeUnidadeById(idUnidade);
                usuario.setUnidadeUsuario(nomeUnidade);
                list.add(usuario);
            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException();

        }
    }

    private String getNomeUnidadeById(String idUnidade) {
        String nomeUnidade = "";
        String query = "SELECT nomeUnidade FROM unidade WHERE idUnidade=" + idUnidade;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultset = stmt.executeQuery();
            if (resultset.next()) {
                nomeUnidade = resultset.getString("nomeUnidade");
            }
            stmt.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return nomeUnidade;
    }

    public String visualizarUsuarioByIdDAO(int id) {
        String query = "SELECT * FROM usuarios WHERE idUsuario = " + id;

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultset = stmt.executeQuery();
            String usuarios = "ID | NOME | CPF | ENDERECO | NOME_USUARIO | SENHA | ACESSO | UNIDADE\n";
            Usuario usuario;

            if (resultset.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(resultset.getInt("idUsuario"));
                usuario.setNome(resultset.getString("nome"));
                usuario.setCpf(resultset.getString("cpf"));
                usuario.setEndereco(resultset.getString("endereco"));
                usuario.setNomeUsuario(resultset.getString("nomeUsuario"));
                usuario.setSenhaUsuario(resultset.getString("senhaUsuario"));
                usuario.setAcessoUsuario(resultset.getString("acessoUsuario"));
                String idUnidade = resultset.getString("unidadeUsuario");
                String nomeUnidade = getNomeUnidadeById(idUnidade);
                usuario.setUnidadeUsuario(nomeUnidade);

                usuarios += usuario + "\n";
            }
            return usuarios;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String editarUsuarioDAO(Usuario usuario, int id) {
        if (! nomeUsuarioExiste(usuario) && cpfValidado(usuario.getCpf())) {
            String query = "UPDATE usuarios SET " +
                    "nome='" + usuario.getNome() + "'," +
                    "cpf='" + usuario.getCpf() + "'," +
                    "endereco='" + usuario.getEndereco() + "'," +
                    "nomeUsuario= '"+usuario.getNomeUsuario()+"', " +
                    "" + "senhaUsuario = '" + usuario.getSenhaUsuario() + "', " +
                    "" + "acessoUsuario = '"+usuario.getAcessoUsuario()+"', " +
                    "" + "unidadeUsuario = '" + usuario.getUnidadeUsuario() +
                    "' WHERE idUsuario = "+ id;
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.execute();
                stmt.close();
                return usuario.getNomeUsuario() + " foi editado com sucesso!";
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "Nome de usuario ja existe ou cpf invalido";
    }

    public String deletarUsuarioDAO(int id){

        String query = "DELETE FROM usuarios WHERE idUsuario = " + id;

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "usuario foi deletado com sucesso!";
    }

    public String loginUsuarioDAO(Usuario usuario) {  // get() > 1 = usuario, 2 = senha, 3 = acesso
        String query = "SELECT * FROM usuarios WHERE nomeUsuario = '" + usuario.getNomeUsuario()+
                "' AND senhaUsuario='" + usuario.getSenhaUsuario()+"'";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultset = stmt.executeQuery();

            if (resultset.next()) {
                String acesso = resultset.getString("acessoUsuario");
                stmt.close();
                controleLogin(usuario);
                return acesso;
            }
        }
        catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private boolean nomeUsuarioExiste(Usuario usuario) {
        String query = "SELECT * FROM usuarios WHERE nomeUsuario='"+usuario.getNomeUsuario()+"'";
        ResultSet resultset;

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            resultset = stmt.executeQuery();
            if (resultset.next()) {
                stmt.close();
                return true;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }


    public boolean cpfValidado(String cpf) {
        String novoCpf = "";
        int somaTotal;
        int i;
        int j;
        int indiceMaior;

        try {
            if (cpf.length() == 11) {

                for (i = 0; i < 2; i ++) {
                    somaTotal = 0;

                    if (i == 0) {
                        indiceMaior = 9;
                    }
                    else {
                        indiceMaior = 10;
                    }

                    for (j = 0; j < indiceMaior; j ++) {
                        if (i == 0) {
                            novoCpf += Character.toString(cpf.charAt(j));
                        }
                        char getNumero = cpf.charAt(j);
                        String numeroAtual = Character.toString(getNumero);
                        somaTotal += Integer.parseInt(numeroAtual) * (indiceMaior + 1 - j);
                    }

                    int equacao = 11 - (somaTotal % 11);

                    if (equacao > 9) {
                        novoCpf += "0";
                    }
                    else {
                        novoCpf += Integer.toString(equacao);
                    }

                }

            }

            String sequencia = Character.toString(novoCpf.charAt(0));
            for (i = 0; i < 10; i ++) {
                sequencia += Character.toString(sequencia.charAt(0));;
            }

            if (cpf.equals(novoCpf) && ! sequencia.equals(novoCpf)) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }
    public int escolher_Usuario(){
        int i = 0;
        List<Usuario> list = ListaUsuario();
        if(list.isEmpty()){
            return -1;
        }
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String[] tmp = new String[list.size()];
        String opc = "";
        String output = "";
        for (Usuario usuario : list) {
            tmp[i] =  i + "| " + "NOME : " + usuario.getNome() + " Possui o acesso de   : " + usuario.getAcessoUsuario();
            i++;
        }
        Object selectionObject = (String) JOptionPane.showInputDialog(frame,"Select Usuario : ","Usuarios",JOptionPane.QUESTION_MESSAGE,null, tmp, tmp[0]);
        Produto produto = new Produto();
        String pegaop = selectionObject.toString();
        StringTokenizer st = new StringTokenizer(pegaop);
        int id1 = Integer.parseInt(st.nextToken("|"));
        return id1;
    }
}
