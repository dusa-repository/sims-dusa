package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IFamiliarDAO;
import interfacedao.maestros.IPacienteDAO;
import modelo.maestros.Familiar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SFamiliar")
public class SFamiliar {

	@Autowired
	private IFamiliarDAO familiarDAO;

	public void guardar(Familiar familiar) {
		familiarDAO.save(familiar);
	}

	public List<Familiar> buscarTodos() {
		return familiarDAO.findAll();
	}

	public List<Familiar> filtroNombre1(String valor) {
		return familiarDAO.findByPrimerNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Familiar> filtroNombre2(String valor) {
		return familiarDAO.findBySegundoNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Familiar> filtroCedula(String valor) {
		return familiarDAO.findByCedulaStartingWithAllIgnoreCase(valor);
	}

	public List<Familiar> filtroApellido1(String valor) {
		return familiarDAO.findByPrimerApellidoStartingWithAllIgnoreCase(valor);
	}

	public List<Familiar> filtroApellido2(String valor) {
		return familiarDAO
				.findBySegundoApellidoStartingWithAllIgnoreCase(valor);
	}

	public List<Familiar> filtroCedulaAsociado(String valor) {
		return familiarDAO.findByCedulaFamiliarStartingWithAllIgnoreCase(valor);
	}

	public Familiar buscarPorCedula(String value) {
		return familiarDAO.findByCedula(value);
	}

	public List<Familiar> buscarPorTrabajador(String value) {
		return familiarDAO.findByCedulaFamiliar(value);
	}

	public List<Familiar> buscarPorTrabajadorYEstado(String idPaciente,
			boolean b) {
		return familiarDAO.findByCedulaFamiliarAndEstatus(idPaciente, b);
	}

	public void guardarVarios(List<Familiar> familiares) {
		familiarDAO.save(familiares);
	}
}
