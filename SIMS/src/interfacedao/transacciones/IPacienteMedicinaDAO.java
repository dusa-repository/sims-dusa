package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Paciente;
import modelo.pk.PacienteMedicinaId;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.PacienteMedicina;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteMedicinaDAO extends JpaRepository<PacienteMedicina, PacienteMedicinaId> {

	List<PacienteMedicina> findByPaciente(Paciente paciente);

}
