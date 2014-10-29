package servicio.transacciones;

import java.util.List;

import interfacedao.transacciones.IOrdenMedicinaDAO;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenMedicina;

import org.springframework.beans.factory.annotation.Autowired;
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

}
