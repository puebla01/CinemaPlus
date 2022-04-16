package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.EntradaDAO;
import daos.ReservaDAO;
import daos.TransaccionesManager;
import daos.UsuarioDAO;
import domain.Reservas;
import domain.Usuario;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioReservas {

	public void insertarReserva(Reservas reserva) throws ServiceException {
		TransaccionesManager trans=null;
		ReservaDAO reservaDAO=null;
		try {
			trans= new TransaccionesManager();
			reservaDAO = trans.getReservaDAO();
			reservaDAO.insertarReserva(reserva);
			trans.closeCommit();

		}catch (DAOException e){

			try{
				if(trans!= null)
					trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		
	}
	public int borrarReservaID(int idReserva) throws ServiceException {
		TransaccionesManager trans=null;
		int borrado=0;
		ReservaDAO reservaDAO = null;
		try {
			trans = new TransaccionesManager();
			reservaDAO = trans.getReservaDAO(); 
			borrado =reservaDAO.borrarReservaID(idReserva);
			trans.closeCommit();

		} catch (DAOException e) {
			try{
				if(trans!=null)
					trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return borrado;
	}
	
	public List<Reservas> recuperarReservasPorIDFuncion(int idFuncion) throws ServiceException {
		TransaccionesManager trans=null;
		List<Reservas> listaReservas = new ArrayList<Reservas>();
		ReservaDAO reservaDAO = null;
		try {
			trans = new TransaccionesManager();
			reservaDAO = trans.getReservaDAO();
			listaReservas= reservaDAO.recuperarReservasPorIDFuncion(idFuncion);
			
			if(listaReservas.size()!=0){
				for(Reservas reserva :listaReservas) {
					ServicioUsuario scUsuario= new ServicioUsuario();
					
					reserva.setUsuario(scUsuario.recuperarUsuarioID(reserva.getUsuario().getIdUsuario()));
					
					ServicioFunciones scFuncion = new ServicioFunciones();
					reserva.setFuncion(scFuncion.recuperarFuncionPorIdFuncion(reserva.getFuncion().getIdFunciones()));
					
					ServicioEntradas scEntrada= new ServicioEntradas();
					reserva.setEntrada( scEntrada.recuperarEntradaID(reserva.getEntrada().getIdEntrada()));
					
				}
			}
			
			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!=null)
					trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
		}
		
		return listaReservas;
		
	}

}
