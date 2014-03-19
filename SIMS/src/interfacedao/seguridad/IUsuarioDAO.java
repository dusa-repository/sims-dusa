package interfacedao.seguridad;

import java.util.List;

import modelo.maestros.Especialidad;
import modelo.maestros.Unidad;
import modelo.seguridad.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {

//	List<Usuario> findByEstadoEliminacionTrue();

//	@Query("select u from Usuario u where u.nombre not in (?1) and u.estadoEliminacion='true'")
//	public List<Usuario> buscarOtrosUsuarios(
//			ArrayList<String> cedulaTutoresEstudiantes);

//	public List<Usuario> findByNombreStartingWithIgnoreCase(String parte);

	Usuario findByLogin(String nombre);

	List<Usuario> findByUnidad(Unidad unidad);

	List<Usuario> findByEspecialidad(Especialidad especialidad);
}