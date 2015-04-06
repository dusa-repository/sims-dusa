package interfacedao.social;

import java.util.List;

import modelo.social.VisitaSocial;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IVisitaSocialDAO  extends JpaRepository<VisitaSocial, Long>{

	List<VisitaSocial> findByIdVisitaStartingWithAllIgnoreCase(String valor);



}
