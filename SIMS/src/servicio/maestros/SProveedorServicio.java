package servicio.maestros;

import java.util.ArrayList;
import java.util.List;

import interfacedao.maestros.IProveedorServicioDAO;

import modelo.maestros.Proveedor;
import modelo.maestros.ProveedorServicio;
import modelo.maestros.ServicioExterno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SProveedorServicio")
public class SProveedorServicio {

	@Autowired
	private IProveedorServicioDAO proveedorServicioDAO;

	public List<ProveedorServicio> buscarEstudiosUsados(Proveedor proveedor) {
		return proveedorServicioDAO.findByProveedor(proveedor);
	}

	public void eliminar(List<ProveedorServicio> estudios) {
		proveedorServicioDAO.delete(estudios);

	}

	public void guardar(List<ProveedorServicio> listaEstudios) {
		proveedorServicioDAO.save(listaEstudios);

	}

	public ProveedorServicio buscarPorCodigoDeAmbos(long parseLong, long id) {
		return proveedorServicioDAO
				.findByProveedorIdProveedorAndServicioExternoIdServicioExterno(
						parseLong, id);
	}

	public List<Proveedor> buscarPorCodigoDeServicio(long id) {
		List<ProveedorServicio> lista = proveedorServicioDAO
				.findByServicioExternoIdServicioExterno(id);
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				proveedores.add(lista.get(i).getProveedor());
			}
		}
		return proveedores;
	}
}
