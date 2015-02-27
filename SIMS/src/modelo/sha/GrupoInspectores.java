package modelo.sha;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the system database table.
 * 
 */
@Entity
@Table(name="grupo_inspectores")
public class GrupoInspectores implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private String grupo;
	
	public GrupoInspectores() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


}
