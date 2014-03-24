package interfacedao.maestros;

import modelo.maestros.MotivoCita;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IMotivoCitaDAO extends JpaRepository<MotivoCita, Long> {

}
