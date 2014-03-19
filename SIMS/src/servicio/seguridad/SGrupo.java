package servicio.seguridad;

import java.util.List;

import interfacedao.seguridad.IGrupoDAO;

import modelo.seguridad.Grupo;
import modelo.seguridad.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SGrupo")
public class SGrupo {

	@Autowired
	private IGrupoDAO interfazGrupo;
	
	public void guardarGrupo(Grupo grupo){
		interfazGrupo.save(grupo);
	}
	
	public List<Grupo> buscarTodos(){
		return interfazGrupo.findByEstadoTrue();
	}
	public Grupo buscarGrupo(long id){
		return interfazGrupo.findOne(id);
	}
	
	public List<Grupo> buscarGruposDelUsuario(Usuario usuario){
		return interfazGrupo.findByUsuarios(usuario);
	}
	
	public List<Grupo> buscarGruposDisponibles(List<Long> ids){
		return interfazGrupo.findByIdGrupoNotInAndEstadoTrue(ids);
	}
}