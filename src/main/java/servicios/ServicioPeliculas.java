package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.CategoriaPeliculaDAO;
import daos.EntradaDAO;
import daos.PeliculaDAO;
import daos.TransaccionesManager;
import domain.CategoriasPeliculas;
import domain.Entradas;
import domain.Peliculas;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioPeliculas {

	public void insertarPelicula(Peliculas pelicula) throws ServiceException {

		TransaccionesManager trans=null;
		PeliculaDAO peliculaDAO=null;
		try {
			trans= new TransaccionesManager();
			peliculaDAO = trans.getPeliculaDAO();
			peliculaDAO.insertarPelicula(pelicula);
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

	public int borrarPeliculaID(int idPelicula) throws ServiceException {
		TransaccionesManager trans=null;
		int borrado=0;
		PeliculaDAO peliculaDAO = null;
		try {
			trans = new TransaccionesManager();
			peliculaDAO = trans.getPeliculaDAO(); 
			borrado =peliculaDAO.borrarEntradaID(idPelicula);
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

	public Peliculas recuperarPeliculaID(int idPelicula) throws ServiceException {

		Peliculas pelicula= new Peliculas();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			PeliculaDAO peliculaDAO = trans.getPeliculaDAO();
			pelicula=peliculaDAO.recuperarPeliculaID(idPelicula);


			if(pelicula!=null){
				CategoriaPeliculaDAO categoriaDAO= trans.getCategoriaDAO();
				CategoriasPeliculas categoria = categoriaDAO.recuperarCategoria(pelicula.getCategoria());
				pelicula.setCategoria(categoria);

			}else{
				pelicula=null;
				trans.closeCommit();
			}
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
		return pelicula;
	}


	public List<Peliculas> recuperarTodasPeliculas() throws ServiceException {

		List<Peliculas> listaPeliculas= new ArrayList<Peliculas>();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			PeliculaDAO peliculaDAO = trans.getPeliculaDAO();
			listaPeliculas=peliculaDAO.recuperarTodasPeliculas();

			if(listaPeliculas.size()!=0){		
				for(int i=0;i<listaPeliculas.size();i++){
					CategoriaPeliculaDAO categoriaDAO = trans.getCategoriaDAO();
					CategoriasPeliculas categoria=categoriaDAO.recuperarCategoria(listaPeliculas.get(i).getCategoria());
					listaPeliculas.get(i).setCategoria(categoria);
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
		return listaPeliculas;
	}


}
