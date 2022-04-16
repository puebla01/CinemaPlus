package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Entradas;
import domain.TipoSala;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class EntradaDAO {
	private Connection con;

	public EntradaDAO (Connection con) {
		this.con= con;
	}

	public void insertarEntrada(Entradas entrada) throws DAOException {
		PreparedStatement st= null;
		try {
			st= con.prepareStatement(DbQuery.getInsertarEntrada());
			st.setString(1, entrada.getDescripcion());
			st.setDouble(2, entrada.getPrecio());
			st.execute();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new DAOException(" El tipo de entrada ya existe");
			} else {
				throw new DAOException("Error en la base de datos", e);
			}
		} finally {// cerramos cursores 
			Recursos.closePreparedStatement(st);

		}	

	}

	public int borrarEntradaID(int idEntrada) throws DAOException {
		PreparedStatement st=null;
		int filas=0;
		try {
			st=con.prepareStatement(DbQuery.getBorrarEntradaID());
			st.setInt(1, idEntrada);
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1452) {
				throw new DAOException("No se pudo eliminar el tipo de entrada ya que está en uso");
			}else
				throw new DAOException("Error de la base de datos", e);
		}  finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}

	public Entradas recuperarEntradaID(int idEntrada) throws DAOException {

		Entradas entrada= null;
		PreparedStatement st = null;
		ResultSet rs =null ;

		try {
			st= con.prepareStatement(DbQuery.getRecuperarEntradaID());
			st.setInt(1, idEntrada);
			rs=st.executeQuery();
			if(rs.next()) {
				entrada= new Entradas(rs.getInt(1), rs.getString(2),rs.getDouble(3));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en la base de datos", e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return entrada;
	}

}
