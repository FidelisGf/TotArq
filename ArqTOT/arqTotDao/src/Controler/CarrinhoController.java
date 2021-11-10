package Controler;

import Dao.CarrinhoDAO;
import Model.Carrinho;

public class CarrinhoController {
    public void adiciona_Carrinho(Carrinho carrinho){
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        carrinhoDAO.Adiciona_Carrinho(carrinho);
    }
    public Carrinho finalizar_pedido(){
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        return  carrinhoDAO.finalizar_compra();
    }
}
