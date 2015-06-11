package servicio.maestros;

import interfacedao.maestros.IClasificacionDiagnosticoDAO;

import java.util.List;

import modelo.maestros.ClasificacionDiagnostico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SClasificacionDiagnostico")
public class SClasificacionDiagnostico {

	@Autowired
	private IClasificacionDiagnosticoDAO diagnosticoDAO;

	public void guardar(ClasificacionDiagnostico unidad) {
		diagnosticoDAO.save(unidad);
	}

	public void eliminar(long id) {
		diagnosticoDAO.delete(id);
	}

	public ClasificacionDiagnostico buscarPorNombre(String value) {
		if (!diagnosticoDAO.findByNombre(value).isEmpty())
			return diagnosticoDAO.findByNombre(value).get(0);
		else
			return null;
	}

	public List<ClasificacionDiagnostico> buscarTodas() {
		return diagnosticoDAO.findAll();
	}

	public List<ClasificacionDiagnostico> filtroNombre(String valor) {
		return diagnosticoDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public ClasificacionDiagnostico buscar(long parseLong) {
		return diagnosticoDAO.findOne(parseLong);
	}
}
