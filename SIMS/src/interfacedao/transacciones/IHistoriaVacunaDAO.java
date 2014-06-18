package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Vacuna;
import modelo.pk.HistoriaVacunaId;
import modelo.transacciones.Historia;
import modelo.transacciones.HistoriaVacuna;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoriaVacunaDAO extends JpaRepository<HistoriaVacuna, HistoriaVacunaId> {

	List<HistoriaVacuna> findByHistoria(Historia historia);

	List<HistoriaVacuna> findByVacuna(Vacuna vacuna);

}
