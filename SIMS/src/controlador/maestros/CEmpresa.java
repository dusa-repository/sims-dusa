package controlador.maestros;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Consultorio;
import modelo.maestros.Empresa;
import modelo.maestros.EmpresaNomina;
import modelo.maestros.Medicina;
import modelo.maestros.MedicinaPresentacionUnidad;
import modelo.maestros.Nomina;
import modelo.maestros.Paciente;
import modelo.maestros.ParteCuerpo;
import modelo.maestros.PresentacionMedicina;
import modelo.seguridad.Arbol;
import modelo.sha.Informe;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.ConsultaParteCuerpo;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;
import componentes.Mensaje;
import componentes.Validador;

public class CEmpresa extends CGenerico {

	private static final long serialVersionUID = -8397437400900885743L;
	@Wire
	private Tab tabBasicos;
	@Wire
	private Tab tabEmpleados;
	@Wire
	private Button btnSiguientePestanna;
	@Wire
	private Button btnAnteriorPestanna;
	@Wire
	private Div divEmpresa;
	@Wire
	private Div botoneraEmpresa;
	@Wire
	private Div catalogoEmpresa;
	@Wire
	private Button btnBuscarEmpresa;
	@Wire
	private Textbox txtNombreEmpresa;
	@Wire
	private Textbox txtDireccionCentro;
	@Wire
	private Textbox txtRifEmpresa;
	@Wire
	private Textbox txtTelefonoEmpresa;
	@Wire
	private Textbox txtRazon;
	@Wire
	private Textbox txtDireccionRazon;
	@Wire
	private Textbox txtNilEmpresa;
	@Wire
	private Textbox txtNroIvssEmpresa;
	@Wire
	private Textbox txtCodigoCiiuEmpresa;
	@Wire
	private Textbox txtActividadEconomica;
	@Wire
	private Textbox txtCorreo;
	@Wire
	private Textbox txtRegistroMercantil;
	@Wire
	private Datebox dtbFechaRegistro;
	@Wire
	private Textbox txtBajoNroEmpresa;
	@Wire
	private Textbox txtTomoEmpresa;
	@Wire
	private Textbox txtRepresentanteEmpresa;
	@Wire
	private Textbox txtCedulaRepresentante;
	@Wire
	private Textbox txtTelefonoRepresentante;
	@Wire
	private Textbox txtCargo;
	@Wire
	private Datebox dtbFechaActualizacion;
	@Wire
	private Textbox txtBajoNro2Empresa;
	@Wire
	private Textbox txtTomo2Empresa;
	@Wire
	private Textbox txtRepresentante2Empresa;
	@Wire
	private Textbox txtCedula2Representante;
	@Wire
	private Textbox txtTelefono2Representante;
	@Wire
	private Textbox txtCargo2;
	@Wire
	private Spinner spnNroTrabajadores;
	@Wire
	private Spinner spnHombres;
	@Wire
	private Spinner spnMujeres;
	@Wire
	private Spinner spnAdolescentes;
	@Wire
	private Spinner spnAprendices;
	@Wire
	private Spinner spnLopcymat;
	@Wire
	private Spinner spnConapdis;
	@Wire
	private Spinner spnExtranjeros;
	@Wire
	private Textbox txtBuscadorNomina;
	@Wire
	private Listbox ltbNominas;
	@Wire
	private Listbox ltbNominasAgregadas;
	@Wire
	private Button btnAbrirNomina;
	List<Nomina> nominasDisponibles = new ArrayList<Nomina>();
	List<EmpresaNomina> nominasAgregadas = new ArrayList<EmpresaNomina>();
	Buscar<Nomina> buscador;

	private CArbol cArbol = new CArbol();
	long id = 0;
	Catalogo<Empresa> catalogo;

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divEmpresa.getParent();
		Tabbox tabox = (Tabbox) divEmpresa.getParent().getParent().getParent()
				.getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (map != null) {
			if (map.get("tabsGenerales") != null) {
				tabs = (List<Tab>) map.get("tabsGenerales");
				System.out.println(tabs.size());
				map.clear();
				map = null;
			}
		}

