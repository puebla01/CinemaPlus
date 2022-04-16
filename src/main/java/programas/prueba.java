package programas;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import exceptions.ServiceException;
import servicios.ServicioFunciones;
import util.Fecha;
import util.Teclado;

public class prueba {
	public static void main(String[] args) throws ServiceException {


		ServicioFunciones sc = new ServicioFunciones();
		Date d=null;

		List<String> lista = new ArrayList<String>();
		
		try {
			lista= sc.recuperarHorasPorDiaYPelicula(3, Fecha.convertirADate("2022-02-22", "yyyy-MM-dd"));
			System.out.println();
			System.out.println(lista.size());
			
			for (String string : lista) {
				System.out.println(string);
			}
		} catch (ServiceException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
