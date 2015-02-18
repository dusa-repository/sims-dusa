package servicio.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import interfacedao.transacciones.IOrdenEspecialistaDAO;
import modelo.maestros.Especialista;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenEspecialista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

	public List<OrdenEspecialista> buscarEntreFechas(Date desde, Date hasta) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("especialistaEspecialidadDescripcion");
		ordenar.add("especialistaCedula");
		ordenar.add("ordenFechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenEspecialistaDAO.findByOrdenFechaOrdenBetween(desde, hasta, o);
	}

	public List<OrdenEspecialista> buscarPorEspecialistaEntreFechas(Date desde,
			Date hasta, Especialista especialista2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("ordenFechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenEspecialistaDAO
				.findByEspecialistaAndOrdenFechaOrdenBetween(
						especialista2, desde, hasta, o);
	}

	public double sumPorOrden(Orden orden) {
		return ordenEspecialistaDAO.sumByOrden(orden);
	}

}
