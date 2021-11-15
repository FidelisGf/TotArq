package Controller;

import DAO.FuncionarioDAO;
import Model.Funcionario;

import java.util.List;


public class FuncionarioController {
    public void cadastraFuncioanario(Funcionario funcionario){
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.cadastrarFuncionario(funcionario);
    }
    public List<Funcionario> listarTodos(int id){
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.listaTodos(id);
    }
    public boolean iniciarSessao(Funcionario funcionario){
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.iniciarSessao(funcionario);
    }
    public int verificaCargo(Funcionario funcionario){
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return  funcionarioDAO.verificaCargo(funcionario);
    }
}
