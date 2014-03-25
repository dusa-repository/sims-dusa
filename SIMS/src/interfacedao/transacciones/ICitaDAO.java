package interfacedao.transacciones;


import java.util.List;

import modelo.maestros.Cita;
import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ICitaDAO extends JpaRepository<Cita, Long> {

	List<Cita> findByMotivoCita(MotivoCita motivoCita);

	List<Cita> findByPaciente(Paciente paciente);

}
