package servicio.maestros;

import java.util.ArrayList;
import java.util.List;

import interfacedao.maestros.IAccidenteDAO;
import interfacedao.transacciones.IHistoriaAccidenteDAO;

import modelo.maestros.Accidente;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaServicioExterno;
import modelo.transacciones.Historia;
import modelo.transacciones.HistoriaAccidente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SAccidente")
public class SAccidente {

	@Autowired
	private IAccidenteDAO accidenteDAO;
	@Autowired
	private IHistoriaAccidenteDAO accidenteHistoriaDAO;

	public List<Accidente> buscarPorTipo(String valor) {
		return accidenteDAO.findByTipo(valor);
	}

	public Accidente buscar(long parseLong) {
		return accidenteDAO.findOne(parseLong);
	}

	public List<Accidente> buscarDisponibles(Historia historia, String string) {
		List<HistoriaAccidente> accidentesHistoricos = accidenteHistoriaDAO.findByHistoriaAndAccidenteTipo(historia, string);
		List<Long> ids = new ArrayList<Long>();
		if(accidentesHistoricos.isEmpty())
			return accidenteDAO.findByTipo(string);
		else{
			for(int i=0; i<accidentesHistoricos.size();i++){
				ids.add(accidentesHistoricos.get(i).getAccidente().getIdAccidente());
			}
			return accidenteDAO.findByTipoAndIdAccidenteNotIn(string,ids);
		}
	}

	public List<Accidente> filtroNombre(String valor, String tipo) {
		// TODO Auto-generated method stub
		return accidenteDAO.findByNombreStartingWithAndTipoAllIgnoreCase(valor, tipo);
	}
}
