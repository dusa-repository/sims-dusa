package controlador.maestros;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Examen;
import modelo.maestros.Proveedor;
import modelo.maestros.ProveedorExamen;
import modelo.maestros.ProveedorServicio;
import modelo.maestros.ServicioExterno;
import modelo.transacciones.ConsultaExamen;
import modelo.transacciones.ConsultaServicioExterno;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Filedownload;
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

import security.modelo.Arbol;
import arbol.CArbol;

import com.csvreader.CsvReader;
import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;
import componentes.Mensaje;
import componentes.Validador;

public class CProveedor extends CGenerico {

	private static final long serialVersionUID = -6178756189105805846L;

	@Wire
	private Listbox ltbEstudios;
	@Wire
	private Listbox ltbEstudiosAgregados;
	@Wire
	private Listbox ltbExamen;
	@Wire
	private Listbox ltbExamenesAgregados;
	@Wire
	private Div divProveedor;
	@Wire
	private Div botoneraProveedor;
	@Wire
	private Div catalogoProveedor;
	@Wire
	private Textbox txtNombreProveedor;
	@Wire
	private Textbox txtTelefonoProveedor;
	@Wire
	private Textbox txtDireccionProveedor;
	@Wire
	private Button btnBuscarProveedor;
	@Wire
	private Combobox cmbCiudadProveedor;
	@Wire
	private Textbox txtBuscadorEstudio;
	@Wire
	private Textbox txtBuscadorExamen;
	@Wire
	private Tab tabEstudios;
	@Wire
	private Tab tabExamenes;
	@Wire
	private Label lblNombre;
	@Wire
	private org.zkoss.zul.Row rowSubir;
	@Wire
	private org.zkoss.zul.Row rowBajar;

