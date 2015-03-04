package interfacedao.transacciones;

import java.sql.Timestamp;
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

	List<Cita> findByPacientePrimerNombreStartingWithAllIgnoreCase(String valor);

	List<Cita> findByPacienteEmpresaNombreStartingWithAllIgnoreCase(String valor);

	List<Cita> findByMotivoCitaDescripcionStartingWithAllIgnoreCase(String valor);

	List<Cita> findByFechaCitaStartingWithAllIgnoreCase(String valor);

	List<Cita> findByUsuario(Usuario usuario);

	List<Cita> findByUsuarioAndEstadoAndFechaCita(Usuario usuario,
			String string, Date value);

	List<Cita> findByUsuarioAndEstadoAndFechaCitaAndPacienteCedulaAndPacienteEstatusTrue(
			Usuario usuario, String string, Date date, String value);

	List<Cita> findByUsuarioAndPacienteFichaStartingWithAndPacienteEstatusTrueAndFechaCitaAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha);

	List<Cita> findByUsuarioAndPacientePrimerNombreStartingWithAndPacienteEstatusTrueAndFechaCitaAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha);

	List<Cita> findByUsuarioAndPacienteCedulaStartingWithAndPacienteEstatusTrueAndFechaCitaAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha);

	List<Cita> findByUsuarioAndPacientePrimerApellidoStartingWithAndPacienteEstatusTrueAndFechaCitaAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha);

	List<Cita> findByUsuarioAndPacienteFichaStartingWithAndPacienteEstatusTrueAndFechaCitaAndEstadoAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha, String string);

	List<Cita> findByUsuarioAndPacientePrimerNombreStartingWithAndPacienteEstatusTrueAndFechaCitaAndEstadoAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha, String string);

	List<Cita> findByUsuarioAndPacienteCedulaStartingWithAndPacienteEstatusTrueAndFechaCitaAndEstadoAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha, String string);

	List<Cita> findByUsuarioAndPacientePrimerApellidoStartingWithAndPacienteEstatusTrueAndFechaCitaAndEstadoAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha, String string);

	List<Cita> findByUsuarioAndPacienteCedulaFamiliarStartingWithAndPacienteEstatusTrueAndFechaCitaAndEstadoAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha, String string);

	List<Cita> findByUsuarioAndPacienteSegundoNombreStartingWithAndPacienteEstatusTrueAndFechaCitaAndEstadoAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha, String string);

	List<Cita> findByUsuarioAndPacienteSegundoApellidoStartingWithAndPacienteEstatusTrueAndFechaCitaAndEstadoAllIgnoreCase(
			Usuario usuario, String valor, Timestamp fecha, String string);

}
