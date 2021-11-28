package View;
import Controller.UnidadeController;
import Model.Unidade;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UnidadeView {
    private int escolha;
    Scanner scan = new Scanner(System.in);
    UnidadeController uc = new UnidadeController();
    public String exibeMenuUnidade(){
        String[] escolhas = {"1", "2", "3", "4", "5", "6",};
        String menuTexto = "1 | Cadastrar Unidade | " + "\n\n2 | Listar Unidades |" + "\n\n3 |  Buscar Unidade |\n\n4 | Editar Unidade |\n\n 5 | Excluir Unidade |\n\n 6 | Sair   |\n\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuProdutos", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public void menuUnidade(){
        Scanner scan = new Scanner(System.in);
        do {
            String escolha = exibeMenuUnidade();
            switch (escolha) {
                case "1":
                    cadastrarUnidade();
                    break;
                case "2":
                    listarUnidade();
                    break;
                case "3":
                    try {
                        buscarUnidadeById();
                    }
                    catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "4":
                    editarUnidade();
                    break;
                case "5":
                    excluirUnidade();
                    break;
                case "6":
                    System.out.println("Xau!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (escolha != 6);
    }
    public void listarUnidade(){
        String output = "";
        for(Unidade unidade : uc.listar()){
            output += "Nome da Unidade : " + unidade.getNomeUnidade() + "\n\nEndereço da Unidade : " + unidade.getEnderecoUnidade() + "\n\n UF da Unidade : " +
                    unidade.getUfUnidade() + "\n\n";
        }
        JOptionPane.showMessageDialog(null, output);
    }

    public void cadastrarUnidade(){
        String nick, ende, estado;
        nick = JOptionPane.showInputDialog(null, "Digite o Nome da Unidade : ");
        ende = JOptionPane.showInputDialog(null, "Digite o Endereço da Unidade : ");
        estado = JOptionPane.showInputDialog(null, "Digite UF da Unidade : ");
        Unidade u1 = new Unidade(nick, ende, estado);
        uc.cadastrarUnidade(u1);
    }

    public void buscarUnidadeById() throws SQLException {
        int idSelect = uc.escolherUnidade();
        idSelect = uc.listar().get(idSelect).getIdUnidade();
        Unidade unidade = uc.buscaUnidadeById(idSelect);
        JOptionPane.showMessageDialog(null, "Nome da Unidade : " + unidade.getNomeUnidade() + "\n\nEndereço da Unidade : " +
                unidade.getEnderecoUnidade() + "\n\nUF da Unidade : " + unidade.getUfUnidade() + "\n\n");
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