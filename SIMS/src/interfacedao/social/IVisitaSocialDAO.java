package interfacedao.social;

import java.util.List;

import modelo.social.VisitaSocial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IVisitaSocialDAO  extends JpaRepository<VisitaSocial, Long>{

	List<VisitaSocial> findByIdVisitaStartingWithAllIgnoreCase(String valor);

	List<VisitaSocial> findByPacientePrimerNombreStartingWithAllIgnoreCase(
			String valor);

	VisitaSocial findByIdVisita(Long idVisita);
	
	@Query("select coalesce(max(v.idVisita), '0') from VisitaSocial v")
	long findMaxIdVisita();



}
