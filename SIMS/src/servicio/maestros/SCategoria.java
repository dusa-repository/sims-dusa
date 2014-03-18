package servicio.maestros;

import interfacedao.maestros.ICategoriaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCategoria")
public class SCategoria {

	@Autowired
	private ICategoriaDAO interfaceCategoria;
}
