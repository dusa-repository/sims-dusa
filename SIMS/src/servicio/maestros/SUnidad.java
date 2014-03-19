package servicio.maestros;

import interfacedao.maestros.IUnidadDAO;

import java.util.List;

import modelo.maestros.Unidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SUnidad")
public class SUnidad {

	@Autowired
	private IUnidadDAO interfaceUnidad;

	public void guardar(Unidad unidad) {
		interfaceUnidad.save(unidad);
	}

	public List<Unidad> buscarTodas() {
		return interfaceUnidad.findAll();
	}

	public Unidad buscar(long id) {
		return interfaceUnidad.findOne(id);
	}

	public void eliminar(Unidad unidad) {
		interfaceUnidad.delete(unidad);
	}

	public Unidad buscarPorNombre(String value) {
		return interfaceUnidad.findByNombre(value);
	}
}
