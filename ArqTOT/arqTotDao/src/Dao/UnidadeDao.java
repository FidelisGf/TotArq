package Dao;
import Model.Unidade;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class UnidadeDao {
    List<String> listAux = new ArrayList<>();

    public void gravarUnidade(Unidade uni) throws IOException{
        try{
            FileWriter fwg = new FileWriter("../TotArq/ArqTOT/Config/manusUnid.txt", true);
            PrintWriter bwg = new PrintWriter(fwg);
            FileWriter fw = new FileWriter("../TotArq/ArqTOT/Config/getIdNick.txt", true);
            PrintWriter bw = new PrintWriter(fw);
            bw.print("Nome: " + uni.getNomeUnidade() + "\n");
            bwg.println(uni.toString());
            bwg.close();
            fwg.close();
            fw.close();
            bw.close();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void buscarUnidade(String idSelecionado) throws IOException {
        Path path = Paths.get("../TotArq/ArqTOT/Config/manusUnid.txt");
        listAux = Files.readAllLines(path);
        for (int i = 0; i < listAux.size(); i++){
            if (idSelecionado.equals(listAux.get(i))) {
                System.out.println(listAux.get(i));
                System.out.println(listAux.get(i+1).toUpperCase());
                System.out.println(listAux.get(i+2).toUpperCase());
                System.out.println(listAux.get(i+3).toUpperCase());
                break;
            }
        }
    }

    public void buscarUnidadeByName(String nome) throws IOException {
        Path path = Paths.get("../TotArq/ArqTOT/Config/manusUnid.txt");
        listAux = Files.readAllLines(path);
        for (int i = 0; i < listAux.size(); i++){
            if (nome.equals(listAux.get(i))) {
                System.out.println(listAux.get(i));
                break;
            }
        }
    }

    public void editarUnidade(String idSelec, Unidade unidEd) throws IOException {
        Path path = Paths.get("../TotArq/ArqTOT/Config/manusUnid.txt");
        listAux = Files.readAllLines(path);
        FileWriter fwg = new FileWriter("../TotArq/ArqTOT/Config/manusUnid.txt");
        PrintWriter bwg = new PrintWriter(fwg);

        for (int i = 0; i < listAux.size(); i++){
            if (idSelec.equals(listAux.get(i))) {
                listAux.set(i+1, unidEd.getNomeUnidade());
                listAux.set(i+2, unidEd.getEnderecoUnidade());
                listAux.set(i+3, unidEd.getUfUnidade());
            }
            bwg.println(listAux.get(i));
        }
        fwg.close();
        bwg.close();
    }

    public void excluirUnidade(String idEscolhido) throws IOException {
        Path path = Paths.get("../TotArq/ArqTOT/Config/manusUnid.txt");
        listAux = Files.readAllLines(path);
        FileWriter fwg = new FileWriter("../TotArq/ArqTOT/Config/manusUnid.txt");
        PrintWriter bwg = new PrintWriter(fwg);

        for (int i = 0; i < listAux.size(); i++){
            if (idEscolhido.equals(listAux.get(i))) {
                listAux.set(i, "");
                listAux.set(i+1, "");
                listAux.set(i+2, "");
                listAux.set(i+3, "");
            }
            if(!listAux.get(i).trim().equals("")) {
                bwg.println(listAux.get(i));
            }
        }
        fwg.close();
        bwg.close();
    }

    public boolean idExiste(String idEscolhido) throws IOException {
        Path path = Paths.get("../TotArq/ArqTOT/Config/manusUnid.txt");
        listAux = Files.readAllLines(path);
        if (listAux.contains(idEscolhido)) {
            return true;
        }else {
            return false;
        }
    }

    public void escreveId(int cont) throws IOException{
        File file = new File("../TotArq/ArqTOT/Config/guarda_ID.txt");
        FileWriter fwid = new FileWriter(file);
        PrintWriter bwid = new PrintWriter(fwid);
        try {
            bwid.println(cont);
            fwid.close();
            bwid.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void leId() throws IOException {
        Unidade unid = new Unidade();
        FileReader arquivo = new FileReader("../TotArq/ArqTOT/Config/guarda_ID.txt");
        BufferedReader arquivoR = new BufferedReader(arquivo);
        String line;
        line = arquivoR.readLine();
        int pegaid = Integer.parseInt(line);
        pegaid += 1;
        unid.setIdUnidade(pegaid);
        escreveId(pegaid);
        arquivo.close();
        arquivoR.close();
        try {
            FileWriter fwg = new FileWriter("../TotArq/ArqTOT/Config/manusUnid.txt", true);
            PrintWriter bwg = new PrintWriter(fwg);
            bwg.print("\n" + pegaid + "\n");
            FileWriter fw = new FileWriter("../TotArq/ArqTOT/Config/getIdNick.txt", true);
            PrintWriter bw = new PrintWriter(fw);
            bwg.print("\n" + pegaid + "\n");
            bw.print("ID: " + pegaid + " | ");
            fwg.close();
            bwg.close();
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarUnidade() throws IOException{
        FileReader arquivo = new FileReader("../TotArq/ArqTOT/Config/manusUnid.txt");
        BufferedReader arquivoR = new BufferedReader(arquivo);
        String linha;

        linha = arquivoR.readLine();
        while (linha != null) {
            System.out.println(linha);
            linha = arquivoR.readLine();
        }
        arquivo.close();
    }

    public void limparArquivo() throws IOException {
        FileWriter fwg = new FileWriter("../TotArq/ArqTOT/Config/manusUnid.txt");
        BufferedWriter bwg = new BufferedWriter(fwg);
        bwg.write("");
        bwg.write("");
        bwg.close();
        fwg.close();
    }

    public void listarCategoriasUnidade(long idSelecionado) throws IOException {
        Path path = Paths.get("../TotArq/ArqTOT/Config/listaCategoriasUnidade.txt");
        listAux = Files.readAllLines(path);
        String convertLong = Long.toString(idSelecionado);
        for (int i = 0; i < listAux.size(); i++){
            if (convertLong.equals(listAux.get(i))) {
                System.out.println(listAux.get(i+1).toUpperCase());
            }
        }
    }

    public void listaNomeIdUnidade() throws IOException {
        FileReader arquivo = new FileReader("../TotArq/ArqTOT/Config/getIdNick.txt");
        BufferedReader arquivoR = new BufferedReader(arquivo);
        String linha;

        linha = arquivoR.readLine();
        while (linha != null) {
            System.out.println(linha);
            linha = arquivoR.readLine();
        }
        arquivo.close();
    }
}
