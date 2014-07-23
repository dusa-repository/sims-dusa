package interfacedao.sha;

import java.util.List;

import modelo.sha.Condicion;
import modelo.sha.Informe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IInformeDAO extends JpaRepository<Informe, Long> {

	List<Informe> findByCodigoStartingWithAllIgnoreCase(String valor);

	List<Informe> findByPacientePrimerNombreStartingWithAllIgnoreCase(
			String valor);

	List<Informe> findByPacientePrimerApellidoStartingWithAllIgnoreCase(
			String valor);

	List<Informe> findByEmpresaNombreStartingWithAllIgnoreCase(String valor);

	List<Informe> findByCondicionAOrCondicionBOrCondicionCOrCondicionDOrCondicionEOrCondicionF(
			Condicion condicion, Condicion condicion2, Condicion condicion3,
			Condicion condicion4, Condicion condicion5, Condicion condicion6);

}
