package View;

import Controller.UnidadeController;

import javax.swing.*;

public class TotemView {
    CarrinhoView carrinhoView = new CarrinhoView();
    public void menu_Totfast(){
        int id = 0;
        while (true){
            String opc = exibeMenuTotem();
            switch (opc){
                case "1":
                    JOptionPane.showMessageDialog(null,"Seja Bem-Vindo ao Tot-Fast");
                    UnidadeController unidadeController = new UnidadeController();
                    unidadeController.escolherUnidade();
                    carrinhoView.menuCarrinho(id);
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null,"Seja Bem-Vindo ao Tot-Fast");
                    UsuarioView usuarioView = new UsuarioView();
                    usuarioView.loginUsuarioView();
                    break;
            }
        }
        
    }
    public String exibeMenuTotem(){
        String[] escolhas = {"1", "2"};
        String menuTexto = "1 | Modulo de Vendas | " + "\n\n2 | Administração |" + "\n\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuProdutos", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }

}
