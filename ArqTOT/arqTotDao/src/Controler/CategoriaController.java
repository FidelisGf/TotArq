package Controler;

import Dao.CategoriaDAO;
import Model.Categoria;

import java.util.List;

public class CategoriaController {
    public void Registrar_Categoria(Categoria categoria){
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.Registrar_Categoria(categoria);
    }
    public List<String> listar_Categorias(){
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return  categoriaDAO.listar_Categorias();
    }
    public void excluirCategoria(Categoria categoria, int op){
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.excluirCategoria(categoria, op);
    }
}
