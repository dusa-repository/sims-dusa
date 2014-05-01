package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IAntecedenteDAO;
import interfacedao.maestros.IProveedorDAO;

import modelo.maestros.Proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

	@Service("SProveedor")
	public class SProveedor {

		@Autowired
		private IProveedorDAO	proveedorDAO;

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
			return  proveedorDAO.findAll();
		}

		public List<Proveedor> filtroNombre(String valor) {
			return  proveedorDAO.findByNombreStartingWithAllIgnoreCase(valor);
		}

		public List<Proveedor> filtroDireccion(String valor) {
			return  proveedorDAO.findByDireccionStartingWithAllIgnoreCase(valor);
		}

		public List<Proveedor> filtroTelefono(String valor) {
			return  proveedorDAO.findByTelefonoStartingWithAllIgnoreCase(valor);
		}

		public List<Proveedor> filtroCiudad(String valor) {
			return  proveedorDAO.findByCiudadNombreStartingWithAllIgnoreCase(valor);
		}

		public Proveedor buscarPorNombre(String value) {
			return  proveedorDAO.findByNombre(value);
		}

}
