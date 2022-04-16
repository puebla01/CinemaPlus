package servicios;

import daos.TipoSalaDAO;
import daos.TransaccionesManager;
import domain.TipoSala;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioTipoSala {

	public void insertarTipoSala(TipoSala tipoSala) throws ServiceException {
		TransaccionesManager trans=null;
		TipoSalaDAO tipoSalaDAO=null;
		try {
			trans= new TransaccionesManager();
			tipoSalaDAO = trans.getTipoSalaDAO();
			tipoSalaDAO.insertarTipoSala(tipoSala);
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

	public int borrarTipoSalaID(int idTipoSala) throws ServiceException {
		TransaccionesManager trans=null;
		int borrado=0;

		try {
			trans = new TransaccionesManager();
			TipoSalaDAO tipoSalaDAO = trans.getTipoSalaDAO(); 
			borrado =tipoSalaDAO.borrarTipoSalaID(idTipoSala);
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

	public TipoSala recuperarTipoSalaID(int idTipoSala) throws ServiceException {

		TipoSala tipoSala= new TipoSala();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			TipoSalaDAO tipoSalaDAO = trans.getTipoSalaDAO();
			tipoSala=tipoSalaDAO.recuperarTipoSalaID(idTipoSala);
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
		return tipoSala;
	}

}
