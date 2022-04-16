package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.CategoriasPeliculas;
import domain.Entradas;
import domain.Peliculas;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class PeliculaDAO {
	private Connection con;

	public PeliculaDAO (Connection con) {
		this.con= con;
	}


	public void insertarPelicula(Peliculas pelicula) throws DAOException {
		PreparedStatement st= null;
		PreparedStatement sti= null;
		ResultSet rs=null;

		try {
			st= con.prepareStatement(DbQuery.getInsertarPelicula());
			st.setString(1, pelicula.getTitulo());
			st.setString(2, pelicula.getResumen());
			st.setInt(3, pelicula.getDuracion());
			st.setInt(4, pelicula.getCategoria().getidCategorias());
			st.setString(5, pelicula.getImagen());
			
			//Compruebo la categoria
			try{
				  sti = con.prepareStatement(DbQuery.getRecuperarCategoriaID());
				  sti.setInt(1, pelicula.getCategoria().getidCategorias());
				  rs=sti.executeQuery();
				  if(!rs.next()) {	
					  throw new DAOException(" La categoria de la pelicula  no existe");
				  }
				 }  finally {
					  Recursos.closeResultSet( rs);	
				 }
			st.execute();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new DAOException(" La pelicula ya existe");
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


	public int borrarEntradaID(int idPelicula) throws DAOException {
		PreparedStatement st=null;
		int filas=0;
		try {
			st=con.prepareStatement(DbQuery.getBorrarPeliculaID());
			st.setInt(1, idPelicula);
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1452)
				throw new DAOException("No se puede borrar la pelicula ya que está en uso");
			else
				throw new DAOException("Error de la base de datos", e);
		}  finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}


	public Peliculas recuperarPeliculaID(int idPelicula) throws DAOException {

		Peliculas pelicula= null;
		PreparedStatement st = null;
		ResultSet rs =null ;

		try {
			st= con.prepareStatement(DbQuery.getRecuperarPeliculaID());
			st.setInt(1, idPelicula);
			rs=st.executeQuery();
			if(rs.next()) {
				pelicula= new Peliculas(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						new CategoriasPeliculas(rs.getInt(5)),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en la base de datos", e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return pelicula;
	}

	public Peliculas recuperarPeliculaID(Peliculas pelicula) throws DAOException {

		PreparedStatement st = null;
		ResultSet rs =null ;

		try {
			st= con.prepareStatement(DbQuery.getRecuperarPeliculaID());
			st.setInt(1, pelicula.getIdPelicula());
			rs=st.executeQuery();
			if(rs.next()) {
				pelicula= new Peliculas(rs.getInt("idpeliculas"),
						rs.getString("titulo"),
						rs.getString("resumen"),
						rs.getInt("duracion"),
						new CategoriasPeliculas(rs.getInt("categoria")),
						rs.getString("imagen"),
						rs.getString("pais"),
						rs.getString("director"),
						rs.getString("guionista"),
						rs.getString("productora"),
						rs.getString("actores"));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en la base de datos", e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return pelicula;
	}


	public List<Peliculas> recuperarTodasPeliculas() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Peliculas> list = new ArrayList<Peliculas>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodasPeliculas());
			rs = st.executeQuery();
			while (rs.next()) {
				Peliculas pelicula = new Peliculas(rs.getInt("idpeliculas"),
						rs.getString("titulo"),
						rs.getString("resumen"),
						rs.getInt("duracion"),
						new CategoriasPeliculas(rs.getInt("categoria")),
						rs.getString("imagen"),
						rs.getString("pais"),
						rs.getString("director"),
						rs.getString("guionista"),
						rs.getString("productora"),
						rs.getString("actores"));

				list.add(pelicula);
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
