package servicio.transacciones;

import interfacedao.transacciones.IOrdenServicioExternoDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.maestros.Proveedor;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenServicioExterno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SOrdenServicioExterno")
public class SOrdenServicioExterno {

	@Autowired
	private IOrdenServicioExternoDAO ordenServicioDAO;

	public List<OrdenServicioExterno> buscarPorOrden(Orden orden) {
		return ordenServicioDAO.findByOrden(orden);
	}

	public void borrarServiciosDeOrden(Orden orden) {
		List<OrdenServicioExterno> lista = ordenServicioDAO.findByOrden(orden);
		if (!lista.isEmpty())
			ordenServicioDAO.delete(lista);
	}

	public void guardar(List<OrdenServicioExterno> listaServicioExterno) {
		ordenServicioDAO.save(listaServicioExterno);
	}

	public List<OrdenServicioExterno> buscarPorOrdenYProveedor(Orden orden,
			Long part5) {
		return ordenServicioDAO.findByOrdenAndProveedorIdProveedor(orden, part5);
	}

	public List<OrdenServicioExterno> buscarEntreFechas(Date desde, Date hasta) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("proveedorIdProveedor");
		ordenar.add("ordenFechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenServicioDAO.findByOrdenFechaOrdenBetween(desde, hasta, o);
	}

	public List<OrdenServicioExterno> buscarPorProveedorEntreFechas(Date desde,
			Date hasta, Proveedor proveedor) {

		List<String> ordenar = new ArrayList<String>();
		ordenar.add("proveedorIdProveedor");
		ordenar.add("OrdenFechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenServicioDAO
				.findByProveedorAndOrdenFechaOrdenBetween(proveedor,
						desde, hasta, o);
	}

	public double sumPorOrden(Orden orden) {
		return ordenServicioDAO.sumByOrden(orden);
	}
}
