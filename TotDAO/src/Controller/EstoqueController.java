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
    public void cadastrarEstoque(Estoque estoque){
        estoqueDAO.cadastrarEstoque(estoque);
    }

    public int escolheInsumoEstoque(){
        return estoqueDAO.escolheInsumoEstoque();
    }
    public void editarEstoque(Estoque estoque){
        estoqueDAO.editarEstoque(estoque);
    }
    public void descontarInsumo(Estoque estoque){
        estoqueDAO.descontarInsumo(estoque);
    }
}
