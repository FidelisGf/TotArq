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

}
