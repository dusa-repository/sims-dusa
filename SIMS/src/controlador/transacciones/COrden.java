package controlador.transacciones;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import modelo.control.ControlOrden;
import modelo.inventario.F4101;
import modelo.inventario.F4105;
import modelo.inventario.F4105PK;
import modelo.inventario.F4211;
import modelo.inventario.F4211PK;
import modelo.maestros.Especialista;
import modelo.maestros.Examen;
import modelo.maestros.Medicina;
import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;
import modelo.maestros.Proveedor;
import modelo.maestros.ProveedorExamen;
import modelo.maestros.ProveedorServicio;
import modelo.maestros.ServicioExterno;
import modelo.seguridad.Arbol;
import modelo.seguridad.Usuario;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenEspecialista;
import modelo.transacciones.OrdenExamen;
import modelo.transacciones.OrdenMedicina;
import modelo.transacciones.OrdenServicioExterno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.json.JSONException;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.West;

import arbol.CArbol;
import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class COrden extends CGenerico {

	private static final long serialVersionUID = 5657394783043114204L;

	@Wire
	private Tab tabCarga;
	@Wire
	private Tab tabResumen;
	@Wire
	private Datebox dtbFecha;
	@Wire
	private Combobox cmbMotivo;
	@Wire
	private Combobox cmbDoctor;
	@Wire
	private Textbox txtBuscadorExamen;
	@Wire
	private Textbox txtBuscadorEspecialista;
	@Wire
	private Textbox txtBuscadorMedicina;
	@Wire
	private Textbox txtBuscadorServicioExterno;
	@Wire
	private Textbox txtCedula;
	@Wire
	private Combobox cmbPrioridadMedicina;
	@Wire
	private Combobox cmbPrioridadEspecialista;
	@Wire
	private Combobox cmbPrioridadServicio;
	@Wire
	private Combobox cmbPrioridadExamen;
	@Wire
	private Combobox cmbProveedor;
	@Wire
	private Combobox cmbTratamiento;
	@Wire
	private Datebox dtbValido;
	@Wire
	private Div botoneraOrden;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Div divCatalogoPacientes;
	@Wire
	private Div divOrden;
	@Wire
	private Listbox ltbMedicinas;
	@Wire
	private Listbox ltbMedicinasAgregadas;
	@Wire
	private Listbox ltbExamenes;
	@Wire
	private Listbox ltbExamenesAgregados;
	@Wire
	private Listbox ltbServicioExterno;
	@Wire
	private Listbox ltbServicioExternoAgregados;
	@Wire
	private Listbox ltbEspecialistas;
	@Wire
	private Listbox ltbEspecialistasAgregados;
	@Wire
	private Listbox ltbResumenMedicinas;
	@Wire
	private Listbox ltbResumenExamenes;
	@Wire
	private Listbox ltbResumenEspecialistas;
	@Wire
	private Listbox ltbResumenServicios;
	@Wire
	private Listbox ltbCargaFamiliar;
	@Wire
	private Listbox ltbOrdenes;
	@Wire
	private Image imagenPaciente;
	@Wire
	private Label lblNombres;
	@Wire
	private Label lblCedula;
	@Wire
	private Label lblApellidos;
	@Wire
	private Label lblEmpresa;
	@Wire
	private Label lblFicha;
	@Wire
	private Label lblTrabajador;
	@Wire
	private Label lblFechaNac;
	@Wire
	private Label lblLugarNac;
	@Wire
	private Label lblAlergias;
	@Wire
	private Label lblAlergico;
	@Wire
	private Label lblLentes;
	@Wire
	private Label lblEdad;
	@Wire
	private Label lblSexo;
	@Wire
	private Label lblEstadoCivil;
	@Wire
	private Label lblGrupoSanguineo;
	@Wire
	private Label lblMano;
	@Wire
	private Label lblEstatura;
	@Wire
	private Label lblPeso;
	@Wire
	private Label lblDiscapacidad;
	@Wire
	private Label lblOrigen;
	@Wire
	private Label lblTipoDiscapacidad;
	@Wire
	private Label lblObservacionDiscapacidad;
	@Wire
	private Label lblCargo;
	@Wire
	private Label lblCiudad;
	@Wire
	private Label lblDireccion;
	@Wire
	private Label lblTelefono1;
	@Wire
	private Label lblTelefono2;
	@Wire
	private Label lblCorreo;
	@Wire
	private Label lblNombresE;
	@Wire
	private Label lblApellidosE;
	@Wire
	private Label lblParentesco;
	@Wire
	private Label lblTelefono1E;
	@Wire
	private Label lblTelefono2E;
	@Wire
	private Label lblArea;
	@Wire
	private Button btnGenerarOrden;
	@Wire
	private Button btnGenerarReferencia;
	@Wire
	private Button btnGenerarRecipe;
	@Wire
	private Button btnGenerarOrdenServicios;
	@Wire
	private Button btnBuscarPacienteCita;
	@Wire
	private Textbox txtCedulaCita;

	List<Medicina> medicinasDisponibles = new ArrayList<Medicina>();
	List<OrdenMedicina> medicinasAgregadas = new ArrayList<OrdenMedicina>();
	List<OrdenMedicina> medicinasResumen = new ArrayList<OrdenMedicina>();

	List<Examen> examenesDisponibles = new ArrayList<Examen>();
	List<OrdenExamen> examenesAgregado = new ArrayList<OrdenExamen>();
	List<OrdenExamen> examenesResumen = new ArrayList<OrdenExamen>();

	List<Especialista> especialistasDisponibles = new ArrayList<Especialista>();
	List<OrdenEspecialista> especialistasAgregados = new ArrayList<OrdenEspecialista>();
	List<OrdenEspecialista> especialistasResumen = new ArrayList<OrdenEspecialista>();

	List<ServicioExterno> serviciosDisponibles = new ArrayList<ServicioExterno>();
	List<OrdenServicioExterno> serviciosAgregados = new ArrayList<OrdenServicioExterno>();
	List<OrdenServicioExterno> serviciosResumen = new ArrayList<OrdenServicioExterno>();

	String idPaciente = "";
	long idOrden = 0;
	Catalogo<Paciente> catalogoPaciente;

	Buscar<Medicina> buscarMedicina;
	Buscar<Examen> buscarExamen;
	Buscar<Especialista> buscarEspecialista;
	Buscar<ServicioExterno> buscarServicio;
	ListModelList<Proveedor> proveedores;
	West west;
	List<Listbox> listas = new ArrayList<Listbox>();
	Botonera botonera;

	private CArbol cArbol = new CArbol();
	private Long idControlOrden = null;
	private String idBoton;

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divOrden.getParent();
		Tabbox tabox = (Tabbox) divOrden.getParent().getParent().getParent()
				.getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				west = (West) mapa.get("west");
				mapa.clear();
				mapa = null;
			}
		}
		listas.add(ltbOrdenes);
		listas.add(ltbEspecialistas);
		listas.add(ltbEspecialistasAgregados);
		listas.add(ltbExamenes);
		listas.add(ltbExamenesAgregados);
		listas.add(ltbMedicinas);
		listas.add(ltbMedicinasAgregadas);
		listas.add(ltbServicioExterno);
		listas.add(ltbServicioExternoAgregados);
		llenarListas();
		listasMultiples();
		buscadorMedicina();
		buscadorServicio();
		buscadorExamen();
		buscadorEspecialista();
		cmbDoctor.setModel(new ListModelList<Usuario>(servicioUsuario
				.buscarDoctores()));
		botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divOrden, "Ordenes", tabs);
				west.setOpen(true);
			}

			@Override
			public void limpiar() {
				limpiarCampos();
			}

			@Override
			public void guardar() {
				if (validar()) {
					if (idOrden != 0) {
						Orden orden = servicioOrden.buscar(idOrden);
						servicioOrdenExamen.borrarExamenesDeOrden(orden);
						servicioOrdenMedicina.borrarMedicinasDeOrden(orden);
						servicioOrdenEspecialista
								.borrarEspecialistasDeOrden(orden);
						servicioOrdenServicio.borrarServiciosDeOrden(orden);
					}
					Timestamp fechaOrden = new Timestamp(dtbFecha.getValue()
							.getTime());
					Long idMotivo = Long.parseLong(cmbMotivo.getSelectedItem()
							.getContext());
					MotivoCita motivo = servicioMotivoCita.buscar(idMotivo);
					Paciente paciente = servicioPaciente
							.buscarPorCedula(idPaciente);
					String doctor = cmbDoctor.getValue();
					Orden orden = new Orden(idOrden, paciente, doctor,
							fechaOrden, motivo, metodoHora(), metodoFecha(),
							nombreUsuarioSesion());
					servicioOrden.guardar(orden);
					if (idOrden != 0)
						orden = servicioOrden.buscar(idOrden);
					else
						orden = servicioOrden.buscarUltima();
					guardarMedicinas(orden);
					guardarExamenes(orden);
					guardarEspecialistas(orden);
					guardarEstudios(orden);
					Mensaje.mensajeInformacion("Registro Guardado Exitosamente, Ahora puede Generar las Ordenes Respectivas");
					idOrden = orden.getIdOrden();
					botonera.getChildren().get(0).setVisible(false);
					btnGenerarOrden.setVisible(true);
					btnGenerarReferencia.setVisible(true);
					btnGenerarOrdenServicios.setVisible(true);
					btnGenerarRecipe.setVisible(true);
					actualizarOrdenes(paciente);
					if (idControlOrden != null) {
						ControlOrden control = servicioControlOrden
								.buscar(idControlOrden);
						control.setEstado("Aprobado");
						control.setHoraAuditoria(metodoHora());
						control.setFechaAuditoria(metodoFecha());
						control.setUsuarioAuditoria(nombreUsuarioSesion());
						servicioControlOrden.guardar(control);
						idControlOrden = null;
					}
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botoneraOrden.appendChild(botonera);
	}

	protected void actualizarOrdenes(Paciente paciente) {
		List<Orden> ordenes = servicioOrden.buscarPorPaciente(paciente);
		ltbOrdenes.setModel(new ListModelList<Orden>(ordenes));
		ltbOrdenes.setCheckmark(false);
		ltbOrdenes.setCheckmark(true);
	}

	protected void guardarMedicinas(Orden orden) {
		List<OrdenMedicina> listaMedicina = new ArrayList<OrdenMedicina>();
		Timestamp tiempo = new Timestamp(dtbValido.getValue().getTime());
		String prioridad = cmbPrioridadMedicina.getValue();
		String tratamiento = cmbTratamiento.getValue();
		for (int i = 0; i < ltbMedicinasAgregadas.getItemCount(); i++) {
			Listitem listItem = ltbMedicinasAgregadas.getItemAtIndex(i);
			Integer idMedicina = ((Spinner) ((listItem.getChildren().get(3)))
					.getFirstChild()).getValue();
			Integer costo = ((Spinner) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			Medicina medicina = servicioMedicina.buscar(idMedicina);
			String valor = ((Textbox) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			OrdenMedicina ordenMedicina = new OrdenMedicina(orden, medicina,
					valor, prioridad, tratamiento, tiempo, costo);
			listaMedicina.add(ordenMedicina);
		}
		servicioOrdenMedicina.guardar(listaMedicina);
		if (!listaMedicina.isEmpty())
			guardarOrden(listaMedicina);

	}

	private void guardarOrden(List<OrdenMedicina> listaMedicina) {
		Orden orden = listaMedicina.get(0).getOrden();
		Long idC = orden.getIdOrden();
		F4211PK clave = new F4211PK();
		clave.setSddcto("MC");
		clave.setSddoco(nextNumber("7", "JE"));
		clave.setSdkcoo("DUSA");
		for (int i = 0; i < listaMedicina.size(); i++) {
			// Item
			F4101 f4101 = servicioF4101.buscarPorReferencia(listaMedicina
					.get(i).getMedicina().getIdReferencia());
			// Costo
			if (f4101 != null) {
				F4105 f4105 = new F4105();
				F4105PK claveCosto = new F4105PK();
				claveCosto.setCoitm(f4101.getImitm());
				claveCosto.setColedg("02");
				claveCosto.setColocn("");
				claveCosto.setColotn("");
				claveCosto.setComcu("Planta");
				f4105 = servicioF4105.buscar(claveCosto);
				Double costoIndividual = (double) 0;
				if (f4105 != null) {
					costoIndividual = f4105.getCouncs();
				}
				// Pedido
				F4211 f4211 = new F4211();
				Integer a = i + 1;
				clave.setSdlnid(a.doubleValue());
				f4211.setId(clave);
				f4211.setSdmcu("Planta");
				f4211.setSdkco("DUSA");
				f4211.setSddoc(idC.doubleValue());
				f4211.setSddrqj(transformarGregorianoAJulia(dtbFecha.getValue()));
				f4211.setSdzon(orden.getPaciente().getPrimerNombre() + " "
						+ orden.getPaciente().getPrimerApellido());
				f4211.setSditm(f4101.getImitm());
				f4211.setSduncs(costoIndividual);
				f4211.setSdemcu("Planta");
				// Cantidad por costo total getSdecst
				Integer cantidad = listaMedicina.get(i).getCantidad();
				f4211.setSdecst(costoIndividual * cantidad);
				// Cantidad getSdpqor
				f4211.setSdpqor(cantidad.doubleValue());
				f4211.setSdlocn("LOC001");
				// um del item
				f4211.setSduom(f4101.getImuom1());
				f4211.setSdspattn("Enviada");
				servicioF4211.guardar(f4211);
			}
		}
	}

	protected void guardarExamenes(Orden orden) {
		List<OrdenExamen> listaConsultaExamen = new ArrayList<OrdenExamen>();
		Proveedor proveedor = new Proveedor();
		ProveedorExamen proveedorExamen = new ProveedorExamen();
		String prioridad = cmbPrioridadExamen.getValue();
		for (int i = 0; i < ltbExamenesAgregados.getItemCount(); i++) {
			Listitem listItem = ltbExamenesAgregados.getItemAtIndex(i);
			Integer idExamen = ((Spinner) ((listItem.getChildren().get(3)))
					.getFirstChild()).getValue();
			Examen examen = servicioExamen.buscar(idExamen);
			String valor = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			String idProveedor = ((Combobox) ((listItem.getChildren().get(2)))
					.getFirstChild()).getSelectedItem().getContext();
			proveedor = servicioProveedor.buscar(Long.parseLong(idProveedor));
			proveedorExamen = servicioProveedorExamen
					.buscarPorProveedoryExamen(proveedor, examen);
			double precio = proveedorExamen.getCosto();
			OrdenExamen ordenExamen = new OrdenExamen(orden, examen, proveedor,
					valor, precio, prioridad);
			listaConsultaExamen.add(ordenExamen);
		}
		servicioOrdenExamen.guardar(listaConsultaExamen);
	}

	protected void guardarEspecialistas(Orden orden) {
		List<OrdenEspecialista> listaConsultaEspecialista = new ArrayList<OrdenEspecialista>();
		String prioridad = cmbPrioridadEspecialista.getValue();
		for (int i = 0; i < ltbEspecialistasAgregados.getItemCount(); i++) {
			Listitem listItem = ltbEspecialistasAgregados.getItemAtIndex(i);
			Integer id = ((Spinner) ((listItem.getChildren().get(4)))
					.getFirstChild()).getValue();
			Especialista especialista = servicioEspecialista.buscar(String
					.valueOf(id));
			double valor = ((Doublespinner) ((listItem.getChildren().get(3)))
					.getFirstChild()).getValue();
			String observacion = ((Textbox) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			OrdenEspecialista ordenEspecialista = new OrdenEspecialista(orden,
					especialista, valor, observacion, prioridad);
			listaConsultaEspecialista.add(ordenEspecialista);
		}
		servicioOrdenEspecialista.guardar(listaConsultaEspecialista);
	}

	protected void guardarEstudios(Orden orden) {

		List<OrdenServicioExterno> listaServicioExterno = new ArrayList<OrdenServicioExterno>();
		ProveedorServicio proveedorServicio = new ProveedorServicio();
		String prioridad = cmbPrioridadServicio.getValue();
		for (int i = 0; i < ltbServicioExternoAgregados.getItemCount(); i++) {
			Listitem listItem = ltbServicioExternoAgregados.getItemAtIndex(i);
			Integer id = ((Spinner) ((listItem.getChildren().get(4)))
					.getFirstChild()).getValue();
			ServicioExterno servicioExterno = servicioServicioExterno
					.buscar(id);
			String idProveedor = ((Combobox) ((listItem.getChildren().get(2)))
					.getFirstChild()).getSelectedItem().getContext();
			Proveedor proveedor = servicioProveedor.buscar(Long
					.parseLong(idProveedor));
			String observacion = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			proveedorServicio = servicioProveedorServicio
					.buscarPorCodigoDeAmbos(proveedor.getIdProveedor(), id);
			double valor = proveedorServicio.getCosto();
			OrdenServicioExterno ordenServicio = new OrdenServicioExterno(
					orden, servicioExterno, proveedor, valor, observacion,
					prioridad);
			listaServicioExterno.add(ordenServicio);
		}
		servicioOrdenServicio.guardar(listaServicioExterno);
	}

	protected boolean validar() {
		if (txtCedula.getText().compareTo("") == 0) {
			Mensaje.mensajeError("Debe Seleccionar un Paciente");
			return false;
		} else {
			if (dtbFecha.getText().compareTo("") == 0
					|| cmbDoctor.getText().compareTo("") == 0
					|| cmbMotivo.getText().compareTo("") == 0) {
				Mensaje.mensajeError(Mensaje.camposVacios);
				return false;
			} else {
				if (!agregarMedicina()) {
					Mensaje.mensajeError("Debe Llenar Todos los Campos de la Lista de Medicinas");
					return false;
				} else {
					if (!agregarExamen()) {
						Mensaje.mensajeError("Debe Llenar Todos los Campos de la Lista de Examenes");
						return false;
					} else {
						if (!agregarEspecialista()) {
							Mensaje.mensajeError("Debe Llenar Todos los Campos de la Lista de Especialistas");
							return false;
						} else {
							if (!agregarServicio()) {
								Mensaje.mensajeError("Debe Llenar Todos los Campos de la Lista de Servicios Externos");
								return false;
							} else {
								if (ltbMedicinasAgregadas.getItemCount() != 0
										&& cmbPrioridadMedicina.getText()
												.compareTo("") == 0) {
									Mensaje.mensajeError("Debe Seleccionar la Prioridad del Recipe");
									return false;
								} else {
									if (ltbMedicinasAgregadas.getItemCount() != 0
											&& cmbTratamiento.getText()
													.compareTo("") == 0) {
										Mensaje.mensajeError("Debe indicar el tipo de tratamiento");
										return false;
									} else {
										if (ltbExamenesAgregados.getItemCount() != 0
												&& cmbProveedor.getText()
														.compareTo("") == 0) {
											Mensaje.mensajeError("Debe Seleccionar el Laboratorio que Realizara los Examenes");
											return false;
										} else {
											if (ltbServicioExternoAgregados
													.getItemCount() != 0
													&& cmbPrioridadServicio
															.getText()
															.compareTo("") == 0) {
												Mensaje.mensajeError("Debe Seleccionar la Prioridad de la orden de los Estudios Externos");
												return false;
											} else {
												if (ltbExamenesAgregados
														.getItemCount() != 0
														&& cmbPrioridadExamen
																.getText()
																.compareTo("") == 0) {
													Mensaje.mensajeError("Debe Seleccionar la Prioridad de la orden de los Examenes");
													return false;
												} else {
													if (ltbEspecialistasAgregados
															.getItemCount() != 0
															&& cmbPrioridadEspecialista
																	.getText()
																	.compareTo(
																			"") == 0) {
														Mensaje.mensajeError("Debe Seleccionar la Prioridad de la orden de los Especialistas");
														return false;
													} else
														return true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public ListModelList<Proveedor> getProveedores() {
		proveedores = new ListModelList<Proveedor>(
				servicioProveedor.buscarTodos());
		return proveedores;
	}

	protected void limpiarCampos() {
		if (!botonera.getChildren().get(0).isVisible()) {
			botonera.getChildren().get(0).setVisible(true);
		}
		idControlOrden = null;
		btnGenerarOrden.setVisible(false);
		btnGenerarRecipe.setVisible(false);
		btnGenerarReferencia.setVisible(false);
		btnGenerarOrdenServicios.setVisible(false);
		txtCedula.setValue("");
		dtbFecha.setValue(fecha);
		cmbDoctor.setValue("");
		cmbMotivo.setValue("");
		cmbPrioridadEspecialista.setValue("");
		cmbPrioridadExamen.setValue("");
		cmbPrioridadMedicina.setValue("");
		cmbPrioridadServicio.setValue("");
		cmbProveedor.setValue("");
		idPaciente = "";
		idOrden = 0;
		limpiarListBox();
		limpiarListas();
		llenarListas();
		lblNombres.setValue("");
		lblCedula.setValue("");
		lblApellidos.setValue("");
		lblEmpresa.setValue("");
		imagenPaciente.setVisible(false);
		lblFicha.setValue("");
		lblAlergico.setValue("");
		lblLugarNac.setValue("");
		lblSexo.setValue("");
		lblEstadoCivil.setValue("");
		lblGrupoSanguineo.setValue("");
		lblMano.setValue("");
		lblOrigen.setValue("");
		lblTipoDiscapacidad.setValue("");
		lblObservacionDiscapacidad.setValue("");
		lblCargo.setValue("");
		lblDireccion.setValue("");
		lblTelefono1.setValue("");
		lblTelefono2.setValue("");
		lblCorreo.setValue("");
		lblArea.setValue("");
		lblNombresE.setValue("");
		lblApellidosE.setValue("");
		lblTelefono1E.setValue("");
		lblTelefono2E.setValue("");
		lblParentesco.setValue("");
		lblPeso.setValue("");
		lblEdad.setValue("");
		lblEstatura.setValue("");
		lblCiudad.setValue("");
		lblAlergias.setValue("");
		lblTrabajador.setValue("");
		lblDiscapacidad.setValue("");
		lblLentes.setValue("");
	}

	private void buscadorEspecialista() {
		buscarEspecialista = new Buscar<Especialista>(ltbEspecialistas,
				txtBuscadorEspecialista) {
			@Override
			protected List<Especialista> buscar(String valor) {
				List<Especialista> presentacionesFiltradas = new ArrayList<Especialista>();
				List<Especialista> presentaciones = servicioEspecialista
						.filtroTodo(valor);
				for (int i = 0; i < especialistasDisponibles.size(); i++) {
					Especialista especialista = especialistasDisponibles.get(i);
					for (int j = 0; j < presentaciones.size(); j++) {
						if (especialista.getCedula().equals(
								presentaciones.get(j).getCedula()))
							presentacionesFiltradas.add(especialista);
					}
				}
				return presentacionesFiltradas;
			}
		};
	}

	private void buscadorExamen() {
		buscarExamen = new Buscar<Examen>(ltbExamenes, txtBuscadorExamen) {

			@Override
			protected List<Examen> buscar(String valor) {
				List<Examen> examenesFiltradas = new ArrayList<Examen>();
				List<Examen> examenes = servicioExamen.filtroNombre(valor);
				for (int i = 0; i < examenesDisponibles.size(); i++) {
					Examen examen = examenesDisponibles.get(i);
					for (int j = 0; j < examenes.size(); j++) {
						if (examen.getIdExamen() == examenes.get(j)
								.getIdExamen())
							examenesFiltradas.add(examen);
					}
				}
				return examenesFiltradas;
			}
		};
	}

	private void buscadorServicio() {
		buscarServicio = new Buscar<ServicioExterno>(ltbServicioExterno,
				txtBuscadorServicioExterno) {

			@Override
			protected List<ServicioExterno> buscar(String valor) {
				List<ServicioExterno> serviciosFiltradas = new ArrayList<ServicioExterno>();
				List<ServicioExterno> servicios = servicioServicioExterno
						.filtroNombre(valor);
				for (int i = 0; i < serviciosDisponibles.size(); i++) {
					ServicioExterno servicio = serviciosDisponibles.get(i);
					for (int j = 0; j < servicios.size(); j++) {
						if (servicio.getIdServicioExterno() == servicios.get(j)
								.getIdServicioExterno())
							serviciosFiltradas.add(servicio);
					}
				}
				return serviciosFiltradas;
			}
		};
	}

	private void buscadorMedicina() {
		buscarMedicina = new Buscar<Medicina>(ltbMedicinas, txtBuscadorMedicina) {
			@Override
			protected List<Medicina> buscar(String valor) {
				List<Medicina> medicinasFiltradas = new ArrayList<Medicina>();
				List<Medicina> medicinas = servicioMedicina.filtroNombre(valor);
				for (int i = 0; i < medicinasDisponibles.size(); i++) {
					Medicina medicina = medicinasDisponibles.get(i);
					for (int j = 0; j < medicinas.size(); j++) {
						if (medicina.getIdMedicina() == medicinas.get(j)
								.getIdMedicina())
							medicinasFiltradas.add(medicina);
					}
				}
				return medicinasFiltradas;
			}
		};
	}

	private void llenarListas() {
		Orden orden = servicioOrden.buscar(idOrden);
		Paciente paciente = servicioPaciente.buscarPorCedula(String
				.valueOf(idPaciente));
		List<Paciente> carga = servicioPaciente.buscarParientes(String
				.valueOf(idPaciente));
		if (!idPaciente.equals(""))
			ltbCargaFamiliar.setModel(new ListModelList<Paciente>(carga));

		medicinasDisponibles = servicioMedicina.buscarDisponiblesOrden(orden,
				paciente);
		ltbMedicinas
				.setModel(new ListModelList<Medicina>(medicinasDisponibles));
		medicinasAgregadas = servicioOrdenMedicina.buscarPorOrden(orden);
		ltbMedicinasAgregadas.setModel(new ListModelList<OrdenMedicina>(
				medicinasAgregadas));
		medicinasResumen = medicinasAgregadas;
		ltbResumenMedicinas.setModel(new ListModelList<OrdenMedicina>(
				medicinasResumen));
		if (!medicinasAgregadas.isEmpty())
			cmbPrioridadMedicina.setValue(medicinasAgregadas.get(0)
					.getPrioridad());

		examenesDisponibles = servicioExamen.buscarDisponiblesOrden(orden);
		ltbExamenes.setModel(new ListModelList<Examen>(examenesDisponibles));
		examenesAgregado = servicioOrdenExamen.buscarPorOrden(orden);
		ltbExamenesAgregados.setModel(new ListModelList<OrdenExamen>(
				examenesAgregado));
		examenesResumen = examenesAgregado;
		ltbResumenExamenes.setModel(new ListModelList<OrdenExamen>(
				examenesResumen));
		if (!examenesAgregado.isEmpty()) {
			cmbPrioridadExamen.setValue(examenesAgregado.get(0).getPrioridad());
			if (examenesAgregado.get(0).getProveedor() != null)
				cmbProveedor.setValue(examenesAgregado.get(0).getProveedor()
						.getNombre());
		}

		especialistasDisponibles = servicioEspecialista
				.buscarDisponiblesOrden(orden);
		for (int i = 0; i < especialistasDisponibles.size(); i++) {

			String nombre = especialistasDisponibles.get(i).getNombre();
			String apellido = especialistasDisponibles.get(i).getApellido();
			Especialista especialista = especialistasDisponibles.get(i);
			especialista.setNombre(nombre + " " + apellido);
		}
		ltbEspecialistas.setModel(new ListModelList<Especialista>(
				especialistasDisponibles));
		especialistasAgregados = servicioOrdenEspecialista
				.buscarPorOrden(orden);
		ltbEspecialistasAgregados
				.setModel(new ListModelList<OrdenEspecialista>(
						especialistasAgregados));
		especialistasResumen = especialistasAgregados;
		ltbResumenEspecialistas.setModel(new ListModelList<OrdenEspecialista>(
				especialistasResumen));
		if (!especialistasAgregados.isEmpty())
			cmbPrioridadEspecialista.setValue(especialistasAgregados.get(0)
					.getPrioridad());

		serviciosDisponibles = servicioServicioExterno
				.buscarDisponiblesOrden(orden);
		ltbServicioExterno.setModel(new ListModelList<ServicioExterno>(
				serviciosDisponibles));
		serviciosAgregados = servicioOrdenServicio.buscarPorOrden(orden);
		ltbServicioExternoAgregados
				.setModel(new ListModelList<OrdenServicioExterno>(
						serviciosAgregados));
		serviciosResumen = serviciosAgregados;
		ltbResumenServicios.setModel(new ListModelList<OrdenServicioExterno>(
				serviciosResumen));
		if (!serviciosAgregados.isEmpty())
			cmbPrioridadServicio.setValue(serviciosAgregados.get(0)
					.getPrioridad());

		listasMultiples();
	}

	private void listasMultiples() {

		for (int i = 0; i < listas.size(); i++) {
			if (!listas.get(i).getId().equals("ltbOrdenes")) {
				listas.get(i).setMultiple(false);
				listas.get(i).setCheckmark(false);
				listas.get(i).setMultiple(true);
				listas.get(i).setCheckmark(true);
			} else {
				listas.get(i).setCheckmark(false);
				listas.get(i).setCheckmark(true);
			}
		}
	}

	public void llenarProveedor(Combobox a) {

		if (a.isOpen()) {

			Examen examen = new Examen();
			List<Proveedor> proveedorExamen = new ArrayList<Proveedor>();
			Spinner spin = (Spinner) a.getParent().getParent().getChildren()
					.get(3).getFirstChild();
			examen = servicioExamen.buscar(spin.getValue());
			proveedorExamen = servicioProveedor
					.buscarPorProveedoresPorExamen(examen);
			if (!proveedorExamen.isEmpty())
				a.setModel(new ListModelList<Proveedor>(proveedorExamen));
			else
				Messagebox.show(
						"El examen no es realizado por ningun Proveedor",
						"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);

		}
	}

	public void llenarProveedorServicio(Combobox a) {

		if (a.isOpen()) {

			ServicioExterno servicio = new ServicioExterno();
			List<Proveedor> proveedorServicio = new ArrayList<Proveedor>();
			Spinner spin = (Spinner) a.getParent().getParent().getChildren()
					.get(4).getFirstChild();
			servicio = servicioServicioExterno.buscar(spin.getValue());
			proveedorServicio = servicioProveedor
					.buscarPorProveedoresPorServicio(servicio);
			if (!proveedorServicio.isEmpty())
				a.setModel(new ListModelList<Proveedor>(proveedorServicio));
			else
				Messagebox.show(
						"El estudio no es realizado por ningun Proveedor",
						"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);

		}
	}

	@Listen("onSelect = #cmbProveedor")
	public boolean validarProveedor() {
		Proveedor proveedor = null;
		Examen examen = null;
		String examenes = "\n";
		if (cmbProveedor.getText().compareTo("") != 0)
			proveedor = servicioProveedor.buscar(Long.parseLong(cmbProveedor
					.getSelectedItem().getContext()));
		boolean error = false;
		if (ltbExamenesAgregados.getItemCount() != 0) {
			ProveedorExamen proveedorExamen = new ProveedorExamen();
			for (int i = 0; i < ltbExamenesAgregados.getItemCount(); i++) {
				Listitem listItem = ltbExamenesAgregados.getItemAtIndex(i);
				Integer idExamen = ((Spinner) ((listItem.getChildren().get(3)))
						.getFirstChild()).getValue();
				examen = servicioExamen.buscar(idExamen);
				proveedorExamen = servicioProveedorExamen
						.buscarPorProveedoryExamen(proveedor, examen);
				if (proveedorExamen == null) {
					error = true;
					examenes += "-" + examen.getNombre() + "\n";
				} else {
					Combobox combo = ((Combobox) ((listItem.getChildren()
							.get(2))).getFirstChild());
					if (combo.getSelectedItem() == null) {
						combo.setValue(proveedorExamen.getProveedor()
								.getNombre());
						combo.getSelectedItem().setContext(
								String.valueOf(proveedorExamen.getProveedor()
										.getIdProveedor()));
					}
				}
			}
			if (error) {
				cmbProveedor.setFocus(true);
				Messagebox.show(
						"El proveedor seleccionado no realiza los(el) examen(es):   "
								+ examenes + "Por favor modifiquelos",
						"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			} else
				return true;
		} else
			return true;
	}

	@Listen("onClick = #btnAgregarMedicinas")
	public boolean agregarMedicina() {
		boolean falta = false;
		medicinasResumen.clear();
		if (ltbMedicinasAgregadas.getItemCount() != 0) {
			OrdenMedicina consultaMedicina = new OrdenMedicina();
			List<Listitem> listItem2 = ltbMedicinasAgregadas.getItems();
			for (int i = 0; i < ltbMedicinasAgregadas.getItemCount(); i++) {
				Listitem listItem = ltbMedicinasAgregadas.getItemAtIndex(i);
				consultaMedicina = new OrdenMedicina();
				consultaMedicina = listItem2.get(i).getValue();
				String valor = ((Textbox) ((listItem.getChildren().get(2)))
						.getFirstChild()).getValue();
				Integer cantidad = ((Spinner) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				if (valor.equals("") || cantidad == 0) {
					falta = true;
				}
				consultaMedicina.setDosis(valor);
				medicinasResumen.add(consultaMedicina);
			}
			ltbResumenMedicinas.setModel(new ListModelList<OrdenMedicina>(
					medicinasResumen));
		}
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarExamenes")
	public boolean agregarExamen() {
		boolean falta = false;
		examenesResumen.clear();
		if (ltbExamenesAgregados.getItemCount() != 0) {
			OrdenExamen consulta = new OrdenExamen();
			List<Listitem> listItem2 = ltbExamenesAgregados.getItems();
			for (int i = 0; i < ltbExamenesAgregados.getItemCount(); i++) {
				Listitem listItem = ltbExamenesAgregados.getItemAtIndex(i);
				consulta = new OrdenExamen();
				consulta = listItem2.get(i).getValue();
				String valor = ((Textbox) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				consulta.setObservacion(valor);
				String proveedor = "";
				if (((Combobox) ((listItem.getChildren().get(2)))
						.getFirstChild()).getSelectedItem() == null) {
					falta = true;
				} else
					proveedor = ((Combobox) ((listItem.getChildren().get(2)))
							.getFirstChild()).getSelectedItem().getContext();
				examenesResumen.add(consulta);
			}
			ltbResumenExamenes.setModel(new ListModelList<OrdenExamen>(
					examenesResumen));
		}
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarEspecialistas")
	public boolean agregarEspecialista() {
		boolean falta = false;
		especialistasResumen.clear();
		if (ltbEspecialistasAgregados.getItemCount() != 0) {
			OrdenEspecialista consulta = new OrdenEspecialista();
			List<Listitem> listItem2 = ltbEspecialistasAgregados.getItems();
			for (int i = 0; i < ltbEspecialistasAgregados.getItemCount(); i++) {
				Listitem listItem = ltbEspecialistasAgregados.getItemAtIndex(i);
				consulta = new OrdenEspecialista();
				consulta = listItem.getValue();
				especialistasResumen.add(consulta);
			}
			ltbResumenEspecialistas
					.setModel(new ListModelList<OrdenEspecialista>(
							especialistasResumen));
		}
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarServicios")
	public boolean agregarServicio() {
		boolean falta = false;
		serviciosResumen.clear();
		if (ltbServicioExternoAgregados.getItemCount() != 0) {
			OrdenServicioExterno consulta = new OrdenServicioExterno();
			List<Listitem> listItem2 = ltbServicioExternoAgregados.getItems();
			for (int i = 0; i < ltbServicioExternoAgregados.getItemCount(); i++) {
				Listitem listItem = ltbServicioExternoAgregados
						.getItemAtIndex(i);
				consulta = new OrdenServicioExterno();
				consulta = listItem2.get(i).getValue();
				String valor = ((Textbox) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				String proveedor = "";
				if (((Combobox) ((listItem.getChildren().get(2)))
						.getFirstChild()).getSelectedItem() == null) {
					falta = true;
				} else
					proveedor = ((Combobox) ((listItem.getChildren().get(2)))
							.getFirstChild()).getSelectedItem().getContext();

				consulta.setObservacion(valor);
				serviciosResumen.add(consulta);
			}
			ltbResumenServicios
					.setModel(new ListModelList<OrdenServicioExterno>(
							serviciosResumen));
		}
		if (falta)
			return false;
		else
			return true;
	}

	public void limpiarListas() {
		List<List<?>> limpiador = new ArrayList<List<?>>();
		limpiador.add(especialistasAgregados);
		limpiador.add(especialistasDisponibles);
		limpiador.add(especialistasResumen);
		limpiador.add(examenesAgregado);
		limpiador.add(examenesDisponibles);
		limpiador.add(examenesResumen);
		limpiador.add(medicinasAgregadas);
		limpiador.add(medicinasDisponibles);
		limpiador.add(medicinasResumen);
		limpiador.add(serviciosAgregados);
		limpiador.add(serviciosDisponibles);
		limpiador.add(serviciosResumen);
		for (int q = 0; q < limpiador.size(); q++) {
			limpiador.get(q).clear();
		}
	}

	public void limpiarListBox() {
		for (int i = 0; i < listas.size(); i++) {
			listas.get(i).getItems().clear();
		}
	}

	@Listen("onOpen = #cmbProveedor")
	public void abrirProveedor() {
		cmbProveedor.setModel(new ListModelList<Proveedor>(servicioProveedor
				.buscarTodos()));
	}

	/* Abre la vista de Pais */
	@Listen("onClick = #btnAbrirProveedor")
	public void abrirPais() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Proveedor");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	@Listen("onOpen = #cmbMotivo")
	public void llenarComboMotivo() {
		cmbMotivo.setModel(new ListModelList<MotivoCita>(servicioMotivoCita
				.buscarTodosDeTipo("Orden")));
	}

	@Listen("onClick = #btnAbrirMotivo")
	public void abrirMotivo() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Motivo");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	@Listen("onClick = #btnAbrirEspecialista")
	public void divEspecialista() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "orden");
		map.put("lista", especialistasDisponibles);
		map.put("listbox", ltbEspecialistas);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		List<Arbol> arboles = servicioArbol
				.buscarPorNombreArbol("Especialista");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	@Listen("onClick = #btnAbrirServicioExterno")
	public void divServicio() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "orden");
		map.put("lista", serviciosDisponibles);
		map.put("listbox", ltbServicioExterno);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		List<Arbol> arboles = servicioArbol
				.buscarPorNombreArbol("Estudios Externos");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	@Listen("onClick = #btnAbrirExamen")
	public void divExamen() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "orden");
		map.put("lista", examenesDisponibles);
		map.put("listbox", ltbExamenes);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Examen");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	public void recibir(List<Especialista> lista, Listbox l) {
		ltbEspecialistas = l;
		especialistasDisponibles = lista;
		ltbEspecialistas.setModel(new ListModelList<Especialista>(
				especialistasDisponibles));
		ltbEspecialistas.setMultiple(false);
		ltbEspecialistas.setCheckmark(false);
		ltbEspecialistas.setMultiple(true);
		ltbEspecialistas.setCheckmark(true);
	}

	public void recibirExamen(List<Examen> lista, Listbox l) {
		ltbExamenes = l;
		examenesDisponibles = lista;
		ltbExamenes.setModel(new ListModelList<Examen>(examenesDisponibles));
		ltbExamenes.setMultiple(false);
		ltbExamenes.setCheckmark(false);
		ltbExamenes.setMultiple(true);
		ltbExamenes.setCheckmark(true);
	}

	public void recibirServicio(List<ServicioExterno> lista, Listbox l) {
		ltbServicioExterno = l;
		serviciosDisponibles = lista;
		ltbServicioExterno.setModel(new ListModelList<ServicioExterno>(
				serviciosDisponibles));
		ltbServicioExterno.setMultiple(false);
		ltbServicioExterno.setCheckmark(false);
		ltbServicioExterno.setMultiple(true);
		ltbServicioExterno.setCheckmark(true);
	}

	@Listen("onClick = #btnRefrescarServicio")
	public void refrescarServicio() {
		Orden orden = servicioOrden.buscar(idOrden);
		serviciosDisponibles = servicioServicioExterno
				.buscarDisponiblesOrden(orden);
		ltbServicioExterno.setModel(new ListModelList<ServicioExterno>(
				serviciosDisponibles));
		serviciosAgregados = servicioOrdenServicio.buscarPorOrden(orden);
		ltbServicioExternoAgregados
				.setModel(new ListModelList<OrdenServicioExterno>(
						serviciosAgregados));
		serviciosResumen = serviciosAgregados;
		ltbResumenServicios.setModel(new ListModelList<OrdenServicioExterno>(
				serviciosResumen));
		listasMultiples();
	}

	@Listen("onClick = #btnRefrescarMedicina")
	public void refrescarMedicina() {
		Orden orden = servicioOrden.buscar(idOrden);
		Paciente paciente = servicioPaciente.buscarPorCedula(String
				.valueOf(idPaciente));
		medicinasDisponibles = servicioMedicina.buscarDisponiblesOrden(orden,
				paciente);
		ltbMedicinas
				.setModel(new ListModelList<Medicina>(medicinasDisponibles));
		medicinasAgregadas = servicioOrdenMedicina.buscarPorOrden(orden);
		ltbMedicinasAgregadas.setModel(new ListModelList<OrdenMedicina>(
				medicinasAgregadas));
		medicinasResumen = medicinasAgregadas;
		ltbResumenMedicinas.setModel(new ListModelList<OrdenMedicina>(
				medicinasResumen));
		listasMultiples();

	}

	@Listen("onClick = #btnRefrescarExamen")
	public void refrescarExamen() {
		Orden orden = servicioOrden.buscar(idOrden);
		examenesDisponibles = servicioExamen.buscarDisponiblesOrden(orden);
		ltbExamenes.setModel(new ListModelList<Examen>(examenesDisponibles));
		examenesAgregado = servicioOrdenExamen.buscarPorOrden(orden);
		ltbExamenesAgregados.setModel(new ListModelList<OrdenExamen>(
				examenesAgregado));
		examenesResumen = examenesAgregado;
		ltbResumenExamenes.setModel(new ListModelList<OrdenExamen>(
				examenesResumen));
		listasMultiples();
	}

	@Listen("onClick = #btnRefrescarEspecialista")
	public void refrescarEspecialista() {
		Orden orden = servicioOrden.buscar(idOrden);
		especialistasDisponibles = servicioEspecialista
				.buscarDisponiblesOrden(orden);
		for (int i = 0; i < especialistasDisponibles.size(); i++) {

			String nombre = especialistasDisponibles.get(i).getNombre();
			String apellido = especialistasDisponibles.get(i).getApellido();
			Especialista especialista = especialistasDisponibles.get(i);
			especialista.setNombre(nombre + " " + apellido);
		}
		ltbEspecialistas.setModel(new ListModelList<Especialista>(
				especialistasDisponibles));
		especialistasAgregados = servicioOrdenEspecialista
				.buscarPorOrden(orden);
		ltbEspecialistasAgregados
				.setModel(new ListModelList<OrdenEspecialista>(
						especialistasAgregados));
		especialistasResumen = especialistasAgregados;
		ltbResumenEspecialistas.setModel(new ListModelList<OrdenEspecialista>(
				especialistasResumen));
		listasMultiples();
	}

	@Listen("onClick = #pasar1Medicina")
	public void derechaMedicina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbMedicinas.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Medicina medicina = listItem.get(i).getValue();
					medicinasDisponibles.remove(medicina);
					OrdenMedicina consultaMedicina = new OrdenMedicina();
					consultaMedicina.setMedicina(medicina);
					medicinasAgregadas.clear();
					for (int j = 0; j < ltbMedicinasAgregadas.getItemCount(); j++) {
						Listitem listItemj = ltbMedicinasAgregadas
								.getItemAtIndex(j);
						Integer idMedicina = ((Spinner) ((listItemj
								.getChildren().get(3))).getFirstChild())
								.getValue();
						Medicina medicinaj = servicioMedicina
								.buscar(idMedicina);
						String valor = ((Textbox) ((listItemj.getChildren()
								.get(2))).getFirstChild()).getValue();
						Integer costo = ((Spinner) ((listItemj.getChildren()
								.get(1))).getFirstChild()).getValue();
						OrdenMedicina consultaMedicinaj = new OrdenMedicina(
								null, medicinaj, valor, "", "", null, costo);
						medicinasAgregadas.add(consultaMedicinaj);
					}
					medicinasAgregadas.add(consultaMedicina);
					ltbMedicinasAgregadas
							.setModel(new ListModelList<OrdenMedicina>(
									medicinasAgregadas));
					ltbMedicinasAgregadas.renderAll();
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbMedicinas.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2Medicina")
	public void izquierdaMedicina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbMedicinasAgregadas.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					OrdenMedicina consultaMedicina = listItem2.get(i)
							.getValue();
					medicinasAgregadas.remove(consultaMedicina);
					medicinasDisponibles.add(consultaMedicina.getMedicina());
					ltbMedicinas.setModel(new ListModelList<Medicina>(
							medicinasDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbMedicinasAgregadas.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1Examen")
	public void derechaExamen() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbExamenes.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Examen examen = listItem.get(i).getValue();
					// examenesDisponibles.remove(examen);
					OrdenExamen consultaExamen = new OrdenExamen();
					consultaExamen.setExamen(examen);
					examenesAgregado.clear();
					for (int j = 0; j < ltbExamenesAgregados.getItemCount(); j++) {
						Listitem listItemj = ltbExamenesAgregados
								.getItemAtIndex(j);
						Integer idExamen = ((Spinner) ((listItemj.getChildren()
								.get(3))).getFirstChild()).getValue();
						String idProveedor = "0";
						if (((Combobox) ((listItemj.getChildren().get(2)))
								.getFirstChild()).getSelectedItem() != null)
							idProveedor = ((Combobox) ((listItemj.getChildren()
									.get(2))).getFirstChild())
									.getSelectedItem().getContext();
						Proveedor proveedor = servicioProveedor.buscar(Long
								.parseLong(idProveedor));
						Examen examenj = servicioExamen.buscar(idExamen);
						String valor = ((Textbox) ((listItemj.getChildren()
								.get(1))).getFirstChild()).getValue();
						double precio = 0;
						OrdenExamen consultaExamenj = new OrdenExamen(null,
								examenj, proveedor, valor, precio, "");
						examenesAgregado.add(consultaExamenj);
					}
					examenesAgregado.add(consultaExamen);
					ltbExamenesAgregados
							.setModel(new ListModelList<OrdenExamen>(
									examenesAgregado));
					ltbExamenesAgregados.renderAll();
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		// for (int i = 0; i < listitemEliminar.size(); i++) {
		// ltbExamenes.removeItemAt(listitemEliminar.get(i).getIndex());
		// }
		listasMultiples();
	}

	@Listen("onClick = #pasar2Examen")
	public void izquierdaExamen() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbExamenesAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					OrdenExamen consultaExamen = listItem2.get(i).getValue();
					examenesAgregado.remove(consultaExamen);
					// examenesDisponibles.add(consultaExamen.getExamen());
					// ltbExamenes.setModel(new ListModelList<Examen>(
					// examenesDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbExamenesAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1Especialista")
	public void derechaEspecialista() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbEspecialistas.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Especialista especialista = listItem.get(i).getValue();
					especialistasDisponibles.remove(especialista);
					OrdenEspecialista consultaEspecialista = new OrdenEspecialista();
					consultaEspecialista.setEspecialista(especialista);
					consultaEspecialista.setCosto(especialista.getCosto());
					especialistasAgregados.clear();
					for (int j = 0; j < ltbEspecialistasAgregados
							.getItemCount(); j++) {
						Listitem listItemj = ltbEspecialistasAgregados
								.getItemAtIndex(j);
						Integer id = ((Spinner) ((listItemj.getChildren()
								.get(4))).getFirstChild()).getValue();
						Especialista especialistaj = servicioEspecialista
								.buscar(String.valueOf(id));
						String nombre = especialistaj.getNombre();
						String apellido = especialistaj.getApellido();
						especialistaj.setNombre(nombre + " " + apellido);
						double valor = ((Doublespinner) ((listItemj
								.getChildren().get(3))).getFirstChild())
								.getValue();
						String observacion = ((Textbox) ((listItemj
								.getChildren().get(2))).getFirstChild())
								.getValue();
						OrdenEspecialista consultaEspecialistaj = new OrdenEspecialista(
								null, especialistaj, valor, observacion, "");
						especialistasAgregados.add(consultaEspecialistaj);
					}
					especialistasAgregados.add(consultaEspecialista);
					ltbEspecialistasAgregados
							.setModel(new ListModelList<OrdenEspecialista>(
									especialistasAgregados));
					ltbEspecialistasAgregados.renderAll();
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbEspecialistas.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2Especialista")
	public void izquierdaEspecialista() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbEspecialistasAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					OrdenEspecialista consultaEspecialista = listItem2.get(i)
							.getValue();
					especialistasAgregados.remove(consultaEspecialista);
					especialistasDisponibles.add(consultaEspecialista
							.getEspecialista());
					ltbEspecialistas.setModel(new ListModelList<Especialista>(
							especialistasDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbEspecialistasAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1ServicioExterno")
	public void derechaServicioExterno() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbServicioExterno.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					ServicioExterno servicio = listItem.get(i).getValue();
					// serviciosDisponibles.remove(servicio);
					OrdenServicioExterno consultaServicio = new OrdenServicioExterno();
					consultaServicio.setServicioExterno(servicio);
					serviciosAgregados.clear();
					for (int j = 0; j < ltbServicioExternoAgregados
							.getItemCount(); j++) {
						Listitem listItemj = ltbServicioExternoAgregados
								.getItemAtIndex(j);
						Integer id = ((Spinner) ((listItemj.getChildren()
								.get(4))).getFirstChild()).getValue();
						ServicioExterno servicioExterno = servicioServicioExterno
								.buscar(id);
						double valor = ((Doublespinner) ((listItemj
								.getChildren().get(3))).getFirstChild())
								.getValue();
						String idProveedor = "";
						Proveedor proveedor = null;
						if (((Combobox) ((listItemj.getChildren().get(2)))
								.getFirstChild()).getSelectedItem() != null) {
							idProveedor = ((Combobox) ((listItemj.getChildren()
									.get(2))).getFirstChild())
									.getSelectedItem().getContext();
							proveedor = servicioProveedor.buscar(Long
									.parseLong(idProveedor));
						}
						String observacion = ((Textbox) ((listItemj
								.getChildren().get(1))).getFirstChild())
								.getValue();
						OrdenServicioExterno consultaServicioj = new OrdenServicioExterno(
								null, servicioExterno, proveedor, valor,
								observacion, "");
						serviciosAgregados.add(consultaServicioj);
					}
					serviciosAgregados.add(consultaServicio);
					ltbServicioExternoAgregados
							.setModel(new ListModelList<OrdenServicioExterno>(
									serviciosAgregados));
					ltbServicioExternoAgregados.renderAll();
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		// for (int i = 0; i < listitemEliminar.size(); i++) {
		// ltbServicioExterno.removeItemAt(listitemEliminar.get(i).getIndex());
		// }
		listasMultiples();
	}

	@Listen("onClick = #pasar2ServicioExterno")
	public void izquierdaServicioExterno() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbServicioExternoAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					OrdenServicioExterno consultaServicio = listItem2.get(i)
							.getValue();
					serviciosAgregados.remove(consultaServicio);
					// serviciosDisponibles.add(consultaServicio
					// .getServicioExterno());
					// ltbServicioExterno
					// .setModel(new ListModelList<ServicioExterno>(
					// serviciosDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbServicioExternoAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onOK = #txtCedulaCita")
	public void buscarCedulaOrden() {
		Paciente paciente = new Paciente();
		// if (isPlanta)
		paciente = servicioPaciente.buscarPorCitaCedula(txtCedulaCita.getValue(),
				fecha);
		// else
		// paciente = servicioPaciente.buscarPorCedulaFamiliarActivo(txtCedula
		// .getValue());
		limpiarCampos();
		if (paciente != null) {
			txtCedula.setValue("");
			idControlOrden = Long.valueOf(paciente.getUsuarioAuditoria());
			txtCedulaCita.setValue(paciente.getCedula());
			llenarCampos(paciente);
			idPaciente = paciente.getCedula();
			List<Orden> ordenes = servicioOrden.buscarPorPaciente(paciente);
			ltbOrdenes.setModel(new ListModelList<Orden>(ordenes));
			llenarListas();
		} else {
			Mensaje.mensajeError(Mensaje.pacienteNoExiste);
		}
	}

	@Listen("onOK = #txtCedula")
	public void buscarCedula() {
		Paciente paciente = new Paciente();
		// if (isPlanta)
		paciente = servicioPaciente.buscarPorCedulaActivo(txtCedula.getValue());
		// else
		// paciente = servicioPaciente.buscarPorCedulaFamiliarActivo(txtCedula
		// .getValue());
		limpiarCampos();
		if (paciente != null) {
			llenarCampos(paciente);
			idPaciente = paciente.getCedula();
			List<Orden> ordenes = servicioOrden.buscarPorPaciente(paciente);
			ltbOrdenes.setModel(new ListModelList<Orden>(ordenes));
			llenarListas();
		} else {
			Mensaje.mensajeError(Mensaje.pacienteNoExiste);
		}
	}

	/* Muestra un catalogo de Pacientes */
	@Listen("onClick = #btnBuscarPaciente, #btnBuscarPacienteCita")
	public void mostrarCatalogoPaciente(Event evento) throws IOException {
		idBoton = evento.getTarget().getId();
		List<Paciente> pacientesBuscar = new ArrayList<Paciente>();
		// if (isPlanta)
		if (idBoton.equals("btnBuscarPaciente"))
			pacientesBuscar = servicioPaciente.buscarTodosActivos();
		else
			pacientesBuscar = servicioPaciente.buscarPorOrden(fecha);
		// else
		// pacientesBuscar = servicioPaciente.buscarFamiliaresActivos();
		final List<Paciente> pacientes = pacientesBuscar;
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPacientes,
				"Catalogo de Pacientes", pacientes, false, "Cedula", "Ficha",
				"Primer Nombre","Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Trabajador Asociado") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {
				// if (isPlanta) {
				if (idBoton.equals("btnBuscarPaciente")) {
					switch (combo) {
					case "Ficha":
						return servicioPaciente.filtroFichaActivos(valor);
					case "Primer Nombre":
						return servicioPaciente.filtroNombre1Activos(valor);
					case "Segundo Nombre":
						return servicioPaciente.filtroNombre2Activos(valor);
					case "Cedula":
						return servicioPaciente.filtroCedulaActivos(valor);
					case "Primer Apellido":
						return servicioPaciente.filtroApellido1Activos(valor);
					case "Segundo Apellido":
						return servicioPaciente.filtroApellido2Activos(valor);
					case "Trabajador Asociado":
						return servicioPaciente
								.filtroCedulaFamiliar1Activos(valor);
					default:
						return pacientes;
					}
				} else {
					switch (combo) {
					case "Ficha":
						return servicioPaciente.filtroOrdenFichaActivos(valor,
								fecha);
					case "Primer Nombre":
						return servicioPaciente.filtroOrdenNombre1Activos(
								valor, fecha);
					case "Segundo Nombre":
						return servicioPaciente.filtroOrdenNombre2Activos(
								valor, fecha);
					case "Cedula":
						return servicioPaciente.filtroOrdenCedulaActivos(valor,
								fecha);
					case "Primer Apellido":
						return servicioPaciente.filtroOrdenApellido1Activos(
								valor, fecha);
					case "Segundo Apellido":
						return servicioPaciente.filtroOrdenApellido2Activos(
								valor, fecha);
					case "Trabajador Asociado":
						return servicioPaciente
								.filtroOrdenCedulaFamiliar1Activos(valor, fecha);
					default:
						return pacientes;
					}
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[7];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getSegundoNombre();
				registros[4] = objeto.getPrimerApellido();
				registros[5] = objeto.getSegundoApellido();
				registros[6] = objeto.getCedulaFamiliar();
				return registros;
			}

		};
		catalogoPaciente.setParent(divCatalogoPacientes);
		Listbox lsita = (Listbox) catalogoPaciente.getChildren().get(3);
		lsita.setEmptyMessage("Utilice el filtro para buscar el paciente que desea buscar");
		catalogoPaciente.doModal();
	}

	@Listen("onSeleccion = #divCatalogoPacientes")
	public void seleccionarPaciente() {
		limpiarCampos();
		Paciente paciente = catalogoPaciente.objetoSeleccionadoDelCatalogo();
		if (idBoton.equals("btnBuscarPacienteCita")) {
			txtCedulaCita.setValue(paciente.getCedula());
			idControlOrden = Long.valueOf(paciente.getUsuarioAuditoria());
		} else
			txtCedulaCita.setValue("");
		llenarCampos(paciente);
		idPaciente = paciente.getCedula();
		List<Orden> ordenes = servicioOrden.buscarPorPaciente(paciente);
		ltbOrdenes.setModel(new ListModelList<Orden>(ordenes));
		llenarListas();
		catalogoPaciente.setParent(null);
	}

	private void llenarCampos(Paciente paciente) {
		if (paciente.getArea() != null)
			lblArea.setValue(paciente.getArea().getNombre());
		txtCedula.setValue(paciente.getCedula());
		lblNombres.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getSegundoNombre());
		lblApellidos.setValue(paciente.getPrimerApellido() + " "
				+ paciente.getSegundoApellido());
		if (paciente.getEmpresa() != null)
			lblEmpresa.setValue(paciente.getEmpresa().getNombre());
		lblCedula.setValue(paciente.getCedula());
		lblCiudad.setValue(paciente.getCiudadVivienda().getNombre());
		lblFicha.setValue(paciente.getFicha());
		lblAlergias.setValue(paciente.getObservacionAlergias());
		lblFechaNac.setValue(String.valueOf(formatoFecha.format(paciente
				.getFechaNacimiento())));
		lblLugarNac.setValue(paciente.getLugarNacimiento());
		lblSexo.setValue(paciente.getSexo());
		lblEstadoCivil.setValue(paciente.getEstadoCivil());
		lblGrupoSanguineo.setValue(paciente.getGrupoSanguineo());
		lblMano.setValue(paciente.getMano());
		lblOrigen.setValue(paciente.getOrigenDiscapacidad());
		lblTipoDiscapacidad.setValue(paciente.getTipoDiscapacidad());
		lblObservacionDiscapacidad.setValue(paciente
				.getObservacionDiscapacidad());
		lblDireccion.setValue(paciente.getDireccion());
		lblTelefono1.setValue(paciente.getTelefono1());
		lblTelefono2.setValue(paciente.getTelefono2());
		lblCorreo.setValue(paciente.getEmail());
		lblNombresE.setValue(paciente.getNombresEmergencia());
		lblApellidosE.setValue(paciente.getApellidosEmergencia());
		lblTelefono1E.setValue(paciente.getTelefono1Emergencia());
		lblTelefono2E.setValue(paciente.getTelefono2Emergencia());
		lblParentesco.setValue(paciente.getParentescoEmergencia());
		// lblParentescoFamiliar.setValue(paciente.getParentescoFamiliar());
		lblEdad.setValue(String.valueOf(calcularEdad(paciente
				.getFechaNacimiento())));
		lblEstatura.setValue(String.valueOf(paciente.getEstatura()));
		lblPeso.setValue(String.valueOf(paciente.getPeso()));
		// lblCiudad.setValue(paciente.getCiudadVivienda().getNombre());

		if (paciente.isAlergia())
			lblAlergico.setValue("SI");
		else
			lblAlergico.setValue("NO");
		if (paciente.isTrabajador()) {
			lblTrabajador.setValue("SI");
			lblCargo.setValue(paciente.getCargoReal().getNombre());
			lblArea.setValue(paciente.getArea().getNombre());
		} else
			lblTrabajador.setValue("NO");

		if (paciente.isDiscapacidad())
			lblDiscapacidad.setValue("SI");
		else
			lblDiscapacidad.setValue("NO");

		if (paciente.isLentes())
			lblLentes.setValue("SI");
		else
			lblLentes.setValue("NO");

		BufferedImage imag;
		if (paciente.getImagen() != null) {
			imagenPaciente.setVisible(true);
			try {
				imag = ImageIO.read(new ByteArrayInputStream(paciente
						.getImagen()));
				imagenPaciente.setContent(imag);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			imagenPaciente.setVisible(false);

		if (paciente.isTrabajador()) {
			List<Paciente> familiares = servicioPaciente
					.buscarParientes(paciente.getCedula());
			for (int i = 0; i < familiares.size(); i++) {

				String nombre = familiares.get(i).getPrimerNombre();
				String apellido = familiares.get(i).getPrimerApellido();
				Paciente pacienteFor = familiares.get(i);
				pacienteFor.setPrimerNombre(nombre + " " + apellido);
			}
			ltbCargaFamiliar.setModel(new ListModelList<Paciente>(familiares));
			tabCarga.setVisible(true);
		} else
			tabCarga.setVisible(false);
		idPaciente = paciente.getCedula();
		if (!paciente.isTrabajador()
				&& paciente.getParentescoFamiliar().equals("Hijo(a)")) {
			Paciente representante = servicioPaciente.buscarPorCedula(paciente
					.getCedulaFamiliar());
			if (representante.isMuerte()) {
				if (calcularEdad(representante.getFechaMuerte()) >= 1)
					Mensaje.mensajeAlerta(Mensaje.pacienteFallecido);
			} else {
				if (calcularEdad(paciente.getFechaNacimiento()) >= 18)
					Mensaje.mensajeAlerta(Mensaje.pacienteMayor);
			}
		}
	}

	@Listen("onClick = #btnVerOrden")
	public void seleccionarConsulta() {
		if (ltbOrdenes.getItemCount() != 0) {
			if (ltbOrdenes.getSelectedItems().size() == 1) {
				Listitem listItem = ltbOrdenes.getSelectedItem();
				if (listItem != null) {
					botonera.getChildren().get(0).setVisible(false);
					Orden orden = listItem.getValue();
					idOrden = orden.getIdOrden();
					idPaciente = orden.getPaciente().getCedula();
					llenarCamposOrden(orden);
					llenarCampos(orden.getPaciente());
					llenarListas();
					tabResumen.setSelected(true);
					btnGenerarOrden.setVisible(true);
					btnGenerarReferencia.setVisible(true);
					btnGenerarOrdenServicios.setVisible(true);
					btnGenerarRecipe.setVisible(true);
				}
			} else
				Mensaje.mensajeError("Debe Seleccionar una Consulta");
		} else
			Mensaje.mensajeError("No existen Regitros de Consulta");
	}

	private void llenarCamposOrden(Orden orden) {
		dtbFecha.setValue(orden.getFechaOrden());
		cmbMotivo.setValue(orden.getMotivo().getDescripcion());
		cmbDoctor.setValue(orden.getDoctor());
	}

	@Listen("onClick = #btnGenerarRecipe")
	public void generarRecipe() throws JSONException {
		if (ltbMedicinasAgregadas.getItemCount() != 0) {
			Long id = idOrden;
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=20&valor2="
					+ id
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		} else
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);

	}

	public byte[] reporteRecipe(Long id) throws JRException, IOException {
		byte[] fichero = null;
		Orden orden = getServicioOrden().buscar(id);
		List<OrdenMedicina> listaMedicinas = getServicioOrdenMedicina()
				.buscarPorOrden(orden);

		Date fechaRecipe = listaMedicinas.get(0).getValidez();

		String dias = "";
		dias = formatoFecha.format(fechaRecipe);

		Paciente paciente = orden.getPaciente();
		Map<String, Object> p = new HashMap<String, Object>();
		String nombreEmpresa = "DESTILERIAS UNIDAS 	S.A.";
		String direccionEmpresa = "";
		String rifEmpresa = "J-30940783-0";
		if (paciente.getEmpresa() != null) {
			nombreEmpresa = paciente.getEmpresa().getNombre();
			direccionEmpresa = paciente.getEmpresa().getDireccionCentro();
			rifEmpresa = paciente.getEmpresa().getRif();
		}

		String nombre = paciente.getPrimerNombre() + "   "
				+ paciente.getSegundoNombre();
		String tratamiento = "";
		if (listaMedicinas.get(0).getTratamiento() == null)
			tratamiento = "Sin Informacion";
		else
			tratamiento = listaMedicinas.get(0).getTratamiento();
		p.put("tratamiento", tratamiento);
		p.put("numero", orden.getIdOrden());
		p.put("empresaNombre", nombreEmpresa);
		p.put("empresaDireccion", direccionEmpresa);
		p.put("empresaRif", rifEmpresa);
		p.put("pacienteNombre", nombre);
		p.put("pacienteApellido", paciente.getPrimerApellido() + "   "
				+ paciente.getSegundoApellido());
		p.put("pacienteCedula", paciente.getCedula());
		p.put("doctorNombre", orden.getDoctor());
		p.put("doctorApellido", "");
		if (tratamiento.equals("Agudo"))
			p.put("impresion", "si");
		else
			p.put("impresion", "no");
		p.put("doctorCedula", "");
		p.put("dias", dias);
		String ms = "";
		p.put("msds", ms);
		String cm = "";
		p.put("msds", ms);
		p.put("comelar", cm);
		p.put("edad",
				String.valueOf(calcularEdad(paciente.getFechaNacimiento())));
		p.put("pacienteNacimiento", paciente.getFechaNacimiento());
		
		p.put("mostrar", "si");
		Usuario usuario = new Usuario();
		String u ="";
		if(getServicioUsuario().buscarPorLogin(orden.getUsuarioAuditoria())!=null)
		{
			usuario=getServicioUsuario().buscarPorLogin(orden.getUsuarioAuditoria());
			u=usuario.getPrimerNombre()+"  "+usuario.getPrimerApellido();
		}
		p.put("usuario", u);
		
		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RRecipe.jasper"));
		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(listaMedicinas));
		return fichero;
	}

	@Listen("onClick = #btnGenerarReferencia")
	public void generarEspecialista() {
		if (ltbEspecialistasAgregados.getItemCount() != 0) {
			for (int i = 0; i < ltbEspecialistasAgregados.getItemCount(); i++) {
				Listitem listItem = ltbEspecialistasAgregados.getItemAtIndex(i);
				Integer idEs = ((Spinner) ((listItem.getChildren().get(4)))
						.getFirstChild()).getValue();
				Long id = idOrden;
				String idEspecialista = String.valueOf(idEs);
				Clients.evalJavaScript("window.open('"
						+ damePath()
						+ "Reportero?valor=21&valor2="
						+ id
						+ "&valor3="
						+ idEspecialista
						+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
			}
		} else
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);

	}

	public byte[] reporteEspecialista(Long part2, String par3)
			throws JRException {
		byte[] fichero = null;
		Orden orden = getServicioOrden().buscar(part2);
		OrdenEspecialista especialistaConsulta = getServicioOrdenEspecialista()
				.buscarPorOrdenYIdEspecialista(orden, par3);
		List<OrdenEspecialista> lista = getServicioOrdenEspecialista()
				.buscarPorOrden(orden);
		Paciente paciente = orden.getPaciente();
		Map<String, Object> p = new HashMap<String, Object>();
		String nombreEmpresa = "DESTILERIAS UNIDAS 	S.A.";
		String direccionEmpresa = "";
		String rifEmpresa = "J-30940783-0";
		if (paciente.getEmpresa() != null) {
			nombreEmpresa = paciente.getEmpresa().getNombre();
			direccionEmpresa = paciente.getEmpresa().getDireccionCentro();
			rifEmpresa = paciente.getEmpresa().getRif();
		}

		p.put("empresaNombre", nombreEmpresa);
		p.put("empresaDireccion", direccionEmpresa);
		p.put("empresaRif", rifEmpresa);
		p.put("pacienteNombre",
				paciente.getPrimerNombre() + "   "
						+ paciente.getSegundoNombre());
		p.put("pacienteApellido", paciente.getPrimerApellido() + "   "
				+ paciente.getSegundoApellido());
		p.put("pacienteCedula", paciente.getCedula());
		p.put("edad",
				String.valueOf(calcularEdad(paciente.getFechaNacimiento())));
		p.put("pacienteSexo", paciente.getSexo());
		p.put("doctorNombre", orden.getDoctor());
		p.put("doctorApellido", "");
		p.put("doctorCedula", "");
		p.put("especialidad", especialistaConsulta.getEspecialista()
				.getEspecialidad().getDescripcion());
		p.put("especialistaDireccion", especialistaConsulta.getEspecialista()
				.getDireccion());
		p.put("especialistaTelefono", especialistaConsulta.getEspecialista()
				.getTelefono());
		p.put("empresaDireccion", direccionEmpresa);
		p.put("especialistaNombre", especialistaConsulta.getEspecialista()
				.getNombre());
		p.put("especialistaApellido", especialistaConsulta.getEspecialista()
				.getApellido());
		p.put("enfermedad", especialistaConsulta.getObservacion());
		p.put("observacion", especialistaConsulta.getObservacion());
		p.put("prioridad", especialistaConsulta.getPrioridad());
		p.put("cedula", paciente.getCedula());
		
		p.put("mostrar", "si");
		Usuario usuario = new Usuario();
		String u ="";
		if(getServicioUsuario().buscarPorLogin(orden.getUsuarioAuditoria())!=null)
		{
			usuario=getServicioUsuario().buscarPorLogin(orden.getUsuarioAuditoria());
			u=usuario.getPrimerNombre()+"  "+usuario.getPrimerApellido();
		}
		p.put("usuario", u);
		

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RRecipeEspecialista.jasper"));
		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(lista));
		return fichero;
	}

	// Reporte Servicio

	@Listen("onClick = #btnGenerarOrdenServicios")
	public void generarServicio() {
		if (ltbServicioExternoAgregados.getItemCount() != 0) {
			Long id = idOrden;
			Orden orden = servicioOrden.buscar(id);
			List<OrdenServicioExterno> listaMedicinas = servicioOrdenServicio
					.buscarPorOrden(orden);
			List<Long> idsProveedor = new ArrayList<Long>();
			long provedor = listaMedicinas.get(0).getProveedor()
					.getIdProveedor();
			idsProveedor.add(provedor);
			for (int i = 0; i < listaMedicinas.size(); i++) {
				if (provedor != listaMedicinas.get(i).getProveedor()
						.getIdProveedor()) {
					idsProveedor.add(listaMedicinas.get(i).getProveedor()
							.getIdProveedor());
					provedor = listaMedicinas.get(i).getProveedor()
							.getIdProveedor();
				}
			}
			for (int i = 0; i < idsProveedor.size(); i++) {
				Clients.evalJavaScript("window.open('"
						+ damePath()
						+ "Reportero?valor=22&valor2="
						+ id
						+ "&valor5="
						+ idsProveedor.get(i)
						+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
			}
		} else
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);

	}

	public byte[] reporteServicio(Long part2, Long part5) throws JRException {
		byte[] fichero = null;
		Orden orden = getServicioOrden().buscar(part2);
		List<OrdenServicioExterno> listaMedicinas = getServicioOrdenServicioExterno()
				.buscarPorOrdenYProveedor(orden, part5);
		String servicio = "";
		for (int i = 0; i < listaMedicinas.size(); i++) {
			servicio += listaMedicinas.get(i).getServicioExterno().getNombre()
					+ "; ";
		}
		Paciente paciente = orden.getPaciente();
		Map<String, Object> p = new HashMap<String, Object>();
		String nombreEmpresa = "DESTILERIAS UNIDAS 	S.A.";
		String direccionEmpresa = "";
		String rifEmpresa = "J-30940783-0";
		if (paciente.getEmpresa() != null) {
			nombreEmpresa = paciente.getEmpresa().getNombre();
			direccionEmpresa = paciente.getEmpresa().getDireccionCentro();
			rifEmpresa = paciente.getEmpresa().getRif();
		}
		p.put("cedula", paciente.getCedula());
		p.put("empresaNombre", nombreEmpresa);
		p.put("empresaDireccion", direccionEmpresa);
		p.put("empresaRif", rifEmpresa);
		p.put("pacienteNombre",
				paciente.getPrimerNombre() + "  " + paciente.getSegundoNombre());
		p.put("pacienteApellido", paciente.getPrimerApellido() + "   "
				+ paciente.getSegundoApellido());
		p.put("pacienteCedula", paciente.getCedula());
		p.put("pacienteEdad", paciente.getEdad());
		p.put("pacienteSexo", paciente.getSexo());
		p.put("doctorNombre", orden.getDoctor());
		p.put("doctorApellido", "");
		p.put("doctorCedula", "");
		p.put("servicio", servicio);
		p.put("centro", listaMedicinas.get(0).getProveedor().getNombre());
		p.put("prioridad", listaMedicinas.get(0).getPrioridad());
		p.put("edad",
				String.valueOf(calcularEdad(paciente.getFechaNacimiento())));
		p.put("direccion", listaMedicinas.get(0).getProveedor().getDireccion());
		p.put("mostrar", "si");
		Usuario usuario = new Usuario();
		String u ="";
		if(getServicioUsuario().buscarPorLogin(orden.getUsuarioAuditoria())!=null)
		{
			usuario=getServicioUsuario().buscarPorLogin(orden.getUsuarioAuditoria());
			u=usuario.getPrimerNombre()+"  "+usuario.getPrimerApellido();
		}
		p.put("usuario", u);
		

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RRecipeServicio.jasper"));
		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(listaMedicinas));
		return fichero;
	}

	// Generar Orden Examenes

	@Listen("onClick = #btnGenerarOrden")
	public void generarExamenes() {
		if (ltbExamenesAgregados.getItemCount() != 0) {
			Long id = idOrden;
			Orden orden = servicioOrden.buscar(idOrden);
			List<OrdenExamen> listaMedicinas = servicioOrdenExamen
					.buscarPorOrden(orden);
			List<Long> idsProveedor = new ArrayList<Long>();
			long provedor = 0;
			if (listaMedicinas.get(0).getProveedor() != null)
				provedor = listaMedicinas.get(0).getProveedor()
						.getIdProveedor();
			idsProveedor.add(provedor);
			for (int i = 0; i < listaMedicinas.size(); i++) {
				if (listaMedicinas.get(0).getProveedor() != null) {
					if (provedor != listaMedicinas.get(i).getProveedor()
							.getIdProveedor()) {
						idsProveedor.add(listaMedicinas.get(i).getProveedor()
								.getIdProveedor());
						provedor = listaMedicinas.get(i).getProveedor()
								.getIdProveedor();
					}
				}
			}
			for (int i = 0; i < idsProveedor.size(); i++) {
				Clients.evalJavaScript("window.open('"
						+ damePath()
						+ "Reportero?valor=23&valor2="
						+ id
						+ "&valor5="
						+ idsProveedor.get(i)
						+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
			}
		} else
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);

	}

	public byte[] reporteExamen(Long part2, Long part5) throws JRException {
		byte[] fichero = null;
		Orden orden = getServicioOrden().buscar(part2);
		List<OrdenExamen> listaMedicinas = getServicioOrdenExamen()
				.buscarPorOrdenYProveedor(orden, part5);
		Paciente paciente = orden.getPaciente();
		Map<String, Object> p = new HashMap<String, Object>();
		String nombreEmpresa = "DESTILERIAS UNIDAS 	S.A.";
		String direccionEmpresa = "";
		String rifEmpresa = "J-30940783-0";
		if (paciente.getEmpresa() != null) {
			nombreEmpresa = paciente.getEmpresa().getNombre();
			direccionEmpresa = paciente.getEmpresa().getDireccionCentro();
			rifEmpresa = paciente.getEmpresa().getRif();
		}

		p.put("empresaNombre", nombreEmpresa);
		p.put("empresaDireccion", direccionEmpresa);
		p.put("empresaRif", rifEmpresa);
		p.put("pacienteNombre",
				paciente.getPrimerNombre() + "  " + paciente.getSegundoNombre());
		p.put("pacienteApellido", paciente.getPrimerApellido() + "   "
				+ paciente.getSegundoApellido());
		p.put("pacienteCedula", paciente.getCedula());
		p.put("doctorNombre", orden.getDoctor());
		p.put("doctorApellido", "");
		p.put("doctorCedula", "");
		if (part5 == 0) {
			p.put("prioridad", "N/A");
			p.put("proveedor", "N/A");
		} else {
			if (!listaMedicinas.isEmpty()) {
				p.put("prioridad", listaMedicinas.get(0).getPrioridad());
				p.put("proveedor", listaMedicinas.get(0).getProveedor()
						.getNombre());
			}
		}
		p.put("edad",
				String.valueOf(calcularEdad(paciente.getFechaNacimiento())));
		p.put("pacienteNacimiento", paciente.getFechaNacimiento());
		
		p.put("mostrar", "si");
		Usuario usuario = new Usuario();
		String u ="";
		if(getServicioUsuario().buscarPorLogin(orden.getUsuarioAuditoria())!=null)
		{
			usuario=getServicioUsuario().buscarPorLogin(orden.getUsuarioAuditoria());
			u=usuario.getPrimerNombre()+"  "+usuario.getPrimerApellido();
		}
		p.put("usuario", u);
		

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RRecipeExamen.jasper"));
		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(listaMedicinas));
		return fichero;
	}

}
