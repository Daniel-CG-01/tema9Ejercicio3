package com.hibernate.model;

import jakarta.persistence.*;

@Entity
@Table(name="serie")
public class Serie {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idserie")
	int id;
	
	@Column(name="nombre")
	String nombre;
	
	@Column(name="temporadas")
	int temporada;
	
	@Column(name="capitulos")
	int capitulo;
	
	public Serie() {
		super();
	}
	
	public Serie(String nombre, int temporada, int capitulo) {
		super();
		this.nombre = nombre;
		this.temporada = temporada;
		this.capitulo = capitulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public int getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(int capitulo) {
		this.capitulo = capitulo;
	}	
}