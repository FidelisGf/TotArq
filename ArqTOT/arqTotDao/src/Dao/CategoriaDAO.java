package Dao;

import Model.Categoria;
import Model.Produto;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CategoriaDAO {
    File lista = new File("C:\\Users\\Fifo\\Desktop\\ArqTOT\\Config\\listaCategorias.txt");
    public void Registrar_Categoria(Categoria categoria){
        try {
            File file = new File("C:\\Users\\Fifo\\Desktop\\ArqTOT\\Config\\"+ categoria.getNomeCategoria()+".txt");
            FileWriter fileWriter = new FileWriter(lista,true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            if(!file.exists()){
                file.createNewFile();
            }
            if(!lista.exists()){
                lista.createNewFile();
            }
            printWriter.println(categoria.getNomeCategoria());
            printWriter.close();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<String> listar_Categorias(){
        List<String> lista_Categorias = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(lista);
            BufferedReader br = new BufferedReader(fileReader);

            while (br.ready()){
                lista_Categorias.add(br.readLine());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return lista_Categorias;
    }
    public void excluirCategoria(Categoria categoria, int op){
        List<String> list = listar_Categorias();
        list.remove(op);
        try {
            FileWriter fileWriter = new FileWriter(lista);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(String s : list){
                printWriter.println(s);
            }
            printWriter.close();
            fileWriter.close();

            File file = new File("./Config/" + categoria.getNomeCategoria());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void salvaCategoriaUnidade(String nomeCategoria, Long idUnidade) throws IOException {
        File listaCatUnd = new File("listaCategoriasUnidade.txt");
        FileWriter fw = new FileWriter(listaCatUnd, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(idUnidade);
        pw.println(nomeCategoria);
        fw.close();
        pw.close();
    }
}
