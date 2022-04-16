package programas.programasEntradas;

import java.sql.Connection;

import bbdd.ConexionMySql;
import domain.Entradas;
import domain.TipoSala;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioTipoSala;
import servicios.ServicioEntradas;
import util.Teclado;

public class ProbarInsertarEntradas {

	public static void main(String[] args) {

		Entradas entrada= null;
		ServicioEntradas scEntradas = new ServicioEntradas();
		Teclado t = new Teclado();
		Connection con =null;
		String salida=null;
		try {
			con= new ConexionMySql().getConexion();
			entrada= new Entradas();

			System.out.println("Introduce un nombre para el tipo de entrada");
			entrada.setDescripcion(t.leerCadena());

			try {

				System.out.println("Indica el precio de la entrada");
				entrada.setPrecio(t.leerNumero());
			} catch (NumberFormatException e) {
				salida="mete un numero correcto";
			}
			scEntradas.insertarEntrada(entrada);
			salida= "Tipo de sala insertada correctamene";
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
