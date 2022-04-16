package programas.programasReservas;

import java.sql.Connection;

import bbdd.ConexionMySql;
import domain.Entradas;
import domain.Reservas;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioEntradas;
import servicios.ServicioReservas;
import util.Teclado;

public class ProbarInsertarReserva {

	public static void main(String[] args) {

		Reservas reserva = null;
		ServicioReservas scReservas= new ServicioReservas();
		Teclado t = new Teclado();
		Connection con =null;
		String salida=null;
		try {
			con= new ConexionMySql().getConexion();
			reserva= new Reservas();

			try {
			System.out.println("Introduce el id del usuario");
			reserva.setUsuario(t.leerEntero());

			System.out.println("Introduce el id de la funcion");
			reserva.setFuncion(t.leerEntero());
			
			System.out.println("Introduce la fila");
			reserva.setFila(t.leerEntero());

			System.out.println("Introduce la butaca");
			reserva.setButaca(t.leerEntero());
			
			System.out.println("Introduce la entrada");
			reserva.setEntrada(t.leerEntero());

			} catch (NumberFormatException e) {
				salida="mete un numero correcto";
			}
			scReservas.insertarReserva(reserva);
			salida= "Reserva insertada correctamene";
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
