package servicio.maestros;

import interfacedao.maestros.IMedicinaDAO;
import interfacedao.transacciones.IConsultaMedicinaDAO;
import interfacedao.transacciones.IPacienteMedicinaDAO;

import java.util.ArrayList;
import java.util.List;

import modelo.maestros.CategoriaMedicina;
import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;
import modelo.maestros.Paciente;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.PacienteMedicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SMedicina")
public class SMedicina {

	@Autowired
	private IMedicinaDAO medicinaDAO;
	@Autowired
	private IConsultaMedicinaDAO consultaMedicinaDAO;
	@Autowired
	private IPacienteMedicinaDAO pacienteMedicinaDAO;

	public Medicina buscar(long idMedicina) {

		return medicinaDAO.findOne(idMedicina);
	}

	public List<Medicina> buscarTodas() {
		return medicinaDAO.findAll();
	}

	public void guardar(Medicina medicina) {
		medicinaDAO.save(medicina);
	}

	public Medicina buscarPorNombre(String value) {
		return medicinaDAO.findByNombre(value);
	}

	public List<Medicina> buscarPorLaboratorio(Laboratorio laboratorio) {
		return medicinaDAO.findByLaboratorio(laboratorio);
	}

	public void eliminar(Medicina medicina) {
		medicinaDAO.delete(medicina);
	}

	public List<Medicina> filtroNombre(String valor) {
		return medicinaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Medicina> filtroLaboratorio(String valor) {
		return medicinaDAO
				.findByLaboratorioNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Medicina> filtroPosologia(String valor) {
		return medicinaDAO.findByPosologiaStartingWithAllIgnoreCase(valor);
	}

	public List<Medicina> buscarPorCategoria(CategoriaMedicina categoriaMedicina) {
		return medicinaDAO.findByCategoriaMedicina(categoriaMedicina);
	}

	public Medicina buscarUltima() {
		long id = medicinaDAO.findMaxIdMedicina();
		if (id != 0)
			return medicinaDAO.findOne(id);
		else
			return null;
	}

	public List<Medicina> buscarDisponibles(Consulta consulta) {
		List<ConsultaMedicina> consultasMedicina = consultaMedicinaDAO
				.findByConsulta(consulta);
		List<Long> ids = new ArrayList<Long>();
		if (consultasMedicina.isEmpty())
			return medicinaDAO.findAll();
		else {
			for (int i = 0; i < consultasMedicina.size(); i++) {
				ids.add(consultasMedicina.get(i).getMedicina().getIdMedicina());
			}
			return medicinaDAO.findByIdMedicinaNotIn(ids);
		}
	}

	public void guardarVarios(List<Medicina> medicinas) {
		medicinaDAO.save(medicinas);
	}

	public Medicina buscarPorReferencia(long idRefD) {
		return medicinaDAO.findByIdReferencia(idRefD);
	}

	public List<Medicina> filtroDenominacion(String valor) {
		return medicinaDAO
				.findByDenominacionGenericaStartingWithAllIgnoreCase(valor);
	}

	public long buscarUltimaReferencia() {
		long id = medicinaDAO.findMaxIdReferencia();
		return id;
	}

	public List<Medicina> buscarDisponiblesPaciente(Paciente paciente) {
		List<PacienteMedicina> consultasMedicina = pacienteMedicinaDAO
				.findByPaciente(paciente);
		List<Long> ids = new ArrayList<Long>();
		if (consultasMedicina.isEmpty())
			return medicinaDAO.findAll();
		else {
			for (int i = 0; i < consultasMedicina.size(); i++) {
				ids.add(consultasMedicina.get(i).getMedicina().getIdMedicina());
			}
			return medicinaDAO.findByIdMedicinaNotIn(ids);
		}
	}
}
