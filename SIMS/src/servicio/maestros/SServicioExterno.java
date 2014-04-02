package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IServicioExternoDAO;

import modelo.maestros.ServicioExterno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SServicioExterno")
public class SServicioExterno {

	@Autowired
	private IServicioExternoDAO servicioExternoDAO;

	public void guardar(ServicioExterno servicioExterno) {
		servicioExternoDAO.save(servicioExterno);
	}

	public ServicioExterno buscar(long id) {
		return servicioExternoDAO.findOne(id);
	}

	public void eliminar(ServicioExterno servicioExterno) {
		servicioExternoDAO.delete(servicioExterno);
	}

	public ServicioExterno buscarPorNombre(String value) {
		return servicioExternoDAO.findByNombre(value);
	}

	public List<ServicioExterno> buscarTodas() {
		return servicioExternoDAO.findAll();
	}

	public List<ServicioExterno> filtroNombre(String valor) {
		return servicioExternoDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<ServicioExterno> filtroDireccion(String valor) {
		return servicioExternoDAO.findByDireccionStartingWithAllIgnoreCase(valor);
	}

	public List<ServicioExterno> filtroTelefono(String valor) {
		return servicioExternoDAO.findByTelefonoStartingWithAllIgnoreCase(valor);
	}

	public List<ServicioExterno> filtroCiudad(String valor) {
		return servicioExternoDAO.findByCiudadNombreStartingWithAllIgnoreCase(valor);
	}
}