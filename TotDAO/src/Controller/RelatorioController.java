package Controller;

import DAO.CarrinhoDAO;
import DAO.RelatorioDAO;
import Model.Avaliacao;
import Model.Carrinho;
import Model.Pagamento;
import Model.Produto;

public class RelatorioController {
    public void fazerLogAdicionar(Produto produto){
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        relatorioDAO.fazerLogAdicionar(produto);
    }
    public void fazerLogEditarNomeProduto(Produto produto, String nomePassado){
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        relatorioDAO.fazerlogEditarNomeProduto(produto, nomePassado);
    }
    public void fazerLogExcluirProduto(Produto produto){
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        relatorioDAO.fazerLogExcluirProduto(produto);
    }
    public String listarAcoes(){
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        return relatorioDAO.listarAcoes();
    }
    public void logPedidos(Carrinho carrinho, Pagamento pagamento, Avaliacao avaliacao){
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        relatorioDAO.logPedidos(carrinho, pagamento, avaliacao);
    }
    public String listarVendas(){
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        return relatorioDAO.listarVendas();
    }
}
