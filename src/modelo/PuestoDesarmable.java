package modelo;

public class PuestoDesarmable extends UnidadDeVenta {
	private int cantCarpas;
	private int tiempoDeArmado;
	
	public PuestoDesarmable(int idUnidadDeVenta, String nombreComercial, String codigoUnico, double superficie,
			Persona responsable, int cantCarpas, int tiempoDeArmado) throws Exception{
		
		super(idUnidadDeVenta, nombreComercial, codigoUnico, superficie, responsable);
		
		this.cantCarpas = cantCarpas;
		this.tiempoDeArmado = tiempoDeArmado;
	}

	public int getCantCarpas() {
		return cantCarpas;
	}

	public void setCantCarpas(int cantCarpas) {
		this.cantCarpas = cantCarpas;
	}

	public int getTiempoDeArmado() {
		return tiempoDeArmado;
	}

	public void setTiempoDeArmado(int tiempoDeArmado) {
		this.tiempoDeArmado = tiempoDeArmado;
	}
	
	@Override
	public String toString() {
		return "\nPUESTO DESARMABLE: "+super.toString()+", cantCarpas: "+this.cantCarpas+", tiempoDeArmado: "+this.tiempoDeArmado;
	}
	
	@Override
	public double calcularCanon(double costoSuperficie, double costoAdicional) {
		double canon = this.getSuperficie() * costoSuperficie;
		
		canon -= this.tiempoDeArmado * costoAdicional;
		
		return canon;
	}
}
