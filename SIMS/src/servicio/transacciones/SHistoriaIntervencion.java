package servicio.transacciones;

import java.util.List;

import interfacedao.transacciones.IHistoriaIntervencionDAO;

import modelo.maestros.Intervencion;
import modelo.transacciones.Historia;
import modelo.transacciones.HistoriaIntervencion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SHistoriaIntervencion")
public class SHistoriaIntervencion {

	@Autowired
	private IHistoriaIntervencionDAO historiaIntervencionDAO;

	public List<HistoriaIntervencion> buscarPorHistoria(Historia historia) {
		return historiaIntervencionDAO.findByHistoria(historia);
	}

	public void limpiar(Historia historia) {
		List<HistoriaIntervencion> borrados = historiaIntervencionDAO.findByHistoria(historia);
		if(!borrados.isEmpty())
			historiaIntervencionDAO.delete(borrados);
	}

	public void guardar(List<HistoriaIntervencion> historialIntervenciones) {
		historiaIntervencionDAO.save(historialIntervenciones);
	}

	public List<HistoriaIntervencion> buscarPorIntervencion(
			Intervencion intervencion) {
		return historiaIntervencionDAO.findByIntervencion(intervencion);
	}
}
