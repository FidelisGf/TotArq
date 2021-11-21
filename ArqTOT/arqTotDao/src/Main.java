import View.*;
import Dao.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        EmpresaDAO empresa = new EmpresaDAO();
        UsuarioDAO usuario = new UsuarioDAO();
        usuario.createTableUsuarios();
        empresa.createTableEmpresa();

        TotFast totFast = new TotFast();
        totFast.MenuTotem();
    }
}
