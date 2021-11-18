package View;

import Controller.CargosController;
import Model.Cargos;

import javax.swing.*;

public class CargosView {
    EmpresaView empresaView = new EmpresaView();
    CargosController cargosController = new CargosController();
    public void inserirCargo(int idEmpresa){
        Menu menu = new Menu();

        String nome = "";
        float salario = Float.valueOf(0);
        nome =(String) JOptionPane.showInputDialog(null, "Digite o nome do novo Cargo");
        salario = Float.valueOf(JOptionPane.showInputDialog(null, "Digite o Salario para esse cargo "));
        Cargos cargos = new Cargos(nome, salario);
        if(menu.menuConfirmar().contains("1")){
            cargosController.InserirCargo(cargos, idEmpresa);
        }else{
            JOptionPane.showMessageDialog(null, "Ação cancelada com sucesso !");
        }

    }
    public void listarTodos(int id){
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String output = "";
        for(Cargos cargos : cargosController.listarTodos(id)){
            String tmp = "Cargo : " + cargos.getNomeCargo() + "  Com salario de  R$ : " + String.valueOf(cargos.getSalarioDoCargo());
            output += tmp + " \n\n";
        }
        JOptionPane.showMessageDialog(frame,output);
    }
    public void menu_Cargos(int id){
        while (true){
            String opc = exibeMenuCargos();
            switch (opc){
                case "1":
                    inserirCargo(id);
                    break;
                case "2":
                    listarTodos(id);
                    break;
                case "3":
                    FuncionarioView funcionarioView = new FuncionarioView();
                    funcionarioView.MenuFuncionario(id);
                    break;
            }
        }
    }
    public String exibeMenuCargos(){
        String[] escolhas = {"1", "2", "3"};
        String menuTexto = "1 | Inserir novo cargo | " + "\n\n2 | Listar Cargos |" + "\n\n3 | Sair  |";
        return (String) JOptionPane.showInputDialog(null,"Selecione uma opção :\n\n" + menuTexto,"MenuProdutos", JOptionPane.INFORMATION_MESSAGE, null,escolhas, escolhas[0]);
    }
}
