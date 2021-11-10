package Controler;
import Model.*;
import View.AdministradorView;

import java.io.*;

import Dao.*;
import View.UsuarioView;

import java.util.StringTokenizer;


public class UsuarioController {
    private UsuarioDAO usuarioDao = new UsuarioDAO();

    public String cadastroUsuarioController(Usuario usuario) throws IOException {
        return usuarioDao.cadastroUsuarioDAO(usuario);
    }

    public String visualizarUsuarioController() throws IOException{
        return usuarioDao.visualizarUsuarioDAO();
    }

    public String visualizarUsuarioByIdController(String id) throws IOException{
        return usuarioDao.visualizarUsuarioByIdDAO(id);
    }

    public String editarUsuarioController(Usuario usuario) throws IOException{
        return usuarioDao.editarUsuarioDAO(usuario);
    }

    public String deletarUsuarioController(Usuario usuario) throws IOException{
        return usuarioDao.deletarUsuarioDAO(usuario);
    }

    public String loginUsuarioController(Usuario usuario) throws IOException{  // get() > 1 = usuario, 2 = senha, 3 = acesso
        String info = usuarioDao.loginUsuarioDAO(usuario);

        // redireciona para a classe responsavel, de acordo com o login de acesso
        if (info != null) {
            StringTokenizer token = new StringTokenizer(info, "|");
            String acesso = token.nextToken();
            String id = token.nextToken();

            if (acesso.equals("administrador")) {
                AdministradorView adm = new AdministradorView();
                adm.executaAdministradorView(Long.parseLong(id));  // manda o id do adm pra classe dele
            } else if (acesso.equals("supervisor")) {

            } else if (acesso.equals("funcionario")) {

            }
        }
        return "usuario nao cadastrado!";
    }

    public void funcionarioController(String option) throws IOException {
        UsuarioView usuario = new UsuarioView();

        if (option.equals("1")) {  // cadastrar funcionario
            usuario.cadastroUsuarioView();
            usuario.funcionarioView();
        }
        else if (option.equals("2")) {  // visualizar funcionario
            usuario.visualizarUsuarioView();
            usuario.funcionarioView();
        }
        else if (option.equals("3")) {  // editar funcionario
            usuario.editarUsuarioView();
            usuario.funcionarioView();
        }
    }
}
