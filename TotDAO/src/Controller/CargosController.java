package Controller;

import DAO.CargosDAO;
import Model.Cargos;

import java.util.List;

public class CargosController {
    public void InserirCargo(Cargos cargos){
        CargosDAO cargosDAO = new CargosDAO();
        cargosDAO.InserirCargo(cargos);
    }
    public List<Cargos> listarTodos(int id){
        CargosDAO cargosDAO = new CargosDAO();
        return cargosDAO.ListarTodas(id);
    }
}
