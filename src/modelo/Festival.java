package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Festival {
	private int idFestival;
	private String nombre;
	private String temporada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Costo costo;
	private List<UnidadDeVenta> lstUnidades;
	
	public Festival(int idFestival, String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin,
			Costo costo) {
		this.idFestival = idFestival;
		this.nombre = nombre;
		this.temporada = temporada;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.costo = costo;
		this.lstUnidades = new ArrayList<UnidadDeVenta>();
	}

	public int getIdFestival() {
		return idFestival;
	}

	public void setIdFestival(int idFestival) {
		this.idFestival = idFestival;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Costo getCosto() {
		return costo;
	}

	public void setCosto(Costo costo) {
		this.costo = costo;
	}

	public List<UnidadDeVenta> getLstUnidades() {
		return lstUnidades;
	}
	
	public boolean agregarUnidadDeVenta(UnidadDeVenta unidad) {
		return this.lstUnidades.add(unidad);
	}
	
	@Override
	public String toString() {
		return "FESTIVAL [ id: " + this.idFestival + ", nombre: " + this.nombre + ", temporada: " + this.temporada
				+ ", fechaInicio: " + this.fechaInicio + ", fechaFin: " + this.fechaFin + ", costo: " + this.costo
				+ ", lstUnidades: " + this.lstUnidades + "]";
	}
}
