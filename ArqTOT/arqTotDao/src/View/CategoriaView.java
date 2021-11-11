package View;

import Controler.CategoriaController;
import Controler.UnidadeControler;
import Model.Categoria;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CategoriaView {
    CategoriaController categoriaController = new CategoriaController();
    Scanner scanner = new Scanner(System.in);
    public void menuCategoria() throws IOException {
        Menu menu = new Menu();
        while (true){
            System.out.printf("--------------------------------------\n");
            System.out.printf("|  (1) Inserir Nova Categoria        |\n");
            System.out.printf("|  (2) Listar Todas as Categorias    |\n");
            System.out.printf("|  (3) Sair                          |\n");
            System.out.printf("--------------------------------------\n");
            switch (scanner.nextLine()){
                case "1":
                    Registrar_Categoria();
                    break;
                case "2":
                    listar_Categorias();
                    menu.esperaENTER();
                    break;
                case "3":
                    menu.Menu_Principal();
                    break;
                default:
                    System.out.println("Opção Invalida !");
                    break;
            }
        }
    }
    public void Registrar_Categoria() throws IOException {
        String nome;
        long idUnidadeCategoria;
        System.out.println("Digite o nome da nova categoria : ");
        nome = scanner.nextLine();
        UnidadeControler uc1 = new UnidadeControler();
        System.out.println("Unidades disponíveis: ");
        uc1.listaNomeIdUnid();
        System.out.println("Insira o id da Unidade que terá essa categoria");
        idUnidadeCategoria = scanner.nextLong();
        scanner.nextLine();
        categoriaController.salvaCatUnid(nome, idUnidadeCategoria);

        Categoria categoria = new Categoria(nome);


        categoriaController.Registrar_Categoria(categoria);
    }
    public void listar_Categorias(){
        int i = 0;
        for(String list : categoriaController.listar_Categorias()){
            System.out.println(i + " " + list);
            i++;
        }
    }
    public List<String> escolher_Categoria(){
        return categoriaController.listar_Categorias();
    }
    public Categoria escolher_Categoria_PorNome(){
        System.out.println("Selecione a Categoria dos Produtos desejados");
        Scanner le = new Scanner(System.in);
        CategoriaView categoriaView = new CategoriaView();
        categoriaView.listar_Categorias();
        int op  = le.nextInt();
        le.nextLine();
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(categoriaView.escolher_Categoria().get(op));
        return categoria;
    }

}
