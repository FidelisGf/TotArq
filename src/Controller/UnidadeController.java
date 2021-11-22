package Controller;

import Dao.UnidadeDao;
import Model.Unidade;
import java.util.List;

public class UnidadeController {
    UnidadeDao undDAO = new UnidadeDao();
    public void cadastrarUnidade(Unidade unidade) {
        undDAO.cadastraUnidade(unidade);
    }

    public void listarUnidade(){
        List<Unidade> list = undDAO.listarUnidade();
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    public void buscaUnidadeById(int idUnid){
        Unidade und = undDAO.buscarUnidadeById(idUnid);
        System.out.printf("ID unidade: %d  | Nome da Unidade: %s  |  Endereço da Unidade:  %s  | UF da Unidade:  %s \n", und.getIdUnidade(), und.getNomeUnidade(), und.getEnderecoUnidade(), und.getUfUnidade());
    }

    public void excluirUnidade(int idSelect){
        Unidade und = undDAO.buscarUnidadeById(idSelect);
        undDAO.excluirUnidade(und);
        System.out.println("Unidade excluida com sucesso!");
    }

    public void editarUnidade(Unidade uni, int idSelec){
        undDAO.editarUnidade(uni, idSelec);
    }
}
