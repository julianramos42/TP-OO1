package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class UnidadDeVenta {
	private int idUnidadDeVenta;
	private String nombreComercial;
	private String codigoUnico;
	private double superficie;
	private Persona responsable;
	private List<Persona> lstPersonas;
	private List<Plato> lstPlatos;
	
	public UnidadDeVenta(int idUnidadDeVenta, String nombreComercial, String codigoUnico, double superficie,
			Persona responsable) throws Exception {
		super();
		this.setIdUnidadDeVenta(idUnidadDeVenta);
		this.nombreComercial = nombreComercial;
		this.setCodigoUnico(codigoUnico);
		this.superficie = superficie;
		this.responsable = responsable;
		this.lstPersonas = new ArrayList<Persona>();
		this.lstPlatos = new ArrayList<Plato>();
	}

	public int getIdUnidadDeVenta() {
		return idUnidadDeVenta;
	}

	private void setIdUnidadDeVenta(int idUnidadDeVenta) {
		this.idUnidadDeVenta = idUnidadDeVenta;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getCodigoUnico() {
		return codigoUnico;
	}

	public void setCodigoUnico(String codigoUnico) throws Exception{
		if(!this.validarCodigoUnico(codigoUnico)) { //Valida el codigo antes de setearlo y si no es valido levanta una excepcion
			throw new Exception("Codigo invalido.");
		}
		this.codigoUnico = codigoUnico;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public Persona getResponsable() {
		return responsable;
	}

	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}

	public List<Persona> getLstPersonas() {
		return lstPersonas;
	}

	public void setLstPersonas(List<Persona> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}

	public List<Plato> getLstPlatos() {
		return lstPlatos;
	}

	public void setLstPlatos(List<Plato> lstPlatos) {
		this.lstPlatos = lstPlatos;
	}
	
	// LO MISMO QUE ENCONTRAR PERSONA PERO PARA ESTA LISTA
	public Persona encontrarEmpleado(long dni) {
	    Persona empleado = null;
	    int i = 0;
	    
	    while (i < this.lstPersonas.size() && empleado == null) {
	        if (this.lstPersonas.get(i).getDni() == dni) {
	            empleado = this.lstPersonas.get(i);
	        }
	        i++;
	    }
	    
	    return empleado;
	}
	
	//LE LLEGA UNA PERSONA YA CREADA Y LA AGREGA A LA LISTA COMO EMPLEADO
	public boolean agregarEmpleado(Persona empleado) throws Exception {
	    if (this.encontrarEmpleado(empleado.getDni()) != null) {
	        throw new Exception("El empleado ya está asignado.");
	    }
	   
	    return this.lstPersonas.add(empleado);
	}

	//encontrarPlato
	public Plato encontrarPlato(String nombre) {
		Plato plato = null;
		int i = 0;
		
		while(i < this.lstPlatos.size() && plato == null) {
			if(this.lstPlatos.get(i).getNombre().equalsIgnoreCase(nombre)) {
				plato = this.lstPlatos.get(i);
			}
			i++;
		}
	
	@Override
	public String toString() {
		return "id: "+this.idUnidadDeVenta+", nombreComercial: "+this.nombreComercial+", codigoUnico: "+this.codigoUnico+", superficie: "+this.superficie+", responsable: "+ this.responsable+", lstPersonas: "+this.lstPersonas+", lstPlatos: "+this.lstPlatos;
	}
	
	public boolean equals(UnidadDeVenta unidad) {
		return this.codigoUnico.equals(unidad.codigoUnico);
	}
	
	// VALIDACION DEL ENUNCIADO (NO ESPECIFICA QUE VALIDAR)
	protected boolean validarCodigoUnico(String codigo) {
	    return codigo.length() == 10; 
	}
	
	// CALCULARCANON CLASE ABSTRACTA, SE HACE OVERRIDE EN LAS HIJAS
	public abstract double calcularCanon(double costoSuperficie, double costoAdicional);
	
}
