package domain;

import exceptions.DomainException;

public class Reservas {
	
	private int idReservas;
	private Usuario usuario; //fk de usuario
	private Funciones funcion; //fk de funciones
	private int fila; 
	private int butaca;
	private Entradas entrada; //fk de entrada
	
	public Reservas() {	}

	public Reservas(int idReservas, Usuario usuario, Funciones funcion, int fila, int butaca, Entradas entrada) {
		this.idReservas = idReservas;
		this.usuario = usuario;
		this.funcion = funcion;
		this.fila = fila;
		this.butaca = butaca;
		this.entrada = entrada;
	}

	public int getIdReservas() {
		return idReservas;
	}

	public void setIdReservas(int idReservas) {
		this.idReservas = idReservas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		if(usuario!=null) {
					this.usuario = usuario;
		}else{
			throw new DomainException("El usuario no puede ser negativo y debe existir");
		}
	}

	public Funciones getFuncion() {
		return funcion;
	}

	public void setFuncion(Funciones funcion) {
		if(funcion!=null) {
			this.funcion = funcion;
		}else{
			throw new DomainException("La funcion no puede ser negativa y debe existir");
		}
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		if(fila>0) {
			this.fila = fila;
		}else{
			throw new DomainException("La fila no puede ser negativa y debe existir");
		}
	}

	public int getButaca() {
		return butaca;
		
	}

	public void setButaca(int butaca) {
		if(butaca>0) {
			this.butaca = butaca;
		}else{
			throw new DomainException("La butaca no puede ser negativa y debe existir");
		}
	}

	public Entradas getEntrada() {
		return entrada;
	}

	public void setEntrada(Entradas entrada) {
		if(entrada!=null) {
			this.entrada = entrada;
		}else{
			throw new DomainException("La entrada no puede ser negativa y debe existir");
		}
	}
	
	
	
}
