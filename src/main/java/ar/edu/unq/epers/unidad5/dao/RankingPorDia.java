package ar.edu.unq.epers.unidad5.dao;

import java.io.Serializable;
import java.time.LocalDate;

public class RankingPorDia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fuente;
	private LocalDate fecha;

	public RankingPorDia(String fuente, LocalDate fecha) {
		this.fuente = fuente;
		this.fecha = fecha;
	}
	
}
