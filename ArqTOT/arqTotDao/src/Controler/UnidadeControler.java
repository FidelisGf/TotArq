package Controler;
import Model.Unidade;
import Dao.UnidadeDao;
import java.io.File;
import java.io.IOException;

public class UnidadeControler {
    UnidadeDao undDAO = new UnidadeDao();
    public void cadastraUnidade(Unidade u1) throws IOException {
        File file1 = new File("guarda_ID.txt");
        if(!file1.exists()){
            undDAO.escreveId(0);
        }
        undDAO.leId();
        undDAO.gravarUnidade(u1);
    }

    public void listarUnidade() throws IOException {
        undDAO.listarUnidade();
    }

    public void limpaArquivo(String resp) throws IOException {
        if(confirmacaoOpcao(resp)){
            undDAO.limparArquivo();
        }else {
            System.out.println("cancelado...");
        }
    }

    public void searchUnd(Long idSelect) throws IOException{
        if(verifId(idSelect)){}
    }

    public void editUnid(long idEscolhido, Unidade unE) throws IOException {
        String convertLong = Long.toString(idEscolhido);
        undDAO.editarUnidade(convertLong, unE);
    }

    public void excluirUnidade(long idEsc, String escolha)throws IOException{
        String convertLong = Long.toString(idEsc);
        if(confirmacaoOpcao(escolha)){
            undDAO.excluirUnidade(convertLong);
        }else{
            System.out.println("Cancelamento da exclusao...");
        }
    }

    public boolean verifId(Long id) throws IOException {
        String convertLong = Long.toString(id);
        if(undDAO.idExiste(convertLong)){
            undDAO.buscarUnidade(convertLong);
            return true;
        }else {
            System.out.println("Id inexistente, escolha um id existente");
            return false;
        }
    }

    public boolean confirmacaoOpcao(String resposta){
        if(resposta.equals("sim") || resposta.equals("Sim") || resposta.equals("s") || resposta.equals("SIM")){
            return true;
        }else {
            return false;
        }
    }

    public void listarCatUnid(long idUnd) throws IOException {
        undDAO.listarCategoriasUnidade(idUnd);
    }

    public void listaNomeIdUnid() throws IOException {
        undDAO.listaNomeIdUnidade();
    }
}
