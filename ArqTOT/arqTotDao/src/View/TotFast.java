package View;

import Controler.ProdutoController;
import Model.Categoria;
import Model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TotFast {
    Scanner le = new Scanner(System.in);
    ProdutoController produtoController = new ProdutoController();
    CarrinhoView carrinhoView = new CarrinhoView();
    public void Comprar(){
        int i = 0;
        List<Produto> list = new ArrayList<>();

        System.out.println("-----------------------------");
        System.out.println("|        Bem-Vindo          |");
        System.out.println("|           ao              |");
        System.out.println("|         TOT-FAST          |");
        System.out.println("-----------------------------");
        System.out.println("Toque na tela para continuar :");
        String tmp3 = le.nextLine();
        while (true){
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
            System.out.println("Tecle ENTER para continuar ou 3 para Finalizar a compra");
            String tmp = le.nextLine();
            if(tmp.contains("3")){
                carrinhoView.finalizar_Compra();
                break;
            }
            i = 0;
        }
    }
    public void MenuTotem(){
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
                    Menu menu = new Menu();
                    menu.Menu_Principal();
                    break;
            }
        }
    }
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
