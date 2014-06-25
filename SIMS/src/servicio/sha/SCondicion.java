package servicio.sha;

import java.util.List;

import interfacedao.sha.ICondicionDAO;

import modelo.sha.Condicion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCondicion")
public class SCondicion {

	@Autowired
	private ICondicionDAO condicionDAO;

	public List<Condicion> buscarTodos() {
		return condicionDAO.findAllOrderByNombre();
	}
}
