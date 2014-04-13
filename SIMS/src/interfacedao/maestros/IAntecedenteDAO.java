package interfacedao.maestros;

import modelo.maestros.Antecedente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAntecedenteDAO extends JpaRepository<Antecedente, Long> {

}
