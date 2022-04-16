package servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;

import Clases.FormMultiPart;
import domain.CategoriasPeliculas;
import domain.Peliculas;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioPeliculas;

/**
 * Servlet implementation class SubirFichero
 */
@WebServlet("/GrabarPelicula")
public class GrabarPelicula extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GrabarPelicula() {
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
		//System.out.println(request.getParameter("nombre"));//saldra null or que no podemos cogerlo al ser un formulario del tipo multi

		//añadimos al getrealpatch la ruta /ficherosSubidos
		String ruta="assets/Images/Peliculas";
		String patch =getServletContext().getRealPath(ruta);//Aqui es donde lo vamos a subir

		String salida="/Fin.jsp";
		String mensaje=null;
		Peliculas pelicula = new Peliculas();
		ServicioPeliculas scPelicula= new ServicioPeliculas();
		FormMultiPart datos;
		try {
			String titulo = null;
			String resumen= null;
			CategoriasPeliculas categoria= null;
			int duracion=0;
			
			String imagen= null;

			try {
				datos= new FormMultiPart(patch, request);
				
				 titulo=datos.getCampoForm("inputTitulo");
				 resumen= datos.getCampoForm("inputResumen");
			
				 try{
					 
					 duracion= Integer.parseInt(datos.getCampoForm("inputDuracion"));
					 pelicula.setDuracion(duracion);
				 }catch (NumberFormatException e) {
					 request.setAttribute("mensaje","duracion no es correcta ");
				}
				
				 categoria.setidCategorias(Integer.parseInt(datos.getCampoForm("inputCategoria")));
				//Necesito recuperar el nombre del archivo y la ruta local
				File f = new File(datos.getCampoFile("inputImagen"));//tiene que ser el file.io
				String fin=FilenameUtils.getExtension( f.getName());
				System.out.println(fin);
				 imagen =ruta+"/"+f.getName();//Meto la ruta donde se va a guardar y el nombre del archivo
				 
				 
				
				int numSubidosFicheros=0;
				try {
					numSubidosFicheros=datos.SubirFicheros();
				} catch (Exception e) {
					request.setAttribute("mensaje",e);
				}
			} catch (FileUploadException e) {
				request.setAttribute("mensaje",e);
			}
			pelicula.setTitulo(titulo);
			pelicula.setResumen(resumen);
			pelicula.setDuracion(duracion);
			pelicula.setCategoria(categoria);
			pelicula.setImagen(imagen);
			scPelicula.insertarPelicula(pelicula);
			request.setAttribute("mensaje", "Pelicula insertada correctamente");

		} catch (ServiceException | DomainException e) {
			if(e.getCause()==null){
				request.setAttribute("mensaje",e.getMessage());//Error Lógico para usuario
			}else{
				request.setAttribute("mensaje","error interno");//Error interno para usuario
			}
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);

	}
}
