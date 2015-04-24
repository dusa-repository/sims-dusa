package controlador.social;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.maestros.Paciente;
import modelo.social.Ficha;
import modelo.transacciones.Orden;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CFichaMaestra extends CGenerico {

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
	private Div divBotoneraFicha;
	@Wire
	private Div divFichaMaestra;

	// CAMPOS DE FICHA
	@Wire
	private Radiogroup rdgFuera;
	@Wire
	private Radio rdoSiFuera;
	@Wire
	private Radio rdoNoFuera;
	@Wire
	private Radiogroup rdgTurnos;
	@Wire
	private Radio rdoSiTurno;
	@Wire
	private Radio rdoNoTurno;
	@Wire
	private Radiogroup rdgManejaArmas;
	@Wire
	private Radio rdoSiManejaArmas;
	@Wire
	private Radio rdoNoManejaArmas;
	@Wire
	private Radiogroup rdgPorta;
	@Wire
	private Radio rdoSiPorta;
	@Wire
	private Radio rdoNoPorta;
	@Wire
	private Textbox txtPorteArmas;
	@Wire
	private Radiogroup rdgServicio;
	@Wire
	private Radio rdoSiServicioMilitar;
	@Wire
	private Radio rdoNoServicioMilitar;
	@Wire
	private Radiogroup rdgClaseMilitar;
	@Wire
	private Radio rdoSiClaseMilitar;
	@Wire
	private Radio rdoNoClaseMilitar;
	@Wire
	private Textbox txtCarnetMilitar;
	@Wire
	private Combobox cmbGrado;
	@Wire
	private Datebox dtbFechaVenciLicencia;
	@Wire
	private Radiogroup rdgConduce;
	@Wire
	private Radio rdoSiConduce;
	@Wire
	private Radio rdoNoConduce;
	@Wire
	private Radiogroup rdgLicencia;
	@Wire
	private Radio rdoSiLicencia;
	@Wire
	private Radio rdoNoLicencia;
	@Wire
	private Radiogroup rdgCertificado;
	@Wire
	private Radio rdoSiCertificado;
	@Wire
	private Radio rdoNoCertificado;
	@Wire
	private Datebox dtbFechaVenciCertificado;
	@Wire
	private Radiogroup rdgVehiculo;
	@Wire
	private Radio rdoSiVehiculo;
	@Wire
	private Radio rdoNoVehiculo;
	@Wire
	private Combobox cmbTipoVehiculo;
	@Wire
	private Spinner spnCuantosVehiculos;
	@Wire
	private Textbox txtMarcaVehiculo;
	@Wire
	private Textbox txtModeloVehiculo;
	@Wire
	private Textbox txtColorVehiculo;
	@Wire
	private Textbox txtAnnoVehiculo;
	@Wire
	private Textbox txtPlacaVehiculo;
	@Wire
	private Radiogroup rdgVivienda;
	@Wire
	private Radio rdoSiVivienda;
	@Wire
	private Radio rdoNoVivienda;
	@Wire
	private Combobox cmbTipoVivienda;
	@Wire
	private Combobox cmbTenenciaVivienda;
	@Wire
	private Spinner spnCuartos;
	@Wire
	private Combobox cmbCombustible;
	@Wire
	private Combobox cmbServicioAgua;
	@Wire
	private Radiogroup rdgAgua;
	@Wire
	private Radio rdoSiAgua;
	@Wire
	private Radio rdoNoAgua;
	@Wire
	private Radiogroup rdgElectricidad;
	@Wire
	private Radio rdoSiElectricidad;
	@Wire
	private Radio rdoNoElectricidad;
	@Wire
	private Radiogroup rdgCloaca;
	@Wire
	private Radio rdoSiCloaca;
	@Wire
	private Radio rdoNoCloaca;
	@Wire
	private Radiogroup rdgAseo;
	@Wire
	private Radio rdoSiAseo;
	@Wire
	private Radio rdoNoAseo;
	@Wire
	private Combobox cmbBasuraFinal;
	@Wire
	private Combobox cmbJefe;
	@Wire
	private Spinner spnPersonasVivienda;
	@Wire
	private Spinner spnPersonasCarga;
	@Wire
	private Datebox dtbFechaFicha;

	String idPaciente = "";
	String nombre = "";
	Long idFicha = (long) 0;

	Catalogo<Paciente> catalogoPaciente;

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
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divFichaMaestra, nombre, tabs);

			}

			@Override
			public void limpiar() {
				limpiarCampos();

			}

			@Override
			public void guardar() {

				if (!idPaciente.equals("")) {
					String portaArma, nroCarnetMilitar, grado, tipoVehiculo, marcaVehiculo, modelo, color, anno, placa, tipoVivienda, tenenciaVivienda, combustible, servicioAgua, basuraFinal,jefe ;

					Ficha ficha = new Ficha();

					if (rdoSiFuera.isChecked())
						ficha.setTrabajoAfuera(true);

					if (rdoSiTurno.isChecked())
						ficha.setTrabajoTurnos(true);

					if (rdoSiManejaArmas.isChecked())
						ficha.setManejaArmas(true);

					if (rdoSiPorta.isChecked())
						ficha.setPortaArmas(true);

					if (rdoSiClaseMilitar.isChecked())
						ficha.setClaseMilitar(true);

					if (rdoSiServicioMilitar.isChecked())
						ficha.setServicioMilitar(true);

					if (rdoSiLicencia.isChecked())
						ficha.setPoseeLicencia(true);

					if (rdoSiConduce.isChecked())
						ficha.setConduceVehiculo(true);

					if (rdoSiCertificado.isChecked())
						ficha.setCertificadoMedico(true);

					if (rdoSiVehiculo.isChecked())
						ficha.setPoseeVehiculo(true);

					if (rdoSiVivienda.isChecked())
						ficha.setViviendaPropia(true);

					if (rdoSiAgua.isChecked())
						ficha.setAgua(true);

					if (rdoSiAseo.isChecked())
						ficha.setAseo(true);

					if (rdoSiCloaca.isChecked())
						ficha.setCloacas(true);

					if (rdoSiElectricidad.isChecked())
						ficha.setElectricidad(true);

					Timestamp fechaVenciLicencia = new Timestamp(
							dtbFechaVenciLicencia.getValue().getTime());

					Timestamp fechaVenciCertificado = new Timestamp(
							dtbFechaVenciCertificado.getValue().getTime());
					
					Timestamp fechaFicha = new Timestamp(
							dtbFechaFicha.getValue().getTime());

					if (spnCuantosVehiculos.getValue() != null)
						ficha.setVehiculos(spnCuantosVehiculos.getValue());

					if (spnCuartos.getValue() != null)
						ficha.setCuartos(spnCuartos.getValue());
					
					if (spnPersonasCarga.getValue() != null)
						ficha.setPersonasCarga(spnPersonasCarga.getValue());
					
					if (spnPersonasVivienda.getValue() != null)
						ficha.setPersonasVivienda(spnPersonasVivienda.getValue());

					portaArma = txtPorteArmas.getValue();
					nroCarnetMilitar = txtCarnetMilitar.getValue();
					grado = cmbGrado.getValue();
					marcaVehiculo = txtMarcaVehiculo.getValue();
					tipoVehiculo = cmbTipoVehiculo.getValue();
					modelo = txtModeloVehiculo.getValue();
					color = txtColorVehiculo.getValue();
					anno = txtAnnoVehiculo.getValue();
					placa = txtPlacaVehiculo.getValue();
					tenenciaVivienda = cmbTenenciaVivienda.getValue();
					tipoVivienda = cmbTipoVivienda.getValue();
					combustible = cmbCombustible.getValue();
					servicioAgua = cmbServicioAgua.getValue();
					jefe = cmbJefe.getValue();
					basuraFinal = cmbBasuraFinal.getValue();

					ficha.setNroCarnetMilitar(nroCarnetMilitar);
					ficha.setNroPorteArmas(portaArma);
					ficha.setGradoLicencia(grado);
					ficha.setFechaLicencia(fechaVenciLicencia);
					ficha.setFechaCertificado(fechaVenciCertificado);
					ficha.setMarcaVehiculo(marcaVehiculo);
					ficha.setTipoVehiculo(tipoVehiculo);
					ficha.setPlacaVehiculo(placa);
					ficha.setAnnoVehiculo(anno);
					ficha.setColorVehiculo(color);
					ficha.setModeloVehiculo(modelo);
					ficha.setTenenciaVivienda(tenenciaVivienda);
					ficha.setTipoVivienda(tipoVivienda);
					ficha.setCombustibleCocinar(combustible);
					ficha.setServicioAgua(servicioAgua);
					ficha.setBasuraFinal(basuraFinal);
					ficha.setJefe(jefe);
					ficha.setFechaFicha(fechaFicha);

					ficha.setIdFicha(idFicha);

					Paciente paciente = servicioPaciente
							.buscarPorCedula(idPaciente);
					if (paciente != null)
						ficha.setPaciente(paciente);

					servicioFicha.guardar(ficha);
					limpiarCampos();
					msj.mensajeInformacion(Mensaje.guardado);

				} else
					msj.mensajeError("Debe seleccionar un trabajador");

			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		divBotoneraFicha.appendChild(botonera);

	}

	@Listen("onOK = #txtCedula")
	public void buscarCedula() {
		Paciente paciente = new Paciente();
		paciente = servicioPaciente.buscarPorCedulaYTrabajador(txtCedula
				.getValue());
		limpiarCampos();
		if (paciente != null) {
			llenarCampos(paciente);
			llenarCamposFicha(paciente.getFichaMaestra());
			idPaciente = paciente.getCedula();
			llenarFamiliares();
		} else {
			Mensaje.mensajeError(Mensaje.pacienteNoExiste);
		}
	}

	private void llenarCamposFicha(Ficha ficha) {

		if (ficha != null) {

			idFicha = ficha.getIdFicha();

			if (ficha.isTrabajoAfuera())
				rdoSiFuera.setChecked(true);
			else
				rdoNoFuera.setChecked(true);

			if (ficha.isTrabajoTurnos())
				rdoSiTurno.setChecked(true);
			else
				rdoNoTurno.setChecked(true);

			if (ficha.isPortaArmas())
				rdoSiPorta.setChecked(true);
			else
				rdoNoPorta.setChecked(true);

			if (ficha.isManejaArmas())
				rdoSiManejaArmas.setChecked(true);
			else
				rdoNoManejaArmas.setChecked(true);

			if (ficha.isClaseMilitar())
				rdoSiClaseMilitar.setChecked(true);
			else
				rdoNoClaseMilitar.setChecked(true);

			if (ficha.isServicioMilitar())
				rdoSiServicioMilitar.setChecked(true);
			else
				rdoNoServicioMilitar.setChecked(true);

			if (ficha.isPoseeLicencia())
				rdoSiLicencia.setChecked(true);
			else
				rdoNoLicencia.setChecked(true);

			if (ficha.isConduceVehiculo())
				rdoSiConduce.setChecked(true);
			else
				rdoNoConduce.setChecked(true);

			if (ficha.isCertificadoMedico())
				rdoSiCertificado.setChecked(true);
			else
				rdoNoCertificado.setChecked(true);

			if (ficha.isPoseeVehiculo())
				rdoSiVehiculo.setChecked(true);
			else
				rdoNoVehiculo.setChecked(true);

			if (ficha.isViviendaPropia())
				rdoSiVivienda.setChecked(true);
			else
				rdoNoVivienda.setChecked(true);

			if (ficha.isAgua())
				rdoSiAgua.setChecked(true);
			else
				rdoNoAgua.setChecked(true);

			if (ficha.isAseo())
				rdoSiAseo.setChecked(true);
			else
				rdoNoAseo.setChecked(true);

			if (ficha.isElectricidad())
				rdoSiElectricidad.setChecked(true);
			else
				rdoNoElectricidad.setChecked(true);

			if (ficha.isCloacas())
				rdoSiCloaca.setChecked(true);
			else
				rdoNoCloaca.setChecked(true);

			txtCarnetMilitar.setValue(ficha.getNroCarnetMilitar());
			txtPorteArmas.setValue(ficha.getNroPorteArmas());
			txtMarcaVehiculo.setValue(ficha.getMarcaVehiculo());
			cmbTipoVehiculo.setValue(ficha.getTipoVehiculo());
			cmbGrado.setValue(ficha.getGradoLicencia());
			dtbFechaVenciLicencia.setValue(ficha.getFechaLicencia());
			dtbFechaVenciCertificado.setValue(ficha.getFechaCertificado());
			txtModeloVehiculo.setValue(ficha.getModeloVehiculo());
			txtAnnoVehiculo.setValue(ficha.getAnnoVehiculo());
			txtColorVehiculo.setValue(ficha.getColorVehiculo());
			txtPlacaVehiculo.setValue(ficha.getPlacaVehiculo());
			cmbTenenciaVivienda.setValue(ficha.getTenenciaVivienda());
			cmbTipoVivienda.setValue(ficha.getTipoVivienda());
			cmbCombustible.setValue(ficha.getCombustibleCocinar());
			cmbServicioAgua.setValue(ficha.getServicioAgua());
			cmbBasuraFinal.setValue(ficha.getBasuraFinal());
			cmbJefe.setValue(ficha.getJefe());
			dtbFechaFicha.setValue(ficha.getFechaFicha());

			if (ficha.getVehiculos() != null)
				spnCuantosVehiculos.setValue(ficha.getVehiculos());
			if (ficha.getCuartos() != null)
				spnCuartos.setValue(ficha.getCuartos());
			if (ficha.getPersonasCarga() != null)
				spnPersonasCarga.setValue(ficha.getPersonasCarga());
			if (ficha.getPersonasVivienda() != null)
				spnPersonasVivienda.setValue(ficha.getPersonasVivienda());
		}
	}

	private void llenarFamiliares() {
		// TODO Auto-generated method stub

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
		rdoNoFuera.setChecked(false);
		rdoSiFuera.setChecked(false);
		rdoSiPorta.setChecked(false);
		rdoNoPorta.setChecked(false);
		rdoSiTurno.setChecked(false);
		rdoNoTurno.setChecked(false);
		rdoSiManejaArmas.setChecked(false);
		rdoNoManejaArmas.setChecked(false);
		rdoSiClaseMilitar.setChecked(false);
		rdoNoClaseMilitar.setChecked(false);
		rdoSiServicioMilitar.setChecked(false);
		rdoNoServicioMilitar.setChecked(false);
		rdoSiConduce.setChecked(false);
		rdoNoConduce.setChecked(false);
		rdoSiLicencia.setChecked(false);
		rdoNoLicencia.setChecked(false);
		rdoSiCertificado.setChecked(false);
		rdoNoCertificado.setChecked(false);
		rdoSiVehiculo.setChecked(false);
		rdoNoVehiculo.setChecked(false);
		rdoSiVivienda.setChecked(false);
		rdoNoVivienda.setChecked(false);
		rdoSiAgua.setChecked(false);
		rdoNoAgua.setChecked(false);
		rdoSiAseo.setChecked(false);
		rdoNoAseo.setChecked(false);
		rdoSiElectricidad.setChecked(false);
		rdoNoElectricidad.setChecked(false);
		rdoSiCloaca.setChecked(false);
		rdoNoCloaca.setChecked(false);
		cmbTipoVehiculo.setValue("");
		spnCuantosVehiculos.setValue(0);
		txtMarcaVehiculo.setValue("");
		cmbGrado.setValue("");
		dtbFechaVenciLicencia.setValue(fecha);
		dtbFechaVenciCertificado.setValue(fecha);
		txtCarnetMilitar.setValue("");
		txtPorteArmas.setValue("");
		txtModeloVehiculo.setValue("");
		txtAnnoVehiculo.setValue("");
		txtColorVehiculo.setValue("");
		txtPlacaVehiculo.setValue("");
		cmbTenenciaVivienda.setValue("");
		cmbTipoVivienda.setValue("");
		spnCuartos.setValue(0);
		cmbCombustible.setValue("");
		cmbServicioAgua.setValue("");
		spnPersonasCarga.setValue(0);
		spnPersonasVivienda.setValue(0);
		cmbJefe.setValue("");
		cmbBasuraFinal.setValue("");
		dtbFechaFicha.setValue(fecha);

	}

	@Listen("onClick =  #btnBuscarPaciente")
	public void buscarTrabajador(Event e) {
		final List<Paciente> pacientes = servicioPaciente
				.buscarTodosTrabajadores();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPaciente,
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
		catalogoPaciente.setParent(divCatalogoPaciente);
		catalogoPaciente.doModal();
	}

	@Listen("onSeleccion = #divCatalogoPaciente")
	public void seleccionPaciente() {
		Paciente paciente = catalogoPaciente.objetoSeleccionadoDelCatalogo();
		idPaciente = paciente.getCedula();
		llenarCampos(paciente);
		llenarCamposFicha(paciente.getFichaMaestra());
		catalogoPaciente.setParent(null);
	}
}
