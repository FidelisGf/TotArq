package Controller;
import Model.*;
import View.AdministradorView;

import java.io.*;

import DAO.*;
import View.UsuarioView;

import java.util.List;
import java.util.StringTokenizer;


public class UsuarioController {
    private UsuarioDAO usuarioDao = new UsuarioDAO();

    public String cadastroUsuarioController(Usuario usuario){
        return usuarioDao.cadastroUsuarioDAO(usuario);
    }
    public int escolher_Usuario(){
        return usuarioDao.escolher_Usuario();
    }
    public String visualizarUsuarioController(){

        return usuarioDao.visualizarUsuarioDAO();

    }
    public List<Usuario> listarUsuarios(){
        return usuarioDao.ListaUsuario();
    }

    public String visualizarUsuarioByIdController(int id){
        return usuarioDao.visualizarUsuarioByIdDAO(id);
    }

    public String editarUsuarioController(Usuario usuario, int id){
        return usuarioDao.editarUsuarioDAO(usuario, id);
    }

    public String deletarUsuarioController(int id){
        return usuarioDao.deletarUsuarioDAO(id);
    }

    public String loginUsuarioController(Usuario usuario){  // get() > 1 = usuario, 2 = senha, 3 = acesso
        String acesso = usuarioDao.loginUsuarioDAO(usuario);
        if (acesso != null) {
            if (acesso.equals("administrador")) {
                AdministradorView adm = new AdministradorView();
                adm.menu_Chefe();  // manda o id do adm pra classe dele
            } else if (acesso.equals("supervisor")) {

            } else if (acesso.equals("funcionario")) {

            }
        }
        else {
            return "usuario nao cadastrado!";
        }
        return "";
    }

    public void funcionarioController(String option){
        UsuarioView usuario = new UsuarioView();

        if (option.equals("1")) {  // cadastrar funcionario
            usuario.cadastroUsuarioView();
            usuario.funcionarioView();
        }
        else if (option.equals("2")) {  // visualizar funcionario
            usuario.visualizarUsuarioView();
            usuario.funcionarioView();
        }
        else if (option.equals("3")) {  // visualizar funcionario by id
            usuario.visualizarUsuarioByIdView();
            usuario.funcionarioView();
        }
        else if (option.equals("4")) {  // editar funcionario
            usuario.editarUsuarioView();
            usuario.funcionarioView();
        }
    }
}
