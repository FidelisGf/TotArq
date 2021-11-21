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

    public String editarUsuarioController(Usuario usuario, int id) throws IOException{
        return usuarioDao.editarUsuarioDAO(usuario, id);
    }

    public String deletarUsuarioController(int id) throws IOException{
        return usuarioDao.deletarUsuarioDAO(id);
    }

    public String loginUsuarioController(Usuario usuario) throws IOException{  // get() > 1 = usuario, 2 = senha, 3 = acesso
        String acesso = usuarioDao.loginUsuarioDAO(usuario);
        if (acesso != null) {
            if (acesso.equals("administrador")) {
                AdministradorView adm = new AdministradorView();
                adm.menuAdministradorView();  // manda o id do adm pra classe dele
            } else if (acesso.equals("supervisor")) {

            } else if (acesso.equals("funcionario")) {

            }
        }
        else {
            return "usuario nao cadastrado!";
        }
        return "";
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
