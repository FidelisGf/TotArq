package View;

import Controler.*;

import java.io.*;
import java.util.*;

public class AdministradorView {
    AdministradorController controleAdm = new AdministradorController();
    Scanner scan = new Scanner(System.in);

    public void executaAdministradorView(long idAdm) throws IOException{
        // fazer um constructor dps
        menuAdministradorView();
    }

    public void menuAdministradorView() throws IOException {
        System.out.println("*****************************");
        System.out.println("      Administrador");
        System.out.println("*****************************");
        System.out.println("1 - Empresa");
        System.out.println("2 - Unidades");
        System.out.println("3 - Funcionarios");
        System.out.println("4 - Controle de Categoria e Produtos");
        System.out.println("outro - Sair");
        System.out.println("*****************************");
        controleAdm.menuAdmistradorController(scan.nextLine().trim());
    }
}
