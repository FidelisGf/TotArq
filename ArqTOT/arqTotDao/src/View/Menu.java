package View;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public void Menu_Principal(){
        String op = "";
        while (true){
            System.out.println("-----------------------------------");
            System.out.printf(" | (1) Menu Produtos             |\n\n");
            System.out.printf(" | (2) Menu Categorias           |\n");
            System.out.println("-----------------------------------");
            op = scanner.nextLine();
            switch (op){
                case "1":
                    ProdutoView produtoView = new ProdutoView();
                    produtoView.MenuProdutos();
                    break;
            }
        }
    }
    public void esperaENTER(){
        String op = "";
        System.out.printf("Pressione ENTER para Continuar");
        op = scanner.nextLine();
    }
}
