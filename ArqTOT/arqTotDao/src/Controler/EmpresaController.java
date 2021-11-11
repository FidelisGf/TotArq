package Controler;
import Dao.EmpresaDAO;
import Model.*;
import View.*;
import java.io.*;

public class EmpresaController {
    File caminhoEmpresa = new File("../TotArq/ArqTOT/Config/empresa.txt");
    EmpresaDAO empresaDao = new EmpresaDAO();

    public void menuEmpresaController(String option) throws IOException{
        EmpresaView empresa = new EmpresaView();

        if (option.equals("1")) {  // cadastrar empresa
            empresa.cadastrarEmpresaView();
            empresa.menuEmpresaView();
        }
        else if (option.equals("2")) {  // visualizar empresa
            empresa.visualizarEmpresaView();
            empresa.menuEmpresaView();
        }
        else if (option.equals("3")) {  // editar empresa
            empresa.editarEmpresaView();
            empresa.menuEmpresaView();
        }
    }

    public String cadastrarEmpresaController(Empresa empresa) throws IOException{
        return empresaDao.cadastrarEmpresaDAO(empresa);
    }

    public String visualizarEmpresaController() throws IOException {
        return empresaDao.visualizarEmpresaDAO();
    }

    public String editarEmpresaController(Empresa empresa) throws IOException {
        return empresaDao.editarEmpresaDAO(empresa);
    }
}
