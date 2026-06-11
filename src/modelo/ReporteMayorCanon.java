package modelo;

public class ReporteMayorCanon {

	private UnidadDeVenta unidad;
	private double canon;
	
	public ReporteMayorCanon(UnidadDeVenta unidad, double canon) {
		this.unidad = unidad;
		this.canon = canon;
	}
	
	

	public UnidadDeVenta getUnidad() {
		return unidad;
	}



	public void setUnidad(UnidadDeVenta unidad) {
		this.unidad = unidad;
	}



	public double getCanon() {
		return canon;
	}



	public void setCanon(double canon) {
		this.canon = canon;
	}



	@Override
	public String toString() {
		return "ReporteMayoresCanon [unidad=" + unidad.getNombreComercial() + ", canon=" + canon + "]";
	}	

}
