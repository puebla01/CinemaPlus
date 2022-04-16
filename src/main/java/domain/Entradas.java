package domain;

import exceptions.DomainException;
import util.Validator;

public class Entradas {

	private int idEntrada;
	private String descripcion; //varchhar 20
	private double precio;
	
	public Entradas() {	}

	public Entradas(int idEntrada) {
		this.idEntrada=idEntrada;
	}
	public Entradas(int idEntrada, String descripcion, double precio) {
		this.idEntrada = idEntrada;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		if(Validator.lengthDecimal(precio,4,2)) {
			//lengthDecimal precio, 4,2 quiere decir que el precio no tiene que ser mayor a 10 elevado a la precision- la escala -> "4-2 =2" 10^2=100
			// y la parte decimal no puede ser mayor la longitud que la escala
			this.precio = precio;			
		}else {
			System.out.println("mal");
			throw new DomainException("El precio introducido no es valido");
		}
	}
	
	
}
