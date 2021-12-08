package View;
import Controller.UnidadeController;
import Model.Administrador;
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
        String[] escolhas = {"1", "2", "3", "4", "5", "6","7"};
        String menuTexto = "1 | Cadastrar Unidade | " + "\n\n2 | Listar Unidades |" + "\n\n3 |  Buscar Unidade |\n\n4 | Editar Unidade |\n\n5 | Visualizar Funcionários da Unidade  |\n\n6 | Excluir Unidade |\n\n 7 | Sair   |\n\n";
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
                    listarFuncUnidade();
                    break;
                case "6":
                    excluirUnidade();
                    break;
                case "7":
                    AdministradorView administradorView = new AdministradorView();
                    administradorView.menu_Chefe();
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
        int idSelect = uc.listar().get(uc.escolherUnidade()).getIdUnidade();
        String nome = JOptionPane.showInputDialog("Insira o nome da unidade");
        String endereco = JOptionPane.showInputDialog("Insira o endereço da unidade");
        String uf = JOptionPane.showInputDialog("Insira a UF da unidade");
        Unidade unidade = new Unidade(nome, endereco, uf);
        uc.editarUnidade(unidade,idSelect);
    }

    public void excluirUnidade(){
        int idSelect = uc.listar().get(uc.escolherUnidade()).getIdUnidade();
        uc.excluirUnidade(idSelect);
    }
    public void listarFuncUnidade(){
        String pegaId = String.valueOf(uc.listar().get(uc.escolherUnidade()).getIdUnidade());
        JOptionPane.showMessageDialog(null,uc.listaFuncDaUnd(pegaId));
    }
}