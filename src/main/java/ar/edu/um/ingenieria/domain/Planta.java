package ar.edu.um.ingenieria.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "plantas")
public class Planta implements Serializable {

	private static final long serialVersionUID = 7302378891606112641L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_planta_id")
	private TipoPlanta tipo;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "temporadas_id")
	private Temporada temporada;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "suelos_id")
	private Suelo suelo;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "climas_id")
	private Clima clima;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="HH:mm:ss")
	@Column(name = "tiempo_riego")
	private Date tiempo_riego;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoPlanta getTipo() {
		return tipo;
	}

	public void setTipo(TipoPlanta tipo) {
		this.tipo = tipo;
	}

	public Temporada getTemporada() {
		return temporada;
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}

	public Suelo getSuelo() {
		return suelo;
	}

	public void setSuelo(Suelo suelo) {
		this.suelo = suelo;
	}

	public Clima getClima() {
		return clima;
	}

	public void setClima(Clima clima) {
		this.clima = clima;
	}

	public Date getTiempoRiego() {
		return tiempo_riego;
	}

	public void setTiempoRiego(Date tiempoRiego) {
		this.tiempo_riego = tiempoRiego;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tiempo_riego == null) ? 0 : tiempo_riego.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tiempo_riego == null) {
			if (other.tiempo_riego != null)
				return false;
		} else if (!tiempo_riego.equals(other.tiempo_riego))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Planta [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tiempoRiego="
				+ tiempo_riego + "]";
	}

	public Planta() {
		super();
	}
}
