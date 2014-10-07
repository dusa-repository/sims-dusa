package servicio.maestros;

import interfacedao.maestros.IPacienteDAO;

import java.util.List;

import modelo.maestros.Cargo;
import modelo.maestros.Ciudad;
import modelo.maestros.Empresa;
import modelo.maestros.Nomina;
import modelo.maestros.Paciente;
import modelo.sha.Area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SPaciente")
public class SPaciente {

	@Autowired
	private IPacienteDAO pacienteDAO;

	public List<Paciente> buscarTodos() {
		return pacienteDAO.findAll();
	}

	public List<Paciente> filtroNombre1(String valor) {
		return pacienteDAO.findByPrimerNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroCedula(String valor) {
		return pacienteDAO.findByCedulaStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroApellido1(String valor) {
		return pacienteDAO.findByPrimerApellidoStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroEmpresa(String valor) {
		return pacienteDAO.findByEmpresaNombreStartingWithAllIgnoreCase(valor);
	}

	public Paciente buscarPorCedula(String value) {
		return pacienteDAO.findByCedula(value);
	}

	public void guardar(Paciente paciente) {
		pacienteDAO.save(paciente);		
	}

	public void eliminar(Paciente paciente) {
		pacienteDAO.delete(paciente);
		
	}

	public List<Paciente> buscarPorEmpresa(Empresa empresa) {
		return pacienteDAO.findByEmpresa(empresa);
	}

	public List<Paciente> buscarParientes(String valueOf) {
		return pacienteDAO.findByCedulaFamiliar(valueOf);
	}

	public List<Paciente> buscarPorCargo(Cargo cargo) {
		return pacienteDAO.findByCargoReal(cargo);
	}

	public List<Paciente> buscarPorCiudad(Ciudad ciudad) {
		// TODO Auto-generated method stub
		return pacienteDAO.findByCiudadVivienda(ciudad);
	}

	public List<Paciente> buscarPorArea(Area area) {
		return pacienteDAO.findByArea(area);
	}

	public List<Paciente> buscarPorNomina(Nomina nomina) {
		return pacienteDAO.findByNomina(nomina);
	}

	public List<Paciente> buscarPorFicha(String value) {
		
		return pacienteDAO.findByFicha(value);
	}

	public List<Paciente> filtroFicha(String valor) {
		return pacienteDAO.findByFichaStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> buscarTodosActivos() {
		return pacienteDAO.findByEstatusTrue();
	}

	public void guardarVarios(List<Paciente> inactivos) {
		pacienteDAO.save(inactivos);
	}

	public List<Paciente> filtroParentesco(String valor) {
		return pacienteDAO.findByParentescoFamiliarStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> buscarTodosTrabajadores() {
		return pacienteDAO.findByTrabajadorTrue();
	}

	public List<Paciente> filtroNombre1C(String valor, String value) {
		return pacienteDAO.findByCedulaFamiliarAndPrimerNombreStartingWithAllIgnoreCase(value,valor);
	}

	public List<Paciente> filtroCedulaC(String valor, String value) {
		return pacienteDAO.findByCedulaFamiliarAndCedulaStartingWithAllIgnoreCase(value,valor);
	}

	public List<Paciente> filtroApellido1C(String valor, String value) {
		return pacienteDAO.findByCedulaFamiliarAndPrimerApellidoStartingWithAllIgnoreCase(value,valor);
	}

	public List<Paciente> filtroCedulaT(String valor) {
		return pacienteDAO.findByTrabajadorTrueAndCedulaStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroNombre1T(String valor) {
		return pacienteDAO.findByTrabajadorTrueAndPrimerNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroApellido1T(String valor) {
		return pacienteDAO.findByTrabajadorTrueAndPrimerApellidoStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroParentescoC(String valor, String valor2) {
		return pacienteDAO.findByCedulaFamiliarAndParentescoFamiliarStartingWithAllIgnoreCase(valor2,valor);
	}

	public List<Paciente> filtroNombrePariente(String valor) {
		return pacienteDAO.findByTrabajadorFalseAndPrimerNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroCedulaPariente(String valor) {
		return pacienteDAO.findByTrabajadorFalseAndCedulaStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroApellidoPariente(String valor) {
		return pacienteDAO.findByTrabajadorFalseAndPrimerApellidoStartingWithAllIgnoreCase(valor);
	}
}
