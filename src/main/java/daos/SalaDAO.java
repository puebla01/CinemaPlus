package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Peliculas;
import domain.Salas;
import domain.TipoSala;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class SalaDAO {
	private Connection con;

	public SalaDAO (Connection con) {
		this.con= con;
	}

	public void insertarTipoSala(Salas sala) throws DAOException {

		PreparedStatement st= null;
		PreparedStatement sti= null;
		ResultSet rs=null;

		try {
			st= con.prepareStatement(DbQuery.getInsertarSala());
			st.setString(1, sala.getNombre());
			st.setInt(2, sala.getTipoSala().getIdTipoSala());

			//Compruebo que el tipo de sala existe
			try {
				sti=con.prepareStatement(DbQuery.getRecuperarTipoSalaID());
				sti.setInt(1, sala.getTipoSala().getIdTipoSala());
				rs=sti.executeQuery();
				if(!rs.next()) {	
					throw new DAOException(" La tipo de sala de la pelicula  no existe");
				}
			}finally {
				Recursos.closeResultSet( rs);	
			}

			st.execute();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new DAOException(" La sala ya existe");
			}else if (e.getErrorCode() ==1452 ) {
				throw new DAOException("El tipo de sala no existe"); //1452 error fk
			} else {
				throw new DAOException("Error en la base de datos", e);
			}	
		} finally {// cerramos cursores 
			Recursos.closePreparedStatement(st);
		}
	}

	public int borrarSalaDAO(int idSala) throws DAOException {
		PreparedStatement st=null;
		int filas=0;
		try {
			st=con.prepareStatement(DbQuery.getBorrarSalaID());
			st.setInt(1, idSala);
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1452) {
				throw new DAOException("No se puede borrar la Sala ya que esta en uso");
			}else
				throw new DAOException("Error de la base de datos", e);
		}  finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}

	public Salas recuperarSalaID(Salas salas) throws DAOException {

		Salas sala= null;
		PreparedStatement st = null;
		ResultSet rs =null ;

		try {
			st= con.prepareStatement(DbQuery.getRecuperarSalaID());
			st.setInt(1, salas.getIdSala());
			rs=st.executeQuery();
			if(rs.next()) {
				sala= new Salas(rs.getInt(1), rs.getString(2),new TipoSala(rs.getInt(3)));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en la base de datos", e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return sala;
	}

	public List<Salas> recuperarTodasSalas() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Salas> list = new ArrayList<Salas>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodasSalas());
			rs = st.executeQuery();
			while (rs.next()) {
				Salas sala = new Salas(rs.getInt("idsala"),
						rs.getString("nombre"),
						new TipoSala(rs.getInt("tiposala")));

				list.add(sala);
			}
		} catch (SQLException e) {
			throw new DAOException("Error base de datos", e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
	}
}
