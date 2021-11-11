package View;

import Controler.PagamentoController;
import Controler.UnidadeControler;
import Model.Carrinho;
import Model.Pagamento;

import java.util.Scanner;

public class PagamentoView {

    Scanner scan = new Scanner(System.in);
    PagamentoController pagc = new PagamentoController();

    public void menuPagamento(Carrinho carrinho){
        System.out.println("SELECIONE O MÉTODO DE PAGAMENTO\n\n");
        System.out.println("*** 1 - CARTÃO DE CRÉDITO***\n");
        System.out.println("*** 2 - CARTÃO DE DÉBITO***\n");
        System.out.println("*** 3 - GERAR QR CODE***\n");
        int escolha = scan.nextInt();
        String pass;
        Pagamento pagamento = new Pagamento();
        switch(escolha){
            case 1:
                System.out.println("Método de pagamento escolhido - CARTÃO DE CRÉDITO\n\n");
                System.out.println("Insira seu cartão na entrada abaixo e insira a senha\n");
                scan.nextLine();
                pass = scan.nextLine();

                pagamento.setFormaPagamento("Credito");
                if(pagc.validacaoPagamento(pass)){
                    AvaliacaoView avaliacaoView = new AvaliacaoView();
                    avaliacaoView.menuAvaliacao(carrinho, pagamento);
                }
                break;
            case 2:
                System.out.println("Método de pagamento escolhido - CARTÃO DE DÉBITO\n\n");
                System.out.println("Insira seu cartão na entrada abaixo e digite a senha\n");
                scan.nextLine();
                pass = scan.nextLine();
                pagamento.setFormaPagamento("Debito");
                if(pagc.validacaoPagamento(pass)){
                    AvaliacaoView avaliacaoView = new AvaliacaoView();
                    avaliacaoView.menuAvaliacao(carrinho, pagamento);
                }
                break;
            case 3:
                System.out.println("Método de pagamento escolhido - QR CODE e digite a senha\n\n");
                System.out.println("Escaneie o QR CODE\n");
                scan.nextLine();
                pass = scan.nextLine();
                pagamento.setFormaPagamento("Pix");
                if(pagc.validacaoPagamento(pass)){
                    AvaliacaoView avaliacaoView = new AvaliacaoView();
                    avaliacaoView.menuAvaliacao(carrinho, pagamento);
                }
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
    }

}
