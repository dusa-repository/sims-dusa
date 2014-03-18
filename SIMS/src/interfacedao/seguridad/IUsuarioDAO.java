package interfacedao.seguridad;

import java.util.ArrayList;
import java.util.List;

import modelo.seguridad.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {

//	List<Usuario> findByEstadoEliminacionTrue();

//	@Query("select u from Usuario u where u.nombre not in (?1) and u.estadoEliminacion='true'")
//	public List<Usuario> buscarOtrosUsuarios(
//			ArrayList<String> cedulaTutoresEstudiantes);

//	public List<Usuario> findByNombreStartingWithIgnoreCase(String parte);

	Usuario findByLogin(String nombre);
}