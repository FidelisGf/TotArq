package View;

import Controler.CategoriaController;
import Model.Categoria;

import java.util.List;
import java.util.Scanner;

public class CategoriaView {
    CategoriaController categoriaController = new CategoriaController();
    Scanner scanner = new Scanner(System.in);
    public void Registrar_Categoria(){
        String nome;
        System.out.println("Digite o nome da nova categoria : ");
        nome = scanner.nextLine();
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
