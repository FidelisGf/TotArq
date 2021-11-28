package Controller;
import Model.*;
import View.*;

import java.io.IOException;
import java.sql.SQLException;

public class AdministradorController {

    public void menuAdmistradorController(String option){
        AdministradorView adm = new AdministradorView();
        adm.menu_Chefe();
    }
}
