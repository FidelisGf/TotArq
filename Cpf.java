public class Cpf {
    public boolean cpfValidado(String cpf) {
        String novoCpf = "";
        int somaTotal;
        int i;
        int j;
        int indiceMaior;

        try {
            if (cpf.length() == 11) {
            
                for (i = 0; i < 2; i ++) {
                    somaTotal = 0;
    
                    if (i == 0) {
                        indiceMaior = 9;
                    }
                    else {
                        indiceMaior = 10;
                    }
    
                    for (j = 0; j < indiceMaior; j ++) {
                        if (i == 0) {
                            novoCpf += Character.toString(cpf.charAt(j));
                        }
                        char getNumero = cpf.charAt(j);
                        String numeroAtual = Character.toString(getNumero);
                        somaTotal += Integer.parseInt(numeroAtual) * (indiceMaior + 1 - j);
                    }
                    
                    int equacao = 11 - (somaTotal % 11);
    
                    if (equacao > 9) {
                        novoCpf += "0";
                    }
                    else {
                        novoCpf += Integer.toString(equacao);
                    }
    
                }
                
            }
            
            String sequencia = Character.toString(novoCpf.charAt(0));
            for (i = 0; i < 10; i ++) {
                sequencia += Character.toString(sequencia.charAt(0));;
            }

            if (cpf.equals(novoCpf) && ! sequencia.equals(novoCpf)) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }
}
