package Controller;

import DAO.EmpresaDAO;
import Model.Empresa;

import java.util.List;

public class EmpresaController {
    public void insereEmpresa(Empresa empresa){
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresaDAO.insereEmpresa(empresa);
    }
    public List<Empresa> listarEmpresas(){
        EmpresaDAO empresaDAO = new EmpresaDAO();
        return empresaDAO.listarEmpresa();
    }
}
