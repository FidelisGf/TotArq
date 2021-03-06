package View;
import Model.Administrador;
import View.*;
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
        PagamentoView pagamentoView = new PagamentoView();
        System.out.println("Sua compra :");
        for(Produto produto : carrinhoController.finalizar_pedido().getLista_do_Carrinho()){
            System.out.println(produto.getNomeProduto() + "  R$:  " + produto.getValorProduto());
        }
        System.out.println("Valor total : " + carrinhoController.finalizar_pedido().getValor_Total_Carrinho());
        System.out.println("Deseja Finalizar o Pedido ? (1) Sim (2) Nao");
        if(le.nextLine().contains("1")){
            pagamentoView.menuPagamento(carrinhoController.finalizar_pedido());
        }else{
            System.out.println("Operação cancelada com sucesso");
        }
    }
    public void listar_Carrinho(){
        int i = 0;
        System.out.println("Carrinho atual :");
        for(Produto produto : carrinhoController.finalizar_pedido().getLista_do_Carrinho()){
            System.out.println(produto.getNomeProduto() + "  R$:  " + produto.getValorProduto());
        }
    }
    public void excluir_do_Carrinho(){
        System.out.println("Escolha o item a ser excluido do carrinho : ");
        listar_Carrinho();
        carrinhoController.excluir_do_Carrinho(le.nextInt());
        le.nextLine();
    }
}
