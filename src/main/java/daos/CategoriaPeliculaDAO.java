package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.CategoriasPeliculas;
import domain.Peliculas;
import domain.TipoSala;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class CategoriaPeliculaDAO {
	private Connection con;

	public CategoriaPeliculaDAO(Connection con) {
		this.con=con;
	}

	public void insertarCategoria(CategoriasPeliculas categoria) throws DAOException {
		// TODO Auto-generated method stub

		PreparedStatement st=null;

		try {
			st= con.prepareStatement(DbQuery.getInsertarCategoria());
			st.setString(1, categoria.getDescripcion());
			st.execute();
		}catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new DAOException(" La categoria ya existe");
			} else {
				throw new DAOException("Error en la base de datos", e);
			}
		} finally {// cerramos cursores 
			Recursos.closePreparedStatement(st);

		}	
	}

	public int borrarCategoriaID(int idCategoria) throws DAOException {

		PreparedStatement st= null;
		int filas=0;
		try {
			st= con.prepareStatement(DbQuery.getBorrarCategoria());
			st.setInt(1, idCategoria);
			filas=st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1452) {
				throw new DAOException("No se pudo eliminar la categoria ya que está en uso");
			}else {
				throw new DAOException("Error de la base de datos", e);
			}
		}finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}


	public CategoriasPeliculas recuperarCategoria(CategoriasPeliculas categoria) throws DAOException {

		PreparedStatement st = null;
		ResultSet rs =null ;

		try {
			st= con.prepareStatement(DbQuery.getRecuperarCategoriaID());
			st.setInt(1, categoria.getidCategorias());
			rs=st.executeQuery();
			if(rs.next()) {
				categoria= new CategoriasPeliculas(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en la base de datos", e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return categoria;
	}

	public CategoriasPeliculas recuperarCategoriaID(int idCategoria) throws DAOException {

		CategoriasPeliculas categoria= null;
		PreparedStatement st = null;
		ResultSet rs =null ;

		try {
			st= con.prepareStatement(DbQuery.getRecuperarCategoriaID());
			st.setInt(1, idCategoria);
			rs=st.executeQuery();
			if(rs.next()) {
				categoria= new CategoriasPeliculas(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			throw new DAOException("Error en la base de datos", e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return categoria;
	}

	public List<CategoriasPeliculas> recuperarTodasCategorias() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<CategoriasPeliculas> list = new ArrayList<CategoriasPeliculas>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodasCategorias());
			rs = st.executeQuery();
			while (rs.next()) {
				CategoriasPeliculas pelicula = new CategoriasPeliculas(rs.getInt("idcategorias"),
						rs.getString("descripcion"));

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
