package servicio.maestros;

import interfacedao.maestros.IEmpresaNominaDAO;
import interfacedao.maestros.INominaDAO;

import java.util.ArrayList;
import java.util.List;

import modelo.maestros.Empresa;
import modelo.maestros.EmpresaNomina;
import modelo.maestros.Nomina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SNomina")
public class SNomina {
	
	@Autowired
	private INominaDAO nominaDAO;
	@Autowired
	private IEmpresaNominaDAO empresaNominaDAO;

	public void guardar(Nomina nomina) {
		nominaDAO.save(nomina);
		
	}

	public Nomina buscar(long id) {
		return nominaDAO.findByIdNomina(id);
	}

	public void eliminar(Nomina nomina) {
		nominaDAO.delete(nomina);
		
	}

	public List<Nomina> buscarTodos() {
		return nominaDAO.findAll();
	}

	public List<Nomina> filtroNombre(String valor) {
		return nominaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public Nomina buscarPorNombre(String value) {
		return nominaDAO.findByNombre(value);
	}

	public Nomina buscarUltimo() {

		long id = nominaDAO.findMaxIdExamen();
		if (id != 0)
			return nominaDAO.findOne(id);
		return null;
	}

	public List<Nomina> buscarDisponibles(Empresa empresa) {
		List<EmpresaNomina> empresasNominas = empresaNominaDAO.findByEmpresa(empresa);
		List<Long> ids = new ArrayList<Long>();
		if(empresasNominas.isEmpty())
			return nominaDAO.findAll();
		else{
			for(int i=0; i<empresasNominas.size();i++){
				ids.add(empresasNominas.get(i).getNomina().getIdNomina());
			}
			return nominaDAO.findByIdNominaNotIn(ids);
		}
	}

}
