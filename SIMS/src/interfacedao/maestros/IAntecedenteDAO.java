package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Antecedente;
import modelo.maestros.AntecedenteTipo;
import modelo.maestros.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAntecedenteDAO extends JpaRepository<Antecedente, Long> {

	List<Antecedente> findByNombreStartingWithAllIgnoreCase(String valor);

	Antecedente findByNombre(String value);

	List<Antecedente> findByAntecedenteTipo(AntecedenteTipo antecedenteTipo);

	List<Antecedente> findByAntecedenteTipoNombreStartingWithAllIgnoreCase(
			String valor);

	List<Antecedente> findByAntecedenteTipoTipoOrderByAntecedenteTipoNombreAsc(
			String string);

}
