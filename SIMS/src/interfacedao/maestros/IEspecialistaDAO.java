package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Especialidad;
import modelo.maestros.Especialista;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEspecialistaDAO  extends JpaRepository<Especialista, String>{

	List<Especialista> findByCedulaStartingWithAllIgnoreCase(String valor);

	List<Especialista> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Especialista> findByApellidoStartingWithAllIgnoreCase(String valor);

	List<Especialista> findByCostoStartingWithAllIgnoreCase(String valor);

	List<Especialista> findByEspecialidadDescripcionStartingWithAllIgnoreCase(
			String valor);

	List<Especialista> findByEspecialidad(Especialidad especialidad);

}
