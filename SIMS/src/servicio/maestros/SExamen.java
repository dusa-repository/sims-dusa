package servicio.maestros;

import interfacedao.maestros.IExamenDAO;
import interfacedao.transacciones.IConsultaExamenDAO;

import java.util.ArrayList;
import java.util.List;

import modelo.maestros.Examen;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaExamen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SExamen")
public class SExamen {

	@Autowired
	private IExamenDAO examenDAO;
	@Autowired
	private IConsultaExamenDAO consultaExamenDAO;

	public void guardar(Examen examen) {
		examenDAO.save(examen);
	}

	public Examen buscar(long id) {
		return examenDAO.findOne(id);
	}

	public void eliminar(Examen examen) {
		examenDAO.delete(examen);
	}

	public List<Examen> buscarTodos() {
		return examenDAO.findAll();
	}

	public List<Examen> filtroNombre(String valor) {
		return examenDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Examen> filtroTipo(String valor) {
		return examenDAO.findByTipoStartingWithAllIgnoreCase(valor);
	}

	public List<Examen> filtroResultado(String valor) {
		return examenDAO.findByResultadoStartingWithAllIgnoreCase(valor);
	}

	public List<Examen> filtroCosto(String valor) {
		return examenDAO.findByCostoStartingWithAllIgnoreCase(valor);
	}

	public List<Examen> filtroMinimo(String valor) {
		return examenDAO.findByMinimoStartingWithAllIgnoreCase(valor);
	}

	public List<Examen> filtroMaximo(String valor) {
		return examenDAO.findByMaximoStartingWithAllIgnoreCase(valor);
	}

	public Examen buscarPorNombre(String value) {
		return examenDAO.findByNombre(value);
	}

	public List<Examen> buscarDisponibles(Consulta consulta) {
		List<ConsultaExamen> consultasExamen = consultaExamenDAO
				.findByConsulta(consulta);
		List<Long> ids = new ArrayList<Long>();
		if (consultasExamen.isEmpty())
			return examenDAO.findAll();
		else {
			for (int i = 0; i < consultasExamen.size(); i++) {
				ids.add(consultasExamen.get(i).getExamen().getIdExamen());
			}
			return examenDAO.findByIdExamenNotIn(ids);
		}
	}

	public List<Examen> buscarExamenesDisponibles(List<Long> ids) {
		return examenDAO.findByIdExamenNotIn(ids);
	}

	public Examen buscarUltimo() {
		long id = examenDAO.findMaxIdExamen();
		if (id != 0)
			return examenDAO.findOne(id);
		return null;
	}

	public void guardarVarios(List<Examen> examenes) {
		examenDAO.save(examenes);
	}

	public Examen buscarPorReferencia(long idRefD) {
		return examenDAO.findByIdReferencia(idRefD);
	}
}
