package View;

import Controller.EmpresaController;
import Model.Empresa;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EmpresaView {
    Menu menu = new Menu();
    Scanner le = new Scanner(System.in);
    public void menu(){
        String op;
        while (true){
            op = exibeMenuEmpresas();
            switch (op){
                case "1":
                    insereEmpresa();
                    menu.espera_Enter();
                    break;
                case "2":
                    mostraEmpresas();
                    break;
                case "3":
                    menu.menu_Chefe();
                    break;
            }
        }
    }
    public String exibeMenuEmpresas(){
        String[] escolhas = {"1", "2", "3"};
        String menuTexto = "1 | Inserir Nova Empresa | " + "\n\n2 | Listar Empresas |" + "\n\n3 | Sair |\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuEmpresas", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
    public void insereEmpresa(){
        EmpresaController empresaController = new EmpresaController();
        int cnpj = 0;
        String tmp,tmp1;
        tmp = (String) JOptionPane.showInputDialog(null, "Insira o nome da empresa");
        cnpj = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o Cnpj da empresa"));
        tmp1 = (String) JOptionPane.showInputDialog(null,"Insira o endereço da empresa : ");
        Empresa empresa = new Empresa(tmp, cnpj, tmp1);
        empresaController.insereEmpresa(empresa);
    }
    public List<Empresa> listarEmpresa(){
        EmpresaController empresaController = new EmpresaController();
        return empresaController.listarEmpresas();
    }
    public void mostraEmpresas(){
        EmpresaController empresaController = new EmpresaController();
        List<Empresa> list = new ArrayList<>();
        list = empresaController.listarEmpresas();
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String output = "";
        for (Empresa empresa : list) {
            String tmp = "NOME : " + empresa.getNome() + " Com o CNPJ : " + String.valueOf(empresa.getCnpj()) + " Localizada no ENDEREÇO : " + empresa.getEndereco();
            output += tmp + " \n\n";
        }
        JOptionPane.showMessageDialog(frame,output);
    }
    public int escolherEmpresa(int id){
        List<Empresa>list = new ArrayList<>();
        list = listarEmpresa();
        String[] object = new String[list.size()];
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        int i = 0;
        System.out.printf("Escolha uma empresa\n");
        try {
            for (Empresa empresa : list) {
                object[i] = String.valueOf(empresa.getId()) + "|" + empresa.getNome().toString();
                i++;
            }
            Object selectionObject = (String) JOptionPane.showInputDialog(frame, "Escolha uma empresa", "Empresa", JOptionPane.QUESTION_MESSAGE, null, object, object[0]);
            String tmp = selectionObject.toString();
            StringTokenizer st = new StringTokenizer(tmp);
            id = Integer.valueOf(st.nextToken("|"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
}