	private CArbol cArbol = new CArbol();
	long id = 0;
	Catalogo<Proveedor> catalogo;
	private Media media;
	Buscar<Examen> buscadorExamen;
	Buscar<ServicioExterno> buscadorEstudio;
	List<Examen> examenesDisponibles = new ArrayList<Examen>();
	List<ProveedorExamen> examenesUsados = new ArrayList<ProveedorExamen>();
	List<ServicioExterno> estudiosDisponibles = new ArrayList<ServicioExterno>();
	List<ProveedorServicio> estudiosUsados = new ArrayList<ProveedorServicio>();
	private String archivoConError = "Existe un error en el siguiente archivo adjunto: ";

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divProveedor.getParent();
		Tabbox tabox = (Tabbox) divProveedor.getParent().getParent()
				.getParent().getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				mapa.clear();
				mapa = null;
			}
		}
		llenarComboCiudad();
		llenarListaExamenes(null);
		llenarListaEstudios(null);
		buscarExamen();
		buscarEstudio();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divProveedor, "Proveedor", tabs);
			}

			@Override
			public void limpiar() {
				txtDireccionProveedor.setValue("");
				txtNombreProveedor.setValue("");
				txtTelefonoProveedor.setValue("");
				cmbCiudadProveedor.setValue("");
				cmbCiudadProveedor.setPlaceholder("Seleccione una Ciudad");
				id = 0;
				ltbEstudios.getItems().clear();
				ltbEstudiosAgregados.getItems().clear();
				llenarListaEstudios(null);
				estudiosDisponibles.clear();
				estudiosUsados.clear();
				ltbExamen.getItems().clear();
				ltbExamenesAgregados.getItems().clear();
				llenarListaExamenes(null);
				examenesDisponibles.clear();
				examenesUsados.clear();
				tabEstudios.setSelected(true);
				if (rowSubir.getChildren().size() == 4) {
					A linea = (A) rowSubir.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				rowBajar.setVisible(false);
				rowSubir.setVisible(false);
			}

			@Override
			public void guardar() {
				if (validar()) {
					List<ProveedorServicio> listaEstudios = new ArrayList<ProveedorServicio>();
					List<ProveedorExamen> listaExamen = new ArrayList<ProveedorExamen>();

					/* Verifica los estudios asociados */
					boolean campoNuloEstudio = false;
					ltbEstudiosAgregados.renderAll();
					for (int i = 0; i < ltbEstudiosAgregados.getItemCount(); i++) {
						Listitem listItem = ltbEstudiosAgregados
								.getItemAtIndex(i);
						Double costo = ((Doublespinner) ((listItem
								.getChildren().get(1))).getFirstChild())
								.getValue();
						long idEstudio = ((Spinner) ((listItem.getChildren()
								.get(2))).getFirstChild()).getValue();
						if (String.valueOf(idEstudio) == "" || costo == null) {
							campoNuloEstudio = true;
						} else {
							ServicioExterno servicioExterno = servicioServicioExterno
									.buscar(idEstudio);
							ProveedorServicio proveedorServicio = new ProveedorServicio(
									null, servicioExterno, costo);
							listaEstudios.add(proveedorServicio);
						}
					}

					/* Verifica los examenes asociados */
					boolean campoNuloExamen = false;
					ltbExamenesAgregados.renderAll();
					for (int i = 0; i < ltbExamenesAgregados.getItemCount(); i++) {
						Listitem listItem = ltbExamenesAgregados
								.getItemAtIndex(i);
						Double costo = ((Doublespinner) ((listItem
								.getChildren().get(1))).getFirstChild())
								.getValue();
						long idExamen = ((Spinner) ((listItem.getChildren()
								.get(2))).getFirstChild()).getValue();
						if (String.valueOf(idExamen) == "" || costo == null) {
							campoNuloExamen = true;
						} else {
							Examen examen = servicioExamen.buscar(idExamen);
							ProveedorExamen proveedorExamen = new ProveedorExamen(
									null, examen, costo);
							listaExamen.add(proveedorExamen);
						}
					}
					if (!campoNuloEstudio && !campoNuloExamen) {
						String nombre, direccion, telefono;
						nombre = txtNombreProveedor.getValue();
						direccion = txtDireccionProveedor.getValue();
						telefono = txtTelefonoProveedor.getValue();
						Ciudad ciudad = servicioCiudad.buscar(Long
								.parseLong(cmbCiudadProveedor.getSelectedItem()
										.getContext()));
						Double costo = 0.0;
						Proveedor proveedor = new Proveedor(id, direccion,
								nombre, telefono, fechaHora, horaAuditoria,
								nombreUsuarioSesion(), ciudad, costo);
						servicioProveedor.guardar(proveedor);

						if (id != 0)
							proveedor = servicioProveedor.buscar(id);
						else
							proveedor = servicioProveedor.buscarUltimo();

						List<ProveedorServicio> estudios = servicioProveedorServicio
								.buscarEstudiosUsados(proveedor);
						if (!estudios.isEmpty())
							servicioProveedorServicio.eliminar(estudios);
						for (int i = 0; i < listaEstudios.size(); i++) {
							listaEstudios.get(i).setProveedor(proveedor);
						}
						servicioProveedorServicio.guardar(listaEstudios);

						List<ProveedorExamen> examenes = servicioProveedorExamen
								.buscarExamenesUsados(proveedor);
						if (!examenes.isEmpty())
							servicioProveedorExamen.eliminar(examenes);
						for (int i = 0; i < listaExamen.size(); i++) {
							listaExamen.get(i).setProveedor(proveedor);
						}
						servicioProveedorExamen.guardar(listaExamen);
						if (media != null) {
							guardarPrecios(proveedor);
						}
						limpiar();
						msj.mensajeInformacion(Mensaje.guardado);
					} else
						msj.mensajeError(Mensaje.listaVacia);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Proveedor?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Proveedor proveedor = servicioProveedor
												.buscar(id);
										List<ConsultaServicioExterno> consultas1 = servicioConsultaServicioExterno
												.buscarPorProveedor(proveedor);
										List<ConsultaExamen> consultas2 = servicioConsultaExamen
												.buscarPorProveedor(proveedor);
										if (!consultas1.isEmpty()
												|| !consultas2.isEmpty())
											msj.mensajeError(Mensaje.noEliminar);
										else {
											servicioProveedor
													.eliminar(proveedor);
											limpiar();
											msj.mensajeInformacion(Mensaje.eliminado);
										}
									}
								}
							});
				} else
					msj.mensajeAlerta(Mensaje.noSeleccionoRegistro);
			}
		};
		botoneraProveedor.appendChild(botonera);
	}

	@Listen("onClick = #btnExportar")
	public void exportar() {
		if (id != 0) {
			final Proveedor proveedor = servicioProveedor.buscar(id);
			List<ProveedorServicio> listaEstudios = servicioProveedorServicio
					.buscarEstudiosUsados(proveedor);
			List<ProveedorExamen> listaExamen = servicioProveedorExamen
					.buscarExamenesUsados(proveedor);
			if (!listaEstudios.isEmpty() && !listaExamen.isEmpty()) {
				String s = ";";
				final StringBuffer sb = new StringBuffer();
				sb.append("Tipo;Id;ServicioOfrecido;Precio" + "\n");
				for (int i = 0; i < listaEstudios.size(); i++) {
					String fila = "";
					fila += "Estudio;"
							+ listaEstudios.get(i).getServicioExterno()
									.getIdServicioExterno()
							+ ";"
							+ listaEstudios.get(i).getServicioExterno()
									.getNombre() + ";"
							+ listaEstudios.get(i).getCosto() + s;
					sb.append(fila + "\n");
				}
				for (int i = 0; i < listaExamen.size(); i++) {
					String fila = "";
					fila += "Examen;"
							+ listaExamen.get(i).getExamen().getIdExamen()
							+ ";" + listaExamen.get(i).getExamen().getNombre()
							+ ";" + listaExamen.get(i).getCosto() + s;
					sb.append(fila + "\n");
				}

				// for (Object head : lsbCatalogo.getHeads()) {
				// String h = "";
				// if (head instanceof Listhead) {
				// for (Object header : ((Listhead) head).getChildren()) {
				// h += ((Listheader) header).getLabel() + s;
				// }
				// sb.append(h + "\n");
				// }
				// }
				// for (Object item : lsbCatalogo.getItems()) {
				// String i = "";
				// for (Object cell : ((Listitem) item).getChildren()) {
				// i += ((Listcell) cell).getLabel() + s;
				// }
				// sb.append(i + "\n");
				// }
				Messagebox.show(Mensaje.exportar, "Alerta", Messagebox.OK
						| Messagebox.CANCEL, Messagebox.QUESTION,
						new org.zkoss.zk.ui.event.EventListener<Event>() {
							public void onEvent(Event evt)
									throws InterruptedException {
								if (evt.getName().equals("onOK")) {
									Filedownload.save(sb.toString().getBytes(),
											"text/plain", "Precios proveedor "
													+ proveedor.getNombre()
													+ ".csv");
								}
							}
						});
			} else
				msj.mensajeAlerta(Mensaje.noHayRegistros);

		} else
			msj.mensajeAlerta("Debe seleccionar un Proveedor previamente");

	}


	@Listen("onUpload = #btnImportar")
	public void cargar(UploadEvent event) {
		media = event.getMedia();
		if (media != null && Validador.validarExcel(media)) {
			lblNombre.setValue(media.getName());
			final A rm = new A("Remover");
			rm.addEventListener(Events.ON_CLICK,
					new org.zkoss.zk.ui.event.EventListener<Event>() {
						public void onEvent(Event event) throws Exception {
							lblNombre.setValue("");
							rm.detach();
							media = null;
						}
					});
			rowSubir.appendChild(rm);
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

	protected void guardarPrecios(Proveedor proveedor) {
		
		String tipo = "";
		CsvReader reader = null;
		List<ProveedorServicio> listaEstudios = new ArrayList<ProveedorServicio>();
		List<ProveedorExamen> listaExamen = new ArrayList<ProveedorExamen>();
			try {
				reader = new CsvReader(media.getStreamData(), ';', Charset.defaultCharset());
				boolean entro = false;
				boolean error = false;
				int row = 0;
				String mostrarError = "";
				long idServicio = 0;
				while (reader.readRecord()) {
					if (Validador.validarNumero(reader.get(1))) {
						row++;
						tipo = reader.get(0);
						if (Validador.validarNumero(reader.get(1))) {
							entro = true;
							if (Validador.validarDouble(reader.get(3))) {
								if (tipo.equals("Estudio")) {
									idServicio = Long.valueOf(reader.get(1));
									ServicioExterno servicio = servicioServicioExterno
											.buscar(idServicio);
									if (servicio != null) {
										double costo = Double.valueOf(reader
												.get(3));
										ProveedorServicio proveedorServicio = new ProveedorServicio(
												proveedor, servicio, costo);
										listaEstudios.add(proveedorServicio);
									} else {
										mostrarError = mensajeErrorNoEncontrado(
												mostrarError, idServicio, row,
												2, "Estudio");
										error = true;
									}
								} else {
									if (tipo.equals("Examen")) {
										idServicio = Long
												.valueOf(reader.get(1));
										Examen examen = servicioExamen
												.buscar(idServicio);
										if (examen != null) {
											double costo = Double
													.valueOf(reader.get(3));
											ProveedorExamen proveedorExamen = new ProveedorExamen(
													proveedor, examen, costo);
											listaExamen.add(proveedorExamen);
										} else {
											mostrarError = mensajeErrorNoEncontrado(
													mostrarError, idServicio,
													row, 2, "Examen");
											error = true;
										}
									} else {
										mostrarError = mensajeErrorNull(
												mostrarError, row, 1);
										error = true;

									}

								}

							} else {
								mostrarError = mensajeErrorNull(mostrarError,
										row, 4);
								error = true;
							}
						} else {
							mostrarError = mensajeErrorNull(mostrarError, row,
									2);
							error = true;
						}
					}
				}
				if (entro) {
					if (!error) {
						List<ProveedorExamen> examenes = servicioProveedorExamen
								.buscarExamenesUsados(proveedor);
						if (!examenes.isEmpty())
							servicioProveedorExamen.eliminar(examenes);
						List<ProveedorServicio> estudios = servicioProveedorServicio
								.buscarEstudiosUsados(proveedor);
						if (!estudios.isEmpty())
							servicioProveedorServicio.eliminar(estudios);
						servicioProveedorExamen.guardar(listaExamen);
						servicioProveedorServicio.guardar(listaEstudios);
						msj.mensajeInformacion("Archivo importado con exito"
								+ "\n" + "Cantidad de Filas evaluadas:"
								+ (row - 1) + "\n"
								+ "Cantidad de Filas insertadas:" + (row - 1));
					} else
						msj.mensajeError("El archivo no ha podido ser importado, causas:"
								+ "\n"
								+ mostrarError
								+ "\n"
								+ "Cantidad de Filas evaluadas:"
								+ (row - 1)
								+ "\n" + "Cantidad de Filas insertadas: 0");
				} else
					msj.mensajeError("Archivo vacio o con errores de formato");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private String mensajeErrorNoEncontrado(String mostrarError,
			long idServicio, int row, int i, String string) {
		return mostrarError + " El valor " + idServicio
				+ " no se ha encontrado en la tabla  " + string + ".  Fila: "
				+ row + ". Columna: " + i + "\n";
	}

	private String mensajeErrorNull(String mostrarError, int row, int i) {
		return mostrarError + archivoConError + ". Fila: " + row
				+ ". Columna: " + i + "\n";
	}

	/* Llena el combo de Ciudades cada vez que se abre */
	@Listen("onOpen = #cmbCiudadProveedor")
	public void llenarComboCiudad() {
		List<Ciudad> ciudades = servicioCiudad.buscarTodas();
		cmbCiudadProveedor.setModel(new ListModelList<Ciudad>(ciudades));
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtDireccionProveedor.getText().compareTo("") == 0
				|| txtNombreProveedor.getText().compareTo("") == 0
				|| txtTelefonoProveedor.getText().compareTo("") == 0
				|| cmbCiudadProveedor.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (!Validador.validarTelefono(txtTelefonoProveedor.getValue())) {
				msj.mensajeError(Mensaje.telefonoInvalido);
				return false;
			} else
				return true;
		}
	}

	/* Muestra el catalogo de los servicios externos */
	@Listen("onClick = #btnBuscarProveedor")
	public void mostrarCatalogo() {
		final List<Proveedor> proveedores = servicioProveedor.buscarTodos();
		catalogo = new Catalogo<Proveedor>(catalogoProveedor,
				"Catalogo de Proveedores", proveedores, false,"Nombre", "Direccion",
				"Telefono", "Ciudad") {

			@Override
			protected List<Proveedor> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioProveedor.filtroNombre(valor);
				case "Direccion":
					return servicioProveedor.filtroDireccion(valor);
				case "Telefono":
					return servicioProveedor.filtroTelefono(valor);
				case "Ciudad":
					return servicioProveedor.filtroCiudad(valor);
				default:
					return proveedores;
				}
			}

			@Override
			protected String[] crearRegistros(Proveedor objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getNombre();
				registros[1] = objeto.getDireccion();
				registros[2] = objeto.getTelefono();
				registros[3] = objeto.getCiudad().getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoProveedor);
		catalogo.doModal();
	}
	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefonoProveedor")
	public void validarTelefono() {
		if (!Validador.validarTelefono(txtTelefonoProveedor.getValue())) {
			msj.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoProveedor")
	public void seleccinar() {
		Proveedor proveedor = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(proveedor);
		catalogo.setParent(null);
	}

	/* Busca si existe un servicio externo con el mismo nombre escrito */
	@Listen("onChange = #txtNombreProveedor")
	public void buscarPorNombre() {
		Proveedor proveedor = servicioProveedor
				.buscarPorNombre(txtNombreProveedor.getValue());
		if (proveedor != null)
			llenarCampos(proveedor);
	}

	/* LLena los campos del formulario dado un servicio externo */
	private void llenarCampos(Proveedor proveedor) {
		rowBajar.setVisible(true);
		rowSubir.setVisible(true);
		txtDireccionProveedor.setValue(proveedor.getDireccion());
		txtNombreProveedor.setValue(proveedor.getNombre());
		txtTelefonoProveedor.setValue(proveedor.getTelefono());
		cmbCiudadProveedor.setValue(proveedor.getCiudad().getNombre());
		llenarListaEstudios(proveedor);
		llenarListaExamenes(proveedor);
		id = proveedor.getIdProveedor();
	}

	/* Abre la vista de Ciudad */
	@Listen("onClick = #btnAbrirCiudad")
	public void abrirCiudad() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Ciudad");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	/* Cosas relacionadas con los grid */
	/* Llena la lista al iniciar con todas las presentaciones existentes */
	private void llenarListaEstudios(Proveedor proveedor) {
		if (proveedor == null) {
			estudiosDisponibles = servicioServicioExterno.buscarTodas();
			ltbEstudios.setModel(new ListModelList<ServicioExterno>(
					estudiosDisponibles));
		} else {
			estudiosUsados = servicioProveedorServicio
					.buscarEstudiosUsados(proveedor);
			ltbEstudiosAgregados.setModel(new ListModelList<ProveedorServicio>(
					estudiosUsados));
			if (!estudiosUsados.isEmpty()) {
				List<Long> ids = new ArrayList<Long>();
				for (int i = 0; i < estudiosUsados.size(); i++) {
					long id = estudiosUsados.get(i).getServicioExterno()
							.getIdServicioExterno();
					ids.add(id);
				}
				estudiosDisponibles = servicioServicioExterno
						.buscarEstudiosDisponibles(ids);
				ltbEstudios.setModel(new ListModelList<ServicioExterno>(
						estudiosDisponibles));
			}
		}
		ltbEstudiosAgregados.setMultiple(false);
		ltbEstudiosAgregados.setCheckmark(false);
		ltbEstudiosAgregados.setMultiple(true);
		ltbEstudiosAgregados.setCheckmark(true);

		ltbEstudios.setMultiple(false);
		ltbEstudios.setCheckmark(false);
		ltbEstudios.setMultiple(true);
		ltbEstudios.setCheckmark(true);
	}

	/* Llena la lista al iniciar con todas las presentaciones existentes */
	private void llenarListaExamenes(Proveedor proveedor) {
		if (proveedor == null) {
			examenesDisponibles = servicioExamen.buscarTodos();
			ltbExamen.setModel(new ListModelList<Examen>(examenesDisponibles));
		} else {
			examenesUsados = servicioProveedorExamen
					.buscarExamenesUsados(proveedor);
			ltbExamenesAgregados.setModel(new ListModelList<ProveedorExamen>(
					examenesUsados));
			if (!examenesUsados.isEmpty()) {
				List<Long> ids = new ArrayList<Long>();
				for (int i = 0; i < examenesUsados.size(); i++) {
					long id = examenesUsados.get(i).getExamen().getIdExamen();
					ids.add(id);
				}
				examenesDisponibles = servicioExamen
						.buscarExamenesDisponibles(ids);
				ltbExamen.setModel(new ListModelList<Examen>(
						examenesDisponibles));
			}
		}
		ltbExamenesAgregados.setMultiple(false);
		ltbExamenesAgregados.setCheckmark(false);
		ltbExamenesAgregados.setMultiple(true);
		ltbExamenesAgregados.setCheckmark(true);

		ltbExamen.setMultiple(false);
		ltbExamen.setCheckmark(false);
		ltbExamen.setMultiple(true);
		ltbExamen.setCheckmark(true);
	}

	/*
	 * Permite mover uno o varios elementos seleccionados desde la lista de la
	 * izquierda a la lista de la derecha (ESTUDIO)
	 */
	@Listen("onClick = #pasar1")
	public void moverDerecha() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbEstudios.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					ServicioExterno servicio = listItem.get(i).getValue();
					estudiosDisponibles.remove(servicio);
					ProveedorServicio proveedorServicio = new ProveedorServicio();
					proveedorServicio.setServicioExterno(servicio);
					estudiosUsados.clear();
					ltbEstudiosAgregados.renderAll();
					for (int j = 0; j < ltbEstudiosAgregados.getItemCount(); j++) {
						Listitem listItemj = ltbEstudiosAgregados
								.getItemAtIndex(j);
						double costo = ((Doublespinner) ((listItemj
								.getChildren().get(1))).getFirstChild())
								.getValue();
						long idEstudio = ((Spinner) ((listItemj.getChildren()
								.get(2))).getFirstChild()).getValue();
						ServicioExterno servicioExterno = servicioServicioExterno
								.buscar(idEstudio);
						ProveedorServicio proveedorServicioj = new ProveedorServicio(
								null, servicioExterno, costo);
						estudiosUsados.add(proveedorServicioj);
					}
					estudiosUsados.add(proveedorServicio);
					ltbEstudiosAgregados
							.setModel(new ListModelList<ProveedorServicio>(
									estudiosUsados));
					ltbEstudiosAgregados.renderAll();
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbEstudios.renderAll();
			ltbEstudios.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		ltbEstudiosAgregados.setMultiple(false);
		ltbEstudiosAgregados.setCheckmark(false);
		ltbEstudiosAgregados.setMultiple(true);
		ltbEstudiosAgregados.setCheckmark(true);
	}

	/*
	 * Permite mover uno o varios elementos seleccionados desde la lista de la
	 * derecha a la lista de la izquierda
	 */
	@Listen("onClick = #pasar2")
	public void moverIzquierda() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbEstudiosAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ProveedorServicio proveedorServicio = listItem2.get(i)
							.getValue();
					estudiosUsados.remove(proveedorServicio);
					estudiosDisponibles.add(proveedorServicio
							.getServicioExterno());
					ltbEstudios.setModel(new ListModelList<ServicioExterno>(
							estudiosDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbEstudiosAgregados.renderAll();
			ltbEstudiosAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		ltbEstudios.setMultiple(false);
		ltbEstudios.setCheckmark(false);
		ltbEstudios.setMultiple(true);
		ltbEstudios.setCheckmark(true);
	}

	public void buscarEstudio() {
		buscadorEstudio = new Buscar<ServicioExterno>(ltbEstudios,
				txtBuscadorEstudio) {
			@Override
			protected List<ServicioExterno> buscar(String valor) {
				List<ServicioExterno> estudiosFiltrados = new ArrayList<ServicioExterno>();
				List<ServicioExterno> estudios = servicioServicioExterno
						.filtroNombre(valor);
				for (int i = 0; i < estudiosDisponibles.size(); i++) {
					ServicioExterno estudio = estudiosDisponibles.get(i);
					for (int j = 0; j < estudios.size(); j++) {
						if (estudio.getIdServicioExterno() == estudios.get(j)
								.getIdServicioExterno())
							estudiosFiltrados.add(estudio);
					}
				}
				return estudiosFiltrados;
			}
		};
	}

	/*
	 * Permite mover uno o varios elementos seleccionados desde la lista de la
	 * izquierda a la lista de la derecha (EXAMEN)
	 */
	@Listen("onClick = #pasar11")
	public void moverDerecha2() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbExamen.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Examen examen = listItem.get(i).getValue();
					examenesDisponibles.remove(examen);
					ProveedorExamen proveedorExamen = new ProveedorExamen();
					proveedorExamen.setExamen(examen);
					ltbExamenesAgregados.renderAll();
					examenesUsados.clear();
					for (int j = 0; j < ltbExamenesAgregados.getItemCount(); j++) {
						Listitem listItemj = ltbExamenesAgregados
								.getItemAtIndex(j);
						double costo = ((Doublespinner) ((listItemj
								.getChildren().get(1))).getFirstChild())
								.getValue();
						long idExamen = ((Spinner) ((listItemj.getChildren()
								.get(2))).getFirstChild()).getValue();
						Examen examenj = servicioExamen.buscar(idExamen);
						ProveedorExamen proveedorExamenj = new ProveedorExamen(
								null, examenj, costo);
						examenesUsados.add(proveedorExamenj);
					}
					examenesUsados.add(proveedorExamen);
					ltbExamenesAgregados
							.setModel(new ListModelList<ProveedorExamen>(
									examenesUsados));
					ltbExamenesAgregados.renderAll();
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbExamen.renderAll();
			ltbExamen.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		ltbExamenesAgregados.setMultiple(false);
		ltbExamenesAgregados.setCheckmark(false);
		ltbExamenesAgregados.setMultiple(true);
		ltbExamenesAgregados.setCheckmark(true);
	}

	/*
	 * Permite mover uno o varios elementos seleccionados desde la lista de la
	 * derecha a la lista de la izquierda
	 */
	@Listen("onClick = #pasar22")
	public void moverIzquierda2() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbExamenesAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ProveedorExamen proveedorExamen = listItem2.get(i)
							.getValue();
					examenesUsados.remove(proveedorExamen);
					examenesDisponibles.add(proveedorExamen.getExamen());
					ltbExamen.setModel(new ListModelList<Examen>(
							examenesDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbExamenesAgregados.renderAll();
			ltbExamenesAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		ltbExamen.setMultiple(false);
		ltbExamen.setCheckmark(false);
		ltbExamen.setMultiple(true);
		ltbExamen.setCheckmark(true);
	}

	public void buscarExamen() {
		buscadorExamen = new Buscar<Examen>(ltbExamen, txtBuscadorExamen) {
			@Override
			protected List<Examen> buscar(String valor) {
				List<Examen> examenesFiltrados = new ArrayList<Examen>();
				List<Examen> examenes = servicioExamen.filtroNombre(valor);
				for (int i = 0; i < examenesDisponibles.size(); i++) {
					Examen examen = examenesDisponibles.get(i);
					for (int j = 0; j < examenes.size(); j++) {
						if (examen.getIdExamen() == examenes.get(j)
								.getIdExamen())
							examenesFiltrados.add(examen);
					}
				}
				return examenesFiltrados;
			}
		};
	}

	/* Abre la vista de Estudios */
	@Listen("onClick = #btnAbrirEstudio")
	public void abrirEstudio() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "proveedor");
		map.put("lista", estudiosDisponibles);
		map.put("listbox", ltbEstudios);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		List<Arbol> arboles = servicioArbol
				.buscarPorNombreArbol("Estudios Externos");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	/* Abre la vista de Examenes */
	@Listen("onClick = #btnAbrirExamen")
	public void abrirExamen() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "proveedor");
		map.put("lista", examenesDisponibles);
		map.put("listbox", ltbExamen);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Examen");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	/* Abre la pestanna de examenes */
	@Listen("onClick = #btnSiguientePestanna")
	public void siguientePestanna() {
		tabExamenes.setSelected(true);
	}

	/* Abre la pestanna de estudios */
	@Listen("onClick = #btnAnteriorPestanna")
	public void anteriorPestanna() {
		tabEstudios.setSelected(true);
	}

	public void recibirExamen(List<Examen> lista, Listbox l) {
		ltbExamen = l;
		examenesDisponibles = lista;
		ltbExamen.setModel(new ListModelList<Examen>(examenesDisponibles));
		ltbExamen.setMultiple(false);
		ltbExamen.setCheckmark(false);
		ltbExamen.setMultiple(true);
		ltbExamen.setCheckmark(true);
	}

	public void recibirServicio(List<ServicioExterno> lista, Listbox l) {
		ltbEstudios = l;
		estudiosDisponibles = lista;
		ltbEstudios.setModel(new ListModelList<ServicioExterno>(
				estudiosDisponibles));
		ltbEstudios.setMultiple(false);
		ltbEstudios.setCheckmark(false);
		ltbEstudios.setMultiple(true);
		ltbEstudios.setCheckmark(true);
	}
}
