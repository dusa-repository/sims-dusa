package servicio.maestros;

import interfacedao.maestros.IEmpresaDAO;

import java.util.List;

import modelo.maestros.Empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SEmpresa")
public class SEmpresa {

	@Autowired
	private IEmpresaDAO empresaDAO;

	public List<Empresa> buscarTodas() {
		return empresaDAO.findAll();
	}

	public Empresa buscar(long parseLong) {
		return empresaDAO.findOne(parseLong);
	}

	public void guardar(Empresa empresa) {
		empresaDAO.save(empresa);
	}

	public void eliminar(Empresa empresa) {
		empresaDAO.delete(empresa);
	}

	public List<Empresa> filtroNombre(String valor) {
		return empresaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Empresa> filtroRif(String valor) {
		return empresaDAO.findByRifStartingWithAllIgnoreCase(valor);
	}

	public List<Empresa> filtroDireccionCentro(String valor) {
		return empresaDAO.findByDireccionCentroStartingWithAllIgnoreCase(valor);
	}

	public List<Empresa> filtroTelefono(String valor) {
		return empresaDAO.findByTelefonoStartingWithAllIgnoreCase(valor);
	}


	public Empresa buscarPorRif(String value) {
		return empresaDAO.findByRif(value);
	}

	public Empresa buscarUltima() {
		long id = empresaDAO.findMaxIdEmpresa();
		if (id != 0)
			return empresaDAO.findOne(id);
		return null;
	}
}
