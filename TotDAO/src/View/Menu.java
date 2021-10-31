package View;

import Model.Empresa;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner= new Scanner(System.in);

    public void espera_Enter(){
        System.out.printf("\n\nPRESSIONE ENTER PARA CONTINUAR");
        String tmp = scanner.nextLine();
    }
    public void menu_Principal(){
        String op;
        int id = 0;
        while (true){
            op = exibeMenu();
            switch (op){
                case "1":
                    EmpresaView empresaView = new EmpresaView();
                    id = empresaView.escolherEmpresa(id);
                    ProdutoView produtoView = new ProdutoView();
                    produtoView.menu(id);
                    break;
                case "2":
                    empresaView = new EmpresaView();
                    CategoriaView categoriaView = new CategoriaView();
                    id = empresaView.escolherEmpresa(id);
                    categoriaView.Menu(id);
                case "3":
                    empresaView = new EmpresaView();
                    empresaView.menu();
                    break;
                case "4":
                    empresaView = new EmpresaView();
                    id = empresaView.escolherEmpresa(id);
                    FuncionarioView funcionarioView = new FuncionarioView();
                    funcionarioView.MenuFuncionario(id);
                    break;
                case "5":
                    System.exit(0);
                    break;
            }
        }
    }
    public String exibeMenu(){
        String[] escolhas = {"1", "2", "3", "4", "5"};
        String menuTexto = "1 | Menu Produtos | " + "\n2 | Menu Categorias |" + "\n3 | Menu Empresas  |\n4 | Menu Funcionario  |\n5 | Sair |";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n" + menuTexto,"Menu", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
}
