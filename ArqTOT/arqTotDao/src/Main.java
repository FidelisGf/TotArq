import View.*;

public class Main {
    public static void main(String[] args) {
        TotFast fast = new TotFast();
        CarrinhoView carrinhoView = new CarrinhoView();
        fast.Comprar();
        carrinhoView.finalizar_Compra();
    }
}
