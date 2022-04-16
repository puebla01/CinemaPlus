package programas.programasPeliculas;

import java.sql.Connection;

import bbdd.ConexionMySql;
import domain.CategoriasPeliculas;
import domain.Entradas;
import domain.Peliculas;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioEntradas;
import servicios.ServicioPeliculas;
import util.Teclado;

public class ProbarInsertarPelicula {

	public static void main(String[] args) {

		Peliculas pelicula= null;
		ServicioPeliculas scPelicula= new ServicioPeliculas();
		Teclado t = new Teclado();
		Connection con =null;
		String salida=null;
		try {
			con= new ConexionMySql().getConexion();
			pelicula= new Peliculas();

			System.out.println("Introduce el titulo de la pelicula");
			pelicula.setTitulo(t.leerCadena());


			System.out.println("Introduce el resumen de la pelicula");
			pelicula.setResumen(t.leerCadena());
			
			System.out.println("Indica la duracion de la pelicula en minutos");
			pelicula.setDuracion(t.leerEntero());
			System.out.println("Introduce la categoria de la pelicula");
			CategoriasPeliculas categorias= new CategoriasPeliculas();
			categorias.setidCategorias(t.leerEntero());
			pelicula.setCategoria(categorias);
			
			System.out.println("Introduce la ruta de una imagen");
			pelicula.setImagen(t.leerCadena());
			
			scPelicula.insertarPelicula(pelicula);
			salida= "Pelicula insertada correctamente";
		} catch (DAOException | ServiceException | DomainException e) {
			if(e.getCause()==null){
				salida=e.getMessage();//Error Lógico para usuario
			}else{
				e.printStackTrace();// para administrador
				salida="error interno";//Error interno para usuario
			}
		}

		System.out.println(salida);
	}

}
