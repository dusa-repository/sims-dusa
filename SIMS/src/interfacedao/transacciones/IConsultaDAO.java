package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Paciente;
import modelo.transacciones.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaDAO  extends JpaRepository<Consulta, Long> {

	List<Consulta> findByPaciente(Paciente paciente);

}
