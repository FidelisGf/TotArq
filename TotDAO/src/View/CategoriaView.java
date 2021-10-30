package View;

import Controller.CategoriaController;
import Model.Categoria;
import Model.Produto;

import java.util.List;
import java.util.Scanner;

public class CategoriaView {
    Scanner le= new Scanner(System.in);
    CategoriaController categoriaController = new CategoriaController();
    Menu menu = new Menu();
    public void InsereCategoria(int id){
        String tmp;
        System.out.println("Insira o nome da nova categoria : ");
        tmp = le.nextLine();
        Categoria categoria = new Categoria();
        categoria.setNome(tmp);
        categoria.setIdEmpresa(1);
        categoriaController.InsereCategoria(categoria, id);
    }
    public List<Categoria> listartodos(int id){
        int i = 0;
        List<Categoria> list = categoriaController.listartodos(id);
        for (Categoria categoria : list){
            System.out.println(i + " " +categoria);
            i++;
        }
        return list;
    }
    public void Menu(int id){
        int op;
        Scanner le = new Scanner(System.in);
        while (true){
            System.out.printf("----------------------------------------\n");
            System.out.printf("|||  (1)Criar Nova Categoria         |||\n");
            System.out.printf("|||  (2)Listar todas as Categorias   |||\n");
            System.out.printf("|||  (3)Sair                         |||\n");
            System.out.printf("----------------------------------------\n");
            op = le.nextInt();
            le.nextLine();
            switch (op){
                case 1:
                    InsereCategoria(id);
                    menu.espera_Enter();
                    break;
                case 2:
                    listartodos(id);
                    menu.espera_Enter();
                    break;
                case 3:
                    menu.menu_Principal();
                    break;
            }
        }
    }
}
