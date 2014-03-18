package interfacedao.seguridad;

import modelo.seguridad.Grupo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IGrupoDAO extends JpaRepository<Grupo, Long> {

//	public List<Grupo> findByEstatusTrue();
//	
//	public List<Grupo> findByUsuarios(Usuario usuario);
//	
//	public List<Grupo> findByIdNotIn(List<Long> ids);
//
//	public Grupo findByNombre(String string);
//
//	public List<Grupo> findByNombreStartingWith(String parte);
}
