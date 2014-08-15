package servicio.maestros;

import interfacedao.maestros.IProveedorExamenDAO;

import java.util.List;

import modelo.maestros.Examen;
import modelo.maestros.Proveedor;
import modelo.maestros.ProveedorExamen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SProveedorExamen")
public class SProveedorExamen {

	@Autowired
	private IProveedorExamenDAO proveedorExamenDAO;

	public List<ProveedorExamen> buscarExamenesUsados(Proveedor proveedor) {
		return proveedorExamenDAO.findByProveedor(proveedor);
	}

	public void eliminar(List<ProveedorExamen> examenes) {
		proveedorExamenDAO.delete(examenes);
	}

	public void guardar(List<ProveedorExamen> listaExamen) {
		proveedorExamenDAO.save(listaExamen);
	}

	public List<ProveedorExamen> buscarPorExamen(Examen examen) {
		return proveedorExamenDAO.findByExamen(examen);
	}
}
