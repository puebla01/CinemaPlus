package programas.programasPeliculas;

import java.util.List;

import daos.CategoriaPeliculaDAO;
import domain.CategoriasPeliculas;
import domain.Peliculas;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioPeliculas;
import util.Teclado;

public class ProbarRecuperarTodasPeliculas {

	public static void main(String[] args) {
		List<Peliculas> lista= null;
		ServicioPeliculas sc= null;
		CategoriasPeliculas categorias= null;
		
		
	
	try {
		  
	
		// recuperar  cliente 
	       System.out.println("probando recuperar peliculas");
	       
	      sc= new ServicioPeliculas();
	       lista= sc.recuperarTodasPeliculas();
		  
	       for(Peliculas pelicula : lista) {
	    	   System.out.println( pelicula.getTitulo()+ " --- categoria: "+ pelicula.getCategoria().getDescripcion());
	    	   
	       }
		
		
	 
		
	// ESTA RUTINA DE ERRORES SIEMPRE		
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
