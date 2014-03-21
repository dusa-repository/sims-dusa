package interfacedao.maestros;

import java.util.List;

import modelo.maestros.FormaTerapeutica;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IFormaTerapeuticaDAO extends JpaRepository<FormaTerapeutica, Long> {

	FormaTerapeutica findByNombre(String value);

	List<FormaTerapeutica> findByNombreStartingWithAllIgnoreCase(String valor);

}
