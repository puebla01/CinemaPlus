package servicios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import daos.CategoriaPeliculaDAO;
import daos.FuncionDAO;
import daos.PeliculaDAO;
import daos.SalaDAO;
import daos.TransaccionesManager;
import domain.CategoriasPeliculas;
import domain.Funciones;
import domain.Peliculas;
import domain.Salas;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioFunciones {

	public void insertarFuncion(Funciones funcion) throws ServiceException {

		TransaccionesManager trans=null;
		FuncionDAO funcionDAO=null;
		try {
			trans= new TransaccionesManager();
			funcionDAO = trans.getFuncionDAO();
			funcionDAO.insertarFuncion(funcion);
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
	public List<Funciones> recuperarTodasFunciones() throws ServiceException{

		List<Funciones> listaFunciones= new ArrayList<Funciones>();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			FuncionDAO funcionDAO = trans.getFuncionDAO();
			listaFunciones=funcionDAO.recuperarTodasFunciones();
			if(listaFunciones.size()!=0){		
				for(int i=0;i<listaFunciones.size();i++){
					PeliculaDAO peliculaDAO = trans.getPeliculaDAO();
					Peliculas pelicula=peliculaDAO.recuperarPeliculaID(listaFunciones.get(i).getPelicula());
					listaFunciones.get(i).setPelicula(pelicula);
					
					SalaDAO salaDAO = trans.getSalaDAO();
					Salas sala= salaDAO.recuperarSalaID(listaFunciones.get(i).getSala());
					listaFunciones.get(i).setSala(sala);
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
		return listaFunciones;
	}
	
	public List<Funciones> recuperarTodasFuncionesPorPelicula(int idPelicula) throws ServiceException{

		List<Funciones> listaFunciones= new ArrayList<Funciones>();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			FuncionDAO funcionDAO = trans.getFuncionDAO();
			listaFunciones=funcionDAO.recuperarTodasFuncionesPorPelicula(idPelicula);
			if(listaFunciones.size()!=0){		
				for(int i=0;i<listaFunciones.size();i++){
					PeliculaDAO peliculaDAO = trans.getPeliculaDAO();
					Peliculas pelicula=peliculaDAO.recuperarPeliculaID(listaFunciones.get(i).getPelicula());
					listaFunciones.get(i).setPelicula(pelicula);
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
		return listaFunciones;
	}
	public List<Funciones> recuperarFuncionPorDiaYPelicula(int idPelicula, Date dia) throws ServiceException {

		List<Funciones> listaFunciones= new ArrayList<Funciones>();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			FuncionDAO funcionDAO = trans.getFuncionDAO();
			listaFunciones=funcionDAO.recuperarFuncionPorDiaYPelicula(idPelicula,dia);
		
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
		return listaFunciones;
	}
	
	public Funciones recuperarFuncionPorIdFuncion(int idFuncion) throws ServiceException{

		Funciones funcion= null;
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			FuncionDAO funcionDAO = trans.getFuncionDAO();
			funcion=funcionDAO.recuperarFuncionPorIdFuncion(idFuncion);
			
					PeliculaDAO peliculaDAO = trans.getPeliculaDAO();
					Peliculas pelicula=peliculaDAO.recuperarPeliculaID(funcion.getPelicula());
					funcion.setPelicula(pelicula);
					
					ServicioSala scSala = new ServicioSala();
					Salas sala= scSala.recuperarSalaID(funcion.getSala());
					
							
//					SalaDAO salaDAO = trans.getSalaDAO();
					funcion.setSala(sala);
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
		return funcion;
	}
}
