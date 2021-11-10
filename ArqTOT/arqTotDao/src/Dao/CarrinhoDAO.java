package Dao;

import Model.Carrinho;
import Model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CarrinhoDAO {
    File file = new File("C:\\Users\\Fifo\\Desktop\\ArqTOT\\carrinho.txt");
    public void Adiciona_Carrinho(Carrinho carrinho){
        try {

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            if(!file.exists()){
                file.createNewFile();
            }
            for(Produto produto : carrinho.getLista_do_Carrinho()){
                printWriter.println(produto.getNomeProduto() +"|"+produto.getValorProduto());
            }
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public Carrinho finalizar_compra(){
        try {
            Carrinho carrinho = new Carrinho();
            StringTokenizer myTokens;
            String vl = "";
            if(!file.exists()){
                System.out.println("Carrinho Vazio");
            }else{
                List<Produto> tmp = new ArrayList<>();
                Float Valor_Total = Float.valueOf(0);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()){
                    myTokens = new StringTokenizer(bufferedReader.readLine(), "|");
                    while (myTokens.hasMoreTokens()) {
                        Produto produto = new Produto();
                        produto.setNomeProduto(myTokens.nextToken());
                        vl = myTokens.nextToken();
                        produto.setValorProduto(Float.valueOf(vl));
                        Valor_Total += Float.valueOf(vl);
                        tmp.add(produto);
                    }
                }
                carrinho.setLista_do_Carrinho(tmp);
                carrinho.setValor_Total_Carrinho(Valor_Total);
            }
            return carrinho;
        }catch (IOException e){
            throw new RuntimeException();
        }

    }
}
