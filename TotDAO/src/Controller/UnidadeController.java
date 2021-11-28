package Controller;

import DAO.UnidadeDao;
import Model.Unidade;
import java.util.List;

public class UnidadeController {
    UnidadeDao undDAO = new UnidadeDao();
    public void cadastrarUnidade(Unidade unidade) {
        undDAO.cadastraUnidade(unidade);
    }

    public List<Unidade> listar(){
        return undDAO.listarUnidade();
    }

    public Unidade buscaUnidadeById(int idUnid){
       return undDAO.buscarUnidadeById(idUnid);
    }

    public void excluirUnidade(int idSelect){
        Unidade und = undDAO.buscarUnidadeById(idSelect);
        undDAO.excluirUnidade(und);
        System.out.println("Unidade excluida com sucesso!");
    }
    public int escolherUnidade(){
        UnidadeDao unidadeDao = new UnidadeDao();
        return unidadeDao.escolheUnidade();
    }
    public void editarUnidade(Unidade uni, int idSelec){
        undDAO.editarUnidade(uni, idSelec);
    }
}
