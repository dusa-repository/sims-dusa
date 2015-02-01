package controlador.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;
import modelo.transacciones.PacienteMedicina;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CPacientes extends CGenerico {

	@Wire
	private Div divRPacientes;
	@Wire
	private Div botoneraRPacientes;
	@Wire
	private Row rowTrabajador;
	@Wire
	private Row rowEdad;
	@Wire
	private Row rowDiscapacitado;
	@Wire
	private Row rowFamiliar;
	@Wire
	private Button btnBuscarTrabajador;
	@Wire
	private Label lblTrabajador;
	@Wire
	private Div divCatalogoTrabajador;
	@Wire
	private Combobox cmbTipo;
	@Wire
	private Combobox cmbParentesco;
	@Wire
	private Spinner spnDe;
	@Wire
	private Spinner spnA;
	@Wire
	private Combobox cmbSexo;
	@Wire
	private Radiogroup rdgFamiliar;
	@Wire
	private Radio rdoFamiliares;
	@Wire
	private Radio rdoTrabajadores;
	@Wire
	private Radio rdoTodos;
	@Wire
	private Radiogroup rdgDiscapacitado;
	@Wire
	private Radio rdoSi;
	@Wire
	private Radio rdoNo;
	@Wire
	private Radio rdoTodosDiscapacitado;

	private String tipo = "";
	private String titulo = "";
	private String idTrabajador = "";
	Catalogo<Paciente> catalogo;

	public String getTitulo() {
		return titulo;
	}

	@Override
	public void inicializar() throws IOException {
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

		switch (titulo) {
		case "Familiares":
			rowTrabajador.setVisible(true);
			rowEdad.setVisible(true);
			rowFamiliar.setVisible(false);
			rowDiscapacitado.setVisible(false);
			tipo = "1";
			break;
		case "Pacientes":
			rowTrabajador.setVisible(false);
			rowEdad.setVisible(true);
			rowFamiliar.setVisible(true);
			rowDiscapacitado.setVisible(true);
			tipo = "2";
			break;
		case "Pacientes Cronicos":
			rowTrabajador.setVisible(false);
			rowEdad.setVisible(false);
			rowFamiliar.setVisible(true);
			rowDiscapacitado.setVisible(false);
			tipo = "3";
			break;
		}
		Botonera botonera = new Botonera() {

			@Override
			public void guardar() {
				switch (tipo) {
				case "1":
					if (validarFamiliar())
						reporteFamiliar();
					break;
				case "2":
					if (validarPaciente())
						reportePaciente();
					break;
				case "3":
					if (validarCronico())
						reporteCronico();
					break;
				}

			}

			@Override
			public void limpiar() {
				cmbSexo.setValue("TODOS");
				cmbParentesco.setValue("TODOS");
				cmbTipo.setValue("PDF");
				idTrabajador = "";
				spnA.setValue(100);
				spnDe.setValue(0);
				rdoFamiliares.setChecked(false);
				rdoTodos.setChecked(false);
				rdoTrabajadores.setChecked(false);
				rdoSi.setChecked(false);
				rdoNo.setChecked(false);
				rdoTodosDiscapacitado.setChecked(false);
				lblTrabajador.setValue("");

			}

			@Override
			public void salir() {
				cerrarVentana(divRPacientes, titulo, tabs);

			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}

		};
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Reporte");
		guardar.setSrc("/public/imagenes/botones/reporte.png");
		botonera.getChildren().get(1).setVisible(false);
		botoneraRPacientes.appendChild(botonera);

	}

	private boolean validarCronico() {
		if (!rdoFamiliares.isChecked() && !rdoTrabajadores.isChecked()
				&& !rdoTodos.isChecked()) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	private boolean validarPaciente() {
		if ((!rdoFamiliares.isChecked() && !rdoTrabajadores.isChecked() && !rdoTodos
				.isChecked())
				|| (!rdoSi.isChecked() && !rdoNo.isChecked() && !rdoTodosDiscapacitado
						.isChecked())
				|| spnA.getText().compareTo("") == 0
				|| spnDe.getText().compareTo("") == 0
				|| cmbSexo.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	private boolean validarFamiliar() {
		if (spnA.getText().compareTo("") == 0
				|| spnDe.getText().compareTo("") == 0
				|| cmbSexo.getText().compareTo("") == 0
				|| idTrabajador.equals("")) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	private void reporteFamiliar() {
		String sexo = cmbSexo.getValue();
		int dea = spnDe.getValue();
		int aa = spnA.getValue();
		String de = String.valueOf(spnDe.getValue());
		String a = String.valueOf(spnA.getValue());
		String parentesco = cmbParentesco.getValue();
		String tipoReporte = cmbTipo.getValue();

		if ((sexo.equals("TODOS") && parentesco.equals("TODOS")
				&& idTrabajador.equals("TODOS") && getServicioPaciente()
				.buscarPorEdadesTrabajador(dea, aa, false).isEmpty())
				|| (!sexo.equals("TODOS") && !parentesco.equals("TODOS")
						&& idTrabajador.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesParentescoySexo(dea, aa, parentesco,
								sexo).isEmpty())
				|| (!sexo.equals("TODOS") && parentesco.equals("TODOS")
						&& idTrabajador.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesySexo(dea, aa, sexo).isEmpty())
				|| (sexo.equals("TODOS") && !parentesco.equals("TODOS")
						&& idTrabajador.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesyParentesco(dea, aa, parentesco)
						.isEmpty())
				|| (sexo.equals("TODOS") && parentesco.equals("TODOS")
						&& !idTrabajador.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesyTrabajador(dea, aa, idTrabajador)
						.isEmpty())
				|| (!sexo.equals("TODOS") && parentesco.equals("TODOS")
						&& !idTrabajador.equals("TODOS") && getServicioPaciente()
						.buscarCono(dea, aa, idTrabajador, sexo).isEmpty())
				|| (!sexo.equals("TODOS") && !parentesco.equals("TODOS")
						&& !idTrabajador.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorParentescoSexo(dea, aa,
								idTrabajador, parentesco, sexo).isEmpty())
				|| (!parentesco.equals("TODOS")
						&& !idTrabajador.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadoryParentesco(dea, aa,
								idTrabajador, parentesco).isEmpty()))
			msj.mensajeAlerta(Mensaje.noHayRegistros);
		else
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=24&valor6="
					+ de
					+ "&valor7="
					+ a
					+ "&valor8="
					+ sexo
					+ "&valor9="
					+ parentesco
					+ "&valor10="
					+ idTrabajador
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");

	}

	public byte[] reporteFamiliares(String de, String a, String sexo,
			String parentesco, String idTrabajador, String tipoReporte)
			throws JRException {
		byte[] fichero = null;
		int dea = Integer.valueOf(de);
		int aa = Integer.valueOf(a);

		List<Paciente> pacientes = new ArrayList<Paciente>();

		if (sexo.equals("TODOS") && parentesco.equals("TODOS")
				&& idTrabajador.equals("TODOS"))
			pacientes = getServicioPaciente().buscarPorEdadesTrabajador(dea,
					aa, false);
		else {
			if (!sexo.equals("TODOS") && !parentesco.equals("TODOS")
					&& idTrabajador.equals("TODOS"))
				pacientes = getServicioPaciente()
						.buscarPorEdadesParentescoySexo(dea, aa, parentesco,
								sexo);
			else {
				if (!sexo.equals("TODOS") && parentesco.equals("TODOS")
						&& idTrabajador.equals("TODOS"))
					pacientes = getServicioPaciente().buscarPorEdadesySexo(dea,
							aa, sexo);
				else {
					if (sexo.equals("TODOS") && !parentesco.equals("TODOS")
							&& idTrabajador.equals("TODOS"))
						pacientes = getServicioPaciente()
								.buscarPorEdadesyParentesco(dea, aa, parentesco);
					else {
						if (sexo.equals("TODOS") && parentesco.equals("TODOS")
								&& !idTrabajador.equals("TODOS"))
							pacientes = getServicioPaciente()
									.buscarPorEdadesyTrabajador(dea, aa,
											idTrabajador);
						else {
							if (!sexo.equals("TODOS")
									&& parentesco.equals("TODOS")
									&& !idTrabajador.equals("TODOS"))
								pacientes = getServicioPaciente().buscarCono(
										dea, aa, idTrabajador, sexo);
							else {
								if (!sexo.equals("TODOS")
										&& !parentesco.equals("TODOS")
										&& !idTrabajador.equals("TODOS"))
									pacientes = getServicioPaciente()
											.buscarPorEdadesTrabajadorParentescoSexo(
													dea, aa, idTrabajador,
													parentesco, sexo);
								else
									pacientes = getServicioPaciente()
											.buscarPorEdadesTrabajadoryParentesco(
													dea, aa, idTrabajador,
													parentesco);
							}
						}
					}
				}
			}
		}

		Map p = new HashMap();
		p.put("de", de);
		p.put("a", a);

		for (int i = 0; i < pacientes.size(); i++) {

			Paciente paci = pacientes.get(i);
			Paciente trabaja = getServicioPaciente().buscarPorCedula(
					paci.getCedulaFamiliar());
			if (trabaja != null)
				paci.setCedulaFamiliar(trabaja.getCedula() + " "
						+ trabaja.getPrimerNombre() + " "
						+ trabaja.getPrimerApellido());
			else {
				pacientes.remove(i);
				i--;
			}
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RFamiliares.jasper"));
		if (tipoReporte.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(pacientes));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
			try {
				exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return xlsReport.toByteArray();
		} else {
			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(pacientes));
			return fichero;
		}
	}

	private void reportePaciente() {
		String sexo = cmbSexo.getValue();
		int dea = spnDe.getValue();
		int aa = spnA.getValue();
		String de = String.valueOf(spnDe.getValue());
		String a = String.valueOf(spnA.getValue());
		String tipoReporte = cmbTipo.getValue();
		String tipoPaciente = "";
		String discapacitados = "";
		if (rdoFamiliares.isChecked())
			tipoPaciente = "Familiares";
		else {
			if (rdoTrabajadores.isChecked())
				tipoPaciente = "Trabajadores";
			else
				tipoPaciente = "TODOS";
		}

		if (rdoSi.isChecked())
			discapacitados = "SI";
		else {
			if (rdoNo.isChecked())
				discapacitados = "NO";
			else
				discapacitados = "TODOS";
		}

		if ((tipoPaciente.equals("TODOS") && discapacitados.equals("TODOS")
				&& sexo.equals("TODOS") && getServicioPaciente()
				.buscarPorEdadesTodos(dea, aa).isEmpty())
				|| (tipoPaciente.equals("TODOS")
						&& discapacitados.equals("TODOS")
						&& !sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesySexo(dea, aa, sexo).isEmpty())
				|| (discapacitados.equals("TODOS")
						&& tipoPaciente.equals("Familiares")
						&& sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajador(dea, aa, false).isEmpty())
				|| (discapacitados.equals("TODOS")
						&& tipoPaciente.equals("Trabajadores")
						&& sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajador(dea, aa, true).isEmpty())
				|| (discapacitados.equals("TODOS")
						&& tipoPaciente.equals("Familiares")
						&& !sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorSexo(dea, aa, false, sexo)
						.isEmpty())
				|| (discapacitados.equals("TODOS")
						&& tipoPaciente.equals("Trabajadores")
						&& !sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorSexo(dea, aa, true, sexo)
						.isEmpty())
				|| (discapacitados.equals("SI")
						&& tipoPaciente.equals("Familiares")
						&& sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorDiscapacidad(dea, aa, false,
								true).isEmpty())
				|| (discapacitados.equals("SI")
						&& tipoPaciente.equals("Trabajadores")
						&& sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorDiscapacidad(dea, aa, true,
								true).isEmpty())
				|| (discapacitados.equals("NO")
						&& tipoPaciente.equals("Familiares")
						&& sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorDiscapacidad(dea, aa, false,
								false).isEmpty())
				|| (discapacitados.equals("NO")
						&& tipoPaciente.equals("Trabajadores")
						&& sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorDiscapacidad(dea, aa, true,
								false).isEmpty())
				|| (discapacitados.equals("SI")
						&& tipoPaciente.equals("Familiares")
						&& !sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorDiscapacidadSexo(dea, aa,
								false, true, sexo).isEmpty())
				|| (discapacitados.equals("SI")
						&& tipoPaciente.equals("Trabajadores")
						&& !sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorDiscapacidadSexo(dea, aa,
								true, true, sexo).isEmpty())
				|| (discapacitados.equals("NO")
						&& tipoPaciente.equals("Familiares")
						&& !sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorDiscapacidadSexo(dea, aa,
								false, false, sexo).isEmpty())
				|| (discapacitados.equals("NO")
						&& tipoPaciente.equals("Trabajadores")
						&& !sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesTrabajadorDiscapacidadSexo(dea, aa,
								true, false, sexo).isEmpty())
				|| (discapacitados.equals("SI") && tipoPaciente.equals("TODOS")
						&& sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesDiscapacidad(dea, aa, true).isEmpty())
				|| (discapacitados.equals("NO") && tipoPaciente.equals("TODOS")
						&& sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesDiscapacidad(dea, aa, false).isEmpty())
				|| (discapacitados.equals("SI") && tipoPaciente.equals("TODOS")
						&& !sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesDiscapacidadSexo(dea, aa, true, sexo)
						.isEmpty())
				|| (discapacitados.equals("NO") && tipoPaciente.equals("TODOS")
						&& !sexo.equals("TODOS") && getServicioPaciente()
						.buscarPorEdadesDiscapacidadSexo(dea, aa, false, sexo)
						.isEmpty()))
			msj.mensajeAlerta(Mensaje.noHayRegistros);
		else
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=26&valor6="
					+ de
					+ "&valor7="
					+ a
					+ "&valor8="
					+ sexo
					+ "&valor9="
					+ tipoPaciente
					+ "&valor10="
					+ discapacitados
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");

	}

	public byte[] reportePaciente(String de, String a, String sexo,
			String tipoPaciente, String discapacitados, String tipoReporte)
			throws JRException {
		byte[] fichero = null;
		int dea = Integer.valueOf(de);
		int aa = Integer.valueOf(a);

		List<Paciente> pacientes = new ArrayList<Paciente>();

		if (tipoPaciente.equals("TODOS") && discapacitados.equals("TODOS")
				&& sexo.equals("TODOS"))
			pacientes = getServicioPaciente().buscarPorEdadesTodos(dea, aa);
		else {
			if (tipoPaciente.equals("TODOS") && discapacitados.equals("TODOS")
					&& !sexo.equals("TODOS"))
				pacientes = getServicioPaciente().buscarPorEdadesySexo(dea, aa,
						sexo);
			else {
				if (discapacitados.equals("TODOS")
						&& tipoPaciente.equals("Familiares")
						&& sexo.equals("TODOS"))
					pacientes = getServicioPaciente()
							.buscarPorEdadesTrabajador(dea, aa, false);
				else {
					if (discapacitados.equals("TODOS")
							&& tipoPaciente.equals("Trabajadores")
							&& sexo.equals("TODOS"))
						pacientes = getServicioPaciente()
								.buscarPorEdadesTrabajador(dea, aa, true);
					else {
						if (discapacitados.equals("TODOS")
								&& tipoPaciente.equals("Familiares")
								&& !sexo.equals("TODOS"))
							pacientes = getServicioPaciente()
									.buscarPorEdadesTrabajadorSexo(dea, aa,
											false, sexo);
						else {
							if (discapacitados.equals("TODOS")
									&& tipoPaciente.equals("Trabajadores")
									&& !sexo.equals("TODOS"))
								pacientes = getServicioPaciente()
										.buscarPorEdadesTrabajadorSexo(dea, aa,
												true, sexo);
							else { //
								if (discapacitados.equals("SI")
										&& tipoPaciente.equals("Familiares")
										&& sexo.equals("TODOS"))
									pacientes = getServicioPaciente()
											.buscarPorEdadesTrabajadorDiscapacidad(
													dea, aa, false, true);
								else {
									if (discapacitados.equals("SI")
											&& tipoPaciente
													.equals("Trabajadores")
											&& sexo.equals("TODOS"))
										pacientes = getServicioPaciente()
												.buscarPorEdadesTrabajadorDiscapacidad(
														dea, aa, true, true);
									else {
										if (discapacitados.equals("NO")
												&& tipoPaciente
														.equals("Familiares")
												&& sexo.equals("TODOS"))
											pacientes = getServicioPaciente()
													.buscarPorEdadesTrabajadorDiscapacidad(
															dea, aa, false,
															false);
										else {
											if (discapacitados.equals("NO")
													&& tipoPaciente
															.equals("Trabajadores")
													&& sexo.equals("TODOS"))
												pacientes = getServicioPaciente()
														.buscarPorEdadesTrabajadorDiscapacidad(
																dea, aa, true,
																false);
											else { // //
												if (discapacitados.equals("SI")
														&& tipoPaciente
																.equals("Familiares")
														&& !sexo.equals("TODOS"))
													pacientes = getServicioPaciente()
															.buscarPorEdadesTrabajadorDiscapacidadSexo(
																	dea, aa,
																	false,
																	true, sexo);
												else {
													if (discapacitados
															.equals("SI")
															&& tipoPaciente
																	.equals("Trabajadores")
															&& !sexo.equals("TODOS"))
														pacientes = getServicioPaciente()
																.buscarPorEdadesTrabajadorDiscapacidadSexo(
																		dea,
																		aa,
																		true,
																		true,
																		sexo);
													else {
														if (discapacitados
																.equals("NO")
																&& tipoPaciente
																		.equals("Familiares")
																&& !sexo.equals("TODOS"))
															pacientes = getServicioPaciente()
																	.buscarPorEdadesTrabajadorDiscapacidadSexo(
																			dea,
																			aa,
																			false,
																			false,
																			sexo);
														else {
															if (discapacitados
																	.equals("NO")
																	&& tipoPaciente
																			.equals("Trabajadores")
																	&& !sexo.equals("TODOS"))
																pacientes = getServicioPaciente()
																		.buscarPorEdadesTrabajadorDiscapacidadSexo(
																				dea,
																				aa,
																				true,
																				false,
																				sexo);
															else {
																if (discapacitados
																		.equals("SI")
																		&& tipoPaciente
																				.equals("TODOS")
																		&& sexo.equals("TODOS"))
																	pacientes = getServicioPaciente()
																			.buscarPorEdadesDiscapacidad(
																					dea,
																					aa,
																					true);
																else {
																	if (discapacitados
																			.equals("NO")
																			&& tipoPaciente
																					.equals("TODOS")
																			&& sexo.equals("TODOS"))
																		pacientes = getServicioPaciente()
																				.buscarPorEdadesDiscapacidad(
																						dea,
																						aa,
																						false);
																	else {
																		if (discapacitados
																				.equals("SI")
																				&& tipoPaciente
																						.equals("TODOS")
																				&& !sexo.equals("TODOS"))
																			pacientes = getServicioPaciente()
																					.buscarPorEdadesDiscapacidadSexo(
																							dea,
																							aa,
																							true,
																							sexo);
																		else {
																			if (discapacitados
																					.equals("NO")
																					&& tipoPaciente
																							.equals("TODOS")
																					&& !sexo.equals("TODOS"))
																				pacientes = getServicioPaciente()
																						.buscarPorEdadesDiscapacidadSexo(
																								dea,
																								aa,
																								false,
																								sexo);
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
						}
					}
				}
			}
		}

		Map p = new HashMap();
		p.put("de", de);
		p.put("a", a);
		p.put("tipoP", tipoPaciente);

		for (int i = 0; i < pacientes.size(); i++) {
			Paciente pa = pacientes.get(i);
			if (pacientes.get(i).isDiscapacidad()) {
				pa.setMano("SI");
			} else
				pa.setMano("NO");
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RPacientes.jasper"));
		if (tipoReporte.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(pacientes));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
			try {
				exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return xlsReport.toByteArray();
		} else {
			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(pacientes));
			return fichero;
		}
	}

	private void reporteCronico() {
		String tipoReporte = cmbTipo.getValue();
		String tipoPaciente = "";
		if (rdoFamiliares.isChecked())
			tipoPaciente = "Familiares";
		else {
			if (rdoTrabajadores.isChecked())
				tipoPaciente = "Trabajadores";
			else
				tipoPaciente = "TODOS";
		}

		if ((tipoPaciente.equals("TODOS") && getServicioPacienteMedicina()
				.buscarTodasCronico().isEmpty())
				|| (tipoPaciente.equals("Trabajadores") && getServicioPacienteMedicina()
						.buscarPorTrabajadores(true).isEmpty())
				|| (tipoPaciente.equals("Familiares") && getServicioPacienteMedicina()
						.buscarPorTrabajadores(false).isEmpty()))
			msj.mensajeAlerta(Mensaje.noHayRegistros);
		else
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=27&valor6="
					+ tipoPaciente
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");

	}

	public byte[] reporteCronico(String tipoPaciente, String tipoReporte)
			throws JRException {
		byte[] fichero = null;
		List<PacienteMedicina> pacienteMed = new ArrayList<PacienteMedicina>();

		if (tipoPaciente.equals("TODOS"))
			pacienteMed = getServicioPacienteMedicina().buscarTodasCronico();
		else {
			if (tipoPaciente.equals("Trabajadores"))
				pacienteMed = getServicioPacienteMedicina()
						.buscarPorTrabajadores(true);
			else
				pacienteMed = getServicioPacienteMedicina()
						.buscarPorTrabajadores(false);
		}

		Map p = new HashMap();
		p.put("tipoP", tipoPaciente);

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RCronicos.jasper"));
		if (tipoReporte.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(pacienteMed));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
			try {
				exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return xlsReport.toByteArray();
		} else {
			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(pacienteMed));
			return fichero;
		}
	}

	@Listen("onClick = #btnBuscarTrabajador")
	public void mostrarCatalogoFamiliar() {

		final List<Paciente> pacientes = new ArrayList<Paciente>();

		Paciente pacienteT = new Paciente();
		pacienteT.setCedula("TODOS");
		pacienteT.setFicha("TODOS");
		pacienteT.setPrimerNombre("TODOS");
		pacienteT.setPrimerApellido("TODOS");
		pacientes.add(pacienteT);
		pacientes.addAll(servicioPaciente.buscarTodosTrabajadores());

		catalogo = new Catalogo<Paciente>(divCatalogoTrabajador,
				"Catalogo de Pacientes", pacientes,false, "Cedula", "Ficha",
				"Nombre", "Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioPaciente.filtroNombre1T(valor);
				case "Cedula":
					return servicioPaciente.filtroCedulaT(valor);
				case "Ficha":
					return servicioPaciente.filtroFichaT(valor);
				case "Apellido":
					return servicioPaciente.filtroApellido1T(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getPrimerApellido();
				return registros;
			}

		};
		catalogo.setParent(divCatalogoTrabajador);
		catalogo.doModal();
	}

	@Listen("onSeleccion = #divCatalogoTrabajador")
	public void seleccinarTrabajador() {
		Paciente trabajador = catalogo.objetoSeleccionadoDelCatalogo();
		idTrabajador = trabajador.getCedula();
		lblTrabajador.setValue(trabajador.getPrimerNombre() + " "
				+ trabajador.getPrimerApellido());
		catalogo.setParent(null);
	}

}
