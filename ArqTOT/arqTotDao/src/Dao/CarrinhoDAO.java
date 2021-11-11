package Dao;

import Model.Avaliacao;
import Model.Carrinho;
import Model.Pagamento;
import Model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CarrinhoDAO {
    File file = new File("carrinho.txt");
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

    public int set_N_Pedido(){
        int id = get_N_Pedido();
        try {
            File file = new File("./Config/pedido.txt");
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(id + 1);
            pw.close();
            fw.close();
        }catch (IOException e){
        }
        return id;
    }
    public int get_N_Pedido(){
        int id = 0;
        try {
            File file = new File("./Config/pedido.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            if(!file.exists()){
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("1");
                pw.close();
                fw.close();
            }
            while (bufferedReader.ready()){
                id = Integer.valueOf(bufferedReader.readLine());
            }
            fileReader.close();
        }catch (IOException e){

        }
        return id;
    }
    public void log_Pedidos(Carrinho carrinho, Avaliacao avaliacao, Pagamento pagamento){
        int id = set_N_Pedido();
        try {
            File file = new File("./Config/LogPedidos");
            FileWriter fileWriter  = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            if(!file.exists()){
                file.createNewFile();
            }
            printWriter.println("Pedido Nº " + id + "  No Valor de R$ : " + carrinho.getValor_Total_Carrinho() + "  Avalido como : " + avaliacao.getTpAvaliacao() + "  Pago por : " + pagamento.getFormaPagamento() );
            printWriter.close();
            fileWriter.close();
        }catch (IOException e){

        }
    }
    public void excluir_do_Carrinho(int op){
        Carrinho carrinho = new Carrinho();
        carrinho.setLista_do_Carrinho(finalizar_compra().getLista_do_Carrinho());
        carrinho.getLista_do_Carrinho().remove(op);
        Adiciona_Carrinho(carrinho);
    }

}
