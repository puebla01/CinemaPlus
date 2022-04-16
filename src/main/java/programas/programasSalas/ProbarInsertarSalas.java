package programas.programasSalas;

import java.sql.Connection;

import bbdd.ConexionMySql;
import domain.Salas;
import domain.TipoSala;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioSala;
import util.Teclado;

public class ProbarInsertarSalas {

	public static void main(String[] args) {
		
		Salas sala= null;
		ServicioSala scSala = new ServicioSala();
		
		Teclado t = new Teclado();
		Connection con =null;
		String salida=null;
		boolean correcto =true;
		
		try {
			con= new ConexionMySql().getConexion();
			sala= new Salas();

			System.out.println("Introduce un nombre para la sala");
			sala.setNombre(t.leerCadena());

			System.out.println("Indica el tipo de sala");
			while (correcto){
				try {
					TipoSala tipoSala= new TipoSala();
					tipoSala.setIdTipoSala(t.leerEntero());
					sala.setTipoSala(  tipoSala);
					 correcto=false;
				    } catch (NumberFormatException E) {
				    	System.out.println("Introduzca el número de ID bien MERLUZO");
				    }  
			}

			scSala.insertarSala(sala);
			salida= "Sala insertada correctamene";
			
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
