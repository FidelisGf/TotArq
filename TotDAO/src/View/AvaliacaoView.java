package View;




import Controller.CarrinhoController;
import Controller.RelatorioController;
import Model.Avaliacao;
import Model.Carrinho;
import Model.Pagamento;

import javax.swing.*;
import java.util.Scanner;

public class AvaliacaoView {
    Scanner scan = new Scanner(System.in);
    public String exibeMenuAvaliacao(){
        String[] escolhas = {"1", "2", "3", "4"};
        String menuTexto = "1 | Atendimento Ótimo  | " + "\n\n2 | Atendimento Bom |" + "\n\n3 | Atendimento Ruim |\n\n 4 | Não Avaliar   |\n\n";
        return (String) JOptionPane.showInputDialog(null,"Escolha o Metodo de Pagamento : :\n\n" + menuTexto,"Pagamento", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public void menuAvaliacao(Carrinho carrinho, Pagamento pagamento){
        RelatorioController relatorioController = new RelatorioController();
        Avaliacao avaliacao = new Avaliacao();
        switch (exibeMenuAvaliacao()){
            case "1":
                JOptionPane.showMessageDialog(null, "Obrigado pela sua avaliação !");
                avaliacao.setTpAvaliacao("Otimo");
                relatorioController.logPedidos(carrinho, pagamento, avaliacao);
                break;
            case "2":
                JOptionPane.showMessageDialog(null, "Obrigado pela sua avaliação !");
                avaliacao.setTpAvaliacao("Bom");
                relatorioController.logPedidos(carrinho, pagamento, avaliacao);
                break;
            case "3":
                avaliacao.setTpAvaliacao("Ruim");
                JOptionPane.showMessageDialog(null, "Obrigado pela sua avaliação !");
                relatorioController.logPedidos(carrinho, pagamento, avaliacao);
                break;
            case "4":
                avaliacao.setTpAvaliacao("Nao Avaliado");
                relatorioController.logPedidos(carrinho, pagamento, avaliacao);
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
    }

}