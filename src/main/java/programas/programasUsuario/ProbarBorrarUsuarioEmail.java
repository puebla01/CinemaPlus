package programas.programasUsuario;

import domain.Usuario;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioUsuario;
import util.Teclado;

public class ProbarBorrarUsuarioEmail {


		
		public static void main(String[] args) throws  DAOException {

			String email = null;
			int borrado=0;
			ServicioUsuario scUsuario = new ServicioUsuario();

			String salida = null;
			System.out.println("introduzca el correo del usuario que quiere eliminar");
			Teclado t = new Teclado();
			
			try {
				email=t.leerCadena();
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				borrado=scUsuario.borrarUsuarioEmail(email);
				if(borrado==0) {
					salida="No existe un usuario asignado a este email";
				}else {
					salida="Usuario borrado";			
				}
			} catch (ServiceException | DomainException e) {
				if(e.getCause()==null){
					salida=e.getMessage();//Error Lógico para usuario
				}else{
					e.printStackTrace();// para administrador
					salida="eror interno";//Error interno para usuario
				}
			}
			System.out.println(salida);//dispacher salida para mostrar el error		
		}
	
}
