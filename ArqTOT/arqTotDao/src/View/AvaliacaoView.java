package View;

import Controler.AvaliacaoController;
import Controler.PagamentoController;

import java.util.Scanner;

public class AvaliacaoView {
    Scanner scan = new Scanner(System.in);
    AvaliacaoController ac = new AvaliacaoController();

    public void menuAvaliacao(){
        System.out.println("AVALIE O ATENDIMENTO\n\n");
        System.out.println("1 - Atendimento Ótimo");
        System.out.println("2 - Atendimento Bom");
        System.out.println("3 - Atendimento Ruim");
        System.out.println("4 - Não avaliar");
        String escolha = scan.nextLine();
        switch (escolha){
            case "1":
                System.out.println("Obrigado pela sua avaliação!\n");
                break;
            case "2":
                System.out.println("Obrigado pela sua avaliação!\n");
                break;
            case "3":
                contestResp();
                break;
            case "4":
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }
    }

    public void contestResp(){
        System.out.println("\n\n\n Digite o número que corresponde sua escolha\n");
        System.out.println("1 - Atendimento Demorado");
        System.out.println("2 - Interface Confusa");
        System.out.println("3 - Atendimento Ruim");
        System.out.println("4 - Outros\n");
        String resp = scan.nextLine();
        switch (resp){
            case "1":
                System.out.println("Obrigado pela sua avaliação, analisaremos ela para melhorarmos nosso atendimento\n");
                break;
            case "2":
                System.out.println("Obrigado pela sua avaliação, analisaremos ela para melhorarmos nosso atendimento\n");
                break;
            case "3":
                System.out.println("Obrigado pela sua avaliação, analisaremos ela para melhorarmos nosso atendimento\n");
                break;
            case "4":
                System.out.println("Obrigado pela sua avaliação, analisaremos ela para melhorarmos nosso atendimento\n");
                break;
            default:
                System.out.println("Opção Inválida!\n");
                break;
        }
    }
}
