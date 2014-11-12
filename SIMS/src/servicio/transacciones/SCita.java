package servicio.transacciones;

import interfacedao.transacciones.ICitaDAO;

import java.util.Date;
import java.util.List;

import modelo.maestros.Cita;
import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCita")
public class SCita {

	@Autowired
	private ICitaDAO citaDAO;

	public List<Cita> buscarPorMotivo(MotivoCita motivoCita) {
		return citaDAO.findByMotivoCita(motivoCita);
	}

	public List<Cita> buscarPorPaciente(Paciente paciente) {
		return citaDAO.findByPaciente(paciente);
	}

	public void guardar(Cita cita) {
		citaDAO.save(cita);

	}

	public List<Cita> buscarPorUsuarioYEstado(Usuario usuario, String estado) {
		return citaDAO.findByUsuarioAndEstado(usuario, estado);
	}

	public List<Cita> filtroPaciente(String valor) {
		return citaDAO
				.findByPacientePrimerNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Cita> filtroEmpresa(String valor) {
		return citaDAO
				.findByPacienteEmpresaNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Cita> filtroFecha(String valor) {
		return citaDAO.findByFechaCitaStartingWithAllIgnoreCase(valor);
	}

	public List<Cita> filtroMotivo(String valor) {
		return citaDAO
				.findByMotivoCitaDescripcionStartingWithAllIgnoreCase(valor);
	}

	public List<Cita> buscarPorUsuario(Usuario usuario) {
		return citaDAO.findByUsuario(usuario);
	}

	public int buscarPorUsuarioYFechaYEstado(Usuario usuario, Date value,
			String string) {
		List<Cita> citas = citaDAO.findByUsuarioAndEstadoAndFechaCita(usuario,
				string, value);
		if (!citas.isEmpty())
			return citas.size();
		else
			return 0;
	}

	public Cita buscar(long idCita) {
		Cita cita = citaDAO.findOne(idCita);
		return cita;
	}
}
