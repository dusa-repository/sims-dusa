package interfacedao.maestros;

import modelo.maestros.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteDAO extends JpaRepository<Paciente, Long> {

}
