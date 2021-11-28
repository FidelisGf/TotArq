package View;

import Controller.UnidadeController;
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
    public String exibeMenu(){
        String[] escolhas = {"1", "2", "3", "4", "5", "6"};
        String menuTexto = "1 | Menu Produtos | " + "\n2 | Menu Categorias |" + "\n3 | Menu Empresas  |\n4 | Menu Funcionario  |\n5 | Relatorios  |\n6  | Sair  |";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n" + menuTexto,"Menu", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }

    public String exibeMenuFuncionario(){
        String[] escolhas = {"1", "2", "3"};
        String menuTexto = "1 | Menu Produtos | " + "\n2 | Menu Categorias |" + "\n3 | Sair |";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n" + menuTexto,"Menu", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public String menuConfirmar(){
        String[] escolhas = {"1", "2"};
        String menuTexto = "\n\n1 | Sim | " + "\n\n2 | Nao |\n\n";
        return (String) JOptionPane.showInputDialog(null,"Tem certeza que deseja realizar essa ação  ? \n" + menuTexto,"Confirmação", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
}
