package servicio.maestros;

import interfacedao.maestros.IUnidadUsuarioDAO;

import java.util.List;

import modelo.maestros.UnidadUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SUnidadUsuario")
public class SUnidadUsuario {

	@Autowired
	private IUnidadUsuarioDAO interfaceUnidad;

	public void guardar(UnidadUsuario unidad) {
		interfaceUnidad.save(unidad);
	}

	public List<UnidadUsuario> buscarTodas() {
		return interfaceUnidad.findAll();
	}

	public UnidadUsuario buscar(long id) {
		return interfaceUnidad.findOne(id);
	}

	public void eliminar(UnidadUsuario unidad) {
		interfaceUnidad.delete(unidad);
	}

	public UnidadUsuario buscarPorNombre(String value) {
		return interfaceUnidad.findByNombre(value);
	}

	public List<UnidadUsuario> filtroNombre(String valor) {
		return interfaceUnidad.findByNombreStartingWithAllIgnoreCase(valor);
	}
}
