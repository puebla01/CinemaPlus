package domain;

import exceptions.DomainException;
import util.Validator;

public class CategoriasPeliculas {
	
	private int idCategorias;
	private String descripcion; //varchar20
	
	public CategoriasPeliculas() {}
	
	public CategoriasPeliculas ( int idCategorias) {
		this.idCategorias=idCategorias;
	}

	public CategoriasPeliculas(int idCategorias, String descripcion) {
		this.idCategorias = idCategorias;
		this.descripcion = descripcion;
	}

	public int getidCategorias() {
		return idCategorias;
	}

	public void setidCategorias(int idCategorias) {
			this.idCategorias = idCategorias;
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if(Validator.length(descripcion, 1, 20)) {
			this.descripcion = descripcion;
			
		}else {
			throw new DomainException("La longitud de la descripcion no es valida");
		}
	}	
	
}
