package interfacedao.seguridad;

import java.util.List;

import modelo.maestros.Especialidad;
import modelo.seguridad.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String nombre);

	List<Usuario> findByEspecialidad(Especialidad especialidad);

	Usuario findByCedula(String value);

	List<Usuario> findByCedulaStartingWithAllIgnoreCase(String valor);

	List<Usuario> findByFichaStartingWithAllIgnoreCase(String valor);

	List<Usuario> findByLoginStartingWithAllIgnoreCase(String valor);

	List<Usuario> findByEmailStartingWithAllIgnoreCase(String valor);

	List<Usuario> findByEspecialidadDescripcionStartingWithAllIgnoreCase(
			String valor);

	List<Usuario> findByPrimerNombreStartingWithAllIgnoreCase(String valor);

	List<Usuario> findByPrimerApellidoStartingWithAllIgnoreCase(String valor);

	List<Usuario> findByDoctor(boolean b);

	List<Usuario> findByDoctorAndEspecialidadDescripcionStartingWithAllIgnoreCase(
			boolean b, String valor);

	List<Usuario> findByDoctorAndCedulaStartingWithAllIgnoreCase(boolean b,
			String valor);

	List<Usuario> findByDoctorAndFichaStartingWithAllIgnoreCase(boolean b,
			String valor);

	List<Usuario> findByDoctorAndPrimerNombreStartingWithAllIgnoreCase(
			boolean b, String valor);

	List<Usuario> findByDoctorAndPrimerApellidoStartingWithAllIgnoreCase(
			boolean b, String valor);

	Usuario findByCedulaAndEmail(String value, String value2);

}