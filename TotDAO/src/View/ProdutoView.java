package View;

import Controller.CategoriaController;
import Controller.EstoqueController;
import Controller.ProdutoController;
import Controller.RelatorioController;
import Model.Categoria;
import Model.Estoque;
import Model.Produto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    AdministradorView Adm = new AdministradorView();
    Menu menu = new Menu();
    Scanner le = new Scanner(System.in);
    ProdutoController produtoController = new ProdutoController();
    CategoriaController categoriaController = new CategoriaController();
    EstoqueController estoqueController = new EstoqueController();
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
                    detalhesProduto(id);
                    break;
                case "7":
                    Adm.menu_Chefe();
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
        String Desc = "";
        Categoria categoria = new Categoria();
        categoria.setId(IdCategoria = categoriaView.escolherCategoria(id));
        if(IdCategoria != -1 ){
            nome = (String) JOptionPane.showInputDialog(null, "Nome do Produto:").toUpperCase();
            Valor = Float.parseFloat(JOptionPane.showInputDialog(null, "Valor do Produto : "));
            Desc = (String) JOptionPane.showInputDialog(null, "De uma descrição para o produto : ");
            Produto produto = new Produto(nome, Valor, Desc, categoria);
            produto.setInsumos(adicionarInsumoAoProduto());
            if(menu.menuConfirmar().contains("1")){
                produtoController.insereProduto(produto);
            }else{
                JOptionPane.showMessageDialog(null, "Ação cancelada com sucesso !");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Não há Categorias registradas nessa unidade !");
        }

    }
    public String exibeMenuProdutos(){
        JDialog.setDefaultLookAndFeelDecorated(true);
        String[] escolhas = {"1", "2", "3", "4", "5", "6", "7"};
        String menuTexto = "1 | Inserir Produto | " + "\n\n2 | Listar Todos os Produtos |" + "\n\n3 | Listar Produto por Categoria |\n\n4 | Editar Produto |\n\n 5 | Excluir Produto |\n\n 6 | Ver Detalhes de um Produto  |\n\n 7 | Sair   |\n\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuProdutos", JOptionPane.QUESTION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public void listarProdutoporCategoria(int id2){
        int op = categoriaController.escolherCategoria(id2);
        if(op != -1){
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
        }else{
            JOptionPane.showMessageDialog(null, "Não há Categorias registradas nessa Unidade");
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
    public void detalhesProduto(int id){
        String Insumos = "";
        int idCategoria = categoriaController.escolherCategoria(id);
        if(idCategoria != -1){
            int idProduto = produtoController.escolher_produto(idCategoria, id);
            if(idProduto == -1){
                JOptionPane.showMessageDialog(null, "Sem Produtos Registrados Nessa Categoria");
            }else{
                Produto produto =produtoController.listaProdutoporCategoria(idCategoria, id).get(idProduto);
                for(Estoque estoque : produto.getInsumos()){
                    Insumos += "\n" + estoque.getNomeInsumo() + " Quantidade : " + estoque.getQntdInsumo();
                }
                JOptionPane.showMessageDialog(null, "Nome do Produto  :  " + produto.getNome() + "\n\n Valor do Produto :  R$ : " + produto.getPreco() + "\n\nCom a Descrição de : \n" + produto.getDesc() + "\n\nCom os seguintes Ingredientes :\n" +  Insumos);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Não há Categorias registradas nessa Unidade");
        }
    }
    public void editarProduto(int id){
        int i = 0;
        RelatorioController relatorioController = new RelatorioController();
        int idCategoria = categoriaController.escolherCategoria(id);
        if(idCategoria != -1 ){
            List<Produto> list = produtoController.listaProdutoporCategoria(idCategoria,id);
            if(!list.isEmpty()){
                int id1 = produtoController.escolher_produto(idCategoria,id);
                Produto produto = new Produto();
                produto.setId(list.get(id1).getId());
                produto.setNome(list.get(id1).getNome());
                produto.setPreco(list.get(id1).getPreco());
                Categoria categoria = new Categoria();
                categoria.setId(idCategoria);
                produto.setCategoria(categoria);
                int op = 0;
                String opc;
                String[] escolhas = {"1", "2", "3", "4"};
                String menuTexto = "1 | Editar Nome| " + "\n\n2 | Editar Valor |" + "\n\n3 | Edidar Categoria do Produto |\n\n4 | Adiconar Quantidade a um produto |\n";
                opc = (String) (JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"EditarProdutos", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]));
                op = Integer.parseInt(opc);
                switch (op){
                    case 1:
                        String NomePassado = produto.getNome();
                        produto.setNome((String) JOptionPane.showInputDialog(null, "Novo Nome para o Produto : " + produto.getNome()));
                        produto.setDesc((String)  JOptionPane.showInputDialog(null, "Nova Descrição para o produto : ") + produto.getDesc());
                        if(menu.menuConfirmar().contains("1")){
                            produtoController.editarProduto(produto);
                            relatorioController.fazerLogEditarNomeProduto(produto, NomePassado);
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
                        produto.getCategoria().setId(idNewCat);
                        if(menu.menuConfirmar().contains("1")){
                            produtoController.editarProduto(produto);
                        }else{
                            JOptionPane.showMessageDialog(null, " Ação Cancelada com sucesso !");
                        }

                        break;
                }

            }else{
                JOptionPane.showMessageDialog(null, "Não existe produtos Registrados nessa Categoria ! ");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Não Ha Categorias Registradas ! ");
        }

    }
    public void excluirProduto(int id){
        RelatorioController relatorioController = new RelatorioController();
        int idCategoria = categoriaController.escolherCategoria(id);
        if(idCategoria != -1){
            List<Produto> list = produtoController.listaProdutoporCategoria(idCategoria, id);
            if(!list.isEmpty()){
                int idProduto = produtoController.escolher_produto(idCategoria , id );
                if(menu.menuConfirmar().contains("1")){
                    relatorioController.fazerLogExcluirProduto(list.get(idProduto));
                    produtoController.excluirProduto(list.get(idProduto).getNome());
                    JOptionPane.showMessageDialog(null, "Produto excluido com sucesso !");
                }else{
                    JOptionPane.showMessageDialog(null, " Ação Cancelada com sucesso !");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Não há Produtos cadastrados nessa Categoria !");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Não há Categorias cadastradas nessa Unidade !");
        }

    }
    public List<Estoque> adicionarInsumoAoProduto(){
        List<Estoque> list = new ArrayList<>();
        Estoque estoque;
        String escolha = "";
        do {
            int IdInsumo = estoqueController.escolherInsumoEstoque();
            if(IdInsumo != -1){
                estoque = new Estoque();
                estoque = estoqueController.listarEstoque().get(IdInsumo);
                estoque.setQntdInsumo(Integer.valueOf(JOptionPane.showInputDialog(null, "Quantas unidades desse Insumo serão necessárias ? ")));
                list.add(estoque);
                JDialog.setDefaultLookAndFeelDecorated(true);
                String[] escolhas = {"Sim", "Nao"};
                String menuTexto = "Deseja adicionar mais insumos ?" + "\n";
                escolha = (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuProdutos", JOptionPane.QUESTION_MESSAGE, null,escolhas, escolhas[0]);
            }
        }while (escolha.contains("Sim"));
        return list;
    }
}
