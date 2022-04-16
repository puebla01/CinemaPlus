package servicios;

import daos.EntradaDAO;
import daos.TipoSalaDAO;
import daos.TransaccionesManager;
import domain.Entradas;
import domain.TipoSala;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioEntradas {

	public void insertarEntrada(Entradas entrada) throws ServiceException {
		TransaccionesManager trans=null;
		EntradaDAO entradaDAO=null;
		try {
			trans= new TransaccionesManager();
			entradaDAO = trans.getEntradaDAO();
			entradaDAO.insertarEntrada(entrada);
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

	public int borrarEntradaID(int idEntrada) throws ServiceException {
		TransaccionesManager trans=null;
		int borrado=0;
		EntradaDAO entradaDAO = null;
		try {
			trans = new TransaccionesManager();
			entradaDAO = trans.getEntradaDAO(); 
			borrado =entradaDAO.borrarEntradaID(idEntrada);
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

	public Entradas recuperarEntradaID(int idEntrada) throws ServiceException {

		Entradas entrada= new Entradas();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			EntradaDAO entradaDAO = trans.getEntradaDAO();
			entrada=entradaDAO.recuperarEntradaID(idEntrada);
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
		return entrada;
	}

}
