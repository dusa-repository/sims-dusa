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

	public List<Usuario> filtroCedula(String valor) {
		return usuarioDAO.findByCedulaStartingWithAllIgnoreCase(valor);
	}

	public List<Usuario> filtroFicha(String valor) {
		return usuarioDAO.findByFichaStartingWithAllIgnoreCase(valor);
	}

	public List<Usuario> filtroNombre(String valor) {
		return usuarioDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Usuario> filtroCorreo(String valor) {
		return usuarioDAO.findByEmailStartingWithAllIgnoreCase(valor);
	}

	public List<Usuario> filtroLogin(String valor) {
		return usuarioDAO.findByLoginStartingWithAllIgnoreCase(valor);
	}

}