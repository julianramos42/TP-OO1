package modelo;

public class FoodTruck extends UnidadDeVenta{
	private String patente;
	private boolean conexionElectrica;
	
	public FoodTruck(int idUnidadDeVenta, String nombreComercial, String codigoUnico, double superficie,
			Persona responsable, String patente, boolean conexionElectrica) throws Exception {
		
		super(idUnidadDeVenta, nombreComercial, codigoUnico, superficie, responsable);
		
		this.patente = patente;
		this.conexionElectrica = conexionElectrica;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public boolean isConexionElectrica() {
		return conexionElectrica;
	}

	public void setConexionElectrica(boolean conexionElectrica) {
		this.conexionElectrica = conexionElectrica;
	}
	
	@Override
	public String toString() {
		return "\nFOODTRUCK: "+super.toString()+", patente: "+this.patente+", conexionElectrica: "+this.conexionElectrica;
	}
	
	@Override
	public double calcularCanon(double costoSuperficie, double costoAdicional) {
		double canon = this.getSuperficie() * costoSuperficie;
		
		if(this.conexionElectrica) {
			canon += costoAdicional;
		}
		
		return canon;
	}
}
