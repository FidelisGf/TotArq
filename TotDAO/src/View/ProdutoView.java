package View;

import Controller.ProdutoController;
import Model.Categoria;
import Model.Empresa;
import Model.Funcionario;
import Model.Produto;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProdutoView {
    Menu menu = new Menu();
    Scanner le = new Scanner(System.in);
    ProdutoController produtoController = new ProdutoController();
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
                    menu.espera_Enter();
                    break;
                case "5":
                    menu.menu_Principal();
                    break;
            }
        }
    }
    public void insereProduto(int id){
        CategoriaView categoriaView = new CategoriaView();
        String nome = "";
        float tmp1 = 0;
        int tmp2 = 0;
        nome = (String) JOptionPane.showInputDialog(null, "Nome do Produto:");
        tmp1 = Float.parseFloat(JOptionPane.showInputDialog(null, "Valor do Produto : "));
        List<Categoria> list = categoriaView.listartodos(id);
        String[] object = new String[list.size()];
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        int i = 0;
        try {
            for (Categoria categoria : list) {
                object[i] = String.valueOf(categoria.getId()) + "|" + categoria.getNome().toString();
                i++;
            }
            Object selectionObject = (String) JOptionPane.showInputDialog(frame, "Escolha a categoria do produto", "Categorias", JOptionPane.QUESTION_MESSAGE, null, object, object[0]);
            String tmp3 = selectionObject.toString();
            StringTokenizer st = new StringTokenizer(tmp3);
            tmp2 = Integer.valueOf(st.nextToken("|"));
            Produto produto = new Produto(nome ,tmp1, tmp2);
            produtoController.insereProduto(produto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String exibeMenuProdutos(){
        String[] escolhas = {"1", "2", "3", "4", "5"};
        String menuTexto = "1 | Inserir Produto | " + "\n\n2 | Listar Todos os Produtos |" + "\n\n3 | Listar Produto por Categoria |\n\n4 | Editar Produto |\n\n5 | Sair |\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuProdutos", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public void listarProdutoporCategoria(int id2){
        int op = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o Id da Categoria :"));
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
        List<Produto> list = produtoController.listarTodosProdutos(id);
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String[] tmp = new String[list.size()];
        String opc = "";
        String output = "";
        for (Produto produto : list) {
            tmp[i] =  i + "| " + "NOME : " + produto.getNome() + " Com o VALOR de : R$" + String.valueOf(produto.getPreco());
            i++;
        }
        Object selectionObject = (String) JOptionPane.showInputDialog(frame,"Select Product","Produtos",JOptionPane.QUESTION_MESSAGE,null, tmp, tmp[0]);
        Produto produto = new Produto();
        String pegaop = selectionObject.toString();
        StringTokenizer st = new StringTokenizer(pegaop);
        int id1 = Integer.parseInt(st.nextToken("|"));
        produto.setId(list.get(id1).getId());
        produto.setNome(list.get(id1).getNome());
        int op = 0;
        String[] escolhas = {"1", "2", "3"};
        String menuTexto = "1 | Editar Nome| " + "\n\n2 | Editar Valor |" + "\n\n3 | Edidar Categoria do Produto |\n\n";
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

        }

    }
}
