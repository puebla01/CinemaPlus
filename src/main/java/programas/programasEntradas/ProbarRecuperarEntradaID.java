package programas.programasEntradas;

import domain.Entradas;
import exceptions.DAOException;
import exceptions.ServiceException;
import servicios.ServicioEntradas;
import util.Teclado;

public class ProbarRecuperarEntradaID {

	public static void main(String[] args) throws  DAOException {

	Teclado t= new Teclado();
	Entradas entrada= new Entradas();
	ServicioEntradas scEntrada= new ServicioEntradas();
	String salida=null;
	int idEntrada=0;
	boolean correcto= true;
	try {
			
		System.out.println("Introduce un Id de la entrada ");
		while (correcto){
			try {
				idEntrada= t.leerEntero();
				 correcto=false;
			    } catch (NumberFormatException E) {
			    	System.out.println("Introduzca el número de ID bien MERLUZO");
			    }  
		}

		
		entrada=scEntrada.recuperarEntradaID(idEntrada);
		if(entrada!=null) {
			System.out.println("Nombre Entrada: "+entrada.getDescripcion());
			salida="proceso realizado correctamente";
		}else {
			salida="No existe una entrada asignada a ese idEntrada"+ idEntrada;
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
