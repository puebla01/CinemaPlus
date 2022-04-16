package programas.programasEntradas;

import java.sql.Connection;

import bbdd.ConexionMySql;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioEntradas;
import servicios.ServicioTipoSala;
import util.Teclado;

public class ProbarBorrarEntrada {

	public static void main(String[] args) {
		
		Teclado t = new Teclado();
		ServicioEntradas scEntrada = new ServicioEntradas();
		int borrado=0;
		int idEntrada=0;
		String salida= null;
		Connection con=null;
		try {
			con= new ConexionMySql().getConexion();

			try {
				System.out.println("Introduce el ID del tipo de entrada que quiere borrar");
				idEntrada=t.leerEntero();
				
			} catch (NumberFormatException e) {
				salida="Debes introducir un numero entero";
			}
			borrado= scEntrada.borrarEntradaID(idEntrada);
			if(borrado==1) {
				salida="Tipo de entrada borrado correctamente";
			}else {
				salida="El tipo de entrada indicado no existe";
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
