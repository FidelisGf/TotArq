package Controler;

import Dao.ProdutoDAO;
import Model.Categoria;
import Model.Produto;

import java.util.List;

public class ProdutoController {
    public void Registrar_Produto(Produto produto, Categoria categoria){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.Registrar_Produto(produto,categoria, true);
    }
    public List<Produto> Listar_Produto_Categoria(Categoria categoria){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.Listar_Produto_Categoria(categoria);
    }
    public Produto Retorna_Produto(Categoria categoria, int op){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.Retorna_Produto(categoria,op);
    }
    public void excluir_Produto(Categoria categoria, int op){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.excluir_produto(categoria,op);
    }
    public void editar_Produto(Categoria categoria, int op, String escolha, Produto produto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.editar_produto(categoria, op, escolha, produto);
    }
}
