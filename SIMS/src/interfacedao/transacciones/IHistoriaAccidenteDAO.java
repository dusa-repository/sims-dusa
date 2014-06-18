package interfacedao.transacciones;

import java.util.List;

import modelo.pk.HistoriaAccidenteId;
import modelo.transacciones.Historia;
import modelo.transacciones.HistoriaAccidente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoriaAccidenteDAO extends JpaRepository<HistoriaAccidente, HistoriaAccidenteId> {

	List<HistoriaAccidente> findByHistoriaAndAccidenteTipo(Historia historia,
			String string);

	List<HistoriaAccidente> findByHistoria(Historia historia);

}
