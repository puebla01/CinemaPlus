package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.CategoriasPeliculas;
import domain.Entradas;
import domain.Funciones;
import domain.Peliculas;
import domain.Reservas;
import domain.Usuario;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class ReservaDAO {
	private Connection con;

	public ReservaDAO (Connection con) {
		this.con= con;
	}

	public void insertarReserva(Reservas reserva) throws DAOException {
		PreparedStatement st= null;
		try {
			st= con.prepareStatement(DbQuery.getInsertarReserva());
			st.setInt(1, reserva.getUsuario().getIdUsuario());
			st.setInt(2, reserva.getFuncion().getIdFunciones());
			st.setInt(3, reserva.getFila());
			st.setInt(4, reserva.getButaca());
			st.setInt(5, reserva.getEntrada().getIdEntrada());
			
			st.execute();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				throw new DAOException(" La reserva ya existe");
			} else {
				throw new DAOException("Error en la base de datos", e);
			}
		} finally {// cerramos cursores 
			Recursos.closePreparedStatement(st);

		}	

	}

	public int borrarReservaID(int idReserva) throws DAOException {
		PreparedStatement st=null;
		int filas=0;
		try {
			st=con.prepareStatement(DbQuery.getBorrarReservaID());
			st.setInt(1, idReserva);
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1452) {
				throw new DAOException("No se pudo eliminar la reserva ya que está en uso");
			}else
				throw new DAOException("Error de la base de datos", e);
		}  finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}

	public List<Reservas> recuperarReservasPorIDFuncion(int idFuncion) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Reservas> list = new ArrayList<Reservas>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarReservasPorIDFuncion());
			st.setInt(1, idFuncion);
			rs = st.executeQuery();
			while (rs.next()) {
				Reservas reservas = new Reservas(rs.getInt("idreservas"),
						new Usuario (rs.getInt("usuario")),
						new Funciones(rs.getInt("funcion")),
						rs.getInt("fila"),
						rs.getInt("butaca"),
						new Entradas(rs.getInt("entrada")));

				list.add(reservas);
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
