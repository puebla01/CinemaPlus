package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.CategoriaPeliculaDAO;
import daos.SalaDAO;
import daos.TipoSalaDAO;
import daos.TransaccionesManager;
import domain.CategoriasPeliculas;
import domain.Peliculas;
import domain.Salas;
import domain.TipoSala;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioSala {

	public void insertarSala(Salas sala) throws ServiceException {
		TransaccionesManager trans=null;
		SalaDAO salaDAO=null;
		try {
			trans= new TransaccionesManager();
			salaDAO = trans.getSalaDAO();
			salaDAO.insertarTipoSala(sala);
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

	public int borrarTipoSalaID(int idSala) throws ServiceException {
		TransaccionesManager trans=null;
		int borrado=0;

		try {
			trans = new TransaccionesManager();
			SalaDAO salaDAO = trans.getSalaDAO(); 
			borrado =salaDAO.borrarSalaDAO(idSala);
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

	public Salas recuperarSalaID(Salas idSala) throws ServiceException {

		Salas sala= new Salas();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			SalaDAO salaDAO = trans.getSalaDAO();
			sala=salaDAO.recuperarSalaID(idSala);
			if(sala!=null) {
				TipoSalaDAO tipoSalaDAO= trans.getTipoSalaDAO();
				TipoSala tipoSala= tipoSalaDAO.recuperarTipoSala(sala.getTipoSala());
				sala.setTipoSala(tipoSala);
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
		return sala;
	}
	
	public List<Salas> recuperarTodasSalas() throws ServiceException {

		List<Salas> listaSalas= new ArrayList<Salas>();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			SalaDAO salaDAO = trans.getSalaDAO();
			listaSalas=salaDAO.recuperarTodasSalas();
			
			if(listaSalas.size()!=0) {
				for(int i=0;i<listaSalas.size();i++){
					TipoSalaDAO tipoSalaDAO = trans.getTipoSalaDAO();
					TipoSala tipoSala=tipoSalaDAO.recuperarTipoSala(listaSalas.get(i).getTipoSala());
					listaSalas.get(i).setTipoSala(tipoSala);
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
		return listaSalas;
	}
}
