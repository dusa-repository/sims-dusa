package controlador.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.maestros.Medicina;
import modelo.maestros.Paciente;
import modelo.seguridad.Arbol;
import modelo.transacciones.PacienteMedicina;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;
import componentes.Mensaje;

public class CCronicos extends CGenerico {

	private static final long serialVersionUID = -8967604751368729529L;

	@Wire
	private Image imagenPaciente;
	@Wire
	private Label lblNombres;
	@Wire
	private Label lblCedula;
	@Wire
	private Label lblApellidos;
	@Wire
	private Label lblFicha;
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
	private Label lblCiudad;
	@Wire
	private Label lblEstado;
	@Wire
	private Label lblPais;
	@Wire
	private Label lblParroquia;
	@Wire
	private Label lblMunicipio;
	@Wire
	private Label lblDireccion;
	@Wire
	private Label lblTelefono1;
	@Wire
	private Label lblTelefono2;
	@Wire
	private Label lblCorreo;
	@Wire
	private Label lblTelefonoAdicional;
	@Wire
	private Label lblCorreoEmpresa;
	@Wire
	private Label lblNivelEducativo;
	@Wire
	private Label lblOficio;
	@Wire
	private Label lblCarrera;
	@Wire
	private Label lblArea;
	@Wire
	private Label lblEtiquetaAdicionales;
	@Wire
	private Label lblEtiquetaTipo;
	@Wire
	private Label lblEtiquetaOrigen;
	@Wire
	private Label lblEtiquetaAlergias;
	@Wire
	private Label lblRif;
	@Wire
	private Label lblPasaporte;
	@Wire
	private Label lblEstudia;
	@Wire
	private Label lblCarreraEstudios;
	@Wire
	private Label lblPeriodo;
	@Wire
	private Label lblLugarEstudios;
	@Wire
	private Textbox txtCedula;
	@Wire
	private Div divCatalogoPaciente;
	@Wire
	private Div divBotoneraCronicos;
	@Wire
	private Div divCronicos;
	@Wire
	private Listbox ltbMedicinas;
	@Wire
	private Listbox ltbMedicinasAgregadas;
	@Wire
	private Textbox txtBuscadorMedicina;

	private CArbol cArbol = new CArbol();
	Buscar<Medicina> buscarMedicina;
	String idPaciente = "";
	String nombre = "";
	Long idFicha = (long) 0;

