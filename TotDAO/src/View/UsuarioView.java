package View;

import Controller.UnidadeController;
import Model.*;
import Controller.*;

import java.awt.*;
import java.util.Scanner;
import java.io.*;
import DAO.*;
import com.sun.security.auth.module.JndiLoginModule;

import javax.swing.*;
import javax.swing.border.LineBorder;

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
        String menuTexto = "1 | Cadastrar Usuario |\n\n2 | Visualizar Usuarios |\n\n3 | Visualizar Usuario Por ID  |\n\n4 | Editar Usuarios  |\n\n5 | Sair  |";
        return (String) JOptionPane.showInputDialog(null,"Menu Usuario\n\n" + menuTexto,"MenuPermissões", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }

    public void loginUsuarioView(){
        String status;

        JPasswordField password = new JPasswordField(10);
        password.setEchoChar('*');
        JTextField User = new JTextField(8);
        User.setBorder(new LineBorder(Color.BLACK,1));
        JLabel rotulo1 = new JLabel("\nUsuario\n");
        JLabel rotulo = new JLabel("\nSenha:\n");
        JPanel entUsuario = new JPanel();
        entUsuario.add(rotulo1);
        entUsuario.add(User);
        entUsuario.add(rotulo);
        entUsuario.add(password);
        // Mostra o rótulo e a caixa de entrada de password para o usuario fornecer a senha:
        JOptionPane.showMessageDialog(null, entUsuario, "Login", JOptionPane.PLAIN_MESSAGE);
        usuario.setNomeUsuario(String.valueOf(User.getText()));
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
        usuario.setIdUsuario(id);

        if (! status.equals("usuario nao encontrado!") && ! status.equals("nenhum usuario cadastrado!")) {  // se usuario existe
            String option = JOptionPane.showInputDialog("\n1 - Editar / 2 - Deletar / outro - Cancelar: ").trim();

            if (option.equals("1")) {
                JOptionPane.showMessageDialog(null, "DEIXEI OS CAMPOS VAZIOS PARA CONTINUAR COM OS MESMOS DADOS");
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
        Usuario u_temp = new Usuario();
        u_temp = controleUsuario.carregaUsuario(usuario.getIdUsuario());
        UnidadeController unidadeController = new UnidadeController();

        String nome = (String) JOptionPane.showInputDialog(null, "Nome - > ").trim();
        String cpfUsuario = (String) JOptionPane.showInputDialog(null, "Cpf - > ").trim();
        String enderecoUsuario = (String) JOptionPane.showInputDialog(null, " Endereço - >").trim();
        String nomeUsuario = (String) JOptionPane.showInputDialog(null, " Nome do Usuario - >").trim();
        String senhaUsuario = (String) JOptionPane.showInputDialog(null, "Senha do Usuario - >").trim();
        String acessoUsuario = exibeMenuAcesso();
        String unidadeUsuario = String.valueOf(unidadeController.listar().get(unidadeController.escolherUnidade()).getIdUnidade());

        if (u_temp != null) {
            if (nome.length() == 0) {
                nome = u_temp.getNome();
            }
            if (cpfUsuario.length() == 0) {
                cpfUsuario = u_temp.getCpf();
            }
            if (enderecoUsuario.length() == 0) {
                enderecoUsuario = u_temp.getEndereco();
            }
            if (nomeUsuario.length() == 0) {
                nomeUsuario = u_temp.getNomeUsuario();
            }
            if (senhaUsuario.length() == 0) {
                senhaUsuario = u_temp.getSenhaUsuario();
            }
            if (acessoUsuario.length() == 0) {
                acessoUsuario = u_temp.getAcessoUsuario();
            }
            if (unidadeUsuario.length() == 0) {
                unidadeUsuario = u_temp.getUnidadeUsuario();
            }
        }

        u.setNome(nome);
        u.setCpf(cpfUsuario);
        u.setEndereco(enderecoUsuario);
        u.setNomeUsuario(nomeUsuario);
        u.setSenhaUsuario(senhaUsuario);
        u.setAcessoUsuario(acessoUsuario);
        u.setUnidadeUsuario(unidadeUsuario);

        return u;
    }
}
