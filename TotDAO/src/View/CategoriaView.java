package View;

import Controller.CategoriaController;
import Model.Categoria;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class CategoriaView {
    Scanner le= new Scanner(System.in);
    CategoriaController categoriaController = new CategoriaController();
    Menu menu = new Menu();
    public void InsereCategoria(int id){
        String tmp;
        tmp = (String) JOptionPane.showInputDialog(null,"Insira o nome da nova categoria : ");
        Categoria categoria = new Categoria();
        categoria.setNome(tmp);
        if(menu.menuConfirmar().contains("1")){
            categoriaController.InsereCategoria(categoria, id);
        }else{
            JOptionPane.showMessageDialog(null, "Ação cancelada com sucesso");
        }

    }
    public List<Categoria> listartodos(int id){
        int i = 0;
        List<Categoria> list = categoriaController.listartodos(id);
        return list;
    }
    public void mostrarCategorias(int id){
        List <Categoria> list = categoriaController.listartodos(id);
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String output = "";
        for (Categoria categoria : list) {
            String tmp = "NOME : " + categoria.getNome();
            output += tmp + " \n\n";
        }
        JOptionPane.showMessageDialog(frame,output);
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
                    menu.menu_Chefe();
                    break;
            }
        }
    }
    public String exibeMenuCategorias(){
        String[] escolhas = {"1", "2", "3", "4"};
        String menuTexto = "1 | Inserir Nova Categoria | " + "\n\n2 | Listar Categorias |" + "\n\n3 | Sair |\n\n" + "4 | Nova opçao |\n\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuCategorias", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public int escolherCategoria(int id){
        return categoriaController.escolherCategoria(id);
    }
}
