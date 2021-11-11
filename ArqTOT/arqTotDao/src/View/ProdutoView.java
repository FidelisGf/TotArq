package View;

import Controler.ProdutoController;
import Model.Categoria;
import Model.Produto;

import java.io.IOException;
import java.util.Scanner;

public class ProdutoView {
    ProdutoController produtoController = new ProdutoController();
    Produto produto = new Produto();
    Scanner le = new Scanner(System.in);
    Menu menu = new Menu();
    public void MenuProdutos() throws IOException {
        String op = "";
        while (true){
            System.out.println("_________________________");
            System.out.printf(" | 1 Criar Novo Produto |\n");
            System.out.printf(" | 2 Listar Produtos    |\n");
            System.out.printf(" | 3 Excluir Produto    |\n");
            System.out.printf(" | 4 Editar Produto     |\n");
            System.out.printf(" | 5 Voltar             |\n");
            System.out.println("_________________________");
            op = le.nextLine();
            switch (op){
                case "1":
                    Registrar_Produto();
                    menu.esperaENTER();
                    break;
                case "2":
                    listar_Produtos();
                    menu.esperaENTER();
                    break;
                case "3":
                    excluir_Produto();
                    menu.esperaENTER();
                    break;
                case "4":
                    editar_Produto();
                    menu.esperaENTER();
                    break;
                case "5":
                    menu.Menu_Principal();
                    break;
                default:
                    System.out.println("Opção Invalida !");
                    menu.esperaENTER();
                    break;
            }
        }
    }
    public void Registrar_Produto(){
        System.out.println("Digite o nome do produto :");
        produto.setNomeProduto(le.nextLine().toUpperCase());
        System.out.println("Digite o valor do produto : ");
        produto.setValorProduto(le.nextFloat());
        le.nextLine();
        System.out.println("Selecione uma Categoria : ");
        CategoriaView categoriaView = new CategoriaView();
        categoriaView.listar_Categorias();
        int op  = le.nextInt();
        le.nextLine();
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(categoriaView.escolher_Categoria().get(op));
        produtoController.Registrar_Produto(produto, categoria);
    }

    public void listar_Produtos(){
        CategoriaView categoriaView = new CategoriaView();
        Categoria categoria = categoriaView.escolher_Categoria_PorNome();
        for(Produto produto : produtoController.Listar_Produto_Categoria(categoria)){
            System.out.println("Id do Produto : " + produto.getIdProduto() + "  Nome do Produto : " + produto.getNomeProduto() + "  Valor do Produto :" + produto.getValorProduto());
        }
    }
    public void excluir_Produto(){
        int i = 0;
        CategoriaView categoriaView = new CategoriaView();
        Categoria categoria = categoriaView.escolher_Categoria_PorNome();
        int op = escolher_Produto(categoria);
        produtoController.excluir_Produto(categoria,op);
    }
    public void editar_Produto(){
        String escolha = "";
        CategoriaView categoriaView = new CategoriaView();
        Categoria categoria = categoriaView.escolher_Categoria_PorNome();
        int op = escolher_Produto(categoria);
        le.nextLine();
        System.out.printf("_____________________________\n");
        System.out.printf(" |    (1) Editar Nome     |\n");
        System.out.printf(" |    (2) Editar Valor    |\n");
        System.out.printf("_____________________________\n");
        Produto produto = produtoController.Retorna_Produto(categoria,op);
        switch (escolha = le.nextLine()){
            case "1":
                System.out.println("Selecione um novo Nome para " + produto.getNomeProduto());
                produto.setNomeProduto(le.nextLine().toUpperCase());
                break;
        }
        produtoController.editar_Produto(categoria, op, escolha, produto);
    }
    public int escolher_Produto(Categoria categoria){
        int op;
        int i = 0;
        for(Produto produto : produtoController.Listar_Produto_Categoria(categoria)){
            System.out.println( i + " Nome do Produto : " + produto.getNomeProduto() + "  Valor do Produto : " + produto.getValorProduto());
            i++;
        }
        System.out.println("Selecione um produto :");
        return op = le.nextInt();
    }

}
