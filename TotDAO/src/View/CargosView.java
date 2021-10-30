package View;

import Controller.CargosController;
import Model.Cargos;

import java.util.List;
import java.util.Scanner;

public class CargosView {
    Scanner le = new Scanner(System.in);
    CargosController cargosController = new CargosController();
    public void InserirCargo(int id){
        String nome;
        float salario;
        System.out.printf("Insira o nome do novo cargo : \n");
        nome = le.nextLine();
        System.out.printf("Insira o salario para esse cargo : \n");
        salario = le.nextFloat();
        le.nextLine();
        Cargos cargos = new Cargos(nome, salario);
        cargosController.InserirCargo(cargos, id);
    }
    public void listarTodos(int id){
        for(Cargos cargos : cargosController.listarTodos(id)){
            System.out.println(cargos);
        }
    }
}
