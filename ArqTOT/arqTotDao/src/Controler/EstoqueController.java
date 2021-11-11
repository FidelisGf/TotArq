package Controler;

import Dao.*;
import Model.*;
import View.*;

import java.util.List;

public class EstoqueController {
    public List<Produto> listarEstoque(){
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        return estoqueDAO.listarEstoque();
    }

    public void cadastrarEstoque(Produto produto){
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        estoqueDAO.cadastraEstoque(produto);
    }
}
