package servicio.maestros;

import interfacedao.maestros.ILaboratorioDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SLaboratorio")
public class SLaboratorio {

	@Autowired
	private ILaboratorioDAO interfaceLaboratorio;
}
