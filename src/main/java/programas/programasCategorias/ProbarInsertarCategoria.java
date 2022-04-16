package programas.programasCategorias;

import java.sql.Connection;

import bbdd.ConexionMySql;
import domain.CategoriasPeliculas;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCategoriaPelicula;
import util.Teclado;

public class ProbarInsertarCategoria {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServicioCategoriaPelicula sccategoria= new ServicioCategoriaPelicula();
		Teclado t = new Teclado();
		CategoriasPeliculas categoria= new CategoriasPeliculas();
		String salida= null;
		Connection con=null;
		try {
			con= new ConexionMySql().getConexion();

			System.out.println("Introduce una categoria");
			categoria.setDescripcion(t.leerCadena());
			sccategoria.insertarCategoria(categoria);
			salida= "Categoria insertada correctamente";
			
		}catch (ServiceException | DomainException | DAOException e) {
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
