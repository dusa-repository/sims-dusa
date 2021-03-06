package controlador.reporte;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.json.JSONException;
import org.json.JSONObject;

import controlador.control.CControl;
import controlador.sha.CInforme;
import controlador.social.CActualizacion;
import controlador.transacciones.CConsulta;
import controlador.transacciones.COrden;
import controlador.transacciones.CResultado;

/**
 * Servlet implementation class Reportero
 */
@WebServlet("/Reportero")
public class Reportero extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reportero() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CControl control = new CControl();
		CConsulta consulta = new CConsulta();
		CMorbilidad morbilidad = new CMorbilidad();
		CReposo reposo = new CReposo();
		CInforme informe = new CInforme();
		CResumen resumen = new CResumen();
		CCosto costo = new CCosto();
		CResultado resultado = new CResultado();
		CPacientes pacientes = new CPacientes();
		COrden orden = new COrden();
		CGasto gasto = new CGasto();
		CAfiliaciones cAfiliacion = new CAfiliaciones();
		CResumenTrabajadores resumenTrabajadores = new CResumenTrabajadores();
		CReporteCertificado certificado = new CReporteCertificado();
		COrdenProveedor ordenProveedor = new COrdenProveedor();
		CReporteOrden reporteOrden = new CReporteOrden();
		COrdenesConsulta ordenesConsulta = new COrdenesConsulta();
		CActualizacion cActu = new CActualizacion();
		CResumenParentesco cParen = new CResumenParentesco();
		CGastoCarga gastoCarga = new CGastoCarga();
		CDecesos cDe = new CDecesos();
		CDiasHorasReposo cDiasReposos = new CDiasHorasReposo();
		ServletOutputStream out;
		Long part2 = (long) 0;
		String par1 = request.getParameter("valor");
		String part21 = request.getParameter("valor2");
		if (part21 != null)
			part2 = Long.parseLong(part21);
		String par3 = request.getParameter("valor3");
		String partecita4 = request.getParameter("valor4");
		String partecita5 = request.getParameter("valor5");
		Long part4 = (long) 0;
		if (partecita4 != null)
			part4 = Long.parseLong(partecita4);
		Long part5 = (long) 0;
		if (partecita5 != null)
			part5 = Long.parseLong(partecita5);
		// Reporte de Area y Tipo
		String par6 = request.getParameter("valor6");
		String par7 = request.getParameter("valor7");
		String par8 = request.getParameter("valor8");
		String par9 = request.getParameter("valor9");
		String par10 = request.getParameter("valor10");
		String par11 = request.getParameter("valor11");
		String tipo = request.getParameter("valor20");
		JSONObject jObj = new JSONObject();
		if (request.getParameter("valor40") != null) {
			try {
				jObj = new JSONObject(request.getParameter("valor40"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		// Reporte Tipo Consulta

		byte[] fichero = null;
		try {
			switch (par1) {
			case "1":
				fichero = consulta.reporteRecipe(part2);
				break;
			case "2":
				fichero = consulta.reporteEspecialista(part2, par3);
				break;
			case "3":
				fichero = consulta.reporteServicio(part2, part5);
				break;
			case "4":
				fichero = consulta.reporteExamen(part2, part5);
				break;
			case "5":
				fichero = consulta.reporteConsulta(part2);
				break;
			case "6":
				fichero = consulta.reporteConsultaHistorico(par3);
				break;
			case "7":
				fichero = consulta.reporteReposo(part2);
				break;
			case "8":
				fichero = consulta.reporteConstancia(part2);
				break;
			case "9":
				fichero = morbilidad.reporteMorbilidadPorArea(par6, par7, par8,
						par9, tipo);
				break;
			case "10":
				fichero = morbilidad.reporteMorbilidadPorTipo(par6, par7, par8,
						par9, tipo);
				break;
			case "11":
				fichero = morbilidad.reporteMorbilidadPorDiagnostico(par6,
						par7, par8, par9, par10, par11, tipo, jObj);
				break;
			case "12":
				fichero = morbilidad.reporteMorbilidadPorDoctor(par6, par7,
						par8, par9, tipo);
				break;
			case "13":
				fichero = reposo.reporteReposoPorArea(par6, par7, par8, tipo);
				break;
			case "14":
				fichero = reposo.reporteReposoPorDoctor(par6, par7, par8, par9,
						tipo);
				break;
			case "15":
				fichero = reposo.reporteReposoPorDiagnostico(par6, par7, par8,
						tipo, jObj);
				break;
			case "16":
				fichero = resumen.reporteAreaTipoDiagnostico(par6, par7, tipo);
				break;
			case "17":
				fichero = resumen.reporteDiagnostico(par6, par7, par8, par9,
						tipo, par10);
				break;
			case "18":
				fichero = resumen.reporteTipoConsulta(par6, par7, par8, par9,
						tipo);
				break;
			case "19":
				fichero = costo.reporteCosto(par6, par7, part2, par9, tipo);
				break;
			case "20":
				fichero = orden.reporteRecipe(part2);
				break;
			case "21":
				fichero = orden.reporteEspecialista(part2, par3);
				break;
			case "22":
				fichero = orden.reporteServicio(part2, part5);
				break;
			case "23":
				fichero = orden.reporteExamen(part2, part5);
				break;
			case "24":
				fichero = pacientes.reporteFamiliares(par6, par7, par8, par9,
						par10, tipo);
				break;
			case "25":
				fichero = reposo.reporteReposoPorPaciente(par6, par7, par8,
						tipo);
				break;
			case "26":
				fichero = pacientes.reportePaciente(par6, par7, par8, par9,
						par10, tipo);
				break;
			case "27":
				fichero = pacientes.reporteCronico(par6, tipo);
				break;
			case "28":
				fichero = gasto.reporteGastoPorFamiliar(par6, par7, par8, par9,
						tipo);
				break;
			case "29":
				fichero = gasto.reporteGastoPorTrabajador(par6, par7, par9,
						tipo);
				break;
			case "30":
				fichero = consulta.reporteroPreempleo(Long.parseLong(par6));
				break;
			case "31":
				fichero = ordenProveedor.reporteProveedor(par6, par7, part2,
						tipo);
				break;
			case "32":
				fichero = ordenProveedor.reporteEspecialista(par6, par7, par8,
						tipo);
				break;
			case "33":
				fichero = informe.reporteInpsasel(par6);
				break;
			case "34":
				fichero = morbilidad.reporteMorbilidadPorCargo(par6, par7,
						par8, tipo);
				break;
			case "35":
				fichero = morbilidad.reporteMorbilidadPorEmpresa(par6, par7,
						par8, tipo);
				break;
			case "36":
				fichero = morbilidad.reporteMorbilidadPorNomina(par6, par7,
						par8, tipo);
				break;
			case "37":
				fichero = morbilidad.reporteMorbilidadPorClasificacion(par6,
						par7, par8, par9, tipo);
				break;
			case "38":
				fichero = reposo.reporteReposoPorCargo(par6, par7, par8, tipo);
				break;
			case "39":
				fichero = reposo.reporteReposoPorNomina(par6, par7, par8, tipo);
				break;
			case "40":
				fichero = reposo
						.reporteReposoPorEmpresa(par6, par7, par8, tipo);
				break;
			case "41":
				fichero = certificado.reporteCertificado(part2, par7, par8,
						par9, tipo);
				break;
			case "42":
				fichero = reporteOrden.jasperRecipe(par6, par7, tipo);
				break;
			case "43":
				fichero = reporteOrden.jasperProveedor(par6, par7, tipo);
				break;
			case "44":
				fichero = reporteOrden.jasperEspecialista(par6, par7, tipo);
				break;
			case "45":
				fichero = ordenesConsulta.jasperProveedor(par6, par7, tipo);
				break;
			case "46":
				fichero = ordenesConsulta.jasperEspecialista(par6, par7, tipo);
				break;
			case "47":
				fichero = control.jasperConsulta(par6, tipo);
				break;
			case "48":
				fichero = control.jasperOrden(par6, tipo);
				break;
			case "49":
				fichero = ordenProveedor.reporteProveedorOrden(par6, par7,
						part2, tipo);
				break;
			case "50":
				fichero = ordenProveedor.reporteEspecialistaOrden(par6, par7,
						par8, tipo);
				break;
			case "51":
				fichero = gasto.reporteGastoPorFamiliarOrden(par6, par7, par8,
						par9, tipo);
				break;
			case "52":
				fichero = gasto.reporteGastoPorTrabajadorOrden(par6, par7,
						par9, tipo);
				break;
			case "53":
				fichero = resultado.reporteResultado(part2);
				break;
			case "54":
				fichero = cActu.jasperFormatoActualizacion(par3);
				break;
			case "55":
				fichero = resumenTrabajadores.reporteResumen(tipo);
				break;
			case "56":
				fichero = gastoCarga
						.reporteResumenGasto(par6, par7, par8, tipo);
				break;
			case "57":
				fichero = pacientes.reporteEmpresas(par6, par7, par8, par9,par10, tipo);
				break;
			case "58":
				fichero = cParen.reporteResumen(tipo);
				break;
			case "59":
				fichero = cDe.reporteResumen(par6, par7,tipo);
				break;
			case "60":
				fichero = cAfiliacion.reporteResumen(par6, par7,tipo);
				break;
			case "61":
				fichero = cDiasReposos.reporteResumen(par6, par7,tipo);
				break;
			default:
				break;
			}
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (tipo != null) {
			if (tipo.equals("EXCEL")) {
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				response.setHeader("Content-Disposition",
						"inline; filename=Reporte.xlsx");
			} else {
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition",
						"inline; filename=Reporte.pdf");
			}
		} else {
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition",
					"inline; filename=Reporte.pdf");
		}
		response.setHeader("Cache-Control", "max-age=30");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentLength(fichero.length);
		out = response.getOutputStream();
		out.write(fichero, 0, fichero.length);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
