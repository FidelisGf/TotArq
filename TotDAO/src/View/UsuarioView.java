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
        usuario.setSenhaUsuario(JOptionPane.showInputDialog(null, "Senha :").trim());
        status = controleUsuario.loginUsuarioController(usuario);
    }

    public void cadastroUsuarioView(){
        String status;
        UnidadeController unidadeController = new UnidadeController();
        usuario.setNome((String) JOptionPane.showInputDialog(null, "Nome - > ").trim());
        usuario.setCpf((String) JOptionPane.showInputDialog(null, "Cpf - > ").trim());
        usuario.setEndereco((String) JOptionPane.showInputDialog(null, " Endereço - >").trim());
        usuario.setNomeUsuario((String) JOptionPane.showInputDialog(null, " Nome do Usuario - >").trim());
        usuario.setSenhaUsuario((String) JOptionPane.showInputDialog(null, "Senha do Usuario - >").trim());
        usuario.setAcessoUsuario(exibeMenuAcesso());
        usuario.setUnidadeUsuario(String.valueOf(unidadeController.listar().get(unidadeController.escolherUnidade()).getIdUnidade()));
        status = controleUsuario.cadastroUsuarioController(usuario);
        System.out.println(status);
    }
    public String exibeMenuAcesso(){
        String[] escolhas = {"administrador", "supervisor", "funcionario"};
        String menuTexto = "Permissões : ";
        return (String) JOptionPane.showInputDialog(null,"Acesso  :\n\n" + menuTexto,"MenuPermissões", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }

    public void visualizarUsuarioView(){
        String usuarios = "|           NOME                  |               CPF                 |            ENDERECO                    |     NOME_USUARIO     |         SENHA            |            ACESSO               |       UNIDADE        |\n";
        String tmp = "";
        for(Usuario usuario : controleUsuario.listarUsuarios()){
            tmp += "    " + usuario.getNome() + "                    " + usuario.getCpf() + "             " + usuario.getEndereco() + "                " + usuario.getNomeUsuario() + "                      " +
                    "          " + usuario.getSenhaUsuario() + "                        " + usuario.getAcessoUsuario() + "                 " + usuario.getUnidadeUsuario();
            usuarios += tmp + "\n\n";
        }
        JOptionPane.showMessageDialog(null, usuarios);
    }
    public void visualizarUsuarioByIdView(){
        System.out.print("Informe o ID do funcionario: ");
        System.out.println(controleUsuario.visualizarUsuarioByIdController(scan.nextLine()));
    }

    public void editarUsuarioView() {
        try {
            System.out.println(controleUsuario.visualizarUsuarioController());
        }catch (IOException e){
            throw new RuntimeException();
        }
        String status;
        System.out.print("Digite o ID do usuario que deseja editar: ");
        String id = scan.nextLine().trim();

        status = controleUsuario.visualizarUsuarioByIdController(id);
        System.out.println(status);

        if (! status.equals("usuario nao encontrado!") && ! status.equals("nenhum usuario cadastrado!")) {  // se usuario existe

            System.out.print("\n1 - Editar / 2 - Deletar / outro - Cancelar: ");
            String option = scan.nextLine().trim();

            if (option.equals("1")) {
                System.out.print("Nome: ");
                usuario.setNome(scan.nextLine().trim());
                System.out.print("Cpf: ");
                usuario.setCpf(scan.nextLine().trim());
                System.out.print("Endereco: ");
                usuario.setEndereco(scan.nextLine().trim());
                System.out.print("Nome de Usuario: ");
                usuario.setNomeUsuario(scan.nextLine().trim());
                System.out.print("Senha: ");
                usuario.setSenhaUsuario(scan.nextLine().trim());
                System.out.print("Acesso [1 - Administrador / 2 - Supervisor / 3 - Funcionario]: ");
                usuario.setAcessoUsuario(scan.nextLine().trim());
                //uc.listarUnidade();
                System.out.print("Unidade: ");
                usuario.setUnidadeUsuario(scan.nextLine().trim());

                status = controleUsuario.editarUsuarioController(usuario, Integer.parseInt(id));
                System.out.println(status);
            }
            else if (option.equals("2")) {
                System.out.print("Tem certeza que deseja excluir? [sim/nao]: ");
                String confirma = scan.nextLine().trim().toLowerCase();
                if (confirma.equals("sim")) {
                    usuario.setIdUsuario(Long.parseLong(id));
                    status = controleUsuario.deletarUsuarioController(Integer.parseInt(id));
                    System.out.println(status);
                }
            }
        }
    }
}
