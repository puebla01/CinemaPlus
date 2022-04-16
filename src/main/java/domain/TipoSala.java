package domain;

import exceptions.DomainException;
import util.Validator;

public class TipoSala {

	private int idTipoSala;
	private String descripcion; //varchar 20
	private int fila;
	private int butaca;
	private int capacidad;
	
	public TipoSala() {	}
	public TipoSala ( int idTipoSala) {
		this.idTipoSala=idTipoSala;
	}
	
	public TipoSala(int idTipoSala, String descripcion, int fila, int butaca,int capacidad) {
		this.idTipoSala = idTipoSala;
		this.descripcion = descripcion;
		this.fila = fila;
		this.butaca = butaca;
		this.capacidad= capacidad;
	}


	public int getIdTipoSala() {
		return idTipoSala;
	}

	public void setIdTipoSala(int idTipoSala) {
		this.idTipoSala = idTipoSala;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if(Validator.length(descripcion, 1, 20)) {
			this.descripcion = descripcion;			
		}else {
			throw new DomainException("La longitude de la descripcion tipo de sala no es valida");
		}
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		if(fila>0) {
			this.fila = fila;
		}else {
			throw new DomainException("La fila no puede ser un valor menor que 0");
		}
	}

	public int getButaca() {
		return butaca;
	}

	public void setButaca(int butaca) {
		if(butaca>0) {
			this.butaca = butaca;
		}else {
			throw new DomainException("La fila no puede ser un valor menor que 0");
		}
	}
	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		if(capacidad>0) {
			this.capacidad = capacidad;
		}else {
			throw new DomainException("La capacidad no puede ser un valor menor que 0");
		}
	}


	
}
