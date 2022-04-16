package programas.programasSalas;

import domain.Salas;
import domain.TipoSala;
import exceptions.ServiceException;
import servicios.ServicioSala;
import servicios.ServicioTipoSala;
import util.Teclado;

public class ProbarRecuperarSala {

	public static void main(String[] args) {

		Teclado t= new Teclado();
		Salas sala= new Salas();
		ServicioSala scSala= new ServicioSala ();
		String salida=null;
		boolean correcto=true;
		Salas idSala=null;
		try {
			System.out.println("Introduce un Id de la sala ");
			while (correcto){
				try {
					 idSala.setIdSala( t.leerEntero());
					correcto=false;
				    } catch (NumberFormatException E) {
				    	System.out.println("Introduzca el número de ID bien MERLUZO");
				    }  
			}

			sala=scSala.recuperarSalaID(idSala);
			if(sala!=null) {
				System.out.println("Nombre: "+sala.getNombre());
				TipoSala tipoSala= new TipoSala();
				//creamos objeto tipo de sala para recuperar el tipo de la sala
				ServicioTipoSala scTipoSala= new ServicioTipoSala();
				
				tipoSala=scTipoSala.recuperarTipoSalaID(sala.getIdSala());
				System.out.println("Tipo sala: "+tipoSala.getDescripcion());
				
				salida="proceso realizado correctamente";
			}else {
				salida="No existe una sala asignada a este idSala";
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
