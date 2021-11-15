package Controller;

import Model.Pagamento;

import javax.swing.*;

public class PagamentoController {
    public boolean validacaoPagamento(String passwordCard){
        if(passwordCard.equals("mypassword")){
            JOptionPane.showMessageDialog(null, "Pagamento Realizado Com Sucesso");
            return true;
        }else {
            JOptionPane.showMessageDialog(null, "Não Foi Possivel Realizar o Pagamento");
            return false;
        }
    }
}