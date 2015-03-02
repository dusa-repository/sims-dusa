package interfacedao.sha;

import java.util.List;

import modelo.sha.Informe;
import modelo.sha.PlanAccion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlanAccionDAO extends JpaRepository<PlanAccion, Long> {

	List<PlanAccion> findByDescripcionStartingWithAllIgnoreCase(String valor);

	List<PlanAccion> findByQuienStartingWithAllIgnoreCase(String valor);

	List<PlanAccion> findByDondeStartingWithAllIgnoreCase(String valor);

	List<PlanAccion> findByCuandoStartingWithAllIgnoreCase(String valor);

	List<PlanAccion> findByComoStartingWithAllIgnoreCase(String valor);

	List<PlanAccion> findByDescripcion(String value);

	List<PlanAccion> findByInforme(Informe informe);

	List<PlanAccion> findByInformeAndEstado(Informe informe, String string);

}
