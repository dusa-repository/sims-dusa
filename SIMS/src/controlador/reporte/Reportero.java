package controlador.reporte;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.biff.formula.ParseContext;

import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.http.SimpleSession;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Listbox;

import servicio.transacciones.SConsulta;

import modelo.maestros.Accidente;
import modelo.transacciones.ConsultaMedicina;
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
		ServletOutputStream out;
		String par1 = request.getParameter("valor");
		Long part2 = Long.parseLong(request.getParameter("valor2"));
		String par3 = request.getParameter("valor3");
		String partecita4 = request.getParameter("valor4");
		String partecita5 = request.getParameter("valor5");
		Long part4 = (long) 0;
		if (partecita4 != null)
			part4 = Long.parseLong(partecita4);
		Long part5 = (long) 0;
		if (partecita5 != null)
			part5 = Long.parseLong(partecita5);
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
				fichero = consulta.reporteExamen(part2);
				break;
			case "5":
				fichero = consulta.reporteConsulta(part2);
				break;
			case "6":
				fichero = consulta.reporteConsultaHistorico(part2);
				break;
			case "7":
				fichero = consulta.reporteReposo(part2);
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
