package programas.programasUsuario;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.tomcat.util.security.MD5Encoder;

import bbdd.ConexionMySql;
import daos.UsuarioDAO;
import domain.Entradas;
import domain.Funciones;
import domain.Usuario;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;
import recursos.DbQuery;
import recursos.Recursos;
import servicios.ServicioUsuario;
import util.Teclado;
import util.Validator;

public class ProbarInsertarUsuario {

	public static void main(String[] args) throws  DAOException {
		// TODO Auto-generated method stub

		Usuario usuario= null;
		ServicioUsuario scUsuario=new ServicioUsuario();
	 
		Connection con;
		String salida=null;
		try {
			con= new ConexionMySql().getConexion();
			
			usuario= new Usuario();
//			usuario.setNombre("alvaroo");
//			usuario.setPrimerApellido("puebla");
//			usuario.setEmail("jandsdfsdokk@s.com");
//			usuario.setPassword("hola");
//			usuario.setTelefono("666666666");
//			usuario.setTipo(1);
			
			Teclado t= new Teclado();
			
			System.out.println("Introduce un Nombre");
			usuario.setNombre(t.leerCadena());
			
			System.out.println("Introduce un Apellido");
			usuario.setPrimerApellido(t.leerCadena());
			
			System.out.println("Introduce un  segundo apellido");
			usuario.setSegundoApellido(t.leerCadena());

			System.out.println("Introduce un Email");
			usuario.setEmail(t.leerCadena());
			
			System.out.println("Introduce una contaseña");
			usuario.setPassword(t.leerCadena());
			
			System.out.println("Introduce un telefono");
			usuario.setTelefono(t.leerCadena());
			
			System.out.println("Introduce tipo de usuario: administrador 1 y usuario normal 0");
			usuario.setTipo(t.leerEntero());
			
			scUsuario.insertarUsuario(usuario);
			salida="Usuario registrado correctamente";
		} catch (DAOException | ServiceException | DomainException e) {
			if(e.getCause()==null){
				salida=e.getMessage();//Error Lógico para usuario
			}else{
				e.printStackTrace();// para administrador
				salida="error interno";//Error interno para usuario
			}
		}
		System.out.println(salida);//dispacher salida para mostrar el error		
	}
}
