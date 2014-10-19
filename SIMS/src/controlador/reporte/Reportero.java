package controlador.reporte;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import controlador.transacciones.CConsulta;

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
		CConsulta consulta = new CConsulta();
		CMorbilidad morbilidad = new CMorbilidad();
		CReposo reposo = new CReposo();
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
				fichero = consulta.reporteServicio(part2, part4, part5);
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
						par9);
				break;
			case "10":
				fichero = morbilidad.reporteMorbilidadPorTipo(par6, par7, par8,
						par9);
				break;
			case "11":
				fichero = morbilidad.reporteMorbilidadPorDiagnostico(par6, par7, par8,
						par9,par10,par11);
				break;
			case "12":
				fichero = morbilidad.reporteMorbilidadPorDoctor(par6, par7, par8,
						par9);
				break;
			case "13":
				fichero = reposo.reporteReposoPorArea(par6, par7, par8);
				break;
			case "14":
				fichero = reposo.reporteReposoPorDoctor(par6, par7, par8,par9);
				break;
			case "15":
				fichero = reposo.reporteReposoPorDiagnostico(par6, par7, par8);
				break;
			default:
				break;
			}
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition",
				"inline; filename=Reporte.pdf");
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
