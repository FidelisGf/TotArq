package Dao;

import Model.Empresa;

import java.io.*;

public class EmpresaDAO {
    private File caminhoEmpresa = new File("./config/empresa.txt");

    public String cadastrarEmpresaDAO(Empresa empresa) throws IOException {
        if (! caminhoEmpresa.exists() && empresa.getNomeEmpresa() != null && empresa.getEnderecoEmpresa() != null && empresa.getCnpjEmpresa() != null) {
            FileWriter file = new FileWriter(caminhoEmpresa);
            PrintWriter fileW = new PrintWriter(file);

            fileW.println(empresa);
            file.close();

            return empresa.getNomeEmpresa() + " foi cadastrada com sucesso!";
        }
        else {
            return "nao foi possivel cadastrar a empresa!";
        }
    }

    public String visualizarEmpresaDAO() throws IOException{
        if (caminhoEmpresa.exists()) {
            FileReader file = new FileReader(caminhoEmpresa);
            BufferedReader fileR = new BufferedReader(file);
            String texto = fileR.readLine();
            file.close();

            return texto;
        }
        return "nao ha empresa nao cadastrada!";
    }

    public String editarEmpresaDAO(Empresa empresa) throws IOException {
        if (caminhoEmpresa.exists() && empresa.getNomeEmpresa() != null && empresa.getEnderecoEmpresa() != null && empresa.getCnpjEmpresa() != null) {
            FileWriter file = new FileWriter(caminhoEmpresa);
            PrintWriter fileW = new PrintWriter(file);

            fileW.println(empresa);
            file.close();

            return empresa.getNomeEmpresa() + " foi alterada com sucesso!";
        }
        return "nao foi possivel editar a empresa!";
    }
}
