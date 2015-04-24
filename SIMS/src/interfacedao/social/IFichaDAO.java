package interfacedao.social;

import modelo.social.ComposicionFamiliar;
import modelo.social.Ficha;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IFichaDAO  extends JpaRepository<Ficha, Long>{

}
