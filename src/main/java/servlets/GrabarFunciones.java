package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Funciones;
import domain.Peliculas;
import domain.Salas;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioFunciones;
import util.Fecha;

/**
 * Servlet implementation class GrabarFunciones
 */
@WebServlet("/GrabarFunciones")
public class GrabarFunciones extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GrabarFunciones() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Funciones f = new Funciones();
		Peliculas pelicula= new Peliculas();
		pelicula.setIdPelicula(Integer.parseInt( request.getParameter("inputPelicula")));
		Salas sala = new Salas();
		int idSala =Integer.parseInt(  request.getParameter("inputSala"));
		sala.setIdSala(idSala);
		String fecha= request.getParameter("inputFecha");
		String hora=  request.getParameter("inputHora");

		
		String salida="/Fin.jsp";
		f.setPelicula(pelicula);
		try {
			f.setFecha(Fecha.convertirADate(fecha, "yyyy-MM-dd"));
		} catch (ParseException e) {
			request.setAttribute("mensaje"," fecha/s no válidas");
		}
		f.setHora(hora);
		f.setSala(sala);
		
		ServicioFunciones scFunciones = new ServicioFunciones();
		try {
			scFunciones.insertarFuncion(f);
			request.setAttribute("mensaje","Funcion Grabada");
		}  catch (ServiceException | DomainException e) {
			if(e.getCause()==null){
				request.setAttribute("mensaje",e.getMessage());//Error Lógico para usuario
			}else{
				e.printStackTrace();// para administrador
				request.setAttribute("mensaje","error interno");//Error interno para usuario
			}
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);

	}

}
