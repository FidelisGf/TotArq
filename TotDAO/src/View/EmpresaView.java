package View;

import Model.*;
import Controller.*;

import javax.swing.*;
import java.util.Scanner;
import java.io.*;

public class EmpresaView {
    Empresa empresa = new Empresa();
    EmpresaController controleEmpresa = new EmpresaController();
    Scanner scan = new Scanner(System.in);
    public String exibeMenuEmpresa(){
        String[] escolhas = {"1", "2", "3", "4"};
        String menuTexto = "1 | Cadastrar Empresa  | " + "\n\n2 | Visualizar Empresa |" + "\n\n3 | Editar Empresa  |\n\n4 | Sair |";
        return (String) JOptionPane.showInputDialog(null,"" + menuTexto,"MenuEmpresas", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public void menuEmpresaView(){
        controleEmpresa.menuEmpresaController(exibeMenuEmpresa());
    }
    public void cadastrarEmpresaView(){
        empresa = inserirDadosEmpresa();
        controleEmpresa.cadastrarEmpresaController(empresa);
        //JOptionPane.showMessageDialog(null, status);
    }

    public void visualizarEmpresaView(){
        Empresa empresa = controleEmpresa.visualizarEmpresaController();
        JOptionPane.showMessageDialog(null, "Nome da Empresa : " + empresa.getNomeEmpresa() + "\nEndereço da Empresa : " + empresa.getEnderecoEmpresa()
        + "\nCnpj da Empresa : " + empresa.getCnpjEmpresa());
    }

    public void editarEmpresaView(){
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
