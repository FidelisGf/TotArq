package Dao;

import Model.Categoria;
import Model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProdutoDAO {
    File file = new File("./Config/idProduto.txt");

    public void Registrar_Produto(Produto produto, Categoria categoria ,boolean op){
        if(verifica_se_existe(categoria,produto) || !op){
            if(op){
                produto.setIdProduto(SetNovoId());
            }
            try {
                File file = new File("./Config/" + categoria.getNomeCategoria() + ".txt");
                FileWriter fileWriter = new FileWriter(file, op);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(produto.getIdProduto() + "|" + produto.getNomeProduto() + "|" + produto.getValorProduto());
                printWriter.close();
                fileWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Produto ja Cadastrado nessa Categoria !\n");
        }

    }
    public int PegaIdAtual(){
        int id = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()){
                id = Integer.parseInt(bufferedReader.readLine());
            }
            fileReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return id;
    }
    public int SetNovoId(){
        int id = PegaIdAtual();
        try {
            File file = new File("./Config/idProduto.txt");
            if(file.exists()){
                FileWriter fileWriter = new FileWriter(file);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                file.createNewFile();
                printWriter.println(id + 1);
                printWriter.close();
                fileWriter.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return id;
    }
    public List<Produto> Listar_Produto_Categoria(Categoria categoria){
        StringTokenizer myTokens;
        List<Produto> lista = new ArrayList<>();
        try {
            File file = new File("./Config/"+ categoria.getNomeCategoria()+".txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()){
                Produto produto = new Produto();
                myTokens = new StringTokenizer(bufferedReader.readLine(), "|");
                while (myTokens.hasMoreTokens()){
                    produto.setIdProduto(Integer.valueOf(myTokens.nextToken()));
                    produto.setNomeProduto(myTokens.nextToken());
                    produto.setValorProduto(Float.valueOf(myTokens.nextToken()));
                }
                lista.add(produto);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return lista;
    }
    public boolean verifica_se_existe(Categoria categoria, Produto produto1){
        List<Produto> list = this.Listar_Produto_Categoria(categoria);
        for(Produto produto : list){
            if(produto.getNomeProduto().contains(produto1.getNomeProduto())){
                return false;
            }
        }
        return true;
    }
    public Produto Retorna_Produto(Categoria categoria, int op){
        List<Produto> list = this.Listar_Produto_Categoria(categoria);
        Produto produto = list.get(op);
        return produto;
    }
    public void excluir_produto(Categoria categoria, int op){
        List<Produto> list = this.Listar_Produto_Categoria(categoria);
        list.remove(op);
        for(int i = 0; i < list.size();i++){
            this.Registrar_Produto(list.get(i), categoria, false);
        }
    }
    public void editar_produto(Categoria categoria, int op, String escolha, Produto produto){
        switch (escolha){
            case "1":
                List<Produto> list = this.Listar_Produto_Categoria(categoria);
                this.excluir_produto(categoria, op);
                list.get(op).setNomeProduto(produto.getNomeProduto());
                for(int i = 0; i < list.size()-1;i++){
                    this.insere_edicao(list.get(i), categoria);
                }
                break;
            case "2":
                break;
        }
    }
    public void insere_edicao(Produto produto, Categoria categoria){
        try{
            File file = new File("C:\\Users\\Fifo\\Desktop\\ArqTOT\\Config\\" + categoria.getNomeCategoria() + ".txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(produto.getIdProduto() + "|" + produto.getNomeProduto() + "|" + produto.getValorProduto());
            printWriter.close();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
