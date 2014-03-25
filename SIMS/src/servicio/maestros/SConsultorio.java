package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IConsultorioDAO;

import modelo.maestros.Ciudad;
import modelo.maestros.Consultorio;
import modelo.maestros.Empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SConsultorio")
public class SConsultorio {

	@Autowired
	private IConsultorioDAO consultorioDAO;

	public List<Consultorio> buscarPorCiudad(Ciudad ciudad) {
		return consultorioDAO.findByCiudad(ciudad);
	}
	
	public List<Consultorio> buscarTodas() {
		return consultorioDAO.findAll();
	}

	public Consultorio buscar(long parseLong) {
		return consultorioDAO.findOne(parseLong);
	}
	
	public void guardar(Consultorio consultorio) {
		consultorioDAO.save(consultorio);
	}
	
	public void eliminar(Consultorio consultorio) {
		consultorioDAO.delete(consultorio);
	}
	
	public List<Consultorio> filtroNombre(String valor) {
		return consultorioDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Consultorio> filtroCorreo(String valor) {
		return consultorioDAO.findByCorreoElectronicoStartingWithAllIgnoreCase(valor);
	}

	public List<Consultorio> filtroDireccion(String valor) {
		return consultorioDAO.findByDireccionStartingWithAllIgnoreCase(valor);
	}
	
	public List<Consultorio> filtroDescripcion(String valor) {
		return consultorioDAO.findByDescripcionStartingWithAllIgnoreCase(valor);
	}

	public List<Consultorio> filtroTelefono(String valor) {
		return consultorioDAO.findByTelefono1StartingWithAllIgnoreCase(valor);
	}

	public List<Consultorio> filtroCiudad(String valor) {
		return consultorioDAO.findByCiudadNombreStartingWithAllIgnoreCase(valor);
	}

	public Consultorio buscarPorNombre(String value) {
		return consultorioDAO.findByNombre(value);
	}
}
