package modelo;

public class Costo {
	private int idCosto;
	private double porSuperficie;
	private double porMontaje;
	private double porElectricidad;
	
	public Costo(int idCosto, double porSuperficie, double porMonataje, double porElectricidad) {
		this.idCosto = idCosto;
		this.porSuperficie = porSuperficie;
		this.porMontaje = porMonataje;
		this.porElectricidad = porElectricidad;
	}

	public int getIdCosto() {
		return idCosto;
	}

	public void setIdCosto(int idCosto) {
		this.idCosto = idCosto;
	}

	public double getPorSuperficie() {
		return porSuperficie;
	}

	public void setPorSuperficie(double porSuperficie) {
		this.porSuperficie = porSuperficie;
	}

	public double getPorMontaje() {
		return porMontaje;
	}

	public void setPorMontaje(double porMonataje) {
		this.porMontaje = porMonataje;
	}

	public double getPorElectricidad() {
		return porElectricidad;
	}

	public void setPorElectricidad(double porElectricidad) {
		this.porElectricidad = porElectricidad;
	}
	
	@Override
	public String toString() {
		return "COSTO [ id: " + this.idCosto + ", porSuperficie: " + this.porSuperficie + ", porMontaje: " + this.porMontaje
				+ ", porElectricidad: " + this.porElectricidad + "]";
	}
	
}