		llenarListas();
		listasMultiples();
		buscar();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divEmpresa, "Empresa", tabs);
			}

			@Override
			public void limpiar() {
				txtDireccionCentro.setValue("");
				txtNombreEmpresa.setValue("");
				txtRifEmpresa.setValue("");
				txtTelefonoEmpresa.setValue("");
				txtTelefonoRepresentante.setValue("");
				txtRazon.setValue("");
				txtDireccionRazon.setValue("");
				txtNilEmpresa.setValue("");
				txtNroIvssEmpresa.setValue("");
				txtCodigoCiiuEmpresa.setValue("");
				txtActividadEconomica.setValue("");
				txtCorreo.setValue("");
				txtRegistroMercantil.setValue("");
				dtbFechaRegistro.setValue(null);
				txtBajoNroEmpresa.setValue("");
				txtTomoEmpresa.setValue("");
				txtRepresentanteEmpresa.setValue("");
				txtCedulaRepresentante.setValue("");
				txtCargo.setValue("");
				dtbFechaActualizacion.setValue(null);
				txtBajoNro2Empresa.setValue("");
				txtTomo2Empresa.setValue("");
				txtRepresentante2Empresa.setValue("");
				txtCedula2Representante.setValue("");
				txtTelefono2Representante.setValue("");
				txtCargo2.setValue("");
				spnNroTrabajadores.setValue(0);
				spnHombres.setValue(0);
				spnMujeres.setValue(0);
				spnAdolescentes.setValue(0);
				spnAprendices.setValue(0);
				spnLopcymat.setValue(0);
				spnConapdis.setValue(0);
				spnExtranjeros.setValue(0);
				id = 0;
				llenarListas();
				tabBasicos.setSelected(true);
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre, rif, direccion, telefono, telefonoRepresentante, correo, telefono2Representante, razon, direccionRazon, nil, nroIvss, codigoCiiu, actividadEconomica, registroMercantil, bajoNro, tomo, representante, cedula, cargo, bajoNro2, tomo2, representante2, cedula2, cargo2;
					Timestamp fechaRegistro, fechaActualizacion;
					Integer hombres, mujeres, adolescentes, aprendices, lopcymat, conapdis, extranjeros, nroTrabajadores;

					nombre = txtNombreEmpresa.getValue();
					rif = txtRifEmpresa.getValue();
					direccion = txtDireccionCentro.getValue();

					Empresa empresa = new Empresa(id, nombre, rif, direccion,
							fechaHora, horaAuditoria, nombreUsuarioSesion());

					telefono = txtTelefonoEmpresa.getValue();
					razon = txtRazon.getValue();
					direccionRazon = txtDireccionRazon.getValue();
					nil = txtNilEmpresa.getValue();
					nroIvss = txtNroIvssEmpresa.getValue();
					codigoCiiu = txtCodigoCiiuEmpresa.getValue();
					actividadEconomica = txtActividadEconomica.getValue();
					correo = txtCorreo.getValue();
					registroMercantil = txtRegistroMercantil.getValue();
					bajoNro = txtBajoNroEmpresa.getValue();
					tomo = txtTomoEmpresa.getValue();
					representante = txtRepresentanteEmpresa.getValue();
					cedula = txtCedulaRepresentante.getValue();
					telefonoRepresentante = txtTelefonoRepresentante.getValue();
					cargo = txtCargo.getValue();

					bajoNro2 = txtBajoNro2Empresa.getValue();
					tomo2 = txtTomo2Empresa.getValue();
					representante2 = txtRepresentante2Empresa.getValue();
					cedula2 = txtCedula2Representante.getValue();
					telefono2Representante = txtTelefono2Representante
							.getValue();
					cargo2 = txtCargo2.getValue();
					if (spnNroTrabajadores.getValue() != null) {
						nroTrabajadores = spnNroTrabajadores.getValue();
						empresa.setNroTrabajadores(nroTrabajadores);
					}
					if (spnHombres.getValue() != null) {
						hombres = spnHombres.getValue();
						empresa.setHombres(hombres);
					}
					if (spnMujeres.getValue() != null) {
						mujeres = spnMujeres.getValue();
						empresa.setMujeres(mujeres);
					}
					if (spnAdolescentes.getValue() != null) {
						adolescentes = spnAdolescentes.getValue();
						empresa.setAdolescentes(adolescentes);
					}
					if (spnAprendices.getValue() != null) {
						aprendices = spnAprendices.getValue();
						empresa.setAprendices(aprendices);
					}
					if (spnLopcymat.getValue() != null) {
						lopcymat = spnLopcymat.getValue();
						empresa.setLopcymat(lopcymat);
					}
					if (spnConapdis.getValue() != null) {
						conapdis = spnConapdis.getValue();
						empresa.setConapdis(conapdis);
					}
					if (spnExtranjeros.getValue() != null) {
						extranjeros = spnExtranjeros.getValue();
						empresa.setExtranjeros(extranjeros);
					}
					if (dtbFechaRegistro.getText().compareTo("") != 0) {
						fechaRegistro = new Timestamp(dtbFechaRegistro
								.getValue().getTime());
						empresa.setFechaRegistro(fechaRegistro);
					}
					if (dtbFechaActualizacion.getText().compareTo("") != 0) {
						fechaActualizacion = new Timestamp(
								dtbFechaActualizacion.getValue().getTime());
						empresa.setFechaActualizacion(fechaActualizacion);
					}

					empresa.setTelefono(telefono);
					empresa.setRazon(razon);
					empresa.setDireccionRazon(direccionRazon);
					empresa.setNil(nil);
					empresa.setNroIvss(nroIvss);
					empresa.setCodigoCiiu(codigoCiiu);
					empresa.setActividadEconomica(actividadEconomica);
					empresa.setCorreo(correo);
					empresa.setRegistroMercantil(registroMercantil);
					empresa.setBajoNroEmpresa(bajoNro);
					empresa.setTomoEmpresa(tomo);
					empresa.setRepresentanteEmpresa(representante);
					empresa.setCedulaRepresentante(cedula);
					empresa.setTelefonoRepresentante(telefonoRepresentante);
					empresa.setCargo(cargo);
					empresa.setBajoNro2Empresa(bajoNro2);
					empresa.setTomo2Empresa(tomo2);
					empresa.setRepresentante2Empresa(representante2);
					empresa.setCedula2Representante(cedula2);
					empresa.setTelefono2Representante(telefono2Representante);
					empresa.setCargo2(cargo2);
					servicioEmpresa.guardar(empresa);
					if (id != 0)
						empresa = servicioEmpresa.buscar(id);
					else
						empresa = servicioEmpresa.buscarUltima();
					guardarNominas(empresa);
					limpiar();
					msj.mensajeInformacion(Mensaje.guardado);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Empresa?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Empresa empresa = servicioEmpresa
												.buscar(id);
										List<Paciente> pacientes = servicioPaciente
												.buscarPorEmpresa(empresa);
										List<Consultorio> consultorios = servicioConsultorio
												.buscarPorEmpresa(empresa);
										List<Informe> informe = servicioInforme
												.buscarPorEmpresaTrabajador(empresa);
										List<Informe> informe2 = servicioInforme
												.buscarPorEmpresaBeneficiaria(empresa);
										if (!pacientes.isEmpty()
												|| !consultorios.isEmpty()
												|| !informe.isEmpty()
												|| !informe2.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioEmpresa.eliminar(empresa);
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
		botoneraEmpresa.appendChild(botonera);
	}

	protected void guardarNominas(Empresa empresa) {
		List<EmpresaNomina> empresasNominas = new ArrayList<EmpresaNomina>();
		servicioEmpresaNomina.borrarNominas(empresa);
		if (ltbNominasAgregadas.getItemCount() != 0) {
			for (int i = 0; i < ltbNominasAgregadas.getItemCount(); i++) {
				Listitem listItem = ltbNominasAgregadas.getItemAtIndex(i);
				Integer idNomina = ((Spinner) ((listItem.getChildren().get(2)))
						.getFirstChild()).getValue();
				Nomina nomina = servicioNomina.buscar(idNomina);
				int valor = ((Spinner) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				EmpresaNomina empresaNomina = new EmpresaNomina(empresa,
						nomina, valor);
				empresasNominas.add(empresaNomina);
			}
		}
		servicioEmpresaNomina.guardar(empresasNominas);
	}

	private void listasMultiples() {
		ltbNominas.setMultiple(false);
		ltbNominas.setCheckmark(false);
		ltbNominas.setMultiple(true);
		ltbNominas.setCheckmark(true);
		ltbNominasAgregadas.setMultiple(false);
		ltbNominasAgregadas.setCheckmark(false);
		ltbNominasAgregadas.setMultiple(true);
		ltbNominasAgregadas.setCheckmark(true);
	}

	private void llenarListas() {
		Empresa empresa = servicioEmpresa.buscar(id);
		nominasDisponibles = servicioNomina.buscarDisponibles(empresa);
		ltbNominas.setModel(new ListModelList<Nomina>(nominasDisponibles));
		nominasAgregadas = servicioEmpresaNomina.buscarPorEmpresa(empresa);
		ltbNominasAgregadas.setModel(new ListModelList<EmpresaNomina>(
				nominasAgregadas));
		listasMultiples();
	}

	@Listen("onClick = #pasar1")
	public void derechaNomina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbNominas.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Nomina nomina = listItem.get(i).getValue();
					nominasDisponibles.remove(nomina);
					EmpresaNomina empresaNomina = new EmpresaNomina();
					empresaNomina.setNomina(nomina);
					nominasAgregadas.clear();
					for (int j = 0; j < ltbNominasAgregadas.getItemCount(); j++) {
						Listitem listItemj = ltbNominasAgregadas
								.getItemAtIndex(j);
						Integer idNomina = ((Spinner) ((listItemj.getChildren()
								.get(2))).getFirstChild()).getValue();
						Nomina nominaj = servicioNomina.buscar(idNomina);
						int valor = ((Spinner) ((listItemj.getChildren().get(1)))
								.getFirstChild()).getValue();
						EmpresaNomina empresaNominaj = new EmpresaNomina(null,
								nominaj, valor);
						nominasAgregadas.add(empresaNominaj);
					}
					nominasAgregadas.add(empresaNomina);
					ltbNominasAgregadas
							.setModel(new ListModelList<EmpresaNomina>(
									nominasAgregadas));
					ltbNominasAgregadas.renderAll();
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbNominas.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2")
	public void izquierdaNomina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbNominasAgregadas.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					EmpresaNomina empresaNomina = listItem2.get(i).getValue();
					nominasAgregadas.remove(empresaNomina);
					nominasDisponibles.add(empresaNomina.getNomina());
					ltbNominas.setModel(new ListModelList<Nomina>(
							nominasDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbNominasAgregadas
					.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	private void buscar() {
		buscador = new Buscar<Nomina>(ltbNominas, txtBuscadorNomina) {
			@Override
			protected List<Nomina> buscar(String valor) {
				List<Nomina> nominasFiltradas = new ArrayList<Nomina>();
				List<Nomina> nominas = servicioNomina.filtroNombre(valor);
				for (int i = 0; i < nominasDisponibles.size(); i++) {
					Nomina nomina = nominasDisponibles.get(i);
					for (int j = 0; j < nominas.size(); j++) {
						if (nomina.getIdNomina() == nominas.get(j)
								.getIdNomina())
							nominasFiltradas.add(nomina);
					}
				}
				return nominasFiltradas;
			}
		};
	}

	/* Abre la vista de Nomina */
	@Listen("onClick = #btnAbrirNomina")
	public void abrirNomina() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "nomina");
		map.put("lista", nominasDisponibles);
		map.put("listbox", ltbNominas);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Nomina");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	@Listen("onClick = #btnSiguientePestanna")
	public void siguientePestanna() {
		tabEmpleados.setSelected(true);
	}

	/* Abre la pestanna de especificaciones */
	@Listen("onClick = #btnAnteriorPestanna")
	public void anteriorPestanna() {
		tabBasicos.setSelected(true);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtDireccionCentro.getText().compareTo("") == 0
				|| txtNombreEmpresa.getText().compareTo("") == 0
				|| txtRazon.getText().compareTo("") == 0
				|| txtDireccionRazon.getText().compareTo("") == 0
				|| txtRifEmpresa.getText().compareTo("") == 0
				|| txtNilEmpresa.getText().compareTo("") == 0
				|| txtNroIvssEmpresa.getText().compareTo("") == 0
				|| txtActividadEconomica.getText().compareTo("") == 0
				|| txtTelefonoEmpresa.getText().compareTo("") == 0
				|| spnAdolescentes.getText().compareTo("") == 0
				|| spnAprendices.getText().compareTo("") == 0
				|| spnConapdis.getText().compareTo("") == 0
				|| spnExtranjeros.getText().compareTo("") == 0
				|| spnHombres.getText().compareTo("") == 0
				|| spnLopcymat.getText().compareTo("") == 0
				|| spnMujeres.getText().compareTo("") == 0
				|| spnNroTrabajadores.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (txtTelefonoEmpresa.getText().compareTo("") != 0
					&& !Validador
							.validarTelefono(txtTelefonoEmpresa.getValue())) {
				msj.mensajeError(Mensaje.telefonoInvalido);
				return false;
			} else {
				if (txtTelefonoRepresentante.getText().compareTo("") != 0
						&& !Validador.validarTelefono(txtTelefonoRepresentante
								.getValue())) {
					msj.mensajeError(Mensaje.telefonoInvalido);
					return false;
				} else {
					if (txtTelefono2Representante.getText().compareTo("") != 0
							&& !Validador
									.validarTelefono(txtTelefono2Representante
											.getValue())) {
						msj.mensajeError(Mensaje.telefonoInvalido);
						return false;
					} else
						return true;
				}
			}
		}
	}

	/* Muestra el catalogo de las empresas */
	@Listen("onClick = #btnBuscarEmpresa")
	public void mostrarCatalogo() {
		final List<Empresa> empresas = servicioEmpresa.buscarTodas();
		catalogo = new Catalogo<Empresa>(catalogoEmpresa,
				"Catalogo de Empresas", empresas, false,"Rif", "Nombre", "Direccion") {

			@Override
			protected List<Empresa> buscar(String valor, String combo) {

				switch (combo) {
				case "Rif":
					return servicioEmpresa.filtroRif(valor);
				case "Nombre":
					return servicioEmpresa.filtroNombre(valor);
				case "Direccion":
					return servicioEmpresa.filtroDireccionCentro(valor);
				default:
					return empresas;
				}

			}

			@Override
			protected String[] crearRegistros(Empresa objeto) {
				String[] registros = new String[3];
				registros[0] = objeto.getRif();
				registros[1] = objeto.getNombre();
				registros[2] = objeto.getDireccionCentro();
				return registros;
			}

		};
		catalogo.setParent(catalogoEmpresa);
		catalogo.doModal();
	}

	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefonoEmpresa")
	public void validarTelefono() {
		if (!Validador.validarTelefono(txtTelefonoEmpresa.getValue())) {
			msj.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefonoRepresentante")
	public void validarTelefono1() {
		if (!Validador.validarTelefono(txtTelefonoRepresentante.getValue())) {
			msj.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefono2Representante")
	public void validarTelefono2() {
		if (!Validador.validarTelefono(txtTelefono2Representante.getValue())) {
			msj.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoEmpresa")
	public void seleccinar() {
		Empresa empresa = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(empresa);
		catalogo.setParent(null);
	}

	/* Busca si existe una empresa con el mismo rif escrito */
	@Listen("onChange = #txtRifEmpresa")
	public void buscarPorNombre() {
		Empresa empresa = servicioEmpresa
				.buscarPorRif(txtRifEmpresa.getValue());
		if (empresa != null)
			llenarCampos(empresa);
	}

	/* LLena los campos del formulario dado una empresa */
	private void llenarCampos(Empresa empresa) {
		txtRifEmpresa.setValue(empresa.getRif());
		txtDireccionCentro.setValue(empresa.getDireccionCentro());
		txtNombreEmpresa.setValue(empresa.getNombre());
		txtTelefonoEmpresa.setValue(empresa.getTelefono());
		txtRazon.setValue(empresa.getRazon());
		txtDireccionRazon.setValue(empresa.getDireccionRazon());
		txtNilEmpresa.setValue(empresa.getNil());
		txtNroIvssEmpresa.setValue(empresa.getNroIvss());
		txtCodigoCiiuEmpresa.setValue(empresa.getCodigoCiiu());
		txtActividadEconomica.setValue(empresa.getActividadEconomica());
		txtCorreo.setValue(empresa.getCorreo());
		txtRegistroMercantil.setValue(empresa.getRegistroMercantil());
		txtBajoNroEmpresa.setValue(empresa.getBajoNroEmpresa());
		txtTomoEmpresa.setValue(empresa.getTomoEmpresa());
		txtRepresentanteEmpresa.setValue(empresa.getRepresentanteEmpresa());
		txtCedulaRepresentante.setValue(empresa.getCedulaRepresentante());
		txtTelefonoRepresentante.setValue(empresa.getTelefonoRepresentante());
		txtCargo.setValue(empresa.getCargo());
		txtBajoNro2Empresa.setValue(empresa.getBajoNro2Empresa());
		txtTomo2Empresa.setValue(empresa.getTomo2Empresa());
		txtRepresentante2Empresa.setValue(empresa.getRepresentante2Empresa());
		txtCedula2Representante.setValue(empresa.getCedula2Representante());
		txtTelefono2Representante.setValue(empresa.getTelefono2Representante());
		txtCargo2.setValue(empresa.getCargo2());
		if (empresa.getFechaRegistro() != null)
			dtbFechaRegistro.setValue(empresa.getFechaRegistro());
		if (empresa.getFechaActualizacion() != null)
			dtbFechaActualizacion.setValue(empresa.getFechaActualizacion());
		if (empresa.getNroTrabajadores() != null)
			spnNroTrabajadores.setValue(empresa.getNroTrabajadores());
		if (empresa.getHombres() != null)
			spnHombres.setValue(empresa.getHombres());
		if (empresa.getMujeres() != null)
			spnMujeres.setValue(empresa.getMujeres());
		if (empresa.getAdolescentes() != null)
			spnAdolescentes.setValue(empresa.getAdolescentes());
		if (empresa.getAprendices() != null)
			spnAprendices.setValue(empresa.getAprendices());
		if (empresa.getLopcymat() != null)
			spnLopcymat.setValue(empresa.getLopcymat());
		if (empresa.getConapdis() != null)
			spnConapdis.setValue(empresa.getConapdis());
		if (empresa.getExtranjeros() != null)
			spnExtranjeros.setValue(empresa.getExtranjeros());
		id = empresa.getIdEmpresa();
		llenarListas();
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

	/* Metodo que valida el formmato del correo ingresado */
	@Listen("onChange = #txtCorreo")
	public void validarCorreo() throws IOException {
		if (Validador.validarCorreo(txtCorreo.getValue()) == false) {
			msj.mensajeAlerta(Mensaje.correoInvalido);
		}
	}

	public void recibirNomina(List<Nomina> listaNomina, Listbox lista) {
		ltbNominas = lista;
		nominasDisponibles = listaNomina;
		ltbNominas.setModel(new ListModelList<Nomina>(nominasDisponibles));
		ltbNominas.setMultiple(false);
		ltbNominas.setCheckmark(false);
		ltbNominas.setMultiple(true);
		ltbNominas.setCheckmark(true);
	}
}
