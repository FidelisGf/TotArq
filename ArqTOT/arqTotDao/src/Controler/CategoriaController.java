package Controler;

import Dao.CategoriaDAO;
import Dao.UnidadeDao;
import Model.Categoria;

import java.io.IOException;
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
    public void salvaCatUnid(String nomeCat, Long idUnd) throws IOException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        UnidadeDao undDao = new UnidadeDao();
        String convertUnid = Long.toString(idUnd);
        if(undDao.idExiste(convertUnid)){
            categoriaDAO.salvaCategoriaUnidade(nomeCat, idUnd);
        }else{
            System.out.println("Id inexistente ou não encontrado");
        }
    }
}
