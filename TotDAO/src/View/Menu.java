package View;

import Model.Empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner= new Scanner(System.in);

    public void espera_Enter(){
        System.out.printf("\n\nPRESSIONE ENTER PARA CONTINUAR");
        String tmp = scanner.nextLine();
    }
    public void menu_Principal(){
        int op;
        Scanner le = new Scanner(System.in);
        while (true){
            System.out.printf("-------------------------------\n");
            System.out.printf("|||   (1)Menu Produtos      |||\n");
            System.out.printf("|||   (2)Menu Categorias    |||\n");
            System.out.printf("|||   (3)Menu Empresas      |||\n");
            System.out.printf("|||   (4)Menu Funcionario   |||\n");
            System.out.printf("-------------------------------\n");
            op = le.nextInt();
            le.nextLine();
            int id = 0;
            switch (op){
                case 1:
                    EmpresaView empresaView = new EmpresaView();
                    id = empresaView.escolherEmpresa(id);
                    ProdutoView produtoView = new ProdutoView();
                    produtoView.menu(id);
                    break;
                case 2:
                    empresaView = new EmpresaView();
                    CategoriaView categoriaView = new CategoriaView();
                    id = empresaView.escolherEmpresa(id);
                    categoriaView.Menu(id);
                case 3:
                    empresaView = new EmpresaView();
                    empresaView.menu();
                    break;
                case 4:
                    empresaView = new EmpresaView();
                    id = empresaView.escolherEmpresa(id);
                    FuncionarioView funcionarioView = new FuncionarioView();
                    funcionarioView.MenuFuncionario(id);
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }
}
