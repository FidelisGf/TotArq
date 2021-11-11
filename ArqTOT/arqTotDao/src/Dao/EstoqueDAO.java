package Dao;

import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EstoqueDAO {
    File file = new File("../Config/Estoque.txt");

    public void cadastraEstoque(Produto produto){
        try{
            File file = new File("../Config/Estoque.txt");
            FileWriter arq = new FileWriter(file, true);
            PrintWriter writer = new PrintWriter(arq);
            writer.println(produto.getNomeProduto() + "|" + produto.getQuantidadeProduto());
            arq.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public List<Produto> listarEstoque(){
        StringTokenizer token;
        List<Produto> list = new ArrayList<>();
        try{
            File file = new File("../Config/Estoque.txt");
            FileReader read = new FileReader(file);
            BufferedReader buff = new BufferedReader(read);
            while(buff.ready()){
                Produto produto = new Produto();
                token = new StringTokenizer(buff.readLine(), "|");
                while(token.hasMoreTokens()){
                    produto.setNomeProduto(token.nextToken());
                    produto.setQuantidadeProduto(Integer.valueOf(token.nextToken()));
                }
                list.add(produto);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }
}
