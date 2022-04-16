package servicios;

import daos.TransaccionesManager;
import daos.UsuarioDAO;
import domain.Usuario;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioUsuario {

	public ServicioUsuario() {	}

	public void insertarUsuario(Usuario usuario) throws ServiceException {

		TransaccionesManager trans = null;
		UsuarioDAO usuarioDAO = null;

		try {
			trans= new TransaccionesManager();
			usuarioDAO = trans.getUsuarioDAO();
			usuarioDAO.insertarUsuario(usuario);

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

	public int borrarUsuarioEmail(String email) throws ServiceException {
		TransaccionesManager trans=null;
		int borrado=0;

		try {
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO(); 
			borrado =usuarioDAO.borrarUsuarioEmail(email);
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

	public Usuario recuperarUsuarioID(int idUsuario) throws ServiceException {

		Usuario usuario= new Usuario();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuario=usuarioDAO.recuperarUsuarioID(idUsuario);
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
		return usuario;
	}
	
	public Usuario recuperarUsuarioEmailPassword(String email, String password) throws ServiceException {

		Usuario usuario= new Usuario();
		TransaccionesManager trans=null;
		try {
			trans= new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuario=usuarioDAO.recuperarUsuarioEmailPassword(email,password);
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
		return usuario;
	}
}