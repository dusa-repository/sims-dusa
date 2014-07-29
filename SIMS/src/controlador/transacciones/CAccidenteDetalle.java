package controlador.transacciones;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.generico.DetalleAccidente;
import modelo.maestros.Accidente;
import modelo.maestros.Diagnostico;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import componentes.Validador;

import controlador.maestros.CGenerico;

public class CAccidenteDetalle extends CGenerico {

	private static final long serialVersionUID = 851304641959991032L;
	@Wire
	private Window wdwRegistro;
	@Wire
	private Div botoneraDetalle;
	@Wire
	private Label lblDiagnostio;
	@Wire
	private Textbox txtLugar;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Datebox dtbFecha;
	@Wire
	private Textbox txtRazon;
	@Wire
	private Combobox cmbClasificacion;
	@Wire
	private Div catalogoAccidente;
	private Catalogo<Accidente> catalogo;
	private CConsulta controlador = new CConsulta();
	private List<DetalleAccidente> lista = new ArrayList<DetalleAccidente>();
	private long id = 0;
	private long idAccidente = 0;
	private DetalleAccidente detalle = new DetalleAccidente();
	private String[] tipoLaboral = { "Trabajando dentro de la Empresa",
			"Trabajando fuera de la Empresa", "In Itinere", "Deportivo",
			"Actividad Recreacional o turismo" };
	private String[] tipoComun = { "En el Hogar", "Vial", "Transeunte", "Otro" };

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("consulta");
		if (map != null) {
			if (map.get("idDiagnostico") != null) {
				id = (long) map.get("idDiagnostico");
				Diagnostico diagnostico = servicioDiagnostico.buscar((long) map
						.get("idDiagnostico"));
				lista = (List<DetalleAccidente>) map.get("lista");
				lblDiagnostio.setValue(diagnostico.getNombre());
				String tipo = (String) map.get("tipoDiagnostico");
				if (tipo.equals("Accidente Laboral")
						|| tipo.equals("Incidente"))
					cmbClasificacion.setModel(new ListModelList<String>(
							tipoLaboral));
				else
					cmbClasificacion.setModel(new ListModelList<String>(
							tipoComun));
				for (int i = 0; i < lista.size(); i++) {
					if (id == lista.get(i).getDiagnostico()) {
						settearCampos(lista.get(i));
						detalle = lista.get(i);
					}
				}
				map.clear();
				map = null;
			}
		}

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				wdwRegistro.onClose();
			}

			@Override
			public void limpiar() {
				lista = null;
				id = 0;
				detalle = null;
				idAccidente = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String lugar = txtLugar.getValue();
					String motivo = txtRazon.getValue();
					Date fechaCon = dtbFecha.getValue();
					Timestamp fecha = new Timestamp(fechaCon.getTime());
					String clasificacion = cmbClasificacion.getValue();
					Accidente accidente = servicioAccidente.buscar(idAccidente);
					DetalleAccidente detalleAccidente = new DetalleAccidente(
							id, lugar, motivo, clasificacion, fecha, accidente);
					if (detalle != null)
						lista.remove(detalle);
					lista.add(detalleAccidente);
					controlador.recibirLista(lista);
					limpiar();
					salir();
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(false);
		botoneraDetalle.appendChild(botonera);
	}

	private void settearCampos(DetalleAccidente detalleAccidente) {
		txtLugar.setValue(detalleAccidente.getLugar());
		txtRazon.setValue(detalleAccidente.getMotivo());
		dtbFecha.setValue(detalleAccidente.getFecha());
		cmbClasificacion.setValue(detalleAccidente.getClasificacion());
		if (detalleAccidente.getAccidente() != null) {
			txtNombre.setValue(detalleAccidente.getAccidente().getNombre());
			idAccidente = detalleAccidente.getAccidente().getIdAccidente();
		}
	}

	protected boolean validar() {
		if (txtLugar.getText().compareTo("") == 0
				|| txtRazon.getText().compareTo("") == 0
				|| txtNombre.getText().compareTo("") == 0
				|| cmbClasificacion.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	@Listen("onClick = #btnBuscar")
	public void mostrarCatalogo() throws IOException {
		final List<Accidente> accidentes = servicioAccidente.buscarTodos();
		catalogo = new Catalogo<Accidente>(catalogoAccidente,
				"Catalogo de Accidentes", accidentes, "Codigo", "Nombre",
				"Clasificacion") {

			@Override
			protected List<Accidente> buscar(String valor, String combo) {
				switch (combo) {
				case "Codigo":
					return servicioAccidente.filtroCodigo(valor);
				case "Nombre":
					return servicioAccidente.filtroNombre(valor);
				case "Clasificacion":
					return servicioAccidente.filtroClasificacion(valor);
				default:
					return accidentes;
				}
			}

			@Override
			protected String[] crearRegistros(Accidente objeto) {
				String[] registros = new String[3];
				registros[0] = String.valueOf(objeto.getIdAccidente());
				registros[1] = objeto.getNombre();
				registros[2] = objeto.getClasificacion().getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoAccidente);
		catalogo.doModal();
	}

	@Listen("onSeleccion = #catalogoAccidente")
	public void seleccionarDoctor() {
		Accidente accidente = catalogo.objetoSeleccionadoDelCatalogo();
		idAccidente = accidente.getIdAccidente();
		txtNombre.setValue(accidente.getNombre());
		catalogo.setParent(null);
	}

	@Listen("onChange = #txtNombre")
	public void buscar() {
		if (Validador.validarNumero(txtNombre.getValue())) {
			Accidente accidente = servicioAccidente.buscar(Long
					.valueOf(txtNombre.getValue()));
			if (accidente != null) {
				idAccidente = accidente.getIdAccidente();
				txtNombre.setValue(accidente.getNombre());
			}
		} else {
			Messagebox
					.show("Solo se Admiten numeros para el Codigo CIIU del Accidente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
			txtNombre.setValue("");
			txtNombre.setFocus(true);
		}
	}
}
