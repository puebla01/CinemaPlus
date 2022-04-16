package domain;

import org.apache.commons.io.FilenameUtils;

import exceptions.DomainException;
import util.Validator;

public class Peliculas {

	private int idPelicula;
	private String titulo; //varchar45
	private String resumen;//varchar1000;
	private int duracion;
	private CategoriasPeliculas categoria; //fk de categoriasPeliculas
	private String imagen;
	private String pais;
	private String director;
	private String guionista;
	private String productora;
	private String actores;
	


	public Peliculas() {}
	
	public Peliculas(int idPelicula) {
		this.idPelicula=idPelicula;
	}

	public Peliculas(int idPelicula, String titulo, String resumen, int duracion, CategoriasPeliculas categoria,String imagen,String pais, String director, String guionista, String productora, String actores) {
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.resumen = resumen;
		this.duracion = duracion;
		this.categoria = categoria;
		this.imagen= imagen;
		this.pais = pais;
		this.director = director;
		this.guionista = guionista;
		this.productora = productora;
		this.actores = actores;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if(Validator.length(titulo, 1, 45)) {
			this.titulo = titulo;
		}else {
			throw new DomainException("La longitud del titulo no es valida");
		}
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		if(Validator.length(resumen, 1, 1000)) {
			this.resumen = resumen;
		}else {
			throw new DomainException("La longitud del resumen no es valida");
		}
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		if(duracion>0) {
			this.duracion = duracion;
		}else {
			throw new DomainException("La duracion tiene que ser mayor que cero");
		}
	}

	public CategoriasPeliculas getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriasPeliculas categoria) {
		if(categoria!=null) {
			this.categoria = categoria;		
		}else {
			throw new DomainException("Se debe insertar una categoria");
		}
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		//compruebo si la ruta de la imagen viene con archivo ya que assets/images es por definicion y si solo viene eso es que no se ha subido
        if (!imagen.equals("assets/Images/Peliculas")) {
            String extension = FilenameUtils.getExtension(imagen);
            if (extension.equals("jpeg") || extension.equals("png") || extension.equals("gif")
                    || extension.equals("tiff") || extension.equals("psd")) {
                this.imagen = imagen.trim();

            } else {
                throw new DomainException("Tiene que ser un formato correcto.");

            }
        } else {
            throw new DomainException("Es obligatorio insertar una imagen.");
        }
    }
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		if(Validator.length(pais, 1, 50)) {
			this.pais = pais;
		}else {
			throw new DomainException("La longitud del Pais no es valida");
		}
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		if(Validator.length(director, 1, 100)) {
			this.director = director;
		}else {
			throw new DomainException("La longitud del director no es valida");
		}
		
	}

	public String getGuionista() {
		return guionista;
	}

	public void setGuionista(String guionista) {
		if(Validator.length(guionista, 1, 100)) {
			this.guionista = guionista;
		}else {
			throw new DomainException("La longitud del guionista no es valida");
		}
	}

	public String getProductora() {
		return productora;
	}

	public void setProductora(String productora) {
		if(Validator.length(productora, 1, 100)) {
			this.productora = productora;
		}else {
			throw new DomainException("La longitud del productora no es valida");
		}
	}

	public String getActores() {
		return actores;
	}

	public void setActores(String actores) {
		if(Validator.length(actores, 1, 500)) {
			this.actores = actores;
		}else {
			throw new DomainException("La longitud de actores no es valida");
		}
	}

	public String toStringFashion() {
		return "Pelicula [idPelicula=" + idPelicula+ ", titulo=" + titulo
				+ ", resumen=" + resumen + ", duracion=" + duracion + ", categoria id="
				+ categoria.getidCategorias() + ", categoria desc=" + categoria.getDescripcion()+"]";
	}
	
}
