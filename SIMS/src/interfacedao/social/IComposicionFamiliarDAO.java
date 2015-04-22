package interfacedao.social;

import java.util.List;

import modelo.social.ComposicionFamiliar;
import modelo.social.VisitaSocial;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IComposicionFamiliarDAO extends JpaRepository<ComposicionFamiliar, Long>{

	List<ComposicionFamiliar> findByVisita(VisitaSocial visitaSocial);
	
}
