package Controler;
import Model.Categoria;
import View.*;

import java.io.IOException;

public class AdministradorController {

    public void menuAdmistradorController(String option) throws IOException{
        AdministradorView adm = new AdministradorView();
        EmpresaView empresa = new EmpresaView();
        UsuarioView usuario = new UsuarioView();
        UnidadeView unidade = new UnidadeView();
        Menu menu = new Menu();

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
            menu.Menu_Principal();
            adm.menuAdministradorView();
        }
    }

}
