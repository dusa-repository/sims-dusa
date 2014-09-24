package servicio.maestros;

import interfacedao.maestros.IProveedorDAO;
import interfacedao.maestros.IProveedorExamenDAO;
import interfacedao.maestros.IProveedorServicioDAO;

import java.util.ArrayList;
import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Examen;
import modelo.maestros.Proveedor;
import modelo.maestros.ProveedorExamen;
import modelo.maestros.ProveedorServicio;
import modelo.maestros.ServicioExterno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SProveedor")
public class SProveedor {

	@Autowired
	private IProveedorDAO proveedorDAO;
	@Autowired
	private IProveedorServicioDAO proveedorServicioDAO;
	@Autowired
	private IProveedorExamenDAO proveedorExamenDAO;

	public void guardar(Proveedor proveedor) {
		proveedorDAO.save(proveedor);

	}

	public Proveedor buscar(long id) {
		return proveedorDAO.findOne(id);
	}

	public void eliminar(Proveedor proveedor) {
		proveedorDAO.delete(proveedor);

	}

	public List<Proveedor> buscarTodos() {
		return proveedorDAO.findAll();
	}

	public List<Proveedor> filtroNombre(String valor) {
		return proveedorDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Proveedor> filtroDireccion(String valor) {
		return proveedorDAO.findByDireccionStartingWithAllIgnoreCase(valor);
	}

	public List<Proveedor> filtroTelefono(String valor) {
		return proveedorDAO.findByTelefonoStartingWithAllIgnoreCase(valor);
	}

	public List<Proveedor> filtroCiudad(String valor) {
		return proveedorDAO.findByCiudadNombreStartingWithAllIgnoreCase(valor);
	}

	public Proveedor buscarPorNombre(String value) {
		return proveedorDAO.findByNombre(value);
	}

	public List<Proveedor> buscarPorServicio(ServicioExterno servicio) {
		List<ProveedorServicio> proveedoresServicio = proveedorServicioDAO
				.findByServicioExterno(servicio);
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		for (int i = 0; i < proveedoresServicio.size(); i++) {
			proveedores.add(proveedoresServicio.get(i).getProveedor());
		}
		return proveedores;
	}

	public Proveedor buscarUltimo() {
		long id = proveedorDAO.findMaxIdProveedor();
		if (id != 0)
			return proveedorDAO.findOne(id);
		else
			return null;

	}

	public List<Proveedor> buscarPorCiudad(Ciudad ciudad) {
		// TODO Auto-generated method stub
		return proveedorDAO.findByCiudad(ciudad);
	}

	public List<Proveedor> buscarPorProveedoresPorExamen(Examen examen) {
		List<ProveedorExamen> lista = proveedorExamenDAO.findByExamen(examen);
		List<Proveedor> prove = new ArrayList<Proveedor>();
		for (int i = 0; i < lista.size(); i++) {
			prove.add(lista.get(i).getProveedor());
		}
		return prove;
	}
}
