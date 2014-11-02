package servicio.maestros;

import interfacedao.maestros.IPacienteDAO;

import java.util.ArrayList;
import java.util.List;

import modelo.maestros.Cargo;
import modelo.maestros.Ciudad;
import modelo.maestros.Empresa;
import modelo.maestros.Nomina;
import modelo.maestros.Paciente;
import modelo.sha.Area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SPaciente")
public class SPaciente {

	@Autowired
	private IPacienteDAO pacienteDAO;

	public List<Paciente> buscarTodos() {
		Pageable topTen = new PageRequest(0, 10);
		return pacienteDAO.findAllOrderByCedula(topTen);
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
		Pageable topTen = new PageRequest(0, 10);
		return pacienteDAO.findByEstatusTrue(topTen);
	}

	public void guardarVarios(List<Paciente> inactivos) {
		pacienteDAO.save(inactivos);
	}

	public List<Paciente> filtroParentesco(String valor) {
		return pacienteDAO
				.findByParentescoFamiliarStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> buscarTodosTrabajadores() {
		Pageable topTen = new PageRequest(0, 10);
		return pacienteDAO.findByTrabajadorTrue(topTen);
	}

	public List<Paciente> filtroNombre1C(String valor, String value) {
		return pacienteDAO
				.findByCedulaFamiliarAndPrimerNombreStartingWithAllIgnoreCase(
						value, valor);
	}

	public List<Paciente> filtroCedulaC(String valor, String value) {
		return pacienteDAO
				.findByCedulaFamiliarAndCedulaStartingWithAllIgnoreCase(value,
						valor);
	}

	public List<Paciente> filtroApellido1C(String valor, String value) {
		return pacienteDAO
				.findByCedulaFamiliarAndPrimerApellidoStartingWithAllIgnoreCase(
						value, valor);
	}

	public List<Paciente> filtroCedulaT(String valor) {
		return pacienteDAO
				.findByTrabajadorTrueAndCedulaStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroNombre1T(String valor) {
		return pacienteDAO
				.findByTrabajadorTrueAndPrimerNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroApellido1T(String valor) {
		return pacienteDAO
				.findByTrabajadorTrueAndPrimerApellidoStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroParentescoC(String valor, String valor2) {
		return pacienteDAO
				.findByCedulaFamiliarAndParentescoFamiliarStartingWithAllIgnoreCase(
						valor2, valor);
	}

	public List<Paciente> filtroNombrePariente(String valor) {
		return pacienteDAO
				.findByTrabajadorFalseAndPrimerNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroCedulaPariente(String valor) {
		return pacienteDAO
				.findByTrabajadorFalseAndCedulaStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroApellidoPariente(String valor) {
		return pacienteDAO
				.findByTrabajadorFalseAndPrimerApellidoStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroNombre1Activos(String valor) {
		return pacienteDAO
				.findByPrimerNombreStartingWithAndEstatusTrueAllIgnoreCase(valor);
	}

	public List<Paciente> filtroCedulaActivos(String valor) {
		return pacienteDAO
				.findByCedulaStartingWithAndEstatusTrueAllIgnoreCase(valor);
	}

	public List<Paciente> filtroApellido1Activos(String valor) {
		return pacienteDAO
				.findByPrimerApellidoStartingWithAndEstatusTrueAllIgnoreCase(valor);
	}

	public List<Paciente> filtroNombreParienteActivos(String valor) {
		return pacienteDAO
				.findByTrabajadorFalseAndEstatusTrueAndPrimerNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroCedulaParienteActivos(String valor) {
		return pacienteDAO
				.findByTrabajadorFalseAndEstatusTrueAndCedulaStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroApellidoParienteActivos(String valor) {
		return pacienteDAO
				.findByTrabajadorFalseAndEstatusTrueAndPrimerApellidoStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> filtroFichaActivos(String valor) {
		return pacienteDAO
				.findByFichaStartingWithAndEstatusTrueAllIgnoreCase(valor);
	}

	public List<Paciente> filtroFichaParienteActivos(String valor) {
		return pacienteDAO
				.findByTrabajadorFalseAndEstatusTrueAndFichaStartingWithAllIgnoreCase(valor);
	}

	public Paciente buscarPorCedulaActivo(String value) {
		return pacienteDAO.findByCedulaAndEstatusTrue(value);
	}

	public List<Paciente> filtroFichaT(String valor) {
		return pacienteDAO
				.findByTrabajadorTrueAndFichaStartingWithAllIgnoreCase(valor);
	}

	public List<Paciente> buscarFamiliaresActivos() {
		Pageable topTen = new PageRequest(0, 10);
		return pacienteDAO
				.findByTrabajadorFalseAndEstatusTrueOrderByCedulaAsc(topTen);
	}

	public Paciente buscarPorCedulaFamiliarActivo(String value) {
		return pacienteDAO.findByCedulaAndEstatusTrueAndTrabajadorFalse(value);
	}

	public List<Paciente> buscarPorEdadesTrabajador(int dea, int aa, boolean b) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO.findByEdadBetweenAndTrabajador(dea, aa,b, o);

	}

	public List<Paciente> buscarPorEdadesParentescoySexo(int dea, int aa,
			String parentesco, String sexo) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO
				.findByEdadBetweenAndTrabajadorFalseAndSexoAndParentescoFamiliar(
						dea, aa, sexo, parentesco, o);
	}

	public List<Paciente> buscarPorEdadesySexo(int dea, int aa, String sexo) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO.findByEdadBetweenAndTrabajadorFalseAndSexo(dea, aa,
				sexo, o);
	}

	public List<Paciente> buscarPorEdadesyParentesco(int dea, int aa,
			String parentesco) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO
				.findByEdadBetweenAndTrabajadorFalseAndParentescoFamiliar(dea,
						aa, parentesco, o);
	}

	public List<Paciente> buscarPorEdadesyTrabajador(int dea, int aa,
			String idTrabajador) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO
				.findByEdadBetweenAndTrabajadorFalseAndCedulaFamiliar(dea, aa,
						idTrabajador, o);
	}

	public List<Paciente> buscarCono(int dea, int aa,
			String sexo, String idTrabajador) {
//		List<String> ordenar = new ArrayList<String>();
//		ordenar.add("cedulaFamiliar");
//		ordenar.add("cedula");
//		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO
				.findByEdadBetweenAndTrabajadorAndCedulaFamiliarAndSexo(
						dea, aa, false, idTrabajador, sexo);
	}

	public List<Paciente> buscarPorEdadesTrabajadoryParentesco(int dea, int aa,
			String idTrabajador, String parentesco) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO
				.findByEdadBetweenAndTrabajadorFalseAndCedulaFamiliarAndParentescoFamiliar(
						dea, aa, idTrabajador, parentesco, o);
	}

	public List<Paciente> buscarPorEdadesTrabajadorParentescoSexo(int dea,
			int aa, String idTrabajador, String parentesco, String sexo) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO
				.findByEdadBetweenAndTrabajadorFalseAndCedulaFamiliarAndParentescoFamiliarAndSexo(
						dea, aa, idTrabajador, parentesco,sexo, o);
	}

	public List<Paciente> buscarPorEdadesTodos(int dea, int aa) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO.findByEdadBetween(dea, aa, o);
	}

	public List<Paciente> buscarPorEdadesTrabajadorSexo(int dea, int aa,
			boolean b, String sexo) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO.findByEdadBetweenAndTrabajadorAndSexo(dea, aa,b,sexo, o);
	}

	public List<Paciente> buscarPorEdadesTrabajadorDiscapacidad(int dea,
			int aa, boolean b, boolean c) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO.findByEdadBetweenAndTrabajadorAndDiscapacidad(dea, aa,b,c, o);
	}

	public List<Paciente> buscarPorEdadesTrabajadorDiscapacidadSexo(int dea,
			int aa, boolean b, boolean c, String sexo) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO.findByEdadBetweenAndTrabajadorAndDiscapacidadAndSexo(dea, aa,b,c,sexo, o);
	}

	public List<Paciente> buscarPorEdadesDiscapacidad(int dea, int aa, boolean b) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO.findByEdadBetweenAndDiscapacidad(dea, aa,b, o);
	}

	public List<Paciente> buscarPorEdadesDiscapacidadSexo(int dea, int aa,
			boolean b, String sexo) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("cedulaFamiliar");
		ordenar.add("cedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteDAO.findByEdadBetweenAndDiscapacidadAndSexo(dea, aa,b,sexo, o);
	}

}
