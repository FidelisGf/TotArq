package View;

import Controller.CarrinhoController;
import Controller.CategoriaController;
import Controller.ProdutoController;
import Model.Carrinho;
import Model.Pagamento;
import Model.Produto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoView {
    ProdutoController pc = new ProdutoController();
    CategoriaController ct = new CategoriaController();
    CarrinhoController carrinhoController = new CarrinhoController();

    public void insereNoCarrinho(int id){
        int cat = ct.escolherCategoria(id);
        List<Produto> list = new ArrayList<>();
        int idProduto = pc.escolher_produto(cat, id);
        Carrinho carrinho = new Carrinho();
        list.add(pc.listaProdutoporCategoria(cat,id).get(idProduto));
        list = adicionarVirgula(list);
        carrinho.setLista_do_carrinho(list);
        carrinho.setValor_Total(Calcula_total(carrinho.getLista_do_carrinho()));
        carrinhoController.insereNoCarrinho(carrinho);
        JOptionPane.showMessageDialog(null,"Produto Adicionado ao Carrinho");
    }
    public Float Calcula_total(List<Produto> list){
        Float Total = Float.valueOf(0);
        for(Produto produto : list){
            Total += produto.getPreco();
        }
        return Total;
    }
    public List<Produto> adicionarVirgula(List<Produto> list){
        String tmp ="";
        for(Produto produto : list){
            tmp += produto.getNome() + " ,";
            produto.setNome(tmp);
        }
        return list;
    }
    public void listarCarrinho(){
        int i = 0;
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String output = "";
        if(carrinhoController.listarCarrinho().getLista_do_carrinho() == null){
            JOptionPane.showMessageDialog(null, "Carrinho Vazio");
        }else{
            for(Produto produto : carrinhoController.listarCarrinho().getLista_do_carrinho()){
                String tmp = "Produto - > " + produto.getNome() + String.valueOf(produto.getPreco());
                output += tmp + " \n\n";
                i++;
            }
            output += "Valor Total - > " + String.valueOf(carrinhoController.listarCarrinho().getValor_Total()) + " \n\n";
            JOptionPane.showMessageDialog(frame,output);
        }
    }
    public void excluirDoCarrinho(int id1){
        Menu menu = new Menu();
        int id = carrinhoController.escolherProdutoDoCarrinho();
        if(id == -1){
            JOptionPane.showMessageDialog(null, "Carrinho Vazio");
            menuCarrinho(id1);
        }
        Carrinho carrinho = new Carrinho();
        carrinho.setLista_do_carrinho(carrinhoController.listarCarrinho().getLista_do_carrinho());
        if(menu.menuConfirmar().contains("1")){
            carrinhoController.excluirDoCarrinho(carrinho, id);
            JOptionPane.showMessageDialog(null, "Produto excluido do carrinho com sucesso !");
        }else{
            menuCarrinho(id1);
        }

    }
    public void finalizar_Pedido(int id){
        Menu menu = new Menu();
        Carrinho carrinho = carrinhoController.listarCarrinho();
        PagamentoView pagamentoView = new PagamentoView();
        if(menu.menuConfirmar().contains("1")){
            pagamentoView.menuPagamento(carrinho);
        }else{
            menuCarrinho(id);
        }
    }
    public String exibeMenuCarrinho(){
        String[] escolhas = {"1", "2", "3", "4"};
        String menuTexto = "1 | Adicionar ao Carrinho | " + "\n\n2 | Listar Carrinho |" + "\n\n3 | Excluir do Carrinho  |\n\n4 | Finalizar Pedido  |\n\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuProdutos", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public void menuCarrinho(int id){
        while (true){
            String opc =exibeMenuCarrinho();
            switch (opc){
                case "1":
                    insereNoCarrinho(id);
                    break;
                case "2":
                    listarCarrinho();
                    break;
                case "3":
                    excluirDoCarrinho(id);
                    break;
                case "4":
                    finalizar_Pedido(id);
                    break;
            }
        }
    }



}
