package Controller;

import DAO.CarrinhoDAO;
import Model.Avaliacao;
import Model.Carrinho;
import Model.Pagamento;


public class CarrinhoController {
    public void insereNoCarrinho(Carrinho carrinho){
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        carrinhoDAO.insereNoCarrinho(carrinho, false);
    }
    public Carrinho listarCarrinho(){
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        return carrinhoDAO.listarCarrinho();
    }
    public void excluirDoCarrinho(Carrinho carrinho, int op) {
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        carrinhoDAO.excluirDoCarrinho(carrinho, op);
    }
    public int escolherProdutoDoCarrinho(){
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        return carrinhoDAO.escolher_ProdutoDoCarrinho();
    }
    public void limparCarrinho(){
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        carrinhoDAO.limparCarrinho();
    }

}
