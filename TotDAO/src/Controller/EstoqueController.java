package Controller;

import DAO.EstoqueDAO;
import Model.Estoque;

import java.util.List;

public class EstoqueController {
    EstoqueDAO estoqueDAO = new EstoqueDAO();
    public int escolherInsumoEstoque(){
        return estoqueDAO.escolheInsumoEstoque();
    }
    public List<Estoque> listarEstoque(){
        return estoqueDAO.listarEstoque();
    }
}
