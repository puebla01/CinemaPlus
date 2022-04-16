package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.CategoriaPeliculaDAO;
import daos.PeliculaDAO;
import daos.TipoSalaDAO;
import daos.TransaccionesManager;
import domain.CategoriasPeliculas;
import domain.Peliculas;
import domain.TipoSala;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioCategoriaPelicula {

	public void insertarCategoria(CategoriasPeliculas categoria) throws ServiceException {


		TransaccionesManager trans = null;
		
		try {
			trans= new TransaccionesManager();
			CategoriaPeliculaDAO categoriaDAO= trans.getCategoriaDAO();
			categoriaDAO.insertarCategoria(categoria);
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



	public int borrarCategoriaID(int idCategoria) throws ServiceException {
		TransaccionesManager trans = null;
		int borrado=0;
			
			try {
				trans= new TransaccionesManager();
				CategoriaPeliculaDAO categoriaDAO= trans.getCategoriaDAO();
				borrado=categoriaDAO.borrarCategoriaID(idCategoria);
				trans.closeCommit();
			} catch (DAOException e){

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
		return borrado;
	}



	public CategoriasPeliculas recuperarCategoriaID(int idCategoria) throws ServiceException {

		CategoriasPeliculas categoria= new CategoriasPeliculas();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			CategoriaPeliculaDAO categoriaDAO = trans.getCategoriaDAO();
			categoria=categoriaDAO.recuperarCategoriaID(idCategoria);
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
		return categoria;
	}
	public List<CategoriasPeliculas> recuperarTodasCategorias() throws ServiceException {

		List<CategoriasPeliculas> listaCategorias= new ArrayList<CategoriasPeliculas>();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			CategoriaPeliculaDAO categoriaDAO = trans.getCategoriaDAO();
			listaCategorias=categoriaDAO.recuperarTodasCategorias();
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
		return listaCategorias;
	}

}
