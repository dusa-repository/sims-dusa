package controlador.reporte;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.maestros.Cargo;
import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;
import modelo.sha.Area;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import controlador.maestros.CGenerico;

public class CMorbilidad extends CGenerico {

	@Wire
	private Combobox cmbArea;
	@Wire
	private Combobox cmbCargo;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Div divMorbilidad;
	@Wire
	private Div botoneraMorbilidad;

	@Override
	public void inicializar() throws IOException {
		cargarCombos();
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				mapa.clear();
				mapa = null;
			}
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				// Pasar el nombre del arbol por el CARBOL
				cerrarVentana(divMorbilidad, "Morbilidad", tabs);
			}

			@Override
			public void limpiar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void guardar() {
				if (validar()) {
					Date desde = dtbDesde.getValue();
					Date hasta = dtbHasta.getValue();
					DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
					String fecha1 = fecha.format(desde);
					String fecha2 = fecha.format(hasta);
					Clients.evalJavaScript("window.open('"
							+ damePath()
							+ "Reportero?valor=9&valor6="
							+ fecha1
							+ "&valor7="
							+ fecha2
							+ "&valor8="
							+ cmbArea.getValue()
							+ "&valor9="
							+ cmbCargo.getValue()
							+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		botoneraMorbilidad.appendChild(botonera);
	}

	protected boolean validar() {
		// TODO Auto-generated method stub
		return true;
	}

	private void cargarCombos() {
		String todos = "Todos";
		Area area = new Area();
		area.setNombre(todos);
		area.setIdArea(0);
		List<Area> areas = new ArrayList<Area>();
		areas.add(area);
		areas.addAll(servicioArea.buscarTodos());
		Cargo cargo = new Cargo();
		cargo.setNombre(todos);
		cargo.setIdCargo(0);
		List<Cargo> cargos = new ArrayList<Cargo>();
		cargos.add(cargo);
		cargos.addAll(servicioCargo.buscarTodos());
		cmbArea.setModel(new ListModelList<Area>(areas));
		cmbCargo.setModel(new ListModelList<Cargo>(cargos));
	}

	public byte[] reporteMorbilidad(String part1, String part2, String area,
			String cargo) throws JRException {
		byte[] fichero = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = null;
		try {
			fecha1 = formato.parse(part1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date fecha2 = null;
		try {
			fecha2 = formato.parse(part2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Consulta> consuta = getServicioConsulta().buscarEntreFechas(
				fecha1, fecha2);

		Map p = new HashMap();
		p.put("desde", part1);
		p.put("hasta", part2);
		p.put("area", area);
		p.put("cargo", cargo);
		
		for (int i = 0; i < consuta.size(); i++) {
            Consulta cons = consuta.get(i);
            List<ConsultaDiagnostico> dig = getServicioConsultaDiagnostico()
                            .buscarPorConsulta(cons);
            if (!dig.isEmpty()) {
                    if (dig.get(0) != null) {
                            cons.setEnfermedadActual(dig.get(0).getDiagnostico()
                                            .getNombre());
                            cons.setMotivoConsulta(dig.get(0).getTipo());
                    }
            } else {
                    cons.setEnfermedadActual("");
                    cons.setMotivoConsulta("");
            }
    }

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/MorbilidadPorArea.jasper"));
		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(consuta));
		return fichero;
	}

}
