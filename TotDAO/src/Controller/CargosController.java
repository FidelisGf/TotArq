package Controller;

import DAO.CargosDAO;
import Model.Cargos;

import java.util.List;

public class CargosController {
    public void InserirCargo(Cargos cargos, int id){
        CargosDAO cargosDAO = new CargosDAO();
        cargosDAO.InserirCargo(cargos, id);
    }
    public List<Cargos> listarTodos(int id){
        CargosDAO cargosDAO = new CargosDAO();
        return cargosDAO.ListarTodas(id);
    }
}
