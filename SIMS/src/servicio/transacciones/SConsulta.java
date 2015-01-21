package servicio.transacciones;

import interfacedao.transacciones.IConsultaDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javassist.expr.NewArray;
import modelo.maestros.Cargo;
import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.ClasificacionDiagnostico;
import modelo.maestros.Diagnostico;
import modelo.maestros.Empresa;
import modelo.maestros.Nomina;
import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;
import modelo.sha.Area;
import modelo.transacciones.Consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SConsulta")
public class SConsulta {

	@Autowired
	private IConsultaDAO consultaDAO;

	public Consulta buscar(long idConsulta) {
		return consultaDAO.findOne(idConsulta);
	}

	public List<Consulta> buscarPorPaciente(Paciente paciente) {
		return consultaDAO.findByPacienteOrderByFechaConsultaDesc(paciente);
	}

	public void guardar(Consulta consulta) {
		consultaDAO.save(consulta);
	}

	public Consulta buscarUltima() {
		long id = consultaDAO.findMaxIdMedicina();
		if (id != 0)
			return consultaDAO.findOne(id);
		return null;
	}

	public List<Consulta> buscarPorAccidente(Paciente paciente) {
		return consultaDAO.findByPacienteAndAccidenteLaboralTrue(paciente);
	}

	public List<Consulta> filtroFecha(String valor) {
		return consultaDAO.findByFechaConsultaStartingWithAllIgnoreCase(valor);
	}

	public List<Consulta> filtroDoctor(String valor) {
		boolean blanco = false;
		int corte = valor.length() + 1;
		for (int i = 0; i < valor.length(); i++) {
			if (Character.isWhitespace(valor.charAt(i))) {
				blanco = true;
				corte = i;
				i = valor.length();
			}
		}
		List<Consulta> consultas = consultaDAO
				.findByUsuarioPrimerNombreStartingWithAllIgnoreCase(valor
						.substring(0, corte - 1));
		if (blanco)
			consultas.addAll(consultaDAO
					.findByUsuarioPrimerApellidoStartingWithAllIgnoreCase(valor
							.substring(corte) + 1));
		return consultas;
	}

	public List<Consulta> filtroMotivo(String valor) {
		return consultaDAO.findByMotivoConsultaStartingWithAllIgnoreCase(valor);
	}

	public List<Consulta> filtroEnfermedad(String valor) {
		return consultaDAO
				.findByEnfermedadActualStartingWithAllIgnoreCase(valor);
	}

	public List<Consulta> filtroTipo(String valor) {
		return consultaDAO.findByTipoConsultaStartingWithAllIgnoreCase(valor);
	}

	public List<Consulta> filtroTipoSecundaria(String valor) {
		return consultaDAO
				.findByTipoConsultaSecundariaStartingWithAllIgnoreCase(valor);
	}

	public List<Consulta> buscarPorIdPacienteOrdenado(String valueOf) {
		return consultaDAO
				.findByPacienteCedulaOrderByFechaConsultaDesc(valueOf);
	}

	public List<Consulta> buscarPorCargo(Cargo cargo) {
		return consultaDAO.findByCargo(cargo);
	}

	public List<Consulta> buscarPorArea(Area area) {
		return consultaDAO.findByArea(area);
	}

	public List<Consulta> buscarPorArea2(Area area) {
		return consultaDAO.findByAreaDeseada(area);
	}

	public void guardarVarios(List<Consulta> consultas) {
		consultaDAO.save(consultas);
	}

	public Consulta buscarPorReferencias(long idRefC, String cedRef) {
		return consultaDAO
				.findByIdReferenciaAndCedulaReferencia(idRefC, cedRef);
	}

	public List<Consulta> buscarEntreFechas(Date fecha1, Date fecha2) {
		return consultaDAO.findByFechaConsultaBetweenOrderByFechaConsultaDesc(
				fecha1, fecha2, true);
	}

