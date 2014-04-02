package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IEspecialistaDAO;

import modelo.maestros.Especialista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SEspecialista")
public class SEspecialista {

	@Autowired
	private IEspecialistaDAO especialistaDAO;

	public void guardar(Especialista especialista) {
		especialistaDAO.save(especialista);
	}

	public Especialista buscar(String id) {
		return especialistaDAO.findOne(id);
	}

	public void eliminar(Especialista especialista) {
		especialistaDAO.delete(especialista);
	}

	public List<Especialista> buscarTodos() {
		return especialistaDAO.findAll();
	}

	public List<Especialista> filtroCedula(String valor) {
		return especialistaDAO.findByCedulaStartingWithAllIgnoreCase(valor);
	}

	public List<Especialista> filtroNombre(String valor) {
		return especialistaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Especialista> filtroApellido(String valor) {
		return especialistaDAO.findByApellidoStartingWithAllIgnoreCase(valor);
	}

	public List<Especialista> filtroCosto(String valor) {
		return especialistaDAO.findByCostoStartingWithAllIgnoreCase(valor);
	}

	public List<Especialista> filtroEspecialidad(String valor) {
		return especialistaDAO.findByEspecialidadDescripcionStartingWithAllIgnoreCase(valor);
	}
	
}
