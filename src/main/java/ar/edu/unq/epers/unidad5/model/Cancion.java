package ar.edu.unq.epers.unidad5.model;

import java.io.Serializable;
import java.util.Objects;

public class Cancion implements Serializable {
	private static final long serialVersionUID = 1L;

	private String titulo;
	private String interprete;
	private String album;
	
	public Cancion(String titulo, String interprete, String album) {
		this.titulo = titulo;
		this.interprete = interprete;
		this.album = album;
	}

	public String getTitulo() {
		return this.titulo;
	}
	
	public String getInterprete() {
		return this.interprete;
	}
	
	public String getAlbum() {
		return this.album;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.album == null) ? 0 : this.album.hashCode());
		result = prime * result + ((this.interprete == null) ? 0 : this.interprete.hashCode());
		result = prime * result + ((this.titulo == null) ? 0 : this.titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Cancion) {
			Cancion other = (Cancion) obj;
			return Objects.equals(this.titulo, other.titulo) &&
					Objects.equals(this.album, other.album) &&
					Objects.equals(this.interprete, other.interprete);
		}
		return false;
	}
	

}
