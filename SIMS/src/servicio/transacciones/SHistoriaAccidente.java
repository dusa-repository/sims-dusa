package servicio.transacciones;

import interfacedao.transacciones.IHistoriaAccidenteDAO;

import java.util.List;

import modelo.maestros.Accidente;
import modelo.transacciones.Historia;
import modelo.transacciones.HistoriaAccidente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SHistoriaAccidente")
public class SHistoriaAccidente {

	@Autowired
	private IHistoriaAccidenteDAO historiaAccidenteDAO;

	public List<HistoriaAccidente> buscarPorHistoria(Historia historia, String string) {
		return historiaAccidenteDAO.findByHistoriaAndTipoAccidente(historia, string);
	}

	public void limpiar(Historia historia) {
		List<HistoriaAccidente> borrados = historiaAccidenteDAO.findByHistoria(historia);
		if(!borrados.isEmpty())
			historiaAccidenteDAO.delete(borrados);
	}

	public void guardar(List<HistoriaAccidente> historialAccidentes) {
		historiaAccidenteDAO.save(historialAccidentes);
	}

	public List<HistoriaAccidente> buscarPorAccidente(Accidente accidente) {
		return historiaAccidenteDAO.findByAccidente(accidente);
	}
}
