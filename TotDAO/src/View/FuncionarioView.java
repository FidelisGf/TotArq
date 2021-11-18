package View;

import Controller.CargosController;
import Controller.FuncionarioController;
import Model.Cargos;
import Model.Funcionario;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FuncionarioView {
    FuncionarioController funcionarioController = new FuncionarioController();
    Scanner le = new Scanner(System.in);
    Menu menu = new Menu();
    public void cadastraFuncionario(int id){
        String nome, ende, senha;
        int idade;
        nome = (String) JOptionPane.showInputDialog(null, "Nome do Funcionario:");
        senha = (String) JOptionPane.showInputDialog(null, "Senha do Funcionario: ");
        idade = Integer.parseInt(JOptionPane.showInputDialog(null, "Idade do Funcionario : "));
        ende = (String) JOptionPane.showInputDialog(null, "Endereço do Funcionario : ");
        CargosController cargosController = new CargosController();
        List<Cargos> list = cargosController.listarTodos(id);
        int tamanho = list.size();
        String[] object = new String[list.size()];
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        int i = 0;
        System.out.printf("Escolha um cargo para o Funcionario\n");
        try {
            for(Cargos cargos : list){
                object[i] = String.valueOf(cargos.getId()) + "|" + cargos.getNomeCargo().toString();
                i++;
            }
            Object selectionObject = (String) JOptionPane.showInputDialog(frame, "Choose", "Categoria", JOptionPane.QUESTION_MESSAGE, null, object, object[0]);
            String tmp = selectionObject.toString();
            StringTokenizer st = new StringTokenizer(tmp);
            int op = Integer.valueOf(st.nextToken("|"));
            Funcionario funcionario = new Funcionario(nome, idade, ende, op, senha);
            funcionarioController.cadastraFuncioanario(funcionario);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void listarTodos(int id){
        List<Funcionario> list = funcionarioController.listarTodos(id);
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String output = "";
        for (Funcionario funcionario : list) {
            String tmp = "NOME : " + funcionario.getNome() + " Com a Idade de : " + String.valueOf(funcionario.getIdade()) + "anos, mora no ENDERECO : " + funcionario.getEndereco().toString() + " e Possui o CARGO atual de : " + funcionario.getCargo().toString();
            output += tmp + " \n\n";
        }
        JOptionPane.showMessageDialog(frame,output);
    }
    public void MenuFuncionario(int id){
        CargosView cargosView = new CargosView();
        String op = "";
        Menu menu = new Menu();
        while (true){
            op = exibeMenuFuncionario();
            switch (op){
                case "1":
                    cadastraFuncionario(id);
                    break;
                case "2":
                    listarTodos(id);
                    break;
                case "3":
                    cargosView.menu_Cargos(id);
                    break;
                case "4":
                    menu.menu_Chefe();
                    break;
            }
        }
    }
    public String exibeMenuFuncionario(){
        String[] escolhas = {"1", "2", "3", "4"};
        String menuTexto = "1 | Inserir Novo Funcionario | " + "\n\n2 | Listar todos os funcionarios |" + "\n\n3 | Configurar Cargos  |" + "\n\n4 | Sair  |\n\n";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n" + menuTexto,"MenuFuncionario", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }



    public void iniciarSessao(){
        String nome,senha;
        nome = (String) JOptionPane.showInputDialog(null, "Usuario:");
        senha = (String) JOptionPane.showInputDialog(null, "Senha:");
        Funcionario funcionario = new Funcionario(nome, senha);
        boolean verifica = funcionarioController.iniciarSessao(funcionario);
        if(verifica){
            if(funcionarioController.verificaCargo(funcionario) == 1){
//                JOptionPane.showMessageDialog(null, "Login Realizado com sucesso !");
                menu.menu_Chefe();
            }else {
                //JOptionPane.showMessageDialog(null, "Login Realizado com sucesso !");
                menu.exibeMenuFuncionario();
            }
        }else {
            JOptionPane.showMessageDialog(null, "Usuario Não Cadastrado !");
        }
    }

}
