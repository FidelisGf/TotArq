package Controller;

import DAO.EstoqueDAO;
import Model.Estoque;

import java.util.List;

public class EstoqueController {
    EstoqueDAO estoqueDAO = new EstoqueDAO();
    public List<Estoque> listarEstoque(int id){
        return estoqueDAO.listarEstoque(id);
    }
    public void cadastrarEstoque(Estoque estoque){
        estoqueDAO.cadastrarEstoque(estoque);
    }

    public int escolheInsumoEstoque(int id){
        return estoqueDAO.escolheInsumoEstoque(id);
    }
    public void editarEstoque(Estoque estoque){
        estoqueDAO.editarEstoque(estoque);
    }
    public void descontarInsumo(Estoque estoque){
        estoqueDAO.descontarInsumo(estoque);
    }
}
