package programas.programasTipoSala;

import java.sql.Connection;

import bbdd.ConexionMySql;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCategoriaPelicula;
import servicios.ServicioTipoSala;
import util.Teclado;

public class ProbarBorrarTipoSala {
	public static void main(String[] args) {
		Teclado t = new Teclado();
		ServicioTipoSala scTipoSala = new ServicioTipoSala();
		int borrado=0;
		int idTipoSala=0;
		String salida= null;
		Connection con=null;
		try {
			con= new ConexionMySql().getConexion();

			try {
				System.out.println("Introduce el ID del Tipo de Sala que quiere borrar");
				idTipoSala=t.leerEntero();
				
			} catch (NumberFormatException e) {
				salida="Debes introducir un numero entero";
			}
			borrado= scTipoSala.borrarTipoSalaID(idTipoSala);
			if(borrado==1) {
				salida="Tipo de Sala borrada correctamente";
			}else {
				salida="El tipo de Sala indicado no existe";
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