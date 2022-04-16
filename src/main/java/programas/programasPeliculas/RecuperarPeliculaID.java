package programas.programasPeliculas;

import domain.Entradas;
import domain.Peliculas;
import exceptions.DAOException;
import exceptions.ServiceException;
import servicios.ServicioEntradas;
import servicios.ServicioPeliculas;
import util.Teclado;

public class RecuperarPeliculaID {

	public static void main(String[] args) throws  DAOException {

	Teclado t= new Teclado();
	Peliculas pelicula= new Peliculas();
	ServicioPeliculas scPelicula= new ServicioPeliculas();
	String salida=null;
	int idPelicula=0;
	boolean correcto= true;
	try {
			
		System.out.println("Introduce un Id de la pelicula ");
		while (correcto){
			try {
				idPelicula= t.leerEntero();
				 correcto=false;
			    } catch (NumberFormatException E) {
			    	System.out.println("Introduzca el número de ID bien MERLUZO");
			    }  
		}

		
		pelicula=scPelicula.recuperarPeliculaID(idPelicula);
		if(pelicula!=null) {
			System.out.println("Nombre pelicula: "+pelicula.getTitulo() + pelicula.getCategoria().getidCategorias()+ pelicula.getCategoria().getDescripcion());
			//System.out.println(pelicula.toStringFashion());
			salida="proceso realizado correctamente";
		}else {
			salida="No existe una pelicula asignada a ese idPelicula "+ idPelicula;
		}

	}catch (ServiceException e) {
		if(e.getCause()==null){

			salida=(e.getMessage());//Error Lógico para usuario
		}else{
			e.printStackTrace();// para administrador
			salida=("error interno");//Error interno para usuario
		}
	}
	System.out.println(salida);
}



}
