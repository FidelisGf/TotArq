package View;
import Controler.UnidadeControler;
import Model.Unidade;

import java.io.IOException;
import java.util.Scanner;

public class UnidadeView {
    private int escolha;
    Scanner scan = new Scanner(System.in);
    UnidadeControler uc = new UnidadeControler();
    public void menuUnidade() throws IOException {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("******************************************");
            System.out.println("*         1 - Cadastrar Unidade          *");
            System.out.println("*         2 - Listar Unidade             *");
            System.out.println("*         3 - Limpar todas as unidades   *");
            System.out.println("*         4 - Buscar Unidade             *");
            System.out.println("*         5 - Editar Unidade             *");
            System.out.println("*         6 - Excluir Unidade            *");
            System.out.println("*         7 - Listar Categorias Unidade  *");
            System.out.println("*         8 - Sair                       *");
            System.out.println("******************************************");
            escolha = scan.nextInt();
            switch (escolha) {
                case 1:
                    insertUnidade();
                    break;
                case 2:
                    listarUnidades();
                    break;
                case 3:
                    limpaArquivo();
                    break;
                case 4:
                    buscaUnidade();
                    break;
                case 5:
                    editUnidade();
                    break;
                case 6:
                    excluirUnidade();
                    break;
                case 7:
                    uc.listaNomeIdUnid();
                    listarCategoriasUnidades();
                    break;
                case 8:
                    System.out.println("Xau!");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }while (escolha != 8);
    }

    public void insertUnidade() throws IOException {
        String nick, ende, estado;
        System.out.println("Nome da Unidade: ");
        nick = scan.nextLine();
        System.out.println("Endereço da Unidade: ");
        ende = scan.nextLine();
        System.out.println("UF: ");
        estado = scan.nextLine();
        System.out.println("Unidade " + nick.toUpperCase() + " cadastrada com sucesso");
        Unidade u1 = new Unidade(nick, ende, estado);
        uc.cadastraUnidade(u1);
    }

    public void listarUnidades() throws IOException {
        uc.listarUnidade();
    }

    public void limpaArquivo() throws IOException {
        System.out.println("Tem certeza que deseja excluir todas as unidades? Sim [s], [sim] Não[qualquer outro digito]");
        String resposta = scan.nextLine();
        uc.limpaArquivo(resposta);
    }

    public void buscaUnidade() throws IOException {
        System.out.println("Digite o ID da unidade");
        Long idSelec = scan.nextLong();
        uc.searchUnd(idSelec);
    }

    public void editUnidade() throws IOException {
        System.out.println("Insira o id da unidade a ser editada");
        long idSelec = scan.nextLong();
        if(uc.verifId(idSelec)) {
            String nick, ende, estado;
            scan.nextLine();
            System.out.println("\nDigite o novo nome da Unidade: ");
            nick = scan.nextLine();
            System.out.println("Digite o novo Endereço da Unidade: ");
            ende = scan.nextLine();
            System.out.println("Digite o novo UF: ");
            estado = scan.nextLine();
            System.out.println("Unidade " + nick.toUpperCase() + " editada com sucesso");
            Unidade u1 = new Unidade(nick, ende, estado);
            uc.editUnid(idSelec, u1);
        }
    }

    public void excluirUnidade() throws IOException{
        System.out.println("Insira o id da unidade que deseja excluir");
        long idSelec = scan.nextLong();
        if(uc.verifId(idSelec)){
            scan.nextLine();
            System.out.println("Se os dados da unidade estiverem certo e ainda desejar excluir, digite [s], [sim], [SIM]");
            String resp = scan.nextLine();
            uc.excluirUnidade(idSelec, resp);
        }
    }

    public void listarCategoriasUnidades() throws IOException {
        System.out.println("\nInsira o id da Unidade");
        Long idUnid = scan.nextLong();
        scan.nextLine();
        uc.listarCatUnid(idUnid);
    }

}
