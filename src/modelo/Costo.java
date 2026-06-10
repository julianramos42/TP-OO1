package modelo;

public class Costo {
	private int idCosto;
	private double porSuperficie;
	private double porMontaje;
	private double porElectricidad;
	
	public Costo(double porSuperficie, double porMonataje, double porElectricidad) {
		this.porSuperficie = porSuperficie;
		this.porMontaje = porMonataje;
		this.porElectricidad = porElectricidad;
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
