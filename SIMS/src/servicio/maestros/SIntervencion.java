package servicio.maestros;

import java.util.ArrayList;
import java.util.List;

import interfacedao.maestros.IIntervencionDAO;
import interfacedao.transacciones.IHistoriaIntervencionDAO;

import modelo.maestros.Intervencion;
import modelo.transacciones.ConsultaServicioExterno;
import modelo.transacciones.Historia;
import modelo.transacciones.HistoriaIntervencion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SIntervencion")
public class SIntervencion {

	@Autowired
	private IIntervencionDAO intervencionDAO;
	@Autowired
	private IHistoriaIntervencionDAO intervencionHistoriaDAO;

	public List<Intervencion> buscarDisponibles(Historia historia) {
		List<HistoriaIntervencion> intervencionesHistoricas = intervencionHistoriaDAO.findByHistoria(historia);
		List<Long> ids = new ArrayList<Long>();
		if(intervencionesHistoricas.isEmpty())
			return intervencionDAO.findAll();
		else{
			for(int i=0; i<intervencionesHistoricas.size();i++){
				ids.add(intervencionesHistoricas.get(i).getIntervencion().getIdIntervencion());
			}
			return intervencionDAO.findByIdIntervencionNotIn(ids);
		}
	}

	public Intervencion buscar(long id) {
		return intervencionDAO.findOne(id);
	}

	public void guardar(Intervencion intervencion) {
		intervencionDAO.save(intervencion);
	}

	public void eliminar(Intervencion intervencion) {
		intervencionDAO.delete(intervencion);
	}

	public List<Intervencion> filtroNombre(String valor) {
		return intervencionDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Intervencion> buscarTodos() {
		return intervencionDAO.findAll();
	}

	public Intervencion buscarPorNombre(String value) {
		return intervencionDAO.findByNombre(value);
	}

	public Intervencion buscarUltimo() {
		long id = intervencionDAO.findMaxIdIntervencion();
		if (id != 0)
			return intervencionDAO.findOne(id);
		return null;
	}
}
