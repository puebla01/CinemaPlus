package programas.programasTipoSala;

import domain.TipoSala;
import exceptions.DAOException;
import exceptions.ServiceException;
import servicios.ServicioTipoSala;
import util.Teclado;

public class ProbarRecuperarTipoSalaID {

	public static void main(String[] args) throws  DAOException {

	Teclado t= new Teclado();
	TipoSala tipoSala= new TipoSala();
	ServicioTipoSala scTipoSala= new ServicioTipoSala();
	String salida=null;
	boolean correcto=true;
	int idTipoSala=0;
	try {
		System.out.println("Introduce un Id del tipo de sala ");
		while (correcto){
			try {
				 idTipoSala= t.leerEntero();
				correcto=false;
			    } catch (NumberFormatException E) {
			    	System.out.println("Introduzca el número de ID bien MERLUZO");
			    }  
		}

		tipoSala=scTipoSala.recuperarTipoSalaID(idTipoSala);
		if(tipoSala!=null) {
			System.out.println("Nombre: "+tipoSala.getDescripcion());
			salida="proceso realizado correctamente";
		}else {
			salida="No existe un tipo de sala asignado a este idTipoSala";
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
