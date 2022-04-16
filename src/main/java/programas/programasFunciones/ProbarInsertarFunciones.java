package programas.programasFunciones;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bbdd.ConexionMySql;
import domain.Funciones;
import domain.Peliculas;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioFunciones;
import servicios.ServicioPeliculas;
import util.Fecha;
import util.Teclado;

public class ProbarInsertarFunciones {

	public static void main(String[] args) {

		Funciones funcion= null;
		ServicioFunciones scFuncion= new ServicioFunciones();
		Teclado t = new Teclado();
		Connection con =null;
		String salida=null;
		try {
			con= new ConexionMySql().getConexion();
			funcion= new Funciones();

			System.out.println("Introduce el id de la pelicula");
			
			try{
				funcion.setPelicula(t.leerEntero());
				}catch(NumberFormatException e){
					throw new ServiceException("el tipo de iva tiene que ser un numero");
				}
			
			System.out.println("Introduce el id de la sala");
			try{
				funcion.setSala(t.leerEntero());
			}catch(NumberFormatException e){
				throw new ServiceException("el tipo de iva tiene que ser un numero");
			}
		

	
			
			


		     System.out.println("Introduce el dia");
			 String dia= t.leerCadena();
			 System.out.println("introduce el mes");
			 String mes= t.leerCadena();
			 System.out.println("introduce año");
			 String anyo= t.leerCadena();
//			String fecha= dia+"/"+mes+"/"+anyo+ "-12:20";
			String fecha= anyo+"/"+mes+"/"+dia;
			
			funcion.setFecha(fecha);
			scFuncion.insertarFuncion(funcion);
			mensaje="Proceso realizado correctamente";
			
		} catch (DAOException | ServiceException | DomainException e) {
			if(e.getCause()==null){
				mensaje=e.getMessage();//Error Lógico para usuario
			}else{
				e.printStackTrace();// para administrador
				mensaje="error interno";//Error interno para usuario
			}
		}

		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

}
