package Controler;

public class PagamentoController {
    public boolean validacaoPagamento(String passwordCard){
        if(passwordCard.equals("mypassword")){
            System.out.println("Pagamento efetuado com sucesso!");
            return true;
        }else {
            System.out.println("Senha incorreta, falha ao efetuar o pagamento");
            return false;
        }
    }
}
