package View;
import Controller.UnidadeController;
import Model.Unidade;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UnidadeView {
    Scanner scan = new Scanner(System.in);
    UnidadeController uc = new UnidadeController();

    public void menuUnidade() throws IOException{
        Scanner scan = new Scanner(System.in);
        String escolha;
        while(true){
            escolha = JOptionPane.showInputDialog("1 - Cadastrar Unidade\n2 - Listar Unidades\n3 - Buscar Unidade\n4 - Editar Unidade\n5 - Visualizar Funcionários da Unidade\n6 - Excluir Unidade\n\n Insira uma das opções acima:");
            switch (escolha) {
                case "1":
                    cadastrarUnidade();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null,uc.listarUnidade());
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
                    try {
                        editarUnidade();
                    }catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "5":
                    try {
                        listarFuncUnidade();
                    }catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "6":
                    try {
                        excluirUnidade();
                    }catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!");
                    break;
            }
        }
    }

    public void cadastrarUnidade(){
        String nick = JOptionPane.showInputDialog("Insira o nome da unidade");
        String ende = JOptionPane.showInputDialog("Insira o endereço da unidade");
        String estado = JOptionPane.showInputDialog("Insira a UF da unidade");
        Unidade u1 = new Unidade(nick, ende, estado);
        uc.cadastrarUnidade(u1);
    }

    public void buscarUnidadeById() throws SQLException {
        String idSelect = JOptionPane.showInputDialog(uc.listaNomeId());
        JOptionPane.showMessageDialog(null, uc.buscaUnidadeById(Integer.parseInt(idSelect)));
    }

    public void listarFuncUnidade() throws SQLException {
        String pegaId = JOptionPane.showInputDialog(uc.listaNomeId() + "\nDigite o ID da Unidade");
        JOptionPane.showMessageDialog(null,uc.listaFuncDaUnd(pegaId));
    }

    public void editarUnidade() throws SQLException {
        String idSelect = JOptionPane.showInputDialog(uc.listaNomeId() + "\nDigite o ID da Unidade que deseja editar");
        String nome = JOptionPane.showInputDialog("Insira o nome da unidade");
        String endereco = JOptionPane.showInputDialog("Insira o endereço da unidade");
        String uf = JOptionPane.showInputDialog("Insira a UF da unidade");
        Unidade unidade = new Unidade(nome, endereco, uf);
        uc.editarUnidade(unidade, Integer.parseInt(idSelect));
    }

    public void excluirUnidade() throws SQLException {
        String idSelect = JOptionPane.showInputDialog(uc.listaNomeId() + "\nDigite o ID da Unidade que deseja excluir: ");
        uc.excluirUnidade(Integer.parseInt(idSelect));
    }
}