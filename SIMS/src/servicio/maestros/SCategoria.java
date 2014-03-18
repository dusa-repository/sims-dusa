package servicio.maestros;

import interfacedao.maestros.ICategoriaDAO;

import modelo.maestros.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCategoria")
public class SCategoria {

	@Autowired
	private ICategoriaDAO interfaceCategoria;

	public void guardar(Categoria categoria) {
		// TODO Auto-generated method stub
		interfaceCategoria.save(categoria);
	}
}
