package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IEspecialidadDAO;

import modelo.maestros.Especialidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SEspecialidad")
public class SEspecialidad {

	@Autowired
	private IEspecialidadDAO especialidadDAO;

	public void guardar(Especialidad especialidad) {
		especialidadDAO.save(especialidad);
	}

	public List<Especialidad> buscarTodas() {
		return especialidadDAO.findAll();
	}
}
