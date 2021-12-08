package View;

import Controller.EstoqueController;
import Model.Estoque;

import javax.swing.*;

import java.time.LocalDate;

import static javax.swing.JOptionPane.showInputDialog;

public class EstoqueView {

    public void menuEstoque(){
            while(true){
                String op =  exibeMenuEstoque();
                switch (op){
                    case "1":
                        cadastrarEstoque();
                        break;
                    case "2":
                        listarEstoque();
                        break;
                    case "3":
                        editarEstoque();
                        break;
                }
            }
        }
        public String exibeMenuEstoque(){
            String[] escolhas = {"1", "2", "3"};
            String menuTexto = "1 | Cadastrar Insumos | " + "\n\n2 | Listar Insumos  |" + "\n\n" + "3  | Editar Insumos  |" + "\n\n";
            return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"Estoque", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
        }


        public void cadastrarEstoque() {
            EstoqueController estoqueController = new EstoqueController();
            Estoque estoque = new Estoque();

            String recebeNome = showInputDialog(null, "Digite o Nome do Insumo:");
            if (recebeNome == null){
                menuEstoque();
            }
            estoque.setNomeInsumo(recebeNome.toUpperCase());

            String recebeQnt = showInputDialog("Digite a Quantidade do Insumo: ");
            if (recebeQnt == null) menuEstoque();
            estoque.setQntdInsumo(Integer.parseInt(recebeQnt));

            String recebePreco = showInputDialog("Digite o Preço do Insumo: ");
            if (recebePreco == null) menuEstoque();
            estoque.setPrecoInsumo(Float.parseFloat(recebePreco));

            String recebeValidade = showInputDialog("Digite a validade : ");
            if (recebeValidade == null) menuEstoque();
            estoque.setValidade(recebeValidade);
            estoqueController.cadastrarEstoque(estoque);
            JOptionPane.showMessageDialog(null, "Insumo Cadastrado com sucesso !");

        }
    public void listarEstoque(){
        EstoqueController estoqueController = new EstoqueController();
        String recebe = "";
        for (Estoque estoque : estoqueController.listarEstoque()){
            recebe = recebe + " " + "ID: " + estoque.getIdInsumo() +
                    " | NOME: " + estoque.getNomeInsumo() +
                    " | QUANTIDADE: " + estoque.getQntdInsumo() +
                    " | PREÇO: " + estoque.getPrecoInsumo() +
                    " | VALIDADE: " + estoque.getValidade() + "\n";
        }
        JOptionPane.showMessageDialog(null, recebe);
    }
    public void editarEstoque(){
        EstoqueController estoqueController = new EstoqueController();
        String recebe = "";
        Estoque estoque = estoqueController.listarEstoque().get(estoqueController.escolheInsumoEstoque());
        int escolhe= Integer.valueOf(showInputDialog("Digite o que Deseja Editar: " +
                "\n1 - Nome" +
                "\n2 - Quantidade" +
                "\n3 - Preco" +
                "\n4 - Validade" +
                "\n5 - Editar Todas Opções"));
        switch (escolhe){
            case 1:
                String recebeNome =  showInputDialog("Digite o Nome do Insumo:" );
                if(recebeNome == null) menuEstoque();
                estoque.setNomeInsumo(recebeNome.toUpperCase());
                break;
            case 2:
                String recebeQnt =  showInputDialog("Digite a Quantidade do Insumo: " );
                if(recebeQnt == null) menuEstoque();
                estoque.setQntdInsumo(Integer.parseInt(recebeQnt));
                break;
            case 3:
                String recebePreco = showInputDialog("Digite o Preço do Insumo: ");
                if(recebePreco == null) menuEstoque();
                estoque.setPrecoInsumo(Float.parseFloat(recebePreco));
                break;
            case 4:
                estoque.setValidade(String.valueOf(LocalDate.now().getYear()));
                break;
            case 5:
                recebeNome =  showInputDialog("Digite o Nome do Insumo:" );
                if(recebeNome == null) menuEstoque();
                estoque.setNomeInsumo(recebeNome.toUpperCase());

                recebeQnt =  showInputDialog("Digite a Quantidade do Insumo: " );
                if(recebeQnt == null) menuEstoque();
                estoque.setQntdInsumo(Integer.parseInt(recebeQnt));

                recebePreco = showInputDialog("Digite o Preço do Insumo: ");
                if(recebePreco == null) menuEstoque();
                estoque.setPrecoInsumo(Float.parseFloat(recebePreco));
                break;
        }
        estoqueController.editarEstoque(estoque);
    }


}
