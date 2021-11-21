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
                    excluirProduto(id);
                    break;
                case "6":
                    menu.menu_Chefe();
                    break;
            }
        }
    }
    public void insereProduto(int id){
        CategoriaView categoriaView = new CategoriaView();
        String nome = "";
        float Valor = 0;
        int IdCategoria = 0;
        int qntd = 0;
        IdCategoria = categoriaView.escolherCategoria(id);
        nome = (String) JOptionPane.showInputDialog(null, "Nome do Produto:").toUpperCase();
        Valor = Float.parseFloat(JOptionPane.showInputDialog(null, "Valor do Produto : "));
        qntd = Integer.parseInt(JOptionPane.showInputDialog(null,"Insira a quantidade inicial desse produto :"));
        Produto produto = new Produto(nome ,Valor, IdCategoria, qntd);
        if(menu.menuConfirmar().contains("1")){
            produtoController.insereProduto(produto);
        }else{
            JOptionPane.showMessageDialog(null, "Ação cancelada com sucesso !");
        }
    }
    public String exibeMenuProdutos(){
        String[] escolhas = {"1", "2", "3", "4", "5", "6"};
        String menuTexto = "1 | Inserir Produto | " + "\n\n2 | Listar Todos os Produtos |" + "\n\n3 | Listar Produto por Categoria |\n\n4 | Editar Produto |\n\n 5 | Excluir Produto |\n\n 6 | Sair    |\n\n";
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
        produto.setPreco(list.get(id1).getPreco());
        produto.setIdCategoria(list.get(id1).getIdCategoria());
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
                if(menu.menuConfirmar().contains("1")){
                    produtoController.editarProduto(produto);
                }else{
                    JOptionPane.showMessageDialog(null, " Ação Cancelada com sucesso !");
                }
                break;
            case 2:
                float valor = Float.parseFloat(JOptionPane.showInputDialog(null,"Novo valor para o Produto " + produto.getNome()));
                produto.setPreco(valor);
                if(menu.menuConfirmar().contains("1")){
                    produtoController.editarProduto(produto);
                }else{
                    JOptionPane.showMessageDialog(null, " Ação Cancelada com sucesso !");
                }
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Escolha a nova categoria do produto");
                int idNewCat = categoriaController.escolherCategoria(id);
                produto.setIdCategoria(idNewCat);
                if(menu.menuConfirmar().contains("1")){
                    produtoController.editarProduto(produto);
                }else{
                    JOptionPane.showMessageDialog(null, " Ação Cancelada com sucesso !");
                }

                break;
            case 4:
                if(menu.menuConfirmar().contains("1")){
                    adicionarQuantidadeProduto(produto);
                }else {
                    JOptionPane.showMessageDialog(null, " Ação Cancelada com sucesso !");
                }
                break;
        }
    }
    public void adicionarQuantidadeProduto(Produto produto){
        int Quantidade = Integer.parseInt(JOptionPane.showInputDialog(null,"Quantas unidades desse produto voce deseja adicionar : "));
        produto.setQuantidade(Quantidade);
        produtoController.adicionarQuantidadeProduto(produto);
    }
    public void excluirProduto(int id){
        int idCategoria = categoriaController.escolherCategoria(id);
        List<Produto> list = produtoController.listaProdutoporCategoria(idCategoria, id);
        int idProduto = produtoController.escolher_produto(idCategoria , id );
        if(menu.menuConfirmar().contains("1")){
            produtoController.excluirProduto(list.get(idProduto).getNome());
            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso !");
        }else{
            JOptionPane.showMessageDialog(null, " Ação Cancelada com sucesso !");
        }
    }
}
