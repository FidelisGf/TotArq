import View.*;
import Dao.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        EmpresaDAO empresa = new EmpresaDAO();
        UsuarioDAO usuario = new UsuarioDAO();
        UsuarioView u = new UsuarioView();
        UnidadeDao unidade = new UnidadeDao();

        unidade.createTable();
        usuario.createTableUsuarios();
        empresa.createTableEmpresa();

        u.loginUsuarioView();

    }
}
