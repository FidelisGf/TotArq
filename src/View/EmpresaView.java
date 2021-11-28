package View;

import Model.*;
import Controller.*;

import javax.swing.*;
import java.util.Scanner;
import java.io.*;

public class EmpresaView {
    Empresa empresa = new Empresa();
    Controler.EmpresaController controleEmpresa = new Controler.EmpresaController();
    Scanner scan = new Scanner(System.in);

    public void menuEmpresaView() throws IOException{
        String menu = "Empresa\n\n" +
                "1 - Cadastrar Empresa\n" +
                "2 - Visualizar Empresa\n" +
                "3 - Editar Empresa\n" +
                "outro - Voltar\n";

        String op = JOptionPane.showInputDialog(menu).trim();
        controleEmpresa.menuEmpresaController(op);
    }

    public void cadastrarEmpresaView() throws IOException{
        String status;

        empresa = inserirDadosEmpresa();
        status = controleEmpresa.cadastrarEmpresaController(empresa);
        JOptionPane.showMessageDialog(null, status);
    }

    public void visualizarEmpresaView() throws  IOException {
        JOptionPane.showMessageDialog(null, controleEmpresa.visualizarEmpresaController());
    }

    public void editarEmpresaView() throws IOException {
        String status;

        empresa = inserirDadosEmpresa();
        status = controleEmpresa.editarEmpresaController(empresa);
        JOptionPane.showMessageDialog(null, status);
    }

    public Empresa inserirDadosEmpresa() {
        Empresa e = new Empresa();
        e.setNomeEmpresa(JOptionPane.showInputDialog("Nome").trim());
        e.setEnderecoEmpresa(JOptionPane.showInputDialog("Endereco").trim());
        e.setCnpjEmpresa(JOptionPane.showInputDialog("Cnpj").trim());

        return e;
    }
}
