package View;

import Controller.EmpresaController;
import Model.Cargos;
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
        int op;
        while (true){
            System.out.printf("---------------------------------\n");
            System.out.printf("|||  (1)Criar uma Empresa     |||\n");
            System.out.printf("|||  (2)Listar Empresas       |||\n");
            System.out.printf("|||  (3)Sair                  |||\n");
            System.out.printf("---------------------------------\n");
            op = le.nextInt();
            le.nextLine();
            switch (op){
                case 1:
                    insereEmpresa();
                    menu.espera_Enter();
                    break;
                case 2:
                    mostraEmpresas();
                    break;
                case 3:
                    menu.menu_Principal();
                    break;
            }
        }
    }
    public void insereEmpresa(){
        EmpresaController empresaController = new EmpresaController();
        int cnpj = 0;
        String tmp,tmp1;
        System.out.printf("Insira os dados da empresa : \n");
        System.out.printf("Nome da empresa :\n");
        tmp = le.nextLine();
        System.out.printf("Cnpj da empresa :\n");
        cnpj = le.nextInt();
        le.nextLine();
        System.out.printf("Endereço da empresa : \n");
        tmp1 = le.nextLine();
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
        for (Empresa empresa : list){
            System.out.println(empresa);
        }
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
            Object selectionObject = (String) JOptionPane.showInputDialog(frame, "Choose", "Empresa", JOptionPane.QUESTION_MESSAGE, null, object, object[0]);
            String tmp = selectionObject.toString();
            StringTokenizer st = new StringTokenizer(tmp);
            id = Integer.valueOf(st.nextToken("|"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
}
