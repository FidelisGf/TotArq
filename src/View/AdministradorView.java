package View;

import Controller.*;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class AdministradorView {
    AdministradorController controleAdm = new AdministradorController();
    Scanner scan = new Scanner(System.in);

    public void menuAdministradorView() throws IOException {
        String menu = "Administrador\n\n" +
                "1 - Empresa\n" +
                "2 - Unidades\n" +
                "3 - Funcionarios\n" +
                "4 - Categorias\n" +
                "5 - Produtos\n" +
                "outro - Sair\n";
        String op = JOptionPane.showInputDialog(menu).trim();
        controleAdm.menuAdmistradorController(op);
    }
}
