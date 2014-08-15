package servicio.maestros;

import interfacedao.maestros.IEspecialistaDAO;
import interfacedao.transacciones.IConsultaEspecialistaDAO;

import java.util.ArrayList;
import java.util.List;

import modelo.maestros.Especialidad;
import modelo.maestros.Especialista;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaEspecialista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SEspecialista")
public class SEspecialista {

	@Autowired
	private IEspecialistaDAO especialistaDAO;
	@Autowired
	private IConsultaEspecialistaDAO consultaEspecialistaDAO;

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

	public List<Especialista> buscarPorEspecialidad(Especialidad especialidad) {
		return especialistaDAO.findByEspecialidad(especialidad);
	}

	public List<Especialista> buscarDisponibles(Consulta consulta) {
		List<ConsultaEspecialista> consultasEspecialista = consultaEspecialistaDAO.findByConsulta(consulta);
		List<String> ids = new ArrayList<String>();
		if(consultasEspecialista.isEmpty())
			return especialistaDAO.findAll();
		else{
			for(int i=0; i<consultasEspecialista.size();i++){
				ids.add(consultasEspecialista.get(i).getEspecialista().getCedula());
			}
			return especialistaDAO.findByCedulaNotIn(ids);
		}
	}
	
}
