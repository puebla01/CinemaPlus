package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import domain.Funciones;
import domain.Peliculas;
import domain.Salas;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class FuncionDAO {
private Connection con;
	
	public FuncionDAO (Connection con) {
		this.con= con;
	}

	public void insertarFuncion(Funciones funcion) throws DAOException {
		PreparedStatement st= null;
		try {
			st= con.prepareStatement(DbQuery.getInsertarFuncion());
			st.setInt(1, funcion.getPelicula().getIdPelicula());
			st.setInt(2, funcion.getSala().getIdSala());
			st.setDate(3, funcion.getFecha());
			st.setString(4, funcion.getHora());
			st.execute();
			
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new DAOException(" La funcion ya existe");
			}else if(e.getErrorCode()==1452) {
				throw new DAOException(" La categoria no existe");

			}
			else {
				throw new DAOException("Error en la base de datos", e);
			}
		} finally {// cerramos cursores 
			Recursos.closePreparedStatement(st);

		}			
	}

	public List<Funciones> recuperarTodasFunciones() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Funciones> list = new ArrayList<Funciones>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodasFunciones());
			rs = st.executeQuery();
			while (rs.next()) {
				Funciones funcion = new Funciones(rs.getInt("idfunciones"),
						new Peliculas(rs.getInt("pelicula")),
						new Salas(rs.getInt("sala")),
						rs.getDate("fecha"),
						rs.getString("hora"));
				list.add(funcion);
			}
		} catch (SQLException e) {
			throw new DAOException("Error base de datos", e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
	}

	public List<Funciones> recuperarTodasFuncionesPorPelicula(int idPelicula) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Funciones> list = new ArrayList<Funciones>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodasFuncionesPorPelicula());
			st.setInt(1, idPelicula);
			rs = st.executeQuery();
			while (rs.next()) {
				Funciones funcion = new Funciones(rs.getInt("idfunciones"),
						new Peliculas(rs.getInt("pelicula")),
						new Salas(rs.getInt("sala")),
						rs.getDate("fecha"),
						rs.getString("hora"));
				list.add(funcion);
			}
		} catch (SQLException e) {
			throw new DAOException("Error base de datos", e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
	}


	public List<Funciones> recuperarFuncionPorDiaYPelicula(int idPelicula, Date dia) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Funciones> list = new ArrayList<Funciones>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarFuncionPorDiaYPelicula());
			st.setInt(1, idPelicula);
			st.setDate(2, dia);
			rs = st.executeQuery();
			while (rs.next()) {
				Funciones funcion = new Funciones(rs.getInt(1),
						new Peliculas(rs.getInt(2)),
						new Salas(rs.getInt(3)),
						rs.getDate(4),
						rs.getString(5));
				list.add(funcion);
			}
		} catch (SQLException e) {
			throw new DAOException("Error base de datos", e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
	
	
	}

	public Funciones recuperarFuncionPorIdFuncion(int idFuncion) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		Funciones funcion =null;
		try {
			st = con.prepareStatement(DbQuery.getRecuperarFuncionIdFuncion());
			st.setInt(1, idFuncion);
			rs = st.executeQuery();
			if (rs.next()) {
				 funcion = new Funciones(rs.getInt("idfunciones"),
						new Peliculas(rs.getInt("pelicula")),
						new Salas(rs.getInt("sala")),
						rs.getDate("fecha"),
						rs.getString("hora"));
			}
		} catch (SQLException e) {
			throw new DAOException("Error base de datos", e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return funcion;
	}


}
