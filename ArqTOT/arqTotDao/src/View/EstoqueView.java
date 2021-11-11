package View;

import Controler.EstoqueController;
import Model.*;

import java.util.Scanner;

public class EstoqueView {

    EstoqueController estoqueController = new EstoqueController();

    Scanner scan = new Scanner(System.in);

    public void menuEstoque(){
        int escolhe = 0;
        System.out.println("-----------------------------------");
        System.out.printf(" | (1) Listar Estoque            |\n");
        System.out.printf(" | (2) Sair                      |\n");
        System.out.println("-----------------------------------");

        escolhe = scan.nextInt();

        switch (escolhe){
            case 1:
                listarEstoque();
                break;
            case 2:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("ERROR - Reinicie o Sistema");
        }
    }

    public void listarEstoque(){
        for (Produto produto : estoqueController.listarEstoque()){
            System.out.println("Nome: " + produto.getNomeProduto() + "| Quantidade: " + produto.getQuantidadeProduto());
        }
    }
}
