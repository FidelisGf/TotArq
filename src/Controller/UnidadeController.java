package Controller;

import Dao.*;
import Model.*;

import java.sql.SQLException;
import java.util.List;

public class UnidadeController {
    UnidadeDao undDAO = new UnidadeDao();
    public void cadastrarUnidade(Unidade unidade) {
        undDAO.cadastraUnidade(unidade);
    }

    public String listarUnidade(){
        String concate = "";
        List<Unidade> list = undDAO.listarUnidade();
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
            concate += list.get(i) + "\n";
        }
        return concate;
    }
    public String listaNomeId() throws SQLException {
        String concat = "";
        List<String> lista = undDAO.listarUndDisp();
        for (int i = 0; i < lista.size(); i++){
            System.out.println(lista.get(i));
            concat += lista.get(i) + "\n";
        }
        return concat;
    }

    public String buscaUnidadeById(int idUnid) throws SQLException {
        Unidade und = undDAO.buscarUnidadeById(idUnid);
        System.out.printf("ID unidade: %d  | Nome da Unidade: %s  |  Endereço da Unidade:  %s  | UF da Unidade:  %s \n", und.getIdUnidade(), und.getNomeUnidade(), und.getEnderecoUnidade(), und.getUfUnidade());
        String printa = "ID unidade: "+ und.getIdUnidade() +"\nNome da Unidade:  "+ und.getNomeUnidade() + "\nEndereço da Unidade: "+ und.getEnderecoUnidade()+"\nUF da Unidade:  "+ und.getUfUnidade() +  "\n";
        return printa;
    }

    public void excluirUnidade(int idSelect){
        Unidade und = undDAO.buscarUnidadeById(idSelect);
        undDAO.excluirUnidade(und);
        System.out.println("Unidade excluida com sucesso!");
    }

    public void editarUnidade(Unidade uni, int idSelec){
        undDAO.editarUnidade(uni, idSelec);
    }

    public String listaFuncDaUnd(String id){
        String concat = "";
        List<String> lista = undDAO.visualizarFuncionariosDaUnidade(id);
        for (int i = 0; i < lista.size(); i++){
            System.out.println(lista.get(i));
            concat += lista.get(i) + "\n";
        }
        return concat;
    }
}
