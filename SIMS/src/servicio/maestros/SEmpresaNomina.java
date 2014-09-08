package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IEmpresaNominaDAO;

import modelo.maestros.Empresa;
import modelo.maestros.EmpresaNomina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SEmpresaNomina")
public class SEmpresaNomina {

	@Autowired
	private IEmpresaNominaDAO empresaNominaDAO;

	public List<EmpresaNomina> buscarPorEmpresa(Empresa empresa) {
		return empresaNominaDAO.findByEmpresa(empresa);
	}

	public void borrarNominas(Empresa empresa) {
		List<EmpresaNomina> borrados = empresaNominaDAO.findByEmpresa(empresa);
		if (!borrados.isEmpty())
			empresaNominaDAO.delete(borrados);
	}

	public void guardar(List<EmpresaNomina> empresasNominas) {
		empresaNominaDAO.save(empresasNominas);
	}
}
