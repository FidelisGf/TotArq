package View;

import Controller.UnidadeController;
import Model.*;
import Controller.*;
import java.util.Scanner;
import java.io.*;
import DAO.*;

import javax.swing.*;

public class UsuarioView {
    Usuario usuario = new Usuario();
    UsuarioController controleUsuario = new UsuarioController();
    Scanner scan = new Scanner(System.in);
    UsuarioDAO usuarioDao = new UsuarioDAO();
    UnidadeController uc = new UnidadeController();

    public void funcionarioView(){
        controleUsuario.funcionarioController(exibeMenuFuncionario());
    }
    public String exibeMenuFuncionario(){
        String[] escolhas = {"1", "2", "3","4","5"};
        String menuTexto = "1 | Cadastrar Funcionario  |\n\n2 | Visualizar Funcionarios |\n\n3 | Visualizar Funcionario Por ID  |\n\n4 | Editar Funcionarios  |\n\n5 | Sair  |";
        return (String) JOptionPane.showInputDialog(null,"Menu Usuario\n\n" + menuTexto,"MenuPermissões", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }

    public void loginUsuarioView(){
        String status;
        usuario.setNomeUsuario(JOptionPane.showInputDialog(null, "Usuario : ").trim());
        JPasswordField password = new JPasswordField(10);
        password.setEchoChar('*');
        JLabel rotulo = new JLabel("Senha:\n");
        JPanel entUsuario = new JPanel();
        entUsuario.add(rotulo);
        entUsuario.add(password);

        // Mostra o rótulo e a caixa de entrada de password para o usuario fornecer a senha:
        JOptionPane.showMessageDialog(null, entUsuario, "Login", JOptionPane.PLAIN_MESSAGE);

        usuario.setSenhaUsuario(String.valueOf(password.getPassword()));
        status = controleUsuario.loginUsuarioController(usuario);
    }

    public void cadastroUsuarioView(){
        Usuario usuario = inserirDadosUsuario();
        String status = "";
        status = controleUsuario.cadastroUsuarioController(usuario);
        System.out.println(status);
    }
    public String exibeMenuAcesso(){
        String[] escolhas = {"administrador", "supervisor", "funcionario"};
        String menuTexto = "Permissões : ";
        return (String) JOptionPane.showInputDialog(null,"Acesso  :\n\n" + menuTexto,"MenuPermissões", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }

    public void visualizarUsuarioView(){
        String usuarios = "";
        String tmp = "";
        for(Usuario usuario : controleUsuario.listarUsuarios()){
            tmp = "| LOGIN do Usuario : " + usuario.getNomeUsuario() +  " | NOME do Usuario : " + usuario.getNome() + " | CPF do Usuario : " + usuario.getCpf() + " |  UNIDADE do Usuario : " + usuario.getUnidadeUsuario() + " |";
            usuarios += tmp + "\n\n";
        }
        JOptionPane.showMessageDialog(null, usuarios);
    }
    public void visualizarUsuarioByIdView(){
        int id = (int) controleUsuario.listarUsuarios().get(controleUsuario.escolher_Usuario()).getIdUsuario();
        JOptionPane.showMessageDialog(null, controleUsuario.visualizarUsuarioByIdController(id));
    }

    public void editarUsuarioView() {
        String status;
        int id = controleUsuario.escolher_Usuario();
        id = (int) controleUsuario.listarUsuarios().get(id).getIdUsuario();
        status = controleUsuario.visualizarUsuarioByIdController(id);
        //System.out.println(status);

        if (! status.equals("usuario nao encontrado!") && ! status.equals("nenhum usuario cadastrado!")) {  // se usuario existe
            String option = JOptionPane.showInputDialog("\n1 - Editar / 2 - Deletar / outro - Cancelar: ").trim();

            if (option.equals("1")) {
                usuario = inserirDadosUsuario();
                status = controleUsuario.editarUsuarioController(usuario,id);
                JOptionPane.showMessageDialog(null, status);
            }
            else if (option.equals("2")) {
                String confirma = JOptionPane.showInputDialog("Tem certeza que deseja excluir? [sim/nao]: ").trim().toLowerCase();
                if (confirma.equals("sim")) {
                    usuario.setIdUsuario(id);
                    status = controleUsuario.deletarUsuarioController(id);
                    JOptionPane.showMessageDialog(null, status);
                }
            }
        }
    }
    public Usuario inserirDadosUsuario(){
        String status;
        Usuario u = new Usuario();
        UnidadeController unidadeController = new UnidadeController();
        u.setNome((String) JOptionPane.showInputDialog(null, "Nome - > ").trim());
        u.setCpf((String) JOptionPane.showInputDialog(null, "Cpf - > ").trim());
        u.setEndereco((String) JOptionPane.showInputDialog(null, " Endereço - >").trim());
        u.setNomeUsuario((String) JOptionPane.showInputDialog(null, " Nome do Usuario - >").trim());
        u.setSenhaUsuario((String) JOptionPane.showInputDialog(null, "Senha do Usuario - >").trim());
        u.setAcessoUsuario(exibeMenuAcesso());
        u.setUnidadeUsuario(String.valueOf(unidadeController.listar().get(unidadeController.escolherUnidade()).getIdUnidade()));
        System.out.println(u);
        return u;
    }
}
