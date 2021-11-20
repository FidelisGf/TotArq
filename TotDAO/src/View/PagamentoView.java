package View;


import Controller.PagamentoController;
import Model.Carrinho;
import Model.Pagamento;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PagamentoView {

    Scanner scan = new Scanner(System.in);
    PagamentoController pagc = new PagamentoController();
    public String exibeMenuPagamento(){
        String[] escolhas = {"1", "2", "3"};
        String menuTexto = "1 | Cartão de Crédito  | " + "\n\n2 | Cartão de Débito |" + "\n\n3 | Gerar QR CODE para PIX |\n";
        return (String) JOptionPane.showInputDialog(null,"Escolha o Metodo de Pagamento : :\n\n" + menuTexto,"Pagamento", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public void menuPagamento(Carrinho carrinho){

        String escolha = exibeMenuPagamento();
        Pagamento pagamento = new Pagamento();
        switch(escolha){
            case "1":
                JOptionPane.showMessageDialog(null, "Insira seu Cartão na entrada abaixo.");
                pagamento.setFormaPagamento("Credito");
                if(pagc.validacaoPagamento((String) JOptionPane.showInputDialog(null, "Digite a senha do cartão"))){
                    AvaliacaoView avaliacaoView = new AvaliacaoView();
                    avaliacaoView.menuAvaliacao(carrinho, pagamento);
                }
                break;
            case "2":
                JOptionPane.showMessageDialog(null, "Insira seu Cartão na entrada abaixo.");
                pagamento.setFormaPagamento("Debito");
                if(pagc.validacaoPagamento((String) JOptionPane.showInputDialog(null, "Digite a senha do cartão"))){
                    AvaliacaoView avaliacaoView = new AvaliacaoView();
                    avaliacaoView.menuAvaliacao(carrinho, pagamento);
                }
                break;
            case "3":
                JOptionPane.showMessageDialog(null, "Insira seu Cartão na entrada abaixo.");
                pagamento.setFormaPagamento("Pix");
                if(pagc.validacaoPagamento((String) JOptionPane.showInputDialog(null, "Digite a senha Gerada No Pagamento"))){
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