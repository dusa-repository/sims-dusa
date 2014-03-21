package servicio.maestros;

import interfacedao.maestros.ILaboratorioDAO;

import java.util.List;

import modelo.maestros.Laboratorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SLaboratorio")
public class SLaboratorio {

	@Autowired
	private ILaboratorioDAO laboratorioDAO;

	public List<Laboratorio> buscarTodos() {
		return laboratorioDAO.findAll();
	}

	public void guardar(Laboratorio laboratorio) {
		laboratorioDAO.save(laboratorio);		
	}

	public Laboratorio buscar(long idLaboratorio) {
		return laboratorioDAO.findOne(idLaboratorio);
	}

	public Laboratorio buscarPorNombre(String value) {
		return laboratorioDAO.findByNombre(value);
	}

	public void eliminar(Laboratorio laboratorio) {
		laboratorioDAO.delete(laboratorio);
	}

	public List<Laboratorio> filtroNombre(String valor) {
		return laboratorioDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}
}
