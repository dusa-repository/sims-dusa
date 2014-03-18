package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IUnidadDAO;

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
}
