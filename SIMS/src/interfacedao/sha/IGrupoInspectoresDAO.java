package interfacedao.sha;


import modelo.sha.GrupoInspectores;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IGrupoInspectoresDAO extends JpaRepository<GrupoInspectores, Integer> {

	GrupoInspectores findById(int i);

}
