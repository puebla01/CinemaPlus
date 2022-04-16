package programas.programasTipoSala;

import java.sql.Connection;

import bbdd.ConexionMySql;
import domain.TipoSala;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioTipoSala;
import util.Teclado;

public class ProbarInsertarTipoSala {

	public static void main(String[] args)  {
		TipoSala tipoSala= null;
		ServicioTipoSala scTipoSala = new ServicioTipoSala();
		Teclado t = new Teclado();
		Connection con =null;
		String salida=null;
		try {
			con= new ConexionMySql().getConexion();
			tipoSala= new TipoSala();

			System.out.println("Introduce un nombre para el tipo de sala");
			tipoSala.setDescripcion(t.leerCadena());

			try {

				System.out.println("Indica cuantas filas va a tener la sala");
				tipoSala.setFila(t.leerEntero());
			} catch (NumberFormatException e) {
				salida="mete un nnumero correcto";
			}

			System.out.println("Indica cuantas butacas va a haber en las filas");
			tipoSala.setButaca(t.leerEntero());

			tipoSala.setCapacidad(tipoSala.getFila()*tipoSala.getButaca());//La capacidad de la sala la vamos a poner como el numero de filas por el de butacas

			scTipoSala.insertarTipoSala(tipoSala);
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
