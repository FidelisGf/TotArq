package View;

import Controller.*;
import Model.*;
import Controller.*;
import java.util.Scanner;
import java.io.*;
import Dao.*;
import javax.swing.*;

public class UsuarioView {
    Usuario usuario = new Usuario();
    UsuarioController controleUsuario = new UsuarioController();
    Scanner scan = new Scanner(System.in);
    UsuarioDAO usuarioDao = new UsuarioDAO();
    UnidadeController uc = new UnidadeController();

    public void funcionarioView() throws IOException {
        String menu = "1 - Cadastrar Funcionario\n" +
                "2 - Visualizar Funcionarios\n" +
                "3 - Visualizar Funcionario Por ID\n" +
                "4 - Editar Funcionarios\n" +
                "outro - Voltar\n";

        controleUsuario.funcionarioController(JOptionPane.showInputDialog(menu).trim());
    }

    public void loginUsuarioView() throws IOException {
        String status;
        usuario.setNomeUsuario(JOptionPane.showInputDialog("Usuario: ").trim());
        usuario.setSenhaUsuario(JOptionPane.showInputDialog("Senha: ").trim());
        status = controleUsuario.loginUsuarioController(usuario);
        JOptionPane.showMessageDialog(null, status);
    }

    public void cadastroUsuarioView() throws IOException{
        String status;

        usuario = inserirDadosUsuario();

        status = controleUsuario.cadastroUsuarioController(usuario);
        JOptionPane.showMessageDialog(null, status);
    }

    public void visualizarUsuarioView() throws IOException {
        String usuarios = controleUsuario.visualizarUsuarioController();
        JOptionPane.showMessageDialog(null, usuarios);
    }

    public void visualizarUsuarioByIdView() throws IOException{
        String id = JOptionPane.showInputDialog("Informe o ID do funcionario: ").trim();
        JOptionPane.showMessageDialog(null, controleUsuario.visualizarUsuarioByIdController(id));
    }

    public void editarUsuarioView() throws IOException {
        JOptionPane.showMessageDialog(null, controleUsuario.visualizarUsuarioController());
        String status;
        String id = JOptionPane.showInputDialog("Digite o ID do usuario que deseja editar: ").trim();

        status = controleUsuario.visualizarUsuarioByIdController(id);
        JOptionPane.showMessageDialog(null, status);

        if (! status.equals("usuario nao encontrado!") && ! status.equals("nenhum usuario cadastrado!")) {  // se usuario existe
            String option = JOptionPane.showInputDialog("\n1 - Editar / 2 - Deletar / outro - Cancelar: ").trim();

            if (option.equals("1")) {
                usuario = inserirDadosUsuario();
                status = controleUsuario.editarUsuarioController(usuario, Integer.parseInt(id));
                JOptionPane.showMessageDialog(null, status);
            }
            else if (option.equals("2")) {
                String confirma = JOptionPane.showInputDialog("Tem certeza que deseja excluir? [sim/nao]: ").trim().toLowerCase();
                if (confirma.equals("sim")) {
                    usuario.setIdUsuario(Long.parseLong(id));
                    status = controleUsuario.deletarUsuarioController(Integer.parseInt(id));
                    JOptionPane.showMessageDialog(null, status);
                }
            }
        }
    }

    public Usuario inserirDadosUsuario() {
        Usuario u = new Usuario();

        u.setNome(JOptionPane.showInputDialog("Nome: ").trim());
        u.setCpf(JOptionPane.showInputDialog("Cpf: ").trim());
        u.setEndereco(JOptionPane.showInputDialog("Endereco: ").trim());
        u.setNomeUsuario(JOptionPane.showInputDialog("Nome de Usuario: ").trim());
        u.setSenhaUsuario(JOptionPane.showInputDialog("Senha: ").trim());
        u.setAcessoUsuario(JOptionPane.showInputDialog("Acesso [1 - Administrador / 2 - Supervisor / 3 - Funcionario]: ").trim());
        JOptionPane.showMessageDialog(null, uc.listarUnidade());
        u.setUnidadeUsuario(JOptionPane.showInputDialog("Unidade: ").trim());

        return u;
    }
}
