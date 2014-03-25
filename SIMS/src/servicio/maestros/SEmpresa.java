package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IEmpresaDAO;

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
}
