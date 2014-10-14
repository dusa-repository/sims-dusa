package servicio.transacciones;

import interfacedao.transacciones.IConsultaDAO;

import java.util.Date;
import java.util.List;

import modelo.maestros.Cargo;
import modelo.maestros.Paciente;
import modelo.sha.Area;
import modelo.transacciones.Consulta;

import org.springframework.beans.factory.annotation.Autowired;
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
		return consultaDAO.findByPacienteCedulaOrderByFechaConsultaDesc(valueOf);
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
		return consultaDAO.findByIdReferenciaAndCedulaReferencia(idRefC,cedRef);
	}

	public List<Consulta> buscarEntreFechas(Date fecha1, Date fecha2) {
		return consultaDAO.findByFechaConsultaBetween(fecha1, fecha2);
	}
}
