package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Usuario;
import exceptions.ServiceException;
import servicios.ServicioUsuario;

/**
 * Servlet implementation class ComprobarUsuario
 */
@WebServlet("/ComprobarUsuario")
public class ComprobarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprobarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		ServicioUsuario scUsuario = new ServicioUsuario();
		Usuario user=  null;
		HttpSession sesion = request.getSession();
		String salida=(String) sesion.getAttribute("pagina");
		
		try {
			user= scUsuario.recuperarUsuarioEmailPassword(email, password);
			if(user!=null) {
				sesion.setAttribute("user", user);
			request.setAttribute("mensaje", user.getNombre());
			}else {
				request.setAttribute("mensaje", "usuario y contraseña invcorretoos");
			}
			
			
		}catch (ServiceException e) {
			if(e.getCause()==null){

				request.setAttribute("mensaje",e.getMessage());//Error Lógico para usuario
				salida="/Fin.jsp";

			}else{
				e.printStackTrace();// para administrador
				request.setAttribute("mensaje", "error interno");//Error interno para usuario
				salida="/Fin.jsp";

			}
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);

		
	}

}
