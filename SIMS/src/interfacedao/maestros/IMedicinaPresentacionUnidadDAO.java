package interfacedao.maestros;

import java.util.List;

import modelo.maestros.MedicinaPresentacionUnidad;
import modelo.maestros.PresentacionMedicina;
import modelo.maestros.UnidadMedicina;
import modelo.pk.MedicinaPresentacionUnidadId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicinaPresentacionUnidadDAO extends JpaRepository<MedicinaPresentacionUnidad, MedicinaPresentacionUnidadId>{

	List<MedicinaPresentacionUnidad> findByPresentacionMedicina(
			PresentacionMedicina presentacionMedicina);

	List<MedicinaPresentacionUnidad> findByUnidadMedicina(
			UnidadMedicina unidadMedicina);

}
