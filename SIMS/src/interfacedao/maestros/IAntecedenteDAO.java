package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Antecedente;
import modelo.maestros.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAntecedenteDAO extends JpaRepository<Antecedente, Long> {

	List<Antecedente> findByAntecedenteTipoTipoTrueOrderByAntecedenteTipoNombreAsc();

	List<Antecedente> findByAntecedenteTipoTipoFalseOrderByAntecedenteTipoNombreAsc();

	List<Antecedente> findByPacientesAndAntecedenteTipoTipoTrueOrderByAntecedenteTipoNombreAsc(
			Paciente paciente);

	List<Antecedente> findByPacientesAndAntecedenteTipoTipoFalseOrderByAntecedenteTipoNombreAsc(
			Paciente paciente);

}
