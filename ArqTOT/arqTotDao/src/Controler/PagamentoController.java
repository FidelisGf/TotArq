package Controler;

public class PagamentoController {
    public void validacaoPagamento(String passwordCard){
        if(passwordCard.equals("mypassword")){
            System.out.println("Pagamento efetuado com sucesso!");
        }else {
            System.out.println("Senha incorreta, falha ao efetuar o pagamento");
        }
    }
}
