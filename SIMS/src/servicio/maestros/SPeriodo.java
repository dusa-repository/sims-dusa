package servicio.maestros;

import interfacedao.maestros.IPeriodoDAO;

import java.util.ArrayList;
import java.util.List;

import modelo.maestros.Periodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SPeriodo")
public class SPeriodo {

	@Autowired
	private IPeriodoDAO periodoDAO;
	List<String> ordenar = new ArrayList<String>();
	Sort o;

	public void guardar(Periodo periodo) {
		periodoDAO.save(periodo);
	}

	public void eliminar(long id) {
		periodoDAO.delete(id);
	}

	public List<Periodo> buscarTodos() {
		ordenar = new ArrayList<String>();
		ordenar.add("nombre");
		o = new Sort(Sort.Direction.ASC, ordenar);
		return periodoDAO.findAll(o);
	}

	public Periodo buscarPorNombre(String value) {
		return periodoDAO.findByNombre(value);
	}

	public List<Periodo> filtroNombre(String valor) {
		return periodoDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Periodo> filtroInicio(String valor) {
		return periodoDAO.findByFechaInicioStartingWithAllIgnoreCase(valor);
	}

	public List<Periodo> filtroFin(String valor) {
		return periodoDAO.findByFechaFinStartingWithAllIgnoreCase(valor);
	}

	public Periodo buscar(long idPeriodo) {
		return periodoDAO.findOne(idPeriodo);
	}
}
