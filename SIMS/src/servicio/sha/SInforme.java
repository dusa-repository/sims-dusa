package servicio.sha;

import interfacedao.sha.IInformeDAO;

import java.util.List;

import modelo.maestros.Empresa;
import modelo.maestros.Paciente;
import modelo.sha.Area;
import modelo.sha.ClasificacionAccidente;
import modelo.sha.Condicion;
import modelo.sha.Informe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SInforme")
public class SInforme {

	@Autowired
	private IInformeDAO informeDAO;

	public void guardar(Informe informe) {
		informeDAO.save(informe);
		
	}

	public List<Informe> buscarTodos() {
	return	informeDAO.findAll();
	}

	public List<Informe> filtroCodigo(String valor) {
		return informeDAO.findByCodigoStartingWithAllIgnoreCase(valor);
	}

	public List<Informe> filtroNombreTrabajador(String valor) {
		return informeDAO.findByPacientePrimerNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Informe> filtroApellidoTrabajador(String valor) {
		return informeDAO.findByPacientePrimerApellidoStartingWithAllIgnoreCase(valor);
	}

	public List<Informe> filtroEmpresa(String valor) {
		return informeDAO.findByEmpresaNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Informe> buscarPorCondicion(Condicion condicion) {
		return informeDAO.findByCondicionAOrCondicionBOrCondicionCOrCondicionDOrCondicionEOrCondicionF(condicion,condicion,condicion,condicion,condicion,condicion);
	}

	public List<Informe> buscarPorArea(Area area) {
		return informeDAO.findByArea(area);
	}

	public List<Informe> buscarPorPaciente(Paciente paciente) {
		return informeDAO.findByPaciente(paciente);
	}

	public List<Informe> buscarPorClasificacion(
			ClasificacionAccidente clasificacionAccidente) {
		return informeDAO.findByClasificacion(clasificacionAccidente);
	}

	public List<Informe> buscarPorEmpresaTrabajador(Empresa empresa) {
		return informeDAO.findByEmpresa(empresa);
	}

	public List<Informe> buscarPorEmpresaBeneficiaria(Empresa empresa) {
		return informeDAO.findByEmpresaB(empresa);
	}

	public Informe buscar(long parseLong) {
		return informeDAO.findByIdInforme(parseLong);
	}



	}
