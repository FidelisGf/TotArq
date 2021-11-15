package View;

import Controller.CategoriaController;
import Controller.ProdutoController;
import Model.Categoria;
import Model.Produto;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProdutoView {
    Menu menu = new Menu();
    Scanner le = new Scanner(System.in);
    ProdutoController produtoController = new ProdutoController();
    CategoriaController categoriaController = new CategoriaController();
    public void menu(int id){
        while(true){
            String op = exibeMenuProdutos();
            switch (op){
                case "1":
                    insereProduto(id);
                    break;
                case "2":
                    listarTodosProdutos(id);
                    break;
                case "3":
                    listarProdutoporCategoria(id);
                    break;
                case "4":
                    editarProduto(id);
                    break;
                case "5":
                    menu.menu_Chefe();
                    break;
            }
        }
    }
    public void insereProduto(int id){
        CategoriaView categoriaView = new CategoriaView();
        String nome = "";
        float tmp1 = 0;
        int tmp2 = 0;
        int qntd = 0;
        nome = (String) JOptionPane.showInputDialog(null, "Nome do Produto:").toUpperCase();
        tmp1 = Float.parseFloat(JOptionPane.showInputDialog(null, "Valor do Produto : "));
        qntd = Integer.parseInt(JOptionPane.showInputDialog(null,"Insira a quantidade inicial desse produto :"));
        tmp2 = categoriaView.escolherCategoria(id);
        Produto produto = new Produto(nome ,tmp1, tmp2, qntd);
        produtoController.insereProduto(produto);
    }
    public String exibeMenuProdutos(){
        String[] escolhas = {"1", "2", "3", "4", "5"};
        String menuTexto = "1 | Inserir Produto | " + "\n\n2 | Listar Todos os Produtos |" + "\n\n3 | Listar Produto por Categoria |\n\n4 | Editar Produto |\n\n 5 | Sair |\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuProdutos", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public void listarProdutoporCategoria(int id2){
        int op = categoriaController.escolherCategoria(id2);
        List<Produto> list = produtoController.listaProdutoporCategoria(op, id2);
        if(list.isEmpty()){
            JOptionPane.showInternalMessageDialog(null, "Categoria Vazia");
        }else{
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            String output = "";
            for (Produto produto : list) {
                String tmp = "NOME : " + produto.getNome() + " Com o VALOR de : R$" + String.valueOf(produto.getPreco());
                output += tmp + " \n\n";
            }
            JOptionPane.showMessageDialog(frame,output);
        }
    }
    public void listarTodosProdutos(int id){
        List<Produto> list = produtoController.listarTodosProdutos(id);
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String output = "";
        for (Produto produto : list) {
            String tmp = "NOME : " + produto.getNome() + " Com o VALOR de : R$" + String.valueOf(produto.getPreco());
            output += tmp + " \n\n";
        }
        JOptionPane.showMessageDialog(frame,output);
    }
    public void editarProduto(int id){
        int i = 0;
        int idCategoria = categoriaController.escolherCategoria(id);
        List<Produto> list = produtoController.listaProdutoporCategoria(idCategoria,id);
        int id1 = produtoController.escolher_produto(idCategoria,id);
        Produto produto = new Produto();
        produto.setId(list.get(id1).getId());
        produto.setNome(list.get(id1).getNome());
        int op = 0;
        String opc;
        String[] escolhas = {"1", "2", "3", "4"};
        String menuTexto = "1 | Editar Nome| " + "\n\n2 | Editar Valor |" + "\n\n3 | Edidar Categoria do Produto |\n\n4 | Adiconar Quantidade a um produto |\n";
        opc = (String) (JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"EditarProdutos", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]));
        op = Integer.parseInt(opc);
        switch (op){
            case 1:
                String tmp1 = (String) JOptionPane.showInputDialog(null, "Novo Nome para o Produto : " + produto.getNome());
                produto.setNome(tmp1);
                produtoController.editarProduto(produto, op);
                break;
            case 2:

                float valor = Float.parseFloat(JOptionPane.showInputDialog(null,"Novo valor para o Produto " + produto.getNome()));
                produto.setPreco(valor);
                produtoController.editarProduto(produto, op);
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
            case 4:
                adicionarQuantidadeProduto(produto);
                break;

        }
    }
    public void adicionarQuantidadeProduto(Produto produto){
        int Quantidade = Integer.parseInt(JOptionPane.showInputDialog(null,"Quantas unidades desse produto voce deseja adicionar : "));
        produto.setQuantidade(Quantidade);
        produtoController.adicionarQuantidadeProduto(produto);
    }
}
