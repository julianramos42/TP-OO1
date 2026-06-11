package modelo;

import java.time.LocalDate;

public class ReporteVenta {
	private LocalDate fecha;
	private UnidadDeVenta unidad;
	private double recaudacion;
	
	

	public ReporteVenta(LocalDate fecha, UnidadDeVenta unidad, double recaudacion) {
		this.fecha = fecha;
		this.unidad = unidad;
		this.recaudacion = recaudacion;
	}

	public LocalDate getFechaInicio() {
		return fecha;
	}

	public void setFechaInicio(LocalDate fecha) {
		this.fecha = fecha;
	}

	public UnidadDeVenta getUnidad() {
		return unidad;
	}

	public void setUnidad(UnidadDeVenta unidad) {
		this.unidad = unidad;
	}

	public double getRecaudacion() {
		return recaudacion;
	}

	public void setRecaudacion(double recaudacion) {
		this.recaudacion = recaudacion;
	}

	@Override
	public String toString() {
		return "ReporteVenta [fecha=" + fecha + ", unidad=" + unidad.getNombreComercial() + ", recaudacion=" + recaudacion + "]";
	}

	
	
	
	
	
}
