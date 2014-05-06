package servicio.maestros;

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
}
