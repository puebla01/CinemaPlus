package programas.programasCategorias;

import java.sql.Connection;

import bbdd.ConexionMySql;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCategoriaPelicula;
import util.Teclado;

public class ProbarBorrarCategoriaID {

	public static void main(String[] args) {
		Teclado t = new Teclado();
		ServicioCategoriaPelicula scCategoria= new ServicioCategoriaPelicula();
		int borrado=0;
		int idCategoria=0;
		String salida= null;
		Connection con=null;
		
		try {
			con= new ConexionMySql().getConexion();

			System.out.println("Introduce el ID de la categoria que quiere borrar");
			idCategoria=t.leerEntero();
			borrado= scCategoria.borrarCategoriaID(idCategoria);
			if(borrado==1) {
				salida="Categoria borrada correctamente";
			}else {
				salida="La categoria indicada no existe";
			}
			
		} catch (ServiceException | DomainException | DAOException e) {
			if(e.getCause()==null){
				salida=e.getMessage();//Error Lógico para usuario
			}else{
				e.printStackTrace();// para administrador
				salida="error interno";//Error interno para usuario
			}
		}
		System.out.println(salida);//dispacher salida para mostrar el error				
	}

}
