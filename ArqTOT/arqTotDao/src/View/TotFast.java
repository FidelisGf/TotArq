package View;

import Controler.CarrinhoController;
import Controler.ProdutoController;
import Model.Categoria;
import Model.Produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TotFast {
    Scanner le = new Scanner(System.in);
    Menu menu = new Menu();
    ProdutoController produtoController = new ProdutoController();
    CarrinhoView carrinhoView = new CarrinhoView();
    CarrinhoController carrinhoController = new CarrinhoController();
    public void Comprar(){
        int i = 0;

        System.out.printf("*----------------------------------*\n");
        System.out.printf("*        Seja Bem-Vindo            *\n");
        System.out.printf("*             ao                   *\n");
        System.out.printf("*          Tot-Fast                *\n");
        System.out.printf("*----------------------------------*\n");
        System.out.println("Pressione Qualquer tecla para continuar...");
        String tecla = le.nextLine();
        while (true){
            System.out.println("* (1) Adicionar ao carrinho *");
            System.out.println("* (2) Listar o carrinho     *");
            System.out.println("* (3) Excluir do carrinho   *");
            System.out.println("* (4) Finalizar a compra    *");
            switch (le.nextLine()){
                case "1":
                    adicionar_Carrinho();
                    menu.esperaENTER();
                    menu.pula_linha();
                    break;
                case "2":
                    carrinhoView.listar_Carrinho();
                    menu.esperaENTER();
                    menu.pula_linha();
                    break;
                case "3":
                    carrinhoView.excluir_do_Carrinho();
                    menu.esperaENTER();
                    menu.pula_linha();
                    break;
                case "4":
                    carrinhoView.finalizar_Compra();
                    System.exit(0);
                    break;
            }
        }
    }
    public void MenuTotem() throws IOException {
        while (true){
            System.out.printf(" _________________________________\n");
            System.out.printf(" |   (1) Modulo de Venda         |\n");
            System.out.printf(" |   (2) Configurações Gerais    |\n");
            System.out.printf(" _________________________________\n");
            switch (le.nextLine()){
                case "1":
                    Comprar();
                    break;
                case "2":
//                    Menu menu = new Menu();
//                    menu.Menu_Principal();
                    UsuarioView usuario = new UsuarioView();
                    usuario.loginUsuarioView();
                    break;
            }
        }

    }
    public void adicionar_Carrinho(){
        List<Produto> list = new ArrayList<>();
        int i = 0;
        System.out.println("Selecione uma categoria para acessar : ");
        CategoriaView categoriaView = new CategoriaView();
        Categoria categoria = categoriaView.escolher_Categoria_PorNome();
        for(Produto produto : produtoController.Listar_Produto_Categoria(categoria)){
            System.out.println( i + " Nome do Produto : " + produto.getNomeProduto() + "  Valor do Produto : " + produto.getValorProduto());
            i++;
        }
        System.out.println("Escolha o produto que voce deseja adicionar ao carrinho : ");
        int op = le.nextInt();
        le.nextLine();
        list.add(produtoController.Retorna_Produto(categoria, op));
        carrinhoView.adiciona_Carrinho(list);
    }
}
