package View;

import Controller.RegistroVendaController;
import Controller.RelatorioController;
import Model.RegistroVenda;

import javax.swing.*;

public class RelatorioView {
    RelatorioController relatorioController = new RelatorioController();
    AdministradorView administradorView = new AdministradorView();
    public void listarAcoes(){
        String Output =  relatorioController.listarAcoes();
        JOptionPane.showMessageDialog(null, Output);
    }
    public void listarVendas(){
        String Output = relatorioController.listarVendas();
        JOptionPane.showMessageDialog(null, Output);
    }
    public void pegarVendas(){
        String output = "";
        RegistroVendaController registroVendaController = new RegistroVendaController();
        for(RegistroVenda registroVenda : registroVendaController.pegarVendas()){
            output += "Nome do Produto - >" + registroVenda.getProduto().getNome() + " Vendas desse Item - > " + registroVenda.getNumVendas() + "\n";
        }
        JOptionPane.showMessageDialog(null,output);
    }
    public void menuRelatorio(){
        while (true){
            String opc = exibeMenuRelatorio();
            switch (opc){
                case "1":
                    listarVendas();
                    break;
                case "2":
                    listarAcoes();
                    break;
                case "3":
                    pegarVendas();
                    break;
                case "4":
                    Menu menu = new Menu();
                    administradorView.menu_Chefe();
                    break;
            }
        }
    }
    public String exibeMenuRelatorio(){
        String[] escolhas = {"1", "2", "3", "4"};
        String menuTexto = "1 | Ver Relatorio de Vendas | " + "\n\n2 | Ver Relatorio de Ações |" + "\n\n3 | Ver Itens Mais Vendidos |\n\n4 | Sair  |\n\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuRelatorio", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
}
