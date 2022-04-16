package programas.programasCategorias;

import domain.CategoriasPeliculas;
import domain.TipoSala;
import exceptions.DAOException;
import exceptions.ServiceException;
import servicios.ServicioCategoriaPelicula;
import servicios.ServicioTipoSala;
import util.Teclado;

public class ProbarRecuperarCategoriaID {


	public static void main(String[] args) throws  DAOException {

	Teclado t= new Teclado();
	CategoriasPeliculas categoria= new CategoriasPeliculas();
	ServicioCategoriaPelicula scCategoria= new ServicioCategoriaPelicula();
	String salida=null;
	int idCategoria=0;
	boolean correcto=true;
	try {
		System.out.println("Introduce un Id de categoria ");
		while (correcto){
			try {
				 idCategoria= t.leerEntero();
				 correcto=false;
			    } catch (NumberFormatException E) {
			    	System.out.println("Introduzca el número de pedido bien MERLUZO");
			    }  
		}

		categoria=scCategoria.recuperarCategoriaID(idCategoria);
		if(categoria!=null) {
			System.out.println("Nombre: "+categoria.getDescripcion());
			salida="proceso realizado correctamente";
		}else {
			salida="No existe una categoria asignada a este idCategoria";
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
