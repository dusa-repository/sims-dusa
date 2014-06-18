package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Intervencion;
import modelo.pk.HistoriaIntervencionId;
import modelo.transacciones.Historia;
import modelo.transacciones.HistoriaIntervencion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoriaIntervencionDAO extends JpaRepository<HistoriaIntervencion, HistoriaIntervencionId> {

	List<HistoriaIntervencion> findByHistoria(Historia historia);

	List<HistoriaIntervencion> findByIntervencion(Intervencion intervencion);

}
