package Controller;

import DAO.ProdutoDAO;
import Model.Produto;

import javax.sound.midi.MidiChannel;
import java.util.List;

public class ProdutoController {
    public void insereProduto(Produto produto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.insereProduto(produto);
    }
    public List<Produto> listaProdutoporCategoria(int id, int id2){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.listaProdutosporCategoria(id, id2);
    }
    public List<Produto> listarTodosProdutos(int id){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.listarTodosProdutos(id);
    }
    public void editarProduto(Produto produto, int op){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.EditarProduto(produto, op);
    }
    public void adicionarQuantidadeProduto(Produto produto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.adicionarQuantidadeProduto(produto);
    }
    public int escolher_produto(int idEmpresa, int idCategoria){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return  produtoDAO.escolher_produto(idCategoria, idEmpresa);
    }
    public void excluirProduto(String nomeProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.Excluir_produto(nomeProduto);
    }
}
