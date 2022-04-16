package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.util.security.MD5Encoder;

import domain.Usuario;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.ErroresBD;
import recursos.Recursos;

public class UsuarioDAO   {

	private Connection con;

	public UsuarioDAO(Connection con) {
		this.con=con;
	}

	public void insertarUsuario(Usuario usuario) throws DAOException {

		PreparedStatement st= null;
		try {
			st= con.prepareStatement(DbQuery.getInsertarUsuario());
			//EL id es autoincrementable por lo que no lo ponemos
			st.setString(1, usuario.getNombre());
			st.setString(2, usuario.getPrimerApellido());
			st.setString(3, usuario.getSegundoApellido());
			st.setString(4, usuario.getEmail());

			Integer p= usuario.getPassword().hashCode();
			st.setString(5, p.toString()); //hascode para encriptar la contraseña
			st.setString(6, usuario.getTelefono());
			st.setInt(7, usuario.getTipo());
			st.execute();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new DAOException(" El usuario ya existe");
			} else {
				throw new DAOException("Error en la base de datos", e);
			}
		} finally {// cerramos cursores 
			Recursos.closePreparedStatement(st);

		}	
	}

	public int borrarUsuarioEmail(String email) throws DAOException {
		PreparedStatement st=null;
		int filas=0;
		try {
			st=con.prepareStatement(DbQuery.getBorrarUsuario());
			st.setString(1, email);
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1451)
				throw new DAOException("No se pudo eliminar el Usuario");
			else
				throw new DAOException("Error de la base de datos", e);
		}  finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}

	public Usuario recuperarUsuarioID(int idUsuario) throws DAOException {

		Usuario usuario= null;
		PreparedStatement st = null;
		ResultSet rs =null ;

		try {
			st= con.prepareStatement(DbQuery.getRecuperarUsuarioID());
			st.setInt(1, idUsuario);
			rs=st.executeQuery();
			if(rs.next()) {
				usuario= new Usuario(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en la base de datos", e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return usuario;
	}

	public Usuario recuperarUsuarioEmailPassword(String email, String password) throws DAOException {

		Usuario usuario= null;
		PreparedStatement st = null;
		ResultSet rs =null ;

		try {
			st= con.prepareStatement(DbQuery.recuperarUsuarioEmailPassword());
			st.setString(1, email);
			Integer p= password.hashCode();
			st.setString(2, p.toString()); //hascode para encriptar la contraseña
			rs=st.executeQuery();
			if(rs.next()) {
				usuario= new Usuario(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en la base de datos", e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return usuario;
	}
}
