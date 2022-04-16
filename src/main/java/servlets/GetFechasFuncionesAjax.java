package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Funciones;
import domain.Peliculas;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioFunciones;
import servicios.ServicioPeliculas;
import util.Fecha;

/**
 * Servlet implementation class GetFechasFuncionesAjax
 */
@WebServlet("/GetFechasFuncionesAjax")
public class GetFechasFuncionesAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetFechasFuncionesAjax() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();

		//Recupero el id y la fecha para buscar las horas segun la fecha de esa pelicula
		int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
		String fecha= request.getParameter("fecha");

		ServicioFunciones scFuncion= new ServicioFunciones();

		//convierto la fecha recibida a date
		Date dia = null;
		try {
			dia = Fecha.convertirADate(fecha,  "yyyy-MM-dd");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//ahora llamo al metodo que nos va a devolver las horas segun la peli y el dia
		List<Funciones> listaHoras= new ArrayList<Funciones>();
		try {
			listaHoras=scFuncion.recuperarFuncionPorDiaYPelicula(idPelicula, dia);	

			out.println("<div class='row'>");
			for(Funciones funcion : listaHoras) {
				out.println("<div class='col-1 badge bg-primary text-wrap' style='6rem'><a class='link-light' target='_blank' href='CompraEntradas.jsp?idFuncion="+ funcion.getIdFunciones()+"'>" +funcion.getHora() +"</a> </div>");

			}
			out.println("</div>");


		} catch (ServiceException|DomainException  e) {
			if(e.getCause()==null){
				System.out.println(e.getMessage());//Error Lógico para usuario
			}else{
				e.printStackTrace();// para administrador
				System.out.println("error interno");//Error interno para usuario
			}
		}

	}

}