	Catalogo<Paciente> catalogoPaciente;
	List<Medicina> medicinasDisponibles = new ArrayList<Medicina>();
	List<PacienteMedicina> medicinasAgregadas = new ArrayList<PacienteMedicina>();

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				nombre = (String) mapa.get("titulo");
				mapa.clear();
				mapa = null;
			}
		}
		buscadorMedicina();
		llenarMedicinas();

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divCronicos, nombre, tabs);

			}

			@Override
			public void limpiar() {
				limpiarCampos();

			}

			@Override
			public void guardar() {
				if(!idPaciente.equals(""))
				{
				Paciente paciente = servicioPaciente
						.buscarPorCedula(idPaciente);
				paciente.setCronico(true);
				servicioPaciente.guardar(paciente);
				guardarMedicinas(paciente);
				limpiarCampos();
				msj.mensajeInformacion(Mensaje.guardado);
				}
				else
					msj.mensajeError(Mensaje.noSeleccionoRegistro);
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};

		botonera.getChildren().get(1).setVisible(false);
		divBotoneraCronicos.appendChild(botonera);

	}

	private void llenarCampos(Paciente paciente) {
		txtCedula.setValue(paciente.getCedula());
		lblNombres.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getSegundoNombre());
		lblApellidos.setValue(paciente.getPrimerApellido() + " "
				+ paciente.getSegundoApellido());
		lblCedula.setValue(paciente.getCedula());
		lblCiudad.setValue(paciente.getCiudadVivienda().getNombre());
		lblEstado
				.setValue(paciente.getCiudadVivienda().getEstado().getNombre());
		lblPais.setValue(paciente.getCiudadVivienda().getEstado().getPais()
				.getNombre());
		lblMunicipio.setValue(paciente.getMunicipio());
		lblParroquia.setValue(paciente.getParroquia());
		lblFicha.setValue(paciente.getFicha());
		lblAlergias.setValue(paciente.getObservacionAlergias());
		lblFechaNac.setValue(String.valueOf(formatoFecha.format(paciente
				.getFechaNacimiento())));
		lblLugarNac.setValue(paciente.getLugarNacimiento());
		lblSexo.setValue(paciente.getSexo());
		if (paciente.getEstadoCivil() != null)
			lblEstadoCivil.setValue(paciente.getEstadoCivil().getNombre());
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
		lblTelefonoAdicional.setValue(paciente.getTelefonoAdicional());
		lblCorreoEmpresa.setValue(paciente.getEmailEmpresa());
		lblEdad.setValue(String.valueOf(calcularEdad(paciente
				.getFechaNacimiento())));
		lblEstatura.setValue(String.valueOf(paciente.getEstatura()));
		lblPeso.setValue(String.valueOf(paciente.getPeso()));
		lblNivelEducativo.setValue(paciente.getNivelEducativo());
		lblOficio.setValue(paciente.getOficio());
		lblCarrera.setValue(paciente.getProfesion());
		lblCarreraEstudios.setValue(paciente.getCargoOCarrera());
		lblLugarEstudios.setValue(paciente.getLugarTrabajo());
		lblPeriodo.setValue(paciente.getPeriodoEstudios());

		if (paciente.getArea() != null)
			lblArea.setValue(paciente.getArea().getNombre());
		if (paciente.isAlergia()) {
			lblAlergico.setValue("SI");
			lblEtiquetaAlergias.setVisible(true);
		} else {
			lblAlergico.setValue("NO");
			lblEtiquetaAlergias.setVisible(false);
		}

		if (paciente.isDiscapacidad()) {
			lblDiscapacidad.setValue("SI");
			lblEtiquetaOrigen.setVisible(true);
			lblEtiquetaAdicionales.setVisible(true);
			lblEtiquetaTipo.setVisible(true);
		} else {
			lblDiscapacidad.setValue("NO");
			lblEtiquetaOrigen.setVisible(false);
			lblEtiquetaAdicionales.setVisible(false);
			lblEtiquetaTipo.setVisible(false);
		}

		if (paciente.isLentes())
			lblLentes.setValue("SI");
		else
			lblLentes.setValue("NO");

		if (paciente.isEstudia())
			lblEstudia.setValue("SI");
		else
			lblEstudia.setValue("NO");

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

		lblRif.setValue(paciente.getRif());
		lblPasaporte.setValue(paciente.getPasaporte());
		idPaciente = paciente.getCedula();

	}

	private void limpiarCampos() {

		lblEstudia.setValue("");
		lblCarreraEstudios.setValue("");
		lblLugarEstudios.setValue("");
		lblPeriodo.setValue("");
		txtCedula.setValue("");
		lblNombres.setValue("");
		lblCedula.setValue("");
		lblApellidos.setValue("");
		imagenPaciente.setVisible(false);
		lblFicha.setValue("");
		lblAlergico.setValue("");
		lblLugarNac.setValue("");
		lblSexo.setValue("");
		lblEstadoCivil.setValue("");
		lblEstado.setValue("");
		lblPais.setValue("");
		lblMunicipio.setValue("");
		lblParroquia.setValue("");
		lblGrupoSanguineo.setValue("");
		lblMano.setValue("");
		lblOrigen.setValue("");
		lblTipoDiscapacidad.setValue("");
		lblObservacionDiscapacidad.setValue("");
		lblDireccion.setValue("");
		lblTelefono1.setValue("");
		lblTelefono2.setValue("");
		lblCorreo.setValue("");
		lblTelefonoAdicional.setValue("");
		lblCorreoEmpresa.setValue("");
		lblPeso.setValue("");
		lblEdad.setValue("");
		lblEstatura.setValue("");
		lblCiudad.setValue("");
		lblAlergias.setValue("");
		lblDiscapacidad.setValue("");
		lblLentes.setValue("");
		lblArea.setValue("");
		lblOficio.setValue("");
		lblCarrera.setValue("");
		lblNivelEducativo.setValue("");
		lblRif.setValue("");
		lblPasaporte.setValue("");
		idPaciente="";
		llenarMedicinas();
	}

	@Listen("onClick =  #btnBuscarPaciente")
	public void buscarTrabajador(Event e) {
		final List<Paciente> pacientes = servicioPaciente
				.buscarTodosActivos();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPaciente,
				"Catalogo de Trabajadores", pacientes, false, "Cedula",
				"Primer Nombre", "Segundo Nombre", "Primer Apellido",
				"Segundo Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
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
		catalogoPaciente.setParent(divCatalogoPaciente);
		catalogoPaciente.doModal();
	}

	@Listen("onSeleccion = #divCatalogoPaciente")
	public void seleccionPaciente() {
		limpiarCampos();
		Paciente paciente = catalogoPaciente.objetoSeleccionadoDelCatalogo();
		idPaciente = paciente.getCedula();
		llenarCampos(paciente);
		llenarMedicinas();
		catalogoPaciente.setParent(null);
	}

	@Listen("onOK =#txtCedula; onChange =#txtCedula")
	public void buscarCedula() {
		Paciente paciente = new Paciente();
		paciente = servicioPaciente.buscarPorCedulaYTrabajador(txtCedula
				.getValue());
		limpiarCampos();
		if (paciente != null) {
			llenarCampos(paciente);
			idPaciente = paciente.getCedula();
			llenarMedicinas();
		} else {
			Mensaje.mensajeError("El trabajador no existe");
		}
	}

	protected void guardarMedicinas(Paciente paciente) {
		servicioPacienteMedicina.limpiarMedicinas(paciente);
		List<PacienteMedicina> listaMedicina = new ArrayList<PacienteMedicina>();
		for (int i = 0; i < ltbMedicinasAgregadas.getItemCount(); i++) {
			Listitem listItem = ltbMedicinasAgregadas.getItemAtIndex(i);
			Integer idMedicina = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Medicina medicina = servicioMedicina.buscar(idMedicina);
			String valor = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			PacienteMedicina consultaMedicina = new PacienteMedicina(paciente,
					medicina, valor);
			listaMedicina.add(consultaMedicina);
		}
		servicioPacienteMedicina.guardar(listaMedicina);
	}

	private void llenarMedicinas() {
		Paciente paciente = servicioPaciente.buscarPorCedula(idPaciente);
		medicinasDisponibles = servicioMedicina
				.buscarDisponiblesPaciente(paciente);
		ltbMedicinas
				.setModel(new ListModelList<Medicina>(medicinasDisponibles));

		medicinasAgregadas = servicioPacienteMedicina
				.buscarPorPaciente(paciente);
		ltbMedicinasAgregadas.setModel(new ListModelList<PacienteMedicina>(
				medicinasAgregadas));
		listasMultiples();
	}

	public void recibirMedicina(List<Medicina> lista, Listbox l) {
		ltbMedicinas = l;
		medicinasDisponibles = lista;
		ltbMedicinas
				.setModel(new ListModelList<Medicina>(medicinasDisponibles));
		ltbMedicinas.setMultiple(false);
		ltbMedicinas.setCheckmark(false);
		ltbMedicinas.setMultiple(true);
		ltbMedicinas.setCheckmark(true);
	}

	@Listen("onClick = #btnAbrirMedicina")
	public void divMedicina() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "paciente");
		map.put("lista", medicinasDisponibles);
		map.put("listbox", ltbMedicinas);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Medicina");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
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

	@Listen("onClick = #pasar1Medicina")
	public void derechaMedicina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbMedicinas.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Medicina medicina = listItem.get(i).getValue();
					medicinasDisponibles.remove(medicina);
					PacienteMedicina pacienteMedicina = new PacienteMedicina();
					pacienteMedicina.setMedicina(medicina);
					medicinasAgregadas.clear();
					for (int j = 0; j < ltbMedicinasAgregadas.getItemCount(); j++) {
						Listitem listItemj = ltbMedicinasAgregadas
								.getItemAtIndex(j);
						Integer idMedicina = ((Spinner) ((listItemj
								.getChildren().get(2))).getFirstChild())
								.getValue();
						Medicina medicinaj = servicioMedicina
								.buscar(idMedicina);
						String valor = ((Textbox) ((listItemj.getChildren()
								.get(1))).getFirstChild()).getValue();
						PacienteMedicina pacienteMedicinaj = new PacienteMedicina(
								null, medicinaj, valor);
						medicinasAgregadas.add(pacienteMedicinaj);
					}
					medicinasAgregadas.add(pacienteMedicina);
					ltbMedicinasAgregadas
							.setModel(new ListModelList<PacienteMedicina>(
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

	private void listasMultiples() {
		ltbMedicinas.setMultiple(false);
		ltbMedicinas.setCheckmark(false);
		ltbMedicinas.setMultiple(true);
		ltbMedicinas.setCheckmark(true);
		ltbMedicinasAgregadas.setMultiple(false);
		ltbMedicinasAgregadas.setCheckmark(false);
		ltbMedicinasAgregadas.setMultiple(true);
		ltbMedicinasAgregadas.setCheckmark(true);
	}

	@Listen("onClick = #pasar2Medicina")
	public void izquierdaMedicina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbMedicinasAgregadas.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					PacienteMedicina consultaMedicina = listItem2.get(i)
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

}
