package recursos;

public class DbQuery {

	//USUARIOS
	private static final String insertarUsuario= "insert into usuarios values (null,?,?,?,?,?,?,?)"; //poneos la primera null por que es el id que es autoincrementable
	private static final String borrarUsuario= "delete from usuarios where email like (?)";
	private static final String modificarUsuario= "";
	private static final String recuperarUsuarioID= "select idusuarios,nombre,primerapellido,segundoapellido,email,password,telefono,tipousuario from usuarios where idusuarios like (?)";
	private static final String recuperarUsuarioEmailPassword = "select idusuarios,nombre,primerapellido,segundoapellido,email,password,telefono,tipousuario from usuarios where email like ? and password like ?";
	
	//CATEGORIAS PELICULAS
	private static final String insertarCategoria= "insert into categorias values(null,?)";
	private static final String borrarCategoria= "delete from categorias where IDCATEGORIAS like (?)";
	private static final String recuperarCategoriaID= "select idcategorias,descripcion from categorias where idcategorias like(?)";
	private static final String recuperarTodasCategorias= "select idcategorias,descripcion from categorias";
	
	//TIPO SALA
	private static final String insertarTipoSala= "insert into tiposala values(null,?,?,?,?)";
	private static final String borrarTipoSalaID= "delete from tiposala where idtiposala like (?)";
	private static final String recuperarTipoSalaID= "select idtiposala,descripcion, fila,butaca,capacidad from tiposala where idtiposala like (?)";
	private static final String recuperarTodasSalas= "select idsala,nombre,tiposala from sala";
	
	//ENTRADA
	private static final String insertarEntrada= "insert into entradas values(null,?,?)";
	private static final String borrarEntradaID= "delete from entradas where identradas like(?)";
	private static final String recuperarEntradaID= "select identradas,descripcion,precio from entradas where identradas like (?)";

	//SALAS
	private static final String insertarSala= "insert into sala values(null,?,?)";
	private static final String borrarSalaID= "delete from sala where idsala like(?)";
	private static final String recuperarSalaID= "select idsala,nombre,tiposala from sala where idsala like (?)";
	
	//PELICULAS
	private static final String insertarPelicula= "insert into peliculas values(null,?,?,?,?,?,?,?,?,?,?)";
	private static final String borrarPeliculaID= "delete from peliculas where idpeliculas like(?)";
	private static final String recuperarPeliculaID= "select idpeliculas, titulo,resumen,duracion,categoria,imagen, pais, director, guionista,productora, actores from peliculas where idpeliculas like(?)";
	private static final String recuperarTodasPeliculas= "select idpeliculas,titulo,resumen,duracion,categoria,imagen, pais, director, guionista,productora, actores  from peliculas";

	
	//FUNCIONES
	private static final String insertarFuncion= "insert into funciones values(null,?,?,?,?)";
	private static final String recuperarTodasFunciones= "select idfunciones,pelicula,sala,fecha,hora from funciones"; 
//	private static final String recuperarTodasFuncionesPorPelicula= "select distinct idfunciones,pelicula,sala,fecha,hora from funciones where pelicula=?"; 
	private static final String recuperarTodasFuncionesPorPelicula= "select distinct idfunciones,pelicula,sala,fecha,hora from funciones where pelicula=? and fecha between current_date() and date_add(now(), interval 7 day)"; 
	private static final String recuperarFuncionesPorPeliculaYFecha= "select idfunciones, pelicula, sala, fecha, hora from funciones where pelicula=? and fecha=?";
	private static final String recuperarFuncionPorIdFuncion= "select distinct idfunciones,pelicula,sala,fecha,hora from funciones where idfunciones=?"; 

	
	//RESERVAS
	private static final String insertarReserva= "insert into reservas values (null,?,?,?,?,?)";
	private static final String borrarReservaId= "delete from reservas where idreservas like(?)";
	private static final String recuperarReservasPorIDFuncion= "select idreservas,usuario,funcion,fila,butaca,entrada from reservas where funcion like ?";
	
	//////////////////////////////////////////////////////////
	
	//USUARIOS
	public static String getInsertarUsuario() {
		return insertarUsuario;
	}

	public static String getBorrarUsuario() {
		return borrarUsuario;
	}

	public static String getRecuperarUsuarioID() {
		return recuperarUsuarioID;
	}
	public static String recuperarUsuarioEmailPassword() {
		return recuperarUsuarioEmailPassword;
	}

	
	//CATEGORIAS PELICULAS
	public static String getInsertarCategoria() {
		return insertarCategoria;
	}
	
	public static String getBorrarCategoria() {
		return borrarCategoria;
	}
	
	public static String getRecuperarCategoriaID() {
		return recuperarCategoriaID;
	}
	public static String getRecuperarTodasCategorias() {
		return recuperarTodasCategorias;
	}
	
	//TIPO SALA
	public static String getInsertarTipoSala() {
		return insertarTipoSala;
	}

	public static String getBorrarTipoSalaID() {
		return borrarTipoSalaID;
	}
	public static String getRecuperarTipoSalaID() {
		return recuperarTipoSalaID;
	}
	
	//ENTRADAS
	public static String getInsertarEntrada() {
		return insertarEntrada;
	}

	public static String getBorrarEntradaID() {
		return borrarEntradaID;
	}
	public static String getRecuperarEntradaID(){
		return recuperarEntradaID;
	}

	//SALA
	public static String getInsertarSala() {
		return insertarSala;
	}

	public static String getBorrarSalaID() {
		return borrarSalaID;
	}

	public static String getRecuperarSalaID() {
		// TODO Auto-generated method stub
		return recuperarSalaID;
	}
	public static String getRecuperarTodasSalas() {
		return recuperarTodasSalas;
	}
	
	//PELICULAS
	public static String getInsertarPelicula() {
		// TODO Auto-generated method stub
		return insertarPelicula;
	}

	public static String getBorrarPeliculaID() {
		return borrarPeliculaID;
	}

	public static String getRecuperarPeliculaID() {
		return recuperarPeliculaID;
	}
	public static String getRecuperarTodasPeliculas() {
		return recuperarTodasPeliculas;
	}

	//FUNCIONES
	public static String getInsertarFuncion() {
		return insertarFuncion;
	}
	public static String getRecuperarTodasFunciones() {
		return recuperarTodasFunciones;
	}

	public static String getRecuperarTodasFuncionesPorPelicula() {
		return recuperarTodasFuncionesPorPelicula;
	}

	public static String getRecuperarFuncionPorDiaYPelicula() {
		return recuperarFuncionesPorPeliculaYFecha;
	}

	public static String getRecuperarFuncionIdFuncion() {
		return recuperarFuncionPorIdFuncion;
	}
	//RESERVAS
	public static String getInsertarReserva() {
		return insertarReserva;
	}

	public static String getBorrarReservaID() {
		return borrarReservaId;
	}

	public static String getRecuperarReservasPorIDFuncion() {
		return recuperarReservasPorIDFuncion;
	}

	




	

	


}
