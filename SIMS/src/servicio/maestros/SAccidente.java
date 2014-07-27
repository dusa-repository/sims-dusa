package servicio.maestros;

import java.util.ArrayList;
import java.util.List;

import interfacedao.maestros.IAccidenteDAO;
import interfacedao.transacciones.IHistoriaAccidenteDAO;

import modelo.maestros.Accidente;
import modelo.sha.ClasificacionAccidente;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaServicioExterno;
import modelo.transacciones.Historia;
import modelo.transacciones.HistoriaAccidente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SAccidente")
public class SAccidente {

	@Autowired
	private IAccidenteDAO accidenteDAO;
	@Autowired
	private IHistoriaAccidenteDAO accidenteHistoriaDAO;

	public Accidente buscar(long parseLong) {
		return accidenteDAO.findOne(parseLong);
	}

	public List<Accidente> buscarDisponibles(Historia historia, String string) {
		List<HistoriaAccidente> accidentesHistoricos = accidenteHistoriaDAO
				.findByHistoriaAndTipoAccidente(historia, string);
		List<Long> ids = new ArrayList<Long>();
		if (accidentesHistoricos.isEmpty())
			return accidenteDAO.findAllOrderByCodigoAsc();
		else {
			for (int i = 0; i < accidentesHistoricos.size(); i++) {
				ids.add(accidentesHistoricos.get(i).getAccidente()
						.getIdAccidente());
			}
			return accidenteDAO.findByIdAccidenteNotIn(ids);
		}
	}

	public List<Accidente> filtroNombre(String valor) {
		return accidenteDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Accidente> buscarTodos() {
		return accidenteDAO.findAllOrderByCodigoAsc();
	}

	public List<Accidente> filtroCodigo(String valor) {
		return accidenteDAO.findByIdAccidenteStartingWithAllIgnoreCase(valor);
	}

	public List<Accidente> filtroClasificacion(String valor) {
		return accidenteDAO
				.findByClasificacionNombreStartingWithAllIgnoreCase(valor);
	}

	public void guardar(Accidente accidente) {
		accidenteDAO.saveAndFlush(accidente);
	}

	public void eliminar(Accidente accidente) {
		accidenteDAO.delete(accidente);
	}

	public Accidente buscarUltimo() {
		long id = accidenteDAO.findMaxIdDiagnostico();
		if (id != 0)
			return accidenteDAO.findOne(id);
		return null;
	}

	public List<Accidente> buscarPorClasificacion(
			ClasificacionAccidente clasificacionAccidente) {
		// TODO Auto-generated method stub
		return accidenteDAO.findByClasificacion(clasificacionAccidente);
	}
}
