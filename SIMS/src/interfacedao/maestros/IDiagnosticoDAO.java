package interfacedao.maestros;

import modelo.maestros.Diagnostico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiagnosticoDAO extends JpaRepository<Diagnostico, Long> {

}
