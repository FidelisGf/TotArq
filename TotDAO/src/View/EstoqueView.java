package View;

import Controller.EstoqueController;
import Controller.UnidadeController;
import Model.Estoque;
import Model.Unidade;

import javax.swing.*;

import java.time.LocalDate;

import static javax.swing.JOptionPane.showInputDialog;

public class EstoqueView {

    public void menuEstoque(int id){
            while(true){
                String op =  exibeMenuEstoque();
                switch (op){
                    case "1":
                        cadastrarEstoque(id);
                        break;
                    case "2":
                        listarEstoque(id);
                        break;
                    case "3":
                        editarEstoque(id);
                        break;
                    case "4":
                        excluirEstoque(id);
                        break;
                    case "5":
                        AdministradorView administradorView = new AdministradorView();
                        administradorView.menu_Chefe();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Invalida !");
                        break;
                }
            }
        }
        public String exibeMenuEstoque(){
            String[] escolhas = {"1", "2", "3","4"};
            String menuTexto = "1 | Cadastrar Insumos | " + "\n\n2 | Listar Insumos  |" + "\n\n" + "3  | Editar Insumos  |" + "\n\n" + "4  | Excluir Insumo  |\n\n" + "5  | Sair  |\n\n";
            return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"Estoque", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
        }


        public void cadastrarEstoque(int id) {
            UnidadeController unidadeController = new UnidadeController();
            EstoqueController estoqueController = new EstoqueController();
            Estoque estoque = new Estoque();

            String recebeNome = showInputDialog(null, "Digite o Nome do Insumo:");
            if (recebeNome == null){
                menuEstoque(id);
            }
            estoque.setNomeInsumo(recebeNome.toUpperCase());

            String recebeQnt = showInputDialog("Digite a Quantidade do Insumo: ");
            if (recebeQnt == null) menuEstoque(id);
            estoque.setQntdInsumo(Integer.parseInt(recebeQnt));

            String recebePreco = showInputDialog("Digite o Preço do Insumo: ");
            if (recebePreco == null) menuEstoque(id);
            estoque.setPrecoInsumo(Float.parseFloat(recebePreco));

            String recebeValidade = showInputDialog("Digite a validade : ");
            if (recebeValidade == null) menuEstoque(id);
            estoque.setValidade(recebeValidade);
            Unidade unidade = new Unidade();
            unidade.setIdUnidade(id);
            estoque.setUnidade(unidade);
            estoqueController.cadastrarEstoque(estoque);
            JOptionPane.showMessageDialog(null, "Insumo Cadastrado com sucesso !");

        }
    public void listarEstoque(int id){
        EstoqueController estoqueController = new EstoqueController();
        String recebe = "";
        for (Estoque estoque : estoqueController.listarEstoque(id)){
            recebe = recebe + " " + "ID: " + estoque.getIdInsumo() +
                    " | NOME: " + estoque.getNomeInsumo() +
                    " | QUANTIDADE: " + estoque.getQntdInsumo() +
                    " | PREÇO: " + estoque.getPrecoInsumo() +
                    " | VALIDADE: " + estoque.getValidade() + "\n";
        }
        JOptionPane.showMessageDialog(null, recebe);
    }
    public void editarEstoque(int id){
        EstoqueController estoqueController = new EstoqueController();
        String recebe = "";
        Estoque estoque = estoqueController.listarEstoque(id).get(estoqueController.escolheInsumoEstoque(id));
        int escolhe= Integer.valueOf(showInputDialog("Digite o que Deseja Editar: " +
                "\n1 - Nome" +
                "\n2 - Quantidade" +
                "\n3 - Preco" +
                "\n4 - Validade" +
                "\n5 - Editar Todas Opções"));
        switch (escolhe){
            case 1:
                String recebeNome =  showInputDialog("Digite o Nome do Insumo:" );
                if(recebeNome == null) menuEstoque(id);
                estoque.setNomeInsumo(recebeNome.toUpperCase());
                break;
            case 2:
                String recebeQnt =  showInputDialog("Digite a Quantidade do Insumo: " );
                if(recebeQnt == null) menuEstoque(id);
                estoque.setQntdInsumo(Integer.parseInt(recebeQnt));
                break;
            case 3:
                String recebePreco = showInputDialog("Digite o Preço do Insumo: ");
                if(recebePreco == null) menuEstoque(id);
                estoque.setPrecoInsumo(Float.parseFloat(recebePreco));
                break;
            case 4:
                estoque.setValidade(String.valueOf(LocalDate.now().getYear()));
                break;
            case 5:
                recebeNome =  showInputDialog("Digite o Nome do Insumo:" );
                if(recebeNome == null) menuEstoque(id);
                estoque.setNomeInsumo(recebeNome.toUpperCase());

                recebeQnt =  showInputDialog("Digite a Quantidade do Insumo: " );
                if(recebeQnt == null) menuEstoque(id);
                estoque.setQntdInsumo(Integer.parseInt(recebeQnt));

                recebePreco = showInputDialog("Digite o Preço do Insumo: ");
                if(recebePreco == null) menuEstoque(id);
                estoque.setPrecoInsumo(Float.parseFloat(recebePreco));
                break;
        }
        estoqueController.editarEstoque(estoque);
    }
    public void excluirEstoque(int id){
        EstoqueController estoqueController = new EstoqueController();
        Estoque estoque = new Estoque();
        String recebe = "";
        String recebeInt = String.valueOf(estoqueController.listarEstoque(id).get(estoqueController.escolheInsumoEstoque(id)).getIdInsumo());
        if(recebeInt == null) menuEstoque(id);
        estoque.setIdInsumo(Integer.parseInt(recebeInt));
        estoqueController.excluirEstoque(estoque);
        JOptionPane.showMessageDialog(null, "Insumo excluido com Sucesso ! ");
    }


}
