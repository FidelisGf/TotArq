package View;

import Model.*;
import Controler.*;
import java.util.Scanner;
import java.io.*;

public class EmpresaView {
    Empresa empresa = new Empresa();
    EmpresaController controleEmpresa = new EmpresaController();
    Scanner scan = new Scanner(System.in);

    public void menuEmpresaView() throws IOException{
        System.out.println("*****************************");
        System.out.println("         Empresa");
        System.out.println("*****************************");
        System.out.println("1 - Cadastrar Empresa");
        System.out.println("2 - Visualizar Empresa");
        System.out.println("3 - Editar Empresa");
        System.out.println("outro - Voltar");
        System.out.println("*****************************");
        controleEmpresa.menuEmpresaController(scan.nextLine().trim());
    }

    public void cadastrarEmpresaView() throws IOException{
        String status;

        System.out.print("Nome: ");
        empresa.setNomeEmpresa(scan.nextLine().trim());
        System.out.print("Endereco: ");
        empresa.setEnderecoEmpresa(scan.nextLine().trim());
        System.out.print("Cnpj: ");
        empresa.setCnpjEmpresa(scan.nextLine().trim());
        status = controleEmpresa.cadastrarEmpresaController(empresa);
        System.out.println(status);
    }

    public void visualizarEmpresaView() throws  IOException {
        System.out.println(controleEmpresa.visualizarEmpresaController());
    }

    public void editarEmpresaView() throws IOException {
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
