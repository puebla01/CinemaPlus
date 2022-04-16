package domain;

import exceptions.DomainException;
import util.Validator;

public class Salas {

	private int idSala;
	private String nombre; //varchar 20
	private TipoSala tipoSala; //id del tipo de sala para saber cual es y sus caracteristicas
	
	public Salas() {}
	
	public Salas (int idSala) {
		this.idSala= idSala;
	}

	public Salas(int idSala, String nombre, TipoSala tipoSala) {
		this.idSala = idSala;
		this.nombre = nombre;
		this.tipoSala = tipoSala;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(Validator.length(nombre, 1, 20)) {
			this.nombre = nombre;
		}else {
			throw new DomainException("La longitud del nombre de la sala no es valida");
		}
	}

	

	public TipoSala getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(TipoSala tipoSala) {
		if(tipoSala!=null) {
			this.tipoSala = tipoSala;
		}else {
			throw new DomainException("El tipo de sala debe existir");
		}
	}
	
	

	
}
