package View;

import Controller.ProdutoController;
import Model.Categoria;
import Model.Produto;

import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    Menu menu = new Menu();
    Scanner le = new Scanner(System.in);
    ProdutoController produtoController = new ProdutoController();
    public void menu(int id){
        while(true){
            System.out.printf("------------------------------------------------------\n");
            System.out.printf("|||  (1) Criar novo produto                      |||\n");
            System.out.printf("|||  (2) Listar Todos os produtos                |||\n");
            System.out.printf("|||  (3) Listar Produtos Por Categoria           |||\n");
            System.out.printf("|||  (4) Editar Um Produto                       |||\n");
            System.out.printf("|||  (5) Sair                                    |||\n");
            System.out.printf("------------------------------------------------------\n");
            int op;
            op = le.nextInt();
            le.nextLine();
            switch (op){
                case 1:
                    insereProduto(id);
                    menu.espera_Enter();
                    break;
                case 2:
                    listarTodosProdutos(id);
                    menu.espera_Enter();
                    break;
                case 3:
                    listarProdutoporCategoria(id);
                    menu.espera_Enter();
                    break;
                case 4:
                    editarProduto(id);
                    menu.espera_Enter();
                    break;
                case 5:
                    menu.menu_Principal();
                    break;
            }
        }
    }
    public void insereProduto(int id){
        CategoriaView categoriaView = new CategoriaView();
        String tmp;
        float tmp1;
        int tmp2;
        System.out.printf("Digite um nome para o produto : \n");
        tmp = le.nextLine();
        System.out.printf("Digite um valor para o produto : \n");
        tmp1 = le.nextFloat();
        le.nextLine();
        System.out.printf("Digite o ID da categoria desse produto : \n");
        categoriaView.listartodos(id);
        tmp2 = le.nextInt();
        le.nextLine();
        Produto produto = new Produto(tmp ,tmp1, tmp2);
        produtoController.insereProduto(produto);
    }
    public void listarProdutoporCategoria(int id2){
        System.out.printf("Qual a Categoria do produto ?");
        int op = le.nextInt();
        le.nextLine();
        List<Produto> list = produtoController.listaProdutoporCategoria(op, id2);
        for (Produto Produto : list){
            System.out.printf("Id : %d   |||  Nome : %s     |||  Valor : %.2f  ||| Categoria : %d \n",Produto.getId(), Produto.getNome(), Produto.getPreco(), Produto.getIdCategoria());
        }
    }
    public void listarTodosProdutos(int id){
        System.out.println("Os produtos Cadastrados no sistema são : ");
        List<Produto> list = produtoController.listarTodosProdutos(id);
        for (Produto Produto : list){
            System.out.printf("Id : %d   |||  Nome : %s     |||  Valor : %.2f  ||| Categoria : %d\n",Produto.getId(), Produto.getNome(), Produto.getPreco(), Produto.getIdCategoria());
        }
    }
    public void editarProduto(int id){
        int i = 0;
        List<Produto> list = produtoController.listarTodosProdutos(id);
        for (Produto Produto : list){
            System.out.printf("%d  ||| Id : %d   |||  Nome : %s     |||  Valor : %.2f  ||| Categoria : %d \n", i ,Produto.getId(), Produto.getNome(), Produto.getPreco(), Produto.getIdCategoria());
            i++;
        }
        System.out.println("Qual produto voce deseja editar ? ");
        int tmp = le.nextInt();
        le.nextLine();
        Produto produto = new Produto();
        produto.setId(list.get(tmp).getId());
        int op = 0;
        System.out.println("O que voce deseja alterar no produto : ");
        System.out.printf("|| (1) Editar nome                   ||\n");
        System.out.printf("|| (2) Editar Valor                  ||\n");
        System.out.printf("|| (3) Editar Categoria do produto : ||\n");
        op = le.nextInt();
        le.nextLine();
        switch (op){
            case 1:
                System.out.println("Digite um novo nome para o produto : ");
                String tmp1 = le.nextLine();
                produto.setNome(tmp1);
                produtoController.editarProduto(produto, op);
                System.out.println("Produto Editado com sucesso !");
                break;
            case 2:
                System.out.println("Digite um novo valor para o produto :");
                float valor = le.nextFloat();
                le.nextLine();
                produto.setPreco(valor);
                produtoController.editarProduto(produto, op);
                System.out.println("Produto Editado com sucesso !");
                break;
            case 3:
                i = 0;
                CategoriaView categoriaView = new CategoriaView();
                System.out.println("Escolha uma nova categoria : ");
                List<Categoria> list1 = categoriaView.listartodos(id);
                op = le.nextInt();
                le.nextLine();
                produto.setIdCategoria(list1.get(op).getId());
                System.out.println(produto);
                produtoController.editarProduto(produto, op);
                System.out.println("Produto Editado com sucesso !");
                break;

        }


    }
}
