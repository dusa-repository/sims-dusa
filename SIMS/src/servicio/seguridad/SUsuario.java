package servicio.seguridad;

import java.util.List;

import interfacedao.seguridad.IUsuarioDAO;
import modelo.maestros.Especialidad;
import modelo.maestros.Unidad;
import modelo.seguridad.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SUsuario")
public class SUsuario {

	@Autowired
	private IUsuarioDAO usuarioDAO;

	// public List<Usuario> buscarActivos() {
	// List<Usuario> usuarios;
	// usuarios = usuarioDAO.findByEstadoEliminacionTrue();
	// return usuarios;
	// }

	// public List<Usuario> buscarOtrosUsuarios(
	// ArrayList<String> cedulaTutoresEstudiantes) {
	// List<Usuario> usuarios;
	// usuarios = usuarioDAO.buscarOtrosUsuarios(cedulaTutoresEstudiantes);
	// return usuarios;
	// }

	@Transactional
	public Usuario buscarUsuarioPorId(long codigo) {
		Usuario usuario;
		usuario = usuarioDAO.findOne(codigo);
		return usuario;
	}

	public void guardar(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	@Transactional
	public Usuario buscarUsuarioPorNombre(String nombre) {
		return usuarioDAO.findByLogin(nombre);
	}

	@Transactional
	public List<Usuario> buscarPorUnidad(Unidad unidad) {
		return usuarioDAO.findByUnidad(unidad);
	}

	@Transactional
	public List<Usuario> buscarPorEspecialidad(Especialidad especialidad) {
		return usuarioDAO.findByEspecialidad(especialidad);
	}

	@Transactional
	public Usuario buscarPorCedula(String value) {
		return usuarioDAO.findByCedula(value);
	}

	public List<Usuario> buscarTodos() {
		return usuarioDAO.findAll();
	}

	public void eliminar(Usuario usuario) {
		usuarioDAO.delete(usuario);
	}

	// public List<Usuario> buscarCualquierCampoContiene(String parte) {
	// List<Usuario> usuariosActivos = new ArrayList<Usuario>();
	// List<Usuario> usuariosBuscados = new ArrayList<Usuario>();
	// usuariosActivos = buscarActivos();
	// usuariosBuscados = usuarioDAO.findByNombreStartingWithIgnoreCase(parte);
	// usuariosBuscados.retainAll(usuariosActivos);
	// if (parte.isEmpty())
	// return usuariosActivos;
	// else
	// return usuariosBuscados;
	// }
}