package domain;


import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import exceptions.DomainException;

public class Funciones {
	
	private int idFunciones;
	private Peliculas pelicula; //fk de peliculas 
	private Salas sala; //fk de sala
	private Date fecha; 
	private String hora;
//	private Calendar hora;
	

	public Funciones() {}

	public Funciones(int idFunciones) {
		this.idFunciones=idFunciones;
	}

	public Funciones(int idFunciones, Peliculas pelicula, Salas sala, Date fecha, String hora) {
		this.idFunciones = idFunciones;
		this.pelicula = pelicula;
		this.sala = sala;
		this.fecha = fecha;
		this.hora = hora;
	}


	public int getIdFunciones() {
		return idFunciones;
	}


	public void setIdFunciones(int idFunciones) {
		this.idFunciones = idFunciones;
	}


	public Peliculas getPelicula() {
		return pelicula;
	}


	public void setPelicula(Peliculas pelicula) {
		if(pelicula!=null) {
			this.pelicula = pelicula;
		}else{
			throw new DomainException("La pelicula debe existir");
		}
	}


	public Salas getSala() {
		return sala;
	}


	public void setSala(Salas sala) {
		if(sala!=null) {
			this.sala = sala;
		}else{
			throw new DomainException("La sala no puede ser negativa y debe existir");
		}
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}


	
	
	
}
