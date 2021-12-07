package View;

import Controller.CategoriaController;
import Controller.ProdutoController;
import Model.Categoria;
import Model.Produto;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class CategoriaView {
    Scanner le= new Scanner(System.in);
    CategoriaController categoriaController = new CategoriaController();
    AdministradorView administradorView = new AdministradorView();
    Menu menu = new Menu();
    public void InsereCategoria(int id){
        String NomeCategoria;
        do{
            NomeCategoria = (String) JOptionPane.showInputDialog(null,"Insira o nome da nova categoria : ").toUpperCase();
        }while (categoriaController.verificarNome(NomeCategoria));
        Categoria categoria = new Categoria();
        categoria.setNome(NomeCategoria);
        if(menu.menuConfirmar().contains("1")){
            categoriaController.InsereCategoria(categoria, id);
        }else{
            JOptionPane.showMessageDialog(null, "Ação cancelada com sucesso");
        }
    }
    public void excluirCategoria(int id){
        int idCategoria = escolherCategoria(id);
        if(idCategoria == -1){
            JOptionPane.showMessageDialog(null , "Não há Categorias registradas nessa Unidade !");
        }else{
            if(menu.menuConfirmar().contains("1")){
                categoriaController.excluirCategoria(idCategoria);
            }else{
                JOptionPane.showMessageDialog(null, "Ação cancelada com sucesso");
            }
        }
    }
    public List<Categoria> ListarCategorias(int id){
        int i = 0;
        List<Categoria> list = categoriaController.listartodos(id);
        return list;
    }
    public void mostrarCategorias(int id){
        List <Categoria> list = categoriaController.listartodos(id);
        if(list.isEmpty()){
            JOptionPane.showMessageDialog(null, "Não há Categorias registradas nessa Unidade !");
        }else{
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            String output = "";
            for (Categoria categoria : list) {
                String tmp = "NOME : " + categoria.getNome();
                output += tmp + " \n\n";
            }
            JOptionPane.showMessageDialog(frame,output);
        }
    }
    public void verCategoria(int idUnidade){
        ProdutoController produtoController = new ProdutoController();
        int idCategoria = escolherCategoria(idUnidade);
        Categoria categoria = categoriaController.verCategoria(idUnidade, idCategoria);
        String output = "Nome da Categoria - > " + categoria.getNome() + "\nProdutos cadastrados : \n";
        for(Produto produto : produtoController.listaProdutoporCategoria(idCategoria, idUnidade)){
            output += "Produto - > " + produto.getNome() + "  Valor - > R$ " + produto.getPreco() + "\n";
        }
        JOptionPane.showMessageDialog(null, output);
    }
    public void Menu(int id){
        String op;
        Scanner le = new Scanner(System.in);
        while (true){
            op = exibeMenuCategorias();
            switch (op){
                case "1":
                    InsereCategoria(id);
                    break;
                case "2":
                    mostrarCategorias(id);
                    break;
                case "3":
                    excluirCategoria(id);
                    break;
                case "4":
                    verCategoria(id);
                    break;
                case "5":
                    administradorView.menu_Chefe();
                    break;
            }
        }
    }
    public String exibeMenuCategorias(){
        String[] escolhas = {"1", "2", "3", "4","5"};
        String menuTexto = "1 | Inserir Nova Categoria | " + "\n\n2 | Listar Categorias |" + "\n\n3 | Excluir Categoria |" + "\n\n 4 | Ver uma Categoria   |\n\n" + "5 | Sair  |\n\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuCategorias", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public int escolherCategoria(int id){
        return categoriaController.escolherCategoria(id);
    }
}
