package ar.edu.um.ingenieria.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "tareas")
public class Tarea implements Serializable{
	private static final long serialVersionUID = -7153249297528005760L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "nombre")
	private String nombre;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "etapas_id")
	private Etapa etapa;
	
	@Column (name = "descripcion")
	private String descripcion;
	/*
	@OneToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name="estados_id")		
	private Estado estado;
	
	@OneToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name="plantas_id")		
	private Planta planta;
	*/
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

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
/*
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((etapa == null) ? 0 : etapa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Tarea other = (Tarea) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (etapa == null) {
			if (other.etapa != null)
				return false;
		} else if (!etapa.equals(other.etapa))
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
		return true;
	}
	
	@Override
	public String toString() {
		return "Tarea [id=" + id + ", nombre=" + nombre + ", etapa=" + etapa + ", descripcion=" + descripcion
				+ ", estado=" /*+ estado + ", planta=" + planta */+ "]";
	}

	public Tarea() {
		super();
	}
}