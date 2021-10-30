import DAO.FuncionarioDAO;
import Model.Funcionario;
import View.*;

public class Main {
    public static void main(String[] args) {
        FuncionarioView funcionarioView = new FuncionarioView();
        funcionarioView.iniciarSessao();
    }
}
