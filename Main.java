import javax.sound.sampled.SourceDataLine;

public class Main {
    public static void main(String[] args) {
        Cpf c = new Cpf();
        String cpf = "";
        boolean validado = c.cpfValidado(cpf);
        System.out.println(validado);
    }
}