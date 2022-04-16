package programas.programasSalas;

import java.sql.Connection;

import bbdd.ConexionMySql;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioSala;
import servicios.ServicioTipoSala;
import util.Teclado;

public class ProbarBorrarSala {

	public static void main(String[] args) {
		Teclado t = new Teclado();
		ServicioSala scSala = new ServicioSala();
		int borrado=0;
		int idSala=0;
		String salida= null;
		Connection con=null;
		boolean correcto=true;
		try {
			con= new ConexionMySql().getConexion();

			while (correcto){
				try {
					idSala=(t.leerEntero());
					 correcto=false;
				    } catch (NumberFormatException E) {
				    	System.out.println("Introduzca el número de ID bien MERLUZO");
				    }  
			}
			borrado= scSala.borrarTipoSalaID(idSala);
			if(borrado==1) {
				salida="Sala borrada correctamente";
			}else {
				salida="La Sala indicada no existe";
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
