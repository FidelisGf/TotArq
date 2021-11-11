package View;

import Model.Categoria;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public void Menu_Principal() throws IOException {
        String op = "";
        while (true){
            System.out.println("-----------------------------------");
            System.out.printf(" | (1) Menu Produtos             |\n");
            System.out.printf(" | (2) Menu Categorias           |\n");
            System.out.printf(" | (3) Menu Estoque              |\n");
            System.out.printf(" | (4) Sair                      |\n");
            System.out.println("-----------------------------------");
            op = scanner.nextLine();
            switch (op){
                case "1":
                    ProdutoView produtoView = new ProdutoView();
                    produtoView.MenuProdutos();
                    break;
                case "2":
                    CategoriaView categoriaView = new CategoriaView();
                    categoriaView.menuCategoria();
                    break;
                case "3":
                    ProdutoView produtoVer = new ProdutoView();
                    produtoVer.menuEstoque();
                    break;
                case "4":
                    TotFast totFast = new TotFast();
                    totFast.MenuTotem();
                    break;
                default:
                    System.out.println("Opção Invalida !");
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