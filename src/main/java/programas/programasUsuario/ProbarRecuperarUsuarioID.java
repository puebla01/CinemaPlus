package programas.programasUsuario;

import domain.Usuario;
import exceptions.DAOException;
import exceptions.ServiceException;
import servicios.ServicioUsuario;
import util.Teclado;

public class ProbarRecuperarUsuarioID {

	public static void main(String[] args) throws  DAOException {

		Teclado t= new Teclado();
		Usuario usuario= new Usuario();
		ServicioUsuario scUsuario= new ServicioUsuario();
		String salida=null;
		boolean correcto=true;
		int idUsuario=0;
		try {
			System.out.println("Introduce un Id de usuario ");
			while (correcto){
				try {
					idUsuario= t.leerEntero();					 
					correcto=false;
				    } catch (NumberFormatException E) {
				    	System.out.println("Introduzca el número de ID bien MERLUZO");
				    }  
			}
		

			usuario=scUsuario.recuperarUsuarioID(idUsuario);
			if(usuario!=null) {
				System.out.println("Nombre: "+usuario.getNombre()+"\nPrimer Apellido: "+usuario.getPrimerApellido()+"\nEmail"+usuario.getEmail() );
				salida="proceso realizado correctamente";
			}else {
				salida="No existe una cuenta asignada a ese idUsuario";
			}

		}catch (ServiceException e) {
			if(e.getCause()==null){

				salida=(e.getMessage());//Error Lógico para usuario
			}else{
				e.printStackTrace();// para administrador
				salida=("error interno");//Error interno para usuario
			}
		}
		System.out.println(salida);

	}

}
