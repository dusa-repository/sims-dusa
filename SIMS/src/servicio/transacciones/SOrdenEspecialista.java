package servicio.transacciones;

import java.util.List;

import interfacedao.transacciones.IOrdenEspecialistaDAO;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenEspecialista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SOrdenEspecialista")
public class SOrdenEspecialista {

	@Autowired
	private IOrdenEspecialistaDAO ordenEspecialistaDAO;

	public List<OrdenEspecialista> buscarPorOrden(Orden orden) {
		return ordenEspecialistaDAO.findByOrden(orden);
	}

	public void borrarEspecialistasDeOrden(Orden orden) {
		List<OrdenEspecialista> lista = ordenEspecialistaDAO.findByOrden(orden);
		if (!lista.isEmpty())
			ordenEspecialistaDAO.delete(lista);
	}

	public void guardar(List<OrdenEspecialista> listaConsultaEspecialista) {
		ordenEspecialistaDAO.save(listaConsultaEspecialista);
	}

	public OrdenEspecialista buscarPorOrdenYIdEspecialista(Orden orden,
			String par3) {
		return ordenEspecialistaDAO.findByOrdenAndEspecialistaCedula(orden,
				par3);
	}

}
