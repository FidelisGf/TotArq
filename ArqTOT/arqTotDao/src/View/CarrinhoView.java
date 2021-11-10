package View;

import Controler.CarrinhoController;
import Model.Carrinho;
import Model.Produto;

import java.util.List;
import java.util.Scanner;

public class CarrinhoView {
    CarrinhoController carrinhoController = new CarrinhoController();
    Scanner le = new Scanner(System.in);
    public void adiciona_Carrinho(List<Produto> list){
        Carrinho carrinho = new Carrinho();
        carrinho.setLista_do_Carrinho(list);
        Float Total = Float.valueOf(0);
        for(Produto produto : carrinho.getLista_do_Carrinho()){
            Total += produto.getValorProduto();
        }
        carrinho.setValor_Total_Carrinho(Total);

        carrinhoController.adiciona_Carrinho(carrinho);
    }
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
    public void finalizar_Compra(){
        System.out.println("Seus Produtos são :");
        for(Produto produto : carrinhoController.finalizar_pedido().getLista_do_Carrinho()){
            System.out.println(produto.getNomeProduto() + "  R$:  " + produto.getValorProduto());
        }
        System.out.println("Valor total : " + carrinhoController.finalizar_pedido().getValor_Total_Carrinho());
        System.out.println("Deseja Finalizar o Pedido ? (1) Sim (2) Nao");
        String tmp = le.nextLine();
        System.out.println("Qual será a forma de pagamento :");
        System.out.printf("|(1) Dinheiro | (2)Débito  | (3) Crédito |");
        tmp = le.nextLine();
        metodos_pagamento(tmp, carrinhoController.finalizar_pedido());
    }

    void metodos_pagamento(String escolha, Carrinho carrinho){
        switch (escolha){
            case "1":
                System.out.println("Para finalizar seu pedido completamente, pague essa nota no caixa !");
                System.out.printf("----------------------------\n");
                pause(250);
                System.out.printf("|                          |\n");
                pause(250);
                System.out.printf("|                          |\n");
                pause(250);
                System.out.printf("|      Pedido N 678        |\n");
                pause(250);
                System.out.printf("|                          |\n");
                pause(250);
                System.out.printf("|                          |\n");
                pause(250);
                System.out.printf("|     Total : %.2f        |\n", carrinho.getValor_Total_Carrinho());
                pause(250);
                System.out.printf("|                          |\n");
                pause(250);
                System.out.printf("|                          |\n");
                pause(250);
                System.out.printf("----------------------------");
                break;
        }
    }


}
