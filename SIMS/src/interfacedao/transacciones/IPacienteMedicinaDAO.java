package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Paciente;
import modelo.pk.PacienteMedicinaId;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.PacienteMedicina;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteMedicinaDAO extends JpaRepository<PacienteMedicina, PacienteMedicinaId> {

	List<PacienteMedicina> findByPaciente(Paciente paciente);

	List<PacienteMedicina> findByMedicinaIdMedicinaNotInAndPaciente(
			List<Long> ids, Paciente paciente);

	List<PacienteMedicina> findByPacienteCronico(boolean b, Sort o);

	List<PacienteMedicina> findByPacienteCronicoAndPacienteTrabajador(
			boolean b, boolean b2, Sort o);

}
