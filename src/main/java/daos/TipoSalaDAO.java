package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.TipoSala;
import domain.Usuario;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class TipoSalaDAO {

	private Connection con;

	public TipoSalaDAO (Connection con) {
		this.con= con;
	}

	public void insertarTipoSala(TipoSala tipoSala) throws DAOException {

		PreparedStatement st= null;
		try {
			st= con.prepareStatement(DbQuery.getInsertarTipoSala());
			st.setString(1, tipoSala.getDescripcion());
			st.setInt(2, tipoSala.getFila());
			st.setInt(3, tipoSala.getButaca());
			st.setInt(4, tipoSala.getCapacidad());
			st.execute();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new DAOException(" El tipo de sala ya existe");
			} else {
				throw new DAOException("Error en la base de datos", e);
			}
		} finally {// cerramos cursores 
			Recursos.closePreparedStatement(st);

		}	
	}

	public int borrarTipoSalaID(int idTipoSala) throws DAOException {
		PreparedStatement st=null;
		int filas=0;
		try {
			st=con.prepareStatement(DbQuery.getBorrarTipoSalaID());
			st.setInt(1, idTipoSala);
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1452) {
				throw new DAOException("No se pudo eliminar el Tipo de Sala ya que este en uso");	
			}else
				throw new DAOException("Error de la base de datos", e);
		}  finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}

	public TipoSala recuperarTipoSalaID(int idTipoSala) throws DAOException {

		TipoSala tipoSala= null;
		PreparedStatement st = null;
		ResultSet rs =null ;

		try {
			st= con.prepareStatement(DbQuery.getRecuperarTipoSalaID());
			st.setInt(1, idTipoSala);
			rs=st.executeQuery();
			if(rs.next()) {
				tipoSala= new TipoSala(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en la base de datos", e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return tipoSala;
	}

	public TipoSala recuperarTipoSala(TipoSala tipoSala) throws DAOException {

		PreparedStatement st = null;
		ResultSet rs =null ;

		try {
			st= con.prepareStatement(DbQuery.getRecuperarTipoSalaID());
			st.setInt(1, tipoSala.getIdTipoSala());
			rs=st.executeQuery();
			if(rs.next()) {
				tipoSala= new TipoSala(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en la base de datos", e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return tipoSala;
	}
}
