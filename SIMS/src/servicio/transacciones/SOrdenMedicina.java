package servicio.transacciones;

import interfacedao.transacciones.IOrdenMedicinaDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.transacciones.Orden;
import modelo.transacciones.OrdenMedicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SOrdenMedicina")
public class SOrdenMedicina {

	@Autowired
	private IOrdenMedicinaDAO ordenMedicinaDAO;

	public List<OrdenMedicina> buscarPorOrden(Orden orden) {
		return ordenMedicinaDAO.findByOrden(orden);
	}

	public void borrarMedicinasDeOrden(Orden orden) {
		List<OrdenMedicina> lista = ordenMedicinaDAO.findByOrden(orden);
		if (!lista.isEmpty())
			ordenMedicinaDAO.delete(lista);
	}

	public void guardar(List<OrdenMedicina> listaMedicina) {
		ordenMedicinaDAO.save(listaMedicina);
	}

	public List<OrdenMedicina> buscarOrdenesEntreFechas(Date desde, Date hasta) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("ordenPacienteCedula");
		ordenar.add("ordenFechaOrden");
		ordenar.add("medicinaNombre");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenMedicinaDAO.findByOrdenFechaOrdenBetween(desde, hasta, o);
	}

}
