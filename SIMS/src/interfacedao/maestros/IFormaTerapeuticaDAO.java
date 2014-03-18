package interfacedao.maestros;

import modelo.maestros.FormaTerapeutica;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IFormaTerapeuticaDAO extends JpaRepository<FormaTerapeutica, Long> {

}
