package servicio.maestros;

import java.util.ArrayList;
import java.util.List;

import interfacedao.maestros.IDiagnosticoDAO;
import interfacedao.transacciones.IConsultaDiagnosticoDAO;

import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.Diagnostico;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;
import modelo.transacciones.ConsultaMedicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SDiagnostico")
public class SDiagnostico {

	@Autowired
	private IDiagnosticoDAO diagnosticoDAO;
	@Autowired
	private IConsultaDiagnosticoDAO consultaDiagnosticoDAO;

	public List<Diagnostico> buscarTodas() {
		return diagnosticoDAO.findAll();
	}

	public void guardar(Diagnostico diagnostico) {
		diagnosticoDAO.save(diagnostico);
	}

	public Diagnostico buscar(long id) {
		return diagnosticoDAO.findOne(id);
	}

	public void eliminar(Diagnostico diagnostico) {
		diagnosticoDAO.delete(diagnostico);
	}

	public List<Diagnostico> buscarPorCategoria(CategoriaDiagnostico categoria) {
		return diagnosticoDAO.findByCategoria(categoria);
	}

	public Diagnostico buscarPorCodigo(String value) {
		return diagnosticoDAO.findByCodigo(value);
	}

	public List<Diagnostico> filtroNombre(String valor) {
		return diagnosticoDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Diagnostico> filtroCodigo(String valor) {
		return diagnosticoDAO.findByCodigoStartingWithAllIgnoreCase(valor);
	}

	public List<Diagnostico> filtroGrupo(String valor) {
		return diagnosticoDAO.findByGrupoStartingWithAllIgnoreCase(valor);
	}

	public List<Diagnostico> filtroCategoria(String valor) {
		return diagnosticoDAO.findByCategoriaNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Diagnostico> buscarDisponibles(Consulta consulta) {
		List<ConsultaDiagnostico> consultasDiagnosticos = consultaDiagnosticoDAO.findByConsulta(consulta);
		List<Long> ids = new ArrayList<Long>();
		if(consultasDiagnosticos.isEmpty())
			return diagnosticoDAO.findAll();
		else{
			for(int i=0; i<consultasDiagnosticos.size();i++){
				ids.add(consultasDiagnosticos.get(i).getDiagnostico().getIdDiagnostico());
			}
			return diagnosticoDAO.findByIdDiagnosticoNotIn(ids);
		}
	}

	public Diagnostico buscarUltimo() {
		long id = diagnosticoDAO.findMaxIdDiagnostico();
		if (id != 0)
			return diagnosticoDAO.findOne(id);
		return null;
	}
}
