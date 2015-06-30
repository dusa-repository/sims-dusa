package interfacedao.seguridad;

import java.util.List;

import modelo.seguridad.Grupo;
import modelo.seguridad.UsuarioSeguridad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioSeguridadDAO extends JpaRepository<UsuarioSeguridad, String> {

	UsuarioSeguridad findByLogin(String nombre);

	List<UsuarioSeguridad> findByGrupos(Grupo grupo);

	UsuarioSeguridad findByLoginAndEmail(String value, String value2);

}