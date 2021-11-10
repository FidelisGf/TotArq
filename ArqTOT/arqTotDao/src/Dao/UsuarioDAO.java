package Dao;

import java.io.*;
import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UsuarioDAO {
    private long idUsuario;
    private File caminhoUsuarios = new File("./config/usuarios.txt");
    private File caminhoIdUsuario = new File("./config/idUsuarios.txt");

    public long getIdUsuario() throws IOException {
        if (caminhoIdUsuario.exists()) {  // se ja tiver alguem cadastrado
            FileReader file = new FileReader(caminhoIdUsuario);
            BufferedReader fileR = new BufferedReader(file);
            this.idUsuario = Long.parseLong(fileR.readLine());
            file.close();
        }
        else {  // nao existe id cadastrado, entao cria o arquivo com o id (0)
            FileWriter file = new FileWriter(caminhoIdUsuario);
            PrintWriter fileW = new PrintWriter(file);

            fileW.println("0");  // id inicial (0)
            file.close();
            this.idUsuario = 0;
        }

        return this.idUsuario + 1;
    }

    public void setIdUsuario(long idUsuario) throws IOException{
        FileWriter file = new FileWriter(caminhoIdUsuario);
        PrintWriter fileW = new PrintWriter(file);
        fileW.println(idUsuario);
        file.close();
        this.idUsuario = idUsuario;
    }

    public String cadastroUsuarioDAO(Usuario usuario) throws IOException {
        if (usuario.getAcessoUsuario() != null) {
            FileWriter file = new FileWriter(caminhoUsuarios, true);
            PrintWriter fileW = new PrintWriter(file);
            fileW.println(usuario);
            file.close();
            return usuario.getNomeUsuario() + " foi cadastrado com sucesso!";
        }
        else {
            return "erro ao cadastrar usuario!";
        }
    }

    public String visualizarUsuarioDAO() throws IOException{
        if (caminhoUsuarios.exists()) {
            FileReader file = new FileReader(caminhoUsuarios);
            BufferedReader fileR = new BufferedReader(file);
            String texto = fileR.readLine();
            String usuarios = "ID | NOME | SENHA | ACESSO | UNIDADE\n";

            while (texto != null) {
                usuarios += texto + '\n';
                texto = fileR.readLine();
            }

            file.close();

            return usuarios;
        }
        else {
            return "nenhum usuario cadastrado!";
        }
    }

    public String visualizarUsuarioByIdDAO(String id) throws IOException{
        if (caminhoUsuarios.exists()) {
            FileReader file = new FileReader(caminhoUsuarios);
            BufferedReader fileR = new BufferedReader(file);
            StringTokenizer token;
            String texto;
            String buffer;

            while (true) {
                texto = fileR.readLine();
                if (texto == null) {
                    break;
                }
                else {
                    token = new StringTokenizer(texto, " | ");
                    buffer = token.nextToken();
                    if (id.equals(buffer)) {
                        return "ID | NOME | SENHA | ACESSO | UNIDADE\n" + texto;
                    }
                }
            }
            file.close();
            return "usuario nao encontrado!";
        }
        else {
            return "nenhum usuario cadastrado!";
        }
    }

    public List<String> backupUsuarios(long id) throws IOException{
        FileReader file = new FileReader(caminhoUsuarios);
        BufferedReader fileR = new BufferedReader(file);
        StringTokenizer token;
        String bufferId;
        String texto;
        List<String> usuarios = new ArrayList<>();

        while (true) {
            texto = fileR.readLine();
            if (texto == null) {
                break;
            }
            token = new StringTokenizer(texto, " | ");
            bufferId = token.nextToken();
            if (id != Long.parseLong(bufferId)) {
                usuarios.add(texto);
            }
        }
        file.close();

        return usuarios;  // lista dos usuarios
    }

    public String editarUsuarioDAO(Usuario usuario) throws IOException{
        if (usuario.getAcessoUsuario() != null) {
            List<String> usuarios = backupUsuarios(usuario.getIdUsuario());
            FileWriter file = new FileWriter(caminhoUsuarios);
            PrintWriter fileW = new PrintWriter(file);

            for (int i = 0; i < usuarios.size(); i ++) {
                fileW.println(usuarios.get(i));
            }
            fileW.println(usuario);
            file.close();

            return usuario.getNomeUsuario() + " foi editado com sucesso!";
        }
        else {
            return "erro ao editar usuario!";
        }
    }

    public String deletarUsuarioDAO(Usuario usuario) throws IOException{
        List<String> usuarios = backupUsuarios(usuario.getIdUsuario());
        FileWriter file = new FileWriter(caminhoUsuarios);
        PrintWriter fileW = new PrintWriter(file);

        for (int i = 0; i < usuarios.size(); i ++) {
            fileW.println(usuarios.get(i));
        }
        file.close();

        return "usuario foi deletado com sucesso!";
    }

    public String loginUsuarioDAO(Usuario usuario) throws IOException{  // get() > 1 = usuario, 2 = senha, 3 = acesso
        if (caminhoUsuarios.exists()) {
            FileReader file = new FileReader(caminhoUsuarios);
            BufferedReader fileR = new BufferedReader(file);
            StringTokenizer token;
            String texto;
            List<String> linha;
            String acesso = null;

            while (true) {
                linha = new ArrayList<>();
                texto = fileR.readLine();
                if (texto == null) {
                    break;
                }
                token = new StringTokenizer(texto, " | ");
                while (token.hasMoreTokens()) {
                    linha.add(token.nextToken());
                }
                if (linha.get(1).equals(usuario.getNomeUsuario())) {
                    if (linha.get(2).equals(usuario.getSenhaUsuario())) {
                        acesso = linha.get(3);
                        acesso += "|" + linha.get(0);
                        break;
                    }
                }
            }
            file.close();
            return acesso;
        }
        else {
            return "";
        }
    }
}
