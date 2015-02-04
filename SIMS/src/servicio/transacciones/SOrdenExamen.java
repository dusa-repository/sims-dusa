package servicio.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import interfacedao.transacciones.IOrdenExamenDAO;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenExamen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SOrdenExamen")
public class SOrdenExamen {

	@Autowired
	private IOrdenExamenDAO ordenExamenDAO;

	public List<OrdenExamen> buscarPorOrden(Orden orden) {
		return ordenExamenDAO.findByOrden(orden);
	}

	public void borrarExamenesDeOrden(Orden orden) {
		List<OrdenExamen> lista = ordenExamenDAO.findByOrden(orden);
		if (!lista.isEmpty())
			ordenExamenDAO.delete(lista);
	}

	public void guardar(List<OrdenExamen> listaConsultaExamen) {
		ordenExamenDAO.save(listaConsultaExamen);
	}

	public List<OrdenExamen> buscarPorOrdenYProveedor(Orden orden, Long part5) {
		return ordenExamenDAO.findByOrdenAndProveedorIdProveedor(orden, part5);
	}

	public List<OrdenExamen> buscarEntreFechas(Date desde, Date hasta) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("proveedorIdProveedor");
		ordenar.add("ordenFechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenExamenDAO.findByOrdenFechaOrdenBetween(desde, hasta, o);
	}

}
