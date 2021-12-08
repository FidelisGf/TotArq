package View;

import Controller.*;
import Controller.UnidadeController;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class AdministradorView {
    Scanner scan = new Scanner(System.in);

    public void menu_Chefe(){
        String op;
        int id = 0;
        while (true){
            op = exibeMenu();
            switch (op){
                case "1":
                    UnidadeController unidadeController = new UnidadeController();
                    id = unidadeController.listar().get(unidadeController.escolherUnidade()).getIdUnidade();
                    ProdutoView produtoView = new ProdutoView();
                    produtoView.menu(id);
                    break;
                case "2":
                    unidadeController = new UnidadeController();
                    id = unidadeController.listar().get(unidadeController.escolherUnidade()).getIdUnidade();
                    CategoriaView categoriaView = new CategoriaView();
                    categoriaView.Menu(id);
                case "3":
                    UnidadeView unidadeView = new UnidadeView();
                    unidadeView.menuUnidade();
                    break;
                case "4":
                    UsuarioView usuarioView = new UsuarioView();
                    usuarioView.funcionarioView();
                    break;
                case "5":
                     EmpresaView empresaView = new EmpresaView();
                     empresaView.menuEmpresaView();
                case "6":
                    RelatorioView relatorioView = new RelatorioView();
                    relatorioView.menuRelatorio();
                    break;
                case "7":
                    EstoqueView estoqueView = new EstoqueView();
                    estoqueView.menuEstoque(id);
                case "8":
                    TotemView totemView = new TotemView();
                    totemView.menu_Totfast();
                    break;
            }
        }
    }
    public String exibeMenu(){
        JDialog.setDefaultLookAndFeelDecorated(true);
        String[] escolhas = {"1", "2", "3", "4", "5", "6","7","8"};
        String menuTexto = "1 | Menu Produtos | " + "\n\n2 | Menu Categorias |" + "\n\n3 | Menu Unidades  |\n\n4 | Menu Usuarios  |\n\n5 | Menu Empresas  |\n\n6  | Relatorios  |\n\n7  | Menu Estoque |\n\n8  | Sair |\n\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n" + menuTexto,"Menu", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
}
