package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Familiar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IFamiliarDAO extends JpaRepository<Familiar, String> {

	List<Familiar> findByPrimerNombreStartingWithAllIgnoreCase(String valor);

	List<Familiar> findBySegundoNombreStartingWithAllIgnoreCase(String valor);

	List<Familiar> findByCedulaStartingWithAllIgnoreCase(String valor);

	List<Familiar> findByPrimerApellidoStartingWithAllIgnoreCase(String valor);

	List<Familiar> findBySegundoApellidoStartingWithAllIgnoreCase(String valor);

	List<Familiar> findByCedulaFamiliarStartingWithAllIgnoreCase(String valor);

	Familiar findByCedula(String value);

	List<Familiar> findByCedulaFamiliar(String value);

	List<Familiar> findByCedulaFamiliarAndEstatus(String idPaciente, boolean b);

}
