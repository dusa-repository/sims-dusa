package servicio.maestros;

import interfacedao.maestros.IDiagnosticoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SDiagnostico")
public class SDiagnostico {

	@Autowired
	private IDiagnosticoDAO interfaceDiagnostico;
}
