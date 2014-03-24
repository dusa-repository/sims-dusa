package interfacedao.maestros;

import modelo.maestros.Consultorio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultorioDAO extends JpaRepository<Consultorio, Long> {

}
