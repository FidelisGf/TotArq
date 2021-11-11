package Controler;

import Dao.CarrinhoDAO;
import Model.Avaliacao;
import Model.Carrinho;
import Model.Pagamento;

public class CarrinhoController {
    public void adiciona_Carrinho(Carrinho carrinho){
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        carrinhoDAO.Adiciona_Carrinho(carrinho);
    }
    public Carrinho finalizar_pedido(){
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        return  carrinhoDAO.finalizar_compra();
    }
    public void log_Pedidos(Carrinho carrinho, Avaliacao avaliacao, Pagamento pagamento){
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        carrinhoDAO.log_Pedidos(carrinho,avaliacao,pagamento);
    }
}
