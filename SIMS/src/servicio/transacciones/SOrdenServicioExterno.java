package servicio.transacciones;

import java.util.List;

import interfacedao.transacciones.IOrdenServicioExternoDAO;
import modelo.transacciones.ConsultaServicioExterno;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenServicioExterno;

import org.springframework.beans.factory.annotation.Autowired;
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
}