	public List<Consulta> buscarEntreFechasyArea(Date fecha1, Date fecha2,
			Area area2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteAreaNombre");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndPacienteAreaAndPacienteTrabajador(
						fecha1, fecha2, area2, true, o);
	}

	public List<Consulta> buscarEntreFechasyTipoConsulta(Date fecha1,
			Date fecha2, String tipo) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("tipoConsulta");
		ordenar.add("tipoConsultaSecundaria");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO.findByFechaConsultaBetweenAndTipoConsulta(fecha1,
				fecha2, tipo, o);
	}

	public List<Consulta> buscarEntreFechasTipoConsultaySubTipo(Date fecha1,
			Date fecha2, String tipo, String subTipo) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("tipoConsulta");
		ordenar.add("tipoConsultaSecundaria");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndTipoConsultaAndTipoConsultaSecundaria(
						fecha1, fecha2, tipo, subTipo, o);
	}

	public List<Consulta> buscarEntreFechasOrdenadasPorTipo(Date fecha1,
			Date fecha2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("tipoConsulta");
		ordenar.add("tipoConsultaSecundaria");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO.findByFechaConsultaBetween(fecha1, fecha2, o);
	}

	public List<Consulta> buscarEntreFechasOrdenadasPorUnidad(Date fecha1,
			Date fecha2) {

		List<String> ordenar = new ArrayList<String>();
		ordenar.add("usuarioUnidad");
		ordenar.add("usuarioCedula");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO.findByFechaConsultaBetween(fecha1, fecha2, o);
	}

	public List<Consulta> buscarEntreFechasPorUnidad(Date fecha1, Date fecha2,
			String unidad) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("usuarioUnidad");
		ordenar.add("usuarioCedula");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO.findByFechaConsultaBetweenAndUsuarioUnidad(fecha1,
				fecha2, unidad, o);
	}

	public List<Consulta> buscarEntreFechasPorDoctor(Date fecha1, Date fecha2,
			Usuario doc) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("usuarioUnidad");
		ordenar.add("usuarioCedula");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO.findByFechaConsultaBetweenAndUsuario(fecha1, fecha2,
				doc, o);
	}

	public List<Consulta> buscarEntreFechasReposoyTrabajadores(Date fecha1,
			Date fecha2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteAreaNombre");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndReposoAndPacienteTrabajador(
						fecha1, fecha2, true, true, o);
	}



	public List<Consulta> buscarEntreFechasOrdenadasPorUnidadReposoyTrabajadores(
			Date fecha1, Date fecha2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("usuarioUnidad");
		ordenar.add("usuarioCedula");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndReposoAndPacienteTrabajador(
						fecha1, fecha2, true, true, o);
	}

	public List<Consulta> buscarEntreFechasPorUnidadReposoyTrabajadores(
			Date fecha1, Date fecha2, String unidad) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("usuarioUnidad");
		ordenar.add("usuarioCedula");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndReposoAndPacienteTrabajadorAndUsuarioUnidad(
						fecha1, fecha2, true, true, unidad, o);
	}

	public List<Consulta> buscarEntreFechasPorDoctorReposoyTrabajadores(
			Date fecha1, Date fecha2, Usuario doc) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("usuarioUnidad");
		ordenar.add("usuarioCedula");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndUsuarioAndReposoAndPacienteTrabajador(
						fecha1, fecha2, doc, true, true, o);
	}

	public List<Consulta> buscarTipoDeConsultaEntreFechasResumen(Date desde,
			Date hasta) {
		List<String> lista = new ArrayList<String>();
		lista.add("tipoConsulta");
		lista.add("tipoConsultaSecundaria");
		Sort order = new Sort(Sort.Direction.ASC, lista);
		return consultaDAO.findByFechaConsultaBetween(desde, hasta, order);
	}

	public List<Consulta> buscarTipoDeConsultaEntreFechasYTrabajadorResumen(
			Date desde, Date hasta, boolean trabajador) {
		List<String> lista = new ArrayList<String>();
		lista.add("tipoConsulta");
		lista.add("tipoConsultaSecundaria");
		Sort order = new Sort(Sort.Direction.ASC, lista);
		return consultaDAO.findByFechaConsultaBetweenAndPacienteTrabajador(
				desde, hasta, trabajador, order);
	}

	public List<Consulta> buscarEntreFechasOrdenadasPorPacienteReposoyTrabajadores(
			Date fecha1, Date fecha2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndReposoAndPacienteTrabajador(
						fecha1, fecha2, true, true, o);
	}

	public List<Consulta> buscarEntreFechasPorPacienteReposoyTrabajadores(
			Date fecha1, Date fecha2, String idPaciente) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndPacienteCedulaAndReposoAndPacienteTrabajador(
						fecha1, fecha2, idPaciente, true, true, o);
	}

	public List<Consulta> buscarEntreFechasFamiliaresTodosTrabajadores(
			Date desde, Date hasta) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedulaFamiliar");
		ordenar.add("pacienteCedula");
		ordenar.add("pacienteParentescoFamiliar");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO.findByFechaConsultaBetweenAndPacienteTrabajador(
				desde, hasta, false, o);
	}

	public List<Consulta> buscarEntreFechasFamiliaresYUnTrabajador(Date desde,
			Date hasta, String paciente) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		ordenar.add("pacienteParentescoFamiliar");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndPacienteTrabajadorAndPacienteCedulaFamiliar(
						desde, hasta, false, paciente, o);
	}

	public List<Consulta> buscarEntreFechasFamiliaresTodosTrabajadoresUnParentesco(
			Date desde, Date hasta, String parentesco) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedulaFamiliar");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndPacienteTrabajadorAndPacienteParentescoFamiliar(
						desde, hasta, false, parentesco, o);
	}

	public List<Consulta> buscarEntreFechasFamiliaresUnTrabajadorYunParentesco(
			Date desde, Date hasta, String paciente, String parentesco) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndPacienteTrabajadorAndPacienteParentescoFamiliarAndPacienteCedulaFamiliar(
						desde, hasta, false, parentesco, paciente, o);
	}

	public List<Consulta> buscarEntreFechasTrabajadores(Date desde, Date hasta) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO.findByFechaConsultaBetweenAndPacienteTrabajador(
				desde, hasta, true, o);
	}

	public List<Consulta> buscarEntreFechasUnTrabajador(Date desde, Date hasta,
			String paciente) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndPacienteTrabajadorAndPacienteCedula(
						desde, hasta, true, paciente, o);
	}

	public List<Consulta> buscarEntreFechasyCargo(Date desde, Date hasta,
			Cargo cargo2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCargoRealNombre");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndPacienteCargoRealAndPacienteTrabajador(
						desde, hasta, cargo2, true, o);
	}

	public List<Consulta> buscarEntreFechasyEmpresa(Date desde, Date hasta,
			Empresa empresa2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteEmpresaNombre");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndPacienteEmpresaAndPacienteTrabajador(
						desde, hasta, empresa2, true, o);
	}

	public List<Consulta> buscarEntreFechasyNomina(Date desde, Date hasta,
			Nomina nomina2) {

		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteNominaNombre");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndPacienteNominaAndPacienteTrabajador(
						desde, hasta, nomina2, true, o);
	}

	public List<Consulta> buscarEntreFechasReposoCargoyTrabajadores(
			Date desde, Date hasta, Cargo cargo) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCargoRealNombre");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndReposoAndPacienteCargoRealAndPacienteTrabajador(
						desde, hasta, true, cargo, true, o);
	}
	
	public List<Consulta> buscarEntreFechasReposoAreayTrabajadores(Date fecha1,
			Date fecha2, Area area2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteAreaNombre");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndReposoAndPacienteAreaAndPacienteTrabajador(
						fecha1, fecha2, true, area2, true, o);
	}

	public List<Consulta> buscarEntreFechasReposoNominayTrabajadores(
			Date desde, Date hasta, Nomina buscar) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteNominaNombre");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndReposoAndPacienteNominaAndPacienteTrabajador(
						desde, hasta, true, buscar, true, o);
	}

	public List<Consulta> buscarEntreFechasReposoEmpresayTrabajadores(
			Date desde, Date hasta, Empresa buscar) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteEmpresaNombre");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDAO
				.findByFechaConsultaBetweenAndReposoAndPacienteEmpresaAndPacienteTrabajador(
						desde, hasta, true, buscar, true, o);
	}
	
	public void guardarVarias(List<Consulta> consultas) {
		consultaDAO.save(consultas);
	}

}
