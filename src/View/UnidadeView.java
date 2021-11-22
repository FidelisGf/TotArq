package View;
import Controller.UnidadeController;
import Dao.UnidadeDao;
import Model.Unidade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UnidadeView {
    private int escolha;
    Scanner scan = new Scanner(System.in);
    UnidadeController uc = new UnidadeController();

    public void menuUnidade() throws IOException{
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("******************************************");
            System.out.println("*         1 - Cadastrar Unidade          *");
            System.out.println("*         2 - Listar Unidades            *");
            System.out.println("*         3 - Buscar Unidade             *");
            System.out.println("*         4 - Editar Unidade             *");
            System.out.println("*         5 - Excluir Unidade            *");
            System.out.println("*         6 - Sair                       *");
            System.out.println("******************************************");
            escolha = scan.nextInt();
            switch (escolha) {
                case 1:
                    cadastrarUnidade();
                    break;
                case 2:
                    uc.listarUnidade();
                    break;
                case 3:
                    try {
                        buscarUnidadeById();
                    }
                    catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    editarUnidade();
                    break;
                case 5:
                    excluirUnidade();
                    break;
                case 6:
                    System.out.println("Xau!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (escolha != 6);
    }

    public void cadastrarUnidade(){
        String nick, ende, estado;
        System.out.println("Nome da Unidade: ");
        nick = scan.nextLine();
        System.out.println("Endereço da Unidade: ");
        ende = scan.nextLine();
        System.out.println("UF: ");
        estado = scan.nextLine();
        Unidade u1 = new Unidade(nick, ende, estado);
        uc.cadastrarUnidade(u1);
    }

    public void buscarUnidadeById() throws SQLException {
        System.out.println("Digite o ID da Unidade que deseja buscar");
        int idSelect = scan.nextInt();
        scan.nextLine();
        uc.buscaUnidadeById(idSelect);
    }

    public void editarUnidade(){
        System.out.println("Digite o ID da Unidade que deseja buscar");
        int idSelect = scan.nextInt();
        scan.nextLine();
        String nome, endereco, uf;
        System.out.println("Nome da Unidade: ");
        nome = scan.nextLine();
        System.out.println("Endereço da Unidade: ");
        endereco = scan.nextLine();
        System.out.println("UF: ");
        uf = scan.nextLine();
        Unidade unidade = new Unidade(nome, endereco, uf);
        uc.editarUnidade(unidade, idSelect);
    }

    public void excluirUnidade(){
        System.out.println("Digite o ID da Unidade que deseja buscar");
        int idSelect = scan.nextInt();
        scan.nextLine();
        uc.excluirUnidade(idSelect);
    }
}