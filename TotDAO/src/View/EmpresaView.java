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
        empresa.setNomeEmpresa(JOptionPane.showInputDialog(null, " Nome da Empresa : ").trim());
        empresa.setEnderecoEmpresa(JOptionPane.showInputDialog(null, " Endereço da Empresa : ").trim());
        empresa.setCnpjEmpresa(JOptionPane.showInputDialog(null, " Cnpj da Empresa :").trim());
        controleEmpresa.cadastrarEmpresaController(empresa);
    }

    public void visualizarEmpresaView(){
        Empresa empresa = controleEmpresa.visualizarEmpresaController();
        JOptionPane.showMessageDialog(null, "Nome da Empresa : " + empresa.getNomeEmpresa() + "\nEndereço da Empresa : " + empresa.getEnderecoEmpresa()
        + "\nCnpj da Empresa : " + empresa.getCnpjEmpresa());
    }

    public void editarEmpresaView(){
        String status;
        System.out.print("Nome: ");
        empresa.setNomeEmpresa(scan.nextLine().trim());
        System.out.print("Endereco: ");
        empresa.setEnderecoEmpresa(scan.nextLine().trim());
        System.out.print("Cnpj: ");
        empresa.setCnpjEmpresa(scan.nextLine().trim());
        status = controleEmpresa.editarEmpresaController(empresa);
        System.out.println(status);
    }
}
