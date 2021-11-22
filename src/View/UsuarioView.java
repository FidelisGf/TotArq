package View;

import Controller.UnidadeController;
import Model.*;
import Controler.*;
import java.util.Scanner;
import java.io.*;
import Dao.*;

public class UsuarioView {
    Usuario usuario = new Usuario();
    UsuarioController controleUsuario = new UsuarioController();
    Scanner scan = new Scanner(System.in);
    UsuarioDAO usuarioDao = new UsuarioDAO();
    UnidadeController uc = new UnidadeController();

    public void funcionarioView() throws IOException{
        System.out.println("*****************************");
        System.out.println("1 - Cadastrar Funcionario");
        System.out.println("2 - Visualizar Funcionarios");
        System.out.println("3 - Visualizar Funcionario Por ID");
        System.out.println("4 - Editar Funcionarios");
        System.out.println("outro - Voltar");
        System.out.println("*****************************");
        controleUsuario.funcionarioController(scan.nextLine().trim());
    }

    public void loginUsuarioView() throws IOException {
        String status;
        System.out.print("Usuario: ");
        usuario.setNomeUsuario(scan.nextLine().trim());
        System.out.print("Senha: ");
        usuario.setSenhaUsuario(scan.nextLine().trim());
        status = controleUsuario.loginUsuarioController(usuario);
        System.out.println(status);
    }

    public void cadastroUsuarioView() throws IOException{
        String status;

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
        uc.listarUnidade();
        System.out.print("Unidade: ");
        usuario.setUnidadeUsuario(scan.nextLine().trim());

        status = controleUsuario.cadastroUsuarioController(usuario);
        System.out.println(status);
    }

    public void visualizarUsuarioView() throws IOException {
        String usuarios = controleUsuario.visualizarUsuarioController();
        System.out.println(usuarios);
    }

    public void visualizarUsuarioByIdView() throws IOException{
        System.out.print("Informe o ID do funcionario: ");
        System.out.println(controleUsuario.visualizarUsuarioByIdController(scan.nextLine()));
    }

    public void editarUsuarioView() throws IOException {
        System.out.println(controleUsuario.visualizarUsuarioController());
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
                uc.listarUnidade();
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
