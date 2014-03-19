package servicio.seguridad;

import java.util.List;

import interfacedao.seguridad.IUsuarioDAO;
import modelo.maestros.Especialidad;
import modelo.maestros.Unidad;
import modelo.seguridad.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SUsuario")
public class SUsuario {

	@Autowired
	private IUsuarioDAO usuarioDAO;

//	public List<Usuario> buscarActivos() {
//		List<Usuario> usuarios;
//		usuarios = usuarioDAO.findByEstadoEliminacionTrue();
//		return usuarios;
//	}

//	public List<Usuario> buscarOtrosUsuarios(
//			ArrayList<String> cedulaTutoresEstudiantes) {
//		List<Usuario> usuarios;
//		usuarios = usuarioDAO.buscarOtrosUsuarios(cedulaTutoresEstudiantes);
//		return usuarios;
//	}

	public Usuario buscarUsuarioPorId(long codigo) {
		Usuario usuario;
		usuario = usuarioDAO.findOne(codigo);
		return usuario;
	}

	public void guardar(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	public Usuario buscarUsuarioPorNombre(String nombre) {
		return usuarioDAO.findByLogin(nombre);
	}

	public List<Usuario> buscarPorUnidad(Unidad unidad) {
		return usuarioDAO.findByUnidad(unidad);
	}

	public List<Usuario> buscarPorEspecialidad(Especialidad especialidad) {
		return usuarioDAO.findByEspecialidad(especialidad);
	}

//	public List<Usuario> buscarCualquierCampoContiene(String parte) {
//		List<Usuario> usuariosActivos = new ArrayList<Usuario>();
//		List<Usuario> usuariosBuscados = new ArrayList<Usuario>();
//		usuariosActivos = buscarActivos();
//		usuariosBuscados = usuarioDAO.findByNombreStartingWithIgnoreCase(parte);
//		usuariosBuscados.retainAll(usuariosActivos);
//		if (parte.isEmpty())
//			return usuariosActivos;
//		else
//			return usuariosBuscados;
//	}
}