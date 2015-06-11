package controlador.social;

import java.io.IOException;
import java.util.HashMap;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import controlador.maestros.CGenerico;

public class CDatosFamiliar extends CGenerico {

	@Wire
	private Window wdwDatos;
	@Wire
	private Label lblCedula;
	@Wire
	private Label lblNombre;
	@Wire
	private Label lblFechaNacimiento;
	@Wire
	private Label lblRif;
	@Wire
	private Label lblEdad;
	@Wire
	private Label lblEstado;
	@Wire
	private Label lblSexo;
	@Wire
	private Label lblParentesco;
	@Wire
	private Label lblOficio;
	@Wire
	private Label lblProfesion;
	@Wire
	private Label lblLugarEstudio;
	@Wire
	private Label lblCargo;
	@Wire
	private Label lblEstudia;
	@Wire
	private Label lblDesde;
	@Wire
	private Label lblDiscapacidad;
	@Wire
	private Label lblConapdis;
	@Wire
	private Label lblNroCertificado;
	@Wire
	private Label lblAyuda;
	@Wire
	private Label lblDireccion;
	@Wire
	private Label lblTelefono;
	@Wire
	private Label lblObservacion;
	@Wire
	private Label lblVive;

	@Override
	public void inicializar() throws IOException {

		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("datos");
		if (map != null) {
			lblCedula.setValue((String) map.get("cedula"));
			lblNombre.setValue((String) map.get("nombres")+" "+(String) map.get("apellidos"));
			lblRif.setValue((String) map.get("rif"));
			lblEdad.setValue((String) map.get("edad"));
			lblSexo.setValue((String) map.get("sexo"));
			lblEstado.setValue((String) map.get("estadoCivil"));
			lblFechaNacimiento.setValue((String) map.get("fechaNacimiento"));
			lblParentesco.setValue((String) map.get("parentesco"));
			lblOficio.setValue((String) map.get("oficio"));
			lblProfesion.setValue((String) map.get("profesion"));
			lblEstudia.setValue((String) map.get("estudia"));
			lblLugarEstudio.setValue((String) map.get("lugarEstudio"));
			lblCargo.setValue((String) map.get("cargoCarrera"));
			lblDesde.setValue((String) map.get("desde"));
			lblDiscapacidad.setValue((String) map.get("discapacadidad"));
			lblConapdis.setValue((String) map.get("conapdis"));
			lblNroCertificado.setValue((String) map.get("certificado"));
			lblAyuda.setValue((String) map.get("ayuda"));
			lblDireccion.setValue((String) map.get("direccion"));
			lblTelefono.setValue((String) map.get("telefono"));
			lblObservacion.setValue((String) map.get("observaciones"));
			lblVive.setValue((String) map.get("vive"));
			map.clear();
			map = null;

		}
	}
}
