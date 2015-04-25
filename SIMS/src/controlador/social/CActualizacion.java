package controlador.social;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modelo.generico.Persona;
import modelo.maestros.Ciudad;
import modelo.maestros.EstadoCivil;
import modelo.maestros.Familiar;
import modelo.maestros.Paciente;
import modelo.seguridad.Arbol;
import modelo.social.Ficha;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CActualizacion extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Div botoneraActualizacion;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Div divCatalogoPacientes;
	@Wire
	private Div divVActualizacion;
	@Wire
	private Textbox txtCedulaPaciente;
	@Wire
	private Label lblFichaPaciente;
	@Wire
	private Label lblRifPaciente;
	@Wire
	private Label lblNombrePaciente;
	@Wire
	private Label lblApellidoPaciente;
	@Wire
	private Datebox dtbFechaNac;
	@Wire
	private Combobox cmbGrupoSanguineo;
	@Wire
	private Combobox cmbEstadoCivil;
	@Wire
	private Combobox cmbCiudad;
	@Wire
	private Combobox cmbTipoVivienda;
	@Wire
	private Textbox txtTelefono1;
	@Wire
	private Textbox txtTelefono2;
	@Wire
	private Textbox txtDireccion;
	@Wire
	private Textbox txtTallaCamisa;
	@Wire
	private Textbox txtTallaPantalon;
	@Wire
	private Textbox txtTallaGoma;
	@Wire
	private Textbox txtTallaSeguridad;
	@Wire
	private Radio rdoSi;
	@Wire
	private Radio rdoNo;
	@Wire
	private Listbox ltbActivos;
	@Wire
	private Listbox ltbInactivos;
	Catalogo<Paciente> catalogoPaciente;
	List<Persona> cargaActiva = new ArrayList<Persona>();
	List<Persona> cargaInactiva = new ArrayList<Persona>();
	List<Listbox> listas = new ArrayList<Listbox>();
	private String idPaciente = null;
	CArbol cArbol = new CArbol();

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divVActualizacion.getParent();
		Tabbox tabox = (Tabbox) divVActualizacion.getParent().getParent()
				.getParent().getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				titulo = (String) mapa.get("titulo");
				mapa.clear();
				mapa = null;
			}
		}
		listas.add(ltbActivos);
		listas.add(ltbInactivos);
		llenarComboCiudad();
		llenarComboCivil();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divCatalogoPacientes, titulo, tabs);
			}

			@Override
			public void limpiar() {
				limpiarCampos();
			}

			@Override
			public void guardar() {
				if (validar()) {
					Paciente paciente = servicioPaciente
							.buscarPorCedula(idPaciente);
					paciente.setDireccion(txtDireccion.getValue());
					paciente.setFechaNacimiento(new Timestamp(dtbFechaNac
							.getValue().getTime()));
					paciente.setEdad(calcularEdad(dtbFechaNac.getValue()));
					paciente.setTelefono1(txtTelefono1.getValue());
					paciente.setTelefono2(txtTelefono2.getValue());
					paciente.setCiudadVivienda(servicioCiudad.buscar(Long
							.valueOf(cmbCiudad.getSelectedItem().getContext())));
					paciente.setEstadoCivil(servicioEstadoCivil.buscar(Long
							.valueOf(cmbEstadoCivil.getSelectedItem()
									.getContext())));
					paciente.setGrupoSanguineo(cmbGrupoSanguineo.getValue());
					// DATOS DE FICHA
					String camisa = txtTallaCamisa.getValue();
					String seguridad = txtTallaSeguridad.getValue();
					String pantalon = txtTallaPantalon.getValue();
					String goma = txtTallaGoma.getValue();
					boolean boll = false;
					if (rdoSi.isChecked())
						boll = true;
					String tipo = cmbTipoVivienda.getValue();
					Ficha ficha = new Ficha();
					if (paciente.getFichaMaestra() != null) {
						ficha = paciente.getFichaMaestra();
						ficha.setTallaBotas(seguridad);
						ficha.setTallaCamisa(camisa);
						ficha.setTallaGoma(goma);
						ficha.setTallaPantalon(pantalon);
						ficha.setViviendaPropia(boll);
						ficha.setTipoVivienda(tipo);
					} else {
						ficha = new Ficha();
						ficha.setPaciente(paciente);
						ficha.setTallaBotas(seguridad);
						ficha.setTallaCamisa(camisa);
						ficha.setTallaGoma(goma);
						ficha.setTallaPantalon(pantalon);
						ficha.setViviendaPropia(boll);
						ficha.setTipoVivienda(tipo);
					}
					// GUARDO TODO
					servicioPaciente.guardar(paciente);
					servicioFicha.guardar(ficha);
					limpiar();
					Mensaje.mensajeInformacion(Mensaje.guardado);
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botoneraActualizacion.appendChild(botonera);
	}

	protected boolean validar() {
		if (idPaciente == null || txtDireccion.getText().compareTo("") == 0
				|| txtCedulaPaciente.getText().compareTo("") == 0
				|| dtbFechaNac.getText().compareTo("") == 0
				|| txtTallaCamisa.getText().compareTo("") == 0
				|| txtTallaGoma.getText().compareTo("") == 0
				|| txtTallaPantalon.getText().compareTo("") == 0
				|| txtTallaSeguridad.getText().compareTo("") == 0
				|| txtTelefono1.getText().compareTo("") == 0
				|| cmbGrupoSanguineo.getText().compareTo("") == 0
				|| cmbEstadoCivil.getText().compareTo("") == 0
				|| cmbTipoVivienda.getText().compareTo("") == 0
				|| (!rdoSi.isChecked() && !rdoNo.isChecked())) {
			Mensaje.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;

	}

	@Listen("onOpen = #cmbCiudad")
	public void llenarComboCiudad() {
		List<Ciudad> ciudades = servicioCiudad.buscarTodas();
		cmbCiudad.setModel(new ListModelList<Ciudad>(ciudades));
	}

	@Listen("onOpen = #cmbEstadoCivil")
	public void llenarComboCivil() {
		List<EstadoCivil> ciudades = servicioEstadoCivil.buscarTodas();
		cmbEstadoCivil.setModel(new ListModelList<EstadoCivil>(ciudades));
	}

	@Listen("onClick =  #btnBuscarPaciente")
	public void buscarTrabajador() {
		final List<Paciente> pacientes = servicioPaciente
				.buscarTodosTrabajadores();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPacientes,
				"Catalogo de Trabajadores", pacientes, false, "Cedula",
				"Primer Nombre", "Segundo Nombre", "Primer Apellido",
				"Segundo Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Primer Nombre":
					return servicioPaciente.filtroNombre1T(valor);
				case "Segundo Nombre":
					return servicioPaciente.filtroNombre2T(valor);
				case "Cedula":
					return servicioPaciente.filtroCedulaT(valor);
				case "Primer Apellido":
					return servicioPaciente.filtroApellido1T(valor);
				case "Segundo Apellido":
					return servicioPaciente.filtroApellido2T(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[5];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getPrimerNombre();
				registros[2] = objeto.getSegundoNombre();
				registros[3] = objeto.getPrimerApellido();
				registros[4] = objeto.getSegundoApellido();
				return registros;
			}

		};
		catalogoPaciente.setParent(divCatalogoPacientes);
		catalogoPaciente.doModal();
	}

	@Listen("onSeleccion = #divCatalogoPacientes")
	public void seleccionarPaciente() {
		limpiarCampos();
		Paciente paciente = catalogoPaciente.objetoSeleccionadoDelCatalogo();
		llenarCampos(paciente);
		idPaciente = paciente.getCedula();
		catalogoPaciente.setParent(null);
	}

	@Listen("onOK = #txtCedulaPaciente; onChange = #txtCedulaPaciente")
	public void buscarCedula() {
		Paciente paciente = servicioPaciente
				.buscarPorCedulaYTrabajadorActivo(txtCedulaPaciente.getValue());
		if (paciente != null) {
			llenarCampos(paciente);
		} else {
			limpiarCampos();
			Mensaje.mensajeError(Mensaje.pacienteNoExiste);
		}
	}

	private void llenarCampos(Paciente paciente) {
		idPaciente = paciente.getCedula();
		lblNombrePaciente.setValue(paciente.getPrimerNombre());
		lblApellidoPaciente.setValue(paciente.getPrimerApellido());
		lblFichaPaciente.setValue(paciente.getFicha());
		lblRifPaciente.setValue(paciente.getRif());
		dtbFechaNac.setValue(paciente.getFechaNacimiento());
		txtDireccion.setValue(paciente.getDireccion());
		txtTelefono1.setValue(paciente.getTelefono1());
		txtTelefono2.setValue(paciente.getTelefono2());
		txtCedulaPaciente.setValue(paciente.getCedula());
		cmbCiudad.setValue(paciente.getCiudadVivienda().getNombre());
		if (paciente.getEstadoCivil() != null)
			cmbEstadoCivil.setValue(paciente.getEstadoCivil().getNombre());
		cmbGrupoSanguineo.setValue(paciente.getGrupoSanguineo());
		if (paciente.getFichaMaestra() != null) {
			Ficha ficha = paciente.getFichaMaestra();
			txtTallaCamisa.setValue(ficha.getTallaCamisa());
			txtTallaGoma.setValue(ficha.getTallaGoma());
			txtTallaPantalon.setValue(ficha.getTallaPantalon());
			txtTallaSeguridad.setValue(ficha.getTallaBotas());
			cmbTipoVivienda.setValue(ficha.getTipoVivienda());
			if (ficha.isViviendaPropia())
				rdoSi.setChecked(true);
			else
				rdoNo.setChecked(true);
		}
		llenarListas();
	}

	private void llenarListas() {
		cargaActiva.clear();
		cargaInactiva.clear();
		if (idPaciente != null) {
			List<Familiar> cargasActivos = servicioFamiliar
					.buscarPorTrabajadorYEstado(idPaciente, true);
			List<Paciente> familiaresActivos = servicioPaciente
					.buscarParientesYEstado(idPaciente, true);
			for (int i = 0; i < familiaresActivos.size(); i++) {
				Paciente objeto = familiaresActivos.get(i);
				Persona persona = new Persona();
				persona.setApellido(objeto.getPrimerApellido());
				persona.setCedula(objeto.getCedula());
				persona.setDireccion(objeto.getDireccion());
				persona.setFechaNacimiento(formatoFecha.format(objeto
						.getFechaNacimiento()));
				persona.setLugarNacimiento(objeto.getLugarNacimiento());
				persona.setNivelEducacion(objeto.getNivelEducativo());
				persona.setNombre(objeto.getPrimerNombre());
				persona.setParentesco(objeto.getParentescoFamiliar());
				persona.setSexo(objeto.getSexo());
				persona.setTrabajador(idPaciente);
				persona.setServicioMedico("SI");
				String verificacion = "NO";
				if (objeto.getRevision() != null)
					if (objeto.getRevision())
						verificacion = "SI";
				persona.setVerificacionRH(verificacion);
				String jubilado = "NO";
				if (objeto.getJubilado() != null)
					if (objeto.getJubilado())
						jubilado = "SI";
				persona.setJubilado(jubilado);
				cargaActiva.add(persona);
			}
			for (int i = 0; i < cargasActivos.size(); i++) {
				Familiar objeto = cargasActivos.get(i);
				Persona persona = new Persona();
				persona.setApellido(objeto.getPrimerApellido());
				persona.setCedula(objeto.getCedula());
				persona.setDireccion(objeto.getDireccion());
				persona.setFechaNacimiento(formatoFecha.format(objeto
						.getFechaNacimiento()));
				persona.setLugarNacimiento(objeto.getLugarNacimiento());
				persona.setNivelEducacion(objeto.getCargoOCarrera());
				persona.setNombre(objeto.getPrimerNombre());
				persona.setParentesco(objeto.getParentescoFamiliar());
				persona.setSexo(objeto.getSexo());
				persona.setTrabajador(idPaciente);
				persona.setServicioMedico("NO");
				String verificacion = "NO";
				if (objeto.getRevision() != null)
					if (objeto.getRevision())
						verificacion = "SI";
				persona.setVerificacionRH(verificacion);
				String jubilado = "NO";
				if (objeto.getJubilado() != null)
					if (objeto.getJubilado())
						jubilado = "SI";
				persona.setJubilado(jubilado);
				cargaActiva.add(persona);
			}

			List<Familiar> cargasInactivos = servicioFamiliar
					.buscarPorTrabajadorYEstado(idPaciente, false);
			List<Paciente> familiaresInactivos = servicioPaciente
					.buscarParientesYEstado(idPaciente, false);
			for (int i = 0; i < familiaresInactivos.size(); i++) {
				Paciente objeto = familiaresInactivos.get(i);
				Persona persona = new Persona();
				persona.setApellido(objeto.getPrimerApellido());
				persona.setCedula(objeto.getCedula());
				persona.setDireccion(objeto.getDireccion());
				persona.setFechaNacimiento(formatoFecha.format(objeto
						.getFechaNacimiento()));
				persona.setLugarNacimiento(objeto.getLugarNacimiento());
				persona.setNivelEducacion(objeto.getNivelEducativo());
				persona.setNombre(objeto.getPrimerNombre());
				persona.setParentesco(objeto.getParentescoFamiliar());
				persona.setSexo(objeto.getSexo());
				persona.setTrabajador(idPaciente);
				persona.setServicioMedico("SI");
				String verificacion = "NO";
				if (objeto.getRevision() != null)
					if (objeto.getRevision())
						verificacion = "SI";
				persona.setVerificacionRH(verificacion);
				String jubilado = "NO";
				if (objeto.getJubilado() != null)
					if (objeto.getJubilado())
						jubilado = "SI";
				persona.setJubilado(jubilado);
				cargaInactiva.add(persona);
			}
			for (int i = 0; i < cargasInactivos.size(); i++) {
				Familiar objeto = cargasInactivos.get(i);
				Persona persona = new Persona();
				persona.setApellido(objeto.getPrimerApellido());
				persona.setCedula(objeto.getCedula());
				persona.setDireccion(objeto.getDireccion());
				persona.setFechaNacimiento(formatoFecha.format(objeto
						.getFechaNacimiento()));
				persona.setLugarNacimiento(objeto.getLugarNacimiento());
				persona.setNivelEducacion(objeto.getCargoOCarrera());
				persona.setNombre(objeto.getPrimerNombre());
				persona.setParentesco(objeto.getParentescoFamiliar());
				persona.setSexo(objeto.getSexo());
				persona.setTrabajador(idPaciente);
				persona.setServicioMedico("NO");
				String verificacion = "NO";
				if (objeto.getRevision() != null)
					if (objeto.getRevision())
						verificacion = "SI";
				persona.setVerificacionRH(verificacion);
				String jubilado = "NO";
				if (objeto.getJubilado() != null)
					if (objeto.getJubilado())
						jubilado = "SI";
				persona.setJubilado(jubilado);
				cargaInactiva.add(persona);
			}
		}
		ltbActivos.setModel(new ListModelList<Persona>(cargaActiva));
		ltbInactivos.setModel(new ListModelList<Persona>(cargaInactiva));
		listasMultiples();
	}

	private void listasMultiples() {
		for (int i = 0; i < listas.size(); i++) {
			listas.get(i).setMultiple(false);
			listas.get(i).setCheckmark(false);
			listas.get(i).setMultiple(true);
			listas.get(i).setCheckmark(true);
		}
	}

	public void limpiarCampos() {
		idPaciente = null;
		lblNombrePaciente.setValue("");
		lblApellidoPaciente.setValue("");
		lblFichaPaciente.setValue("");
		lblRifPaciente.setValue("");
		dtbFechaNac.setValue(fecha);
		txtDireccion.setValue("");
		txtTelefono1.setValue("");
		txtTelefono2.setValue("");
		txtCedulaPaciente.setValue("");
		cmbCiudad.setValue("");
		cmbEstadoCivil.setValue("");
		cmbGrupoSanguineo.setValue("");
		txtTallaCamisa.setValue("");
		txtTallaGoma.setValue("");
		txtTallaPantalon.setValue("");
		txtTallaSeguridad.setValue("");
		cmbTipoVivienda.setValue("");
		rdoSi.setChecked(false);
		rdoNo.setChecked(false);
		llenarListas();
	}

	@Listen("onClick = #btnActivar, #btnDesactivar")
	public void cambiarEstado(Event evento) {
		List<Persona> procesadas = new ArrayList<Persona>();
		boolean estatus = false;
		String nombreTitulo = "¿Desea Inactivar a la carga seleccionada?";
		if (evento.getTarget().getId().equals("btnActivar")) {
			estatus = true;
			nombreTitulo = "¿Desea Activar a la carga seleccionada?";
			procesadas = obtenerSeleccionados(ltbInactivos);
		} else
			procesadas = obtenerSeleccionados(ltbActivos);
		final boolean estado = estatus;
		if (validarSeleccion(procesadas)) {
			final List<Persona> seleccionadas = procesadas;
			Messagebox.show(nombreTitulo, "Alerta", Messagebox.OK
					| Messagebox.CANCEL, Messagebox.QUESTION,
					new org.zkoss.zk.ui.event.EventListener<Event>() {
						public void onEvent(Event evt)
								throws InterruptedException {
							if (evt.getName().equals("onOK")) {
								List<Familiar> familiares = new ArrayList<Familiar>();
								List<Paciente> pacientes = new ArrayList<Paciente>();
								for (Iterator<Persona> iterator = seleccionadas
										.iterator(); iterator.hasNext();) {
									Persona persona = (Persona) iterator.next();
									if (persona.getServicioMedico()
											.equals("SI")) {
										Paciente paciente = servicioPaciente
												.buscarPorCedula(persona
														.getCedula());
										paciente.setEstatus(estado);
										pacientes.add(paciente);
									} else {
										Familiar familiar = servicioFamiliar
												.buscarPorCedula(persona
														.getCedula());
										familiar.setEstatus(estado);
										familiares.add(familiar);
									}
								}
								if (!familiares.isEmpty())
									servicioFamiliar.guardarVarios(familiares);
								if (!pacientes.isEmpty())
									servicioPaciente.guardarVarios(pacientes);
								llenarListas();
								Mensaje.mensajeInformacion("Operacion Realizada con Exito");
							}
						}
					});
		}
	}

	private boolean validarSeleccion(List<Persona> procesadas) {
		if (procesadas == null) {
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
			return false;
		} else {
			if (procesadas.isEmpty()) {
				Mensaje.mensajeAlerta(Mensaje.noSeleccionoItem);
				return false;
			} else {
				return true;
			}
		}
	}

	private List<Persona> obtenerSeleccionados(Listbox lista) {
		List<Persona> valores = new ArrayList<Persona>();
		if (lista.getItemCount() != 0) {
			List<Listitem> list1 = lista.getItems();
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).isSelected()) {
					Persona clase = list1.get(i).getValue();
					valores.add(clase);
				}
			}
			return valores;
		} else
			return null;
	}

	@Listen("onClick = #btnAbrir")
	public void abrirPais() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Familiar");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	@Listen("onClick = #btnRefrescar")
	public void refrescarServicio() {
		llenarListas();
	}

	@Listen("onClick = #btnReporte")
	public void reporte() {
		if (idPaciente != null)
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=54&valor3="
					+ idPaciente
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		else
			Mensaje.mensajeAlerta("Debe seleccionar un Paciente para observar el formato de Actualizacion");
	}

	public byte[] jasperFormatoActualizacion(String par3) {
		byte[] fichero = null;
		List<Familiar> cargasActivos = getServicioFamiliar()
				.buscarPorTrabajadorYEstado(par3, true);
		List<Paciente> familiaresActivos = getServicioPaciente()
				.buscarParientesYEstado(par3, true);
		List<Persona> lista = new ArrayList<Persona>();
		for (int i = 0; i < familiaresActivos.size(); i++) {
			Paciente objeto = familiaresActivos.get(i);
			Persona persona = new Persona();
			persona.setApellido(objeto.getPrimerApellido());
			persona.setCedula(objeto.getCedula());
			persona.setDireccion(objeto.getDireccion());
			persona.setFechaNacimiento(formatoFecha.format(objeto
					.getFechaNacimiento()));
			persona.setLugarNacimiento(objeto.getLugarNacimiento());
			persona.setNivelEducacion(objeto.getNivelEducativo());
			persona.setNombre(objeto.getPrimerNombre());
			persona.setParentesco(objeto.getParentescoFamiliar());
			persona.setSexo(objeto.getSexo());
			persona.setTrabajador(par3);
			persona.setServicioMedico("SI");
			String verificacion = "NO";
			if (objeto.getRevision() != null)
				if (objeto.getRevision())
					verificacion = "SI";
			persona.setVerificacionRH(verificacion);
			String jubilado = "NO";
			if (objeto.getJubilado() != null)
				if (objeto.getJubilado())
					jubilado = "SI";
			persona.setJubilado(jubilado);
			lista.add(persona);
		}
		for (int i = 0; i < cargasActivos.size(); i++) {
			Familiar objeto = cargasActivos.get(i);
			Persona persona = new Persona();
			persona.setApellido(objeto.getPrimerApellido());
			persona.setCedula(objeto.getCedula());
			persona.setDireccion(objeto.getDireccion());
			persona.setFechaNacimiento(formatoFecha.format(objeto
					.getFechaNacimiento()));
			persona.setLugarNacimiento(objeto.getLugarNacimiento());
			persona.setNivelEducacion(objeto.getCargoOCarrera());
			persona.setNombre(objeto.getPrimerNombre());
			persona.setParentesco(objeto.getParentescoFamiliar());
			persona.setSexo(objeto.getSexo());
			persona.setTrabajador(par3);
			persona.setServicioMedico("NO");
			String verificacion = "NO";
			if (objeto.getRevision() != null)
				if (objeto.getRevision())
					verificacion = "SI";
			persona.setVerificacionRH(verificacion);
			String jubilado = "NO";
			if (objeto.getJubilado() != null)
				if (objeto.getJubilado())
					jubilado = "SI";
			persona.setJubilado(jubilado);
			lista.add(persona);
		}

		Paciente paciente = getServicioPaciente().buscarPorCedula(par3);
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("cedula", paciente.getCedula());
		p.put("nombre", paciente.getPrimerNombre());
		p.put("apellido", paciente.getPrimerApellido());
		p.put("rif", paciente.getRif());
		p.put("ficha", paciente.getFicha());
		p.put("nacimiento", formatoFecha.format(paciente.getFechaNacimiento()));
		p.put("direccion", paciente.getDireccion());
		p.put("telefono1", paciente.getTelefono1());
		p.put("telefono2", paciente.getTelefono2());
		p.put("ciudad", paciente.getCiudadVivienda().getNombre());
		if (paciente.getEstadoCivil() != null)
			p.put("civil", paciente.getEstadoCivil().getNombre());
		else
			p.put("civil", "N/A");
		p.put("grupo", paciente.getGrupoSanguineo());
		if (paciente.getFichaMaestra() != null) {
			Ficha ficha = paciente.getFichaMaestra();
			p.put("camisa", ficha.getTallaCamisa());
			p.put("goma", ficha.getTallaGoma());
			p.put("pantalon", ficha.getTallaPantalon());
			p.put("seguridad", ficha.getTallaBotas());
			if (ficha.isViviendaPropia())
				p.put("casa", "SI");
			else
				p.put("casa", "NO");
			p.put("tipo", ficha.getTipoVivienda());
		} else {
			p.put("camisa", "N/A");
			p.put("goma", "N/A");
			p.put("pantalon", "N/A");
			p.put("seguridad", "N/A");
			p.put("casa", "N/A");
			p.put("tipo", "N/A");
		}

		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RFormatoActualizacion.jasper"));
		} catch (JRException e) {
			e.printStackTrace();
		}
		try {
			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(lista));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return fichero;
	}
}
