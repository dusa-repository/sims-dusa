package servicio.seguridad;

import interfacedao.seguridad.IUsuarioSeguridadDAO;

import java.util.List;

import modelo.seguridad.Grupo;
import modelo.seguridad.UsuarioSeguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SUsuarioSeguridad")
public class SUsuarioSeguridad {

	@Autowired
	private IUsuarioSeguridadDAO usuarioDAO;

	public void guardar(UsuarioSeguridad usuario) {
		usuarioDAO.save(usuario);
	}

	public List<UsuarioSeguridad> buscarTodos() {
		return usuarioDAO.findAll();
	}

	public void eliminar(UsuarioSeguridad usuario) {
		usuarioDAO.delete(usuario);
	}

	public List<UsuarioSeguridad> buscarPorGrupo(Grupo grupo) {
		return usuarioDAO.findByGrupos(grupo);
	}

	@Transactional
	public UsuarioSeguridad buscarPorLogin(String value) {
		return usuarioDAO.findOne(value);
	}

	public UsuarioSeguridad buscarPorLoginyCorreo(String value, String value2) {
		return usuarioDAO.findByLoginAndEmail(value, value2);
	}

	public void eliminarVarios(List<UsuarioSeguridad> eliminarLista) {
		usuarioDAO.delete(eliminarLista);
	}

	public void guardarVarios(List<UsuarioSeguridad> eliminarLista) {
		usuarioDAO.save(eliminarLista);
	}

}