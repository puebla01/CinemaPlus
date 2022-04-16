package servlets;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import domain.Peliculas;
import exceptions.ServiceException;
import servicios.ServicioPeliculas;

/**
 * Servlet implementation class FichaLibro
 */
@WebServlet("/RecuperarPelicula")
public class RecuperarPelicula extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperarPelicula() {
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
		// TODO Auto-generated method stub


		//recupero la pelicula por el id que recibimos y lo guardamos en la sesion el objeto
		Integer idPelicula=Integer.parseInt(request.getParameter("idPelicula"));
		
		Peliculas pelicula= new Peliculas();
		ServicioPeliculas scPeliculas= new ServicioPeliculas();
		HttpSession sesion = request.getSession();
		String salida=null;
		try {
			pelicula=scPeliculas.recuperarPeliculaID(idPelicula);
			salida="/FichaPelicula.jsp";

			sesion.setAttribute("pelicula",pelicula);
			
		} catch (ServiceException e) {
			if(e.getCause()==null){
				request.setAttribute("mensaje", e.getMessage());//Error Lógico
				salida="/Fin.jsp";

			}else{
				e.printStackTrace();
				salida="/Fin";
				request.setAttribute("mensaje", e.getMessage());//Error interno

			}
		}		
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

}
