package servicio.maestros;

import interfacedao.maestros.ICategoriaDAO;

import java.util.List;

import modelo.maestros.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCategoria")
public class SCategoria {

	@Autowired
	private ICategoriaDAO categoriaDAO;

	public void guardar(Categoria categ) {
		categoriaDAO.save(categ);
	}

	public List<Categoria> buscarTodas() {
		return categoriaDAO.findAll();
	}

	public Categoria buscar(long id) {
		return categoriaDAO.findOne(id);
	}
	
	public void eliminar(Categoria categoria){
		categoriaDAO.delete(categoria);
	}
}
