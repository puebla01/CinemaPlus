package domain;

import exceptions.DomainException;
import util.Validator;

public class Usuario {
	
	private int idUsuario;
	private String nombre; //varchar 20
	private String primerApellido; //varchar 20
	private String segundoApellido; //varchar 20
	private String email; //varchar 45
	private String password; //varchar 45
	private String telefono; //varchar 45
	private int tipo; //int 0 usuario normal y 1 admin

	public Usuario() {}

	public Usuario(int idUsuario) {
		this.idUsuario=idUsuario;
	}
	public Usuario(int idUsuario, String nombre, String primerApellido, String segundoApellido, String email,
			String password, String telefono, int tipo) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.tipo = tipo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(Validator.length(nombre,1,20)) {
			this.nombre = nombre;
		}else {
			throw new DomainException("La longitud del nombre no es valida");
		}
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		if(Validator.length(primerApellido,1,20)) {
			this.primerApellido = primerApellido;
		}else {
			throw new DomainException("La longitud del primer apellido no es valida");
		}
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		if(Validator.length(segundoApellido,1,20)) {
			this.segundoApellido = segundoApellido;
		}else {
			throw new DomainException("La longitud del segundo  apellido no es valida");
		}
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		//this.email = email;
		if(Validator.email(email, 1, 45)){
			this.email = email;
		}else {
			throw new DomainException("El email no es valida");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(Validator.length(password,1,45)) {
			this.password = password;
		}else {
			throw new DomainException("La longitud de la contraseña no es valida");
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
		if(Validator.telephone(telefono, 1, 45)) {
			this.telefono = telefono;
		}else {
			throw new DomainException("El telefono no es valido");
		}
	}

	public int getTipo() {
		return tipo;
	}
	
//El tipo será 0 si es usuario normal y 1 si es administrador
	public void setTipo(int tipo) {
		if(tipo==1 || tipo==0) {
			this.tipo = tipo;			
		}else {
			throw new DomainException("Tipo no es valido ya que debe ser 0 o 1");
		}
	}

	
}
