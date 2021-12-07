package Controller;

import DAO.RegistroVendaDAO;
import Model.Carrinho;
import Model.Produto;
import Model.RegistroVenda;

import java.util.List;

public class RegistroVendaController {
    RegistroVendaDAO registroVendaDAO = new RegistroVendaDAO();

    public void RegistrarVendaProduto(Carrinho carrinho){
        registroVendaDAO.RegistraVendaProduto(carrinho);
    }
    public List<RegistroVenda> pegarVendas(){
        return registroVendaDAO.PegaVendas();
    }
}
