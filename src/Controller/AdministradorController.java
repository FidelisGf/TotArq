package Controller;
import Model.*;
import View.*;

import java.io.IOException;
import java.sql.SQLException;

public class AdministradorController {

    public void menuAdmistradorController(String option) throws IOException{
        AdministradorView adm = new AdministradorView();
        EmpresaView empresa = new EmpresaView();
        UsuarioView usuario = new UsuarioView();
        UnidadeView unidade = new UnidadeView();

        if (option.equals("1")) {  // empresa
            empresa.menuEmpresaView();
            adm.menuAdministradorView();
        }
        else if (option.equals("2")) {  // unidades
            unidade.menuUnidade();
            adm.menuAdministradorView();
        }
        else if (option.equals("3")) {  // funcionarios
            usuario.funcionarioView();
            adm.menuAdministradorView();
        }
        else if (option.equals("4")) {  // categorias
            adm.menuAdministradorView();
        }
        else if (option.equals("5")) {
            adm.menuAdministradorView();
        }
    }
}