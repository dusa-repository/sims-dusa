package interfacedao.transacciones;


import java.util.Date;
import java.util.List;

import modelo.maestros.Cita;
import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ICitaDAO extends JpaRepository<Cita, Long> {

	List<Cita> findByMotivoCita(MotivoCita motivoCita);

	List<Cita> findByPaciente(Paciente paciente);

	List<Cita> findByUsuarioAndEstado(Usuario usuario, String string);

	List<Cita> findByPacientePrimerNombreStartingWithAllIgnoreCase(
			String valor);

	List<Cita> findByPacienteEmpresaNombreStartingWithAllIgnoreCase(String valor);

	List<Cita> findByMotivoCitaDescripcionStartingWithAllIgnoreCase(String valor);

	List<Cita> findByFechaCitaStartingWithAllIgnoreCase(String valor);

	List<Cita> findByUsuario(Usuario usuario);

	List<Cita> findByUsuarioAndEstadoAndFechaCita(Usuario usuario, String string,
			Date value);

}
