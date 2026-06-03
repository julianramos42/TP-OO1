package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
	private List<Festival> lstFestivales;
	private List<UnidadDeVenta> lstUnidades;
	private List<Persona> lstPersonal;
	private List<Pedido> lstPedidos;
	
	public Sistema() {
		super();
		this.lstFestivales = new ArrayList<Festival>();
		this.lstUnidades = new ArrayList<UnidadDeVenta>();
		this.lstPersonal = new ArrayList<Persona>();
		this.lstPedidos = new ArrayList<Pedido>();
	}
	
	//encontrarUnidad
	public UnidadDeVenta encontrarUnidad(String codigoUnico) {
		UnidadDeVenta unidad = null;
		int i = 0;
		
		while(i < this.lstUnidades.size() && unidad == null) {
			if(this.lstUnidades.get(i).getCodigoUnico().equals(codigoUnico)) {
				unidad = this.lstUnidades.get(i);
			}
			i++;
		}
		
		return unidad;
	}
	
	//encontrarFestival
	public Festival encontrarFestival(String nombre) {
		Festival festival = null;
		int i = 0;
		
		while (festival == null && i < this.lstFestivales.size()) {
			if (this.lstFestivales.get(i).getNombre().equals(nombre)) {
				festival = this.lstFestivales.get(i);
			}
		}

		return festival;
	}
	
	//agregarFestival
	public boolean agregarFestival(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin, Costo costo) throws Exception {
		if(this.encontrarFestival(nombre) != null) {
			throw new Exception("El festival ya existe.");
		}
		
		int id;
		
		if(this.lstFestivales.isEmpty()) {
			id = 1;
		} else {
			id = this.lstFestivales.get(this.lstFestivales.size() - 1).getIdFestival() + 1;
		}
		
		return this.lstFestivales.add(new Festival(id, nombre, temporada, fechaInicio, fechaFin, costo));
	}
	
	//agregarFoodTruck
	public boolean agregarFoodTruck(String nombreComercial, String codigoUnico, double superficie, Persona responsable, String patente, boolean conexionElectrica) throws Exception {
		if(this.encontrarUnidad(codigoUnico) != null) {
			throw new Exception("Ese codigo ya existe.");
		}
		
		int id = 1;
		if(!this.lstUnidades.isEmpty()) {
			id = this.lstUnidades.get(this.lstUnidades.size()-1).getIdUnidadDeVenta()+1;
		}
		
		return this.lstUnidades.add(new FoodTruck(id, nombreComercial, codigoUnico, superficie, responsable, patente, conexionElectrica));
	}
	
	//agregarPuestoDesarmable
	public boolean agregarPuestoDesarmable(String nombreComercial, String codigoUnico, double superficie, Persona responsable, int cantCarpas, int tiempoDeArmado) throws Exception {
		if(this.encontrarUnidad(codigoUnico) != null) {
			throw new Exception("Ese codigo ya existe.");
		}
		
		int id = 1;
		if(!this.lstUnidades.isEmpty()) {
			id = this.lstUnidades.get(this.lstUnidades.size()-1).getIdUnidadDeVenta()+1;
		}
		
		return this.lstUnidades.add(new PuestoDesarmable(id, nombreComercial, codigoUnico, superficie, responsable, cantCarpas, tiempoDeArmado));
	}
	
	// CALCULA LA RECAUDACION HISTORICA DE UNA UNIDAD DE VENTA
	public double calcularRecaudacion(UnidadDeVenta unidad) {
	    double recaudacionTotal = 0;
	    
	    for (Pedido pedido : this.lstPedidos) {
	        if (pedido.getUnidad().equals(unidad)) {
	            recaudacionTotal += pedido.calcularGanancia(); //NOS DEVUELVE LA GANANCIA DE CADA PEDIDO YA CALCULADA 
	        }
	    }
	    
	    return recaudacionTotal;
	}
	
	// CALCULA LA RENTABLIDIDAD NETA HISTORICA DE UNA UNIDAD, costoSuperficie y costoAdicional SON VARIABLES GENERICAS PARA NO TENER DATOS HARDCODEADOS (LOS $500 | $2000 | $10)
	public double calcularRentabilidadNeta(UnidadDeVenta unidad, double costoSuperficie, double costoAdicional) {
	    double rentabilidad = this.calcularRecaudacion(unidad); // NOS DA LA GANANCIA BRUTA
	    
	    double totalSueldos = 0;
	    for (Persona empleado : unidad.getLstPersonas()) {
	        totalSueldos += empleado.calcularSueldo(); 
	    }
	    rentabilidad -= totalSueldos;
	    
	    double canon = unidad.calcularCanon(costoSuperficie, costoAdicional);
	    rentabilidad -= canon;
	    
	    return rentabilidad;
	}
	
	// CALCULA LA RECAUDACION ENTRE 2 FECHAS DE UNA UNIDAD DE VENTA
	public double calcularRecaudacion(UnidadDeVenta unidad, LocalDate fechaDesde, LocalDate fechaHasta) {
	    double recaudacionTotal = 0;
	    
	    for (Pedido pedido : this.lstPedidos) {
	        if (pedido.getUnidad().equals(unidad) && !pedido.getFecha().isBefore(fechaDesde) && !pedido.getFecha().isAfter(fechaHasta)) { // SI ES IGUAL A LA UNIDAD, SI LA FECHA NO ESTA ANTES NI DESPUES (ENTONCES ESTA DENTRO O EN LOS EXTREMOS)
	            recaudacionTotal += pedido.calcularGanancia(); 
	        }
	    }
	    
	    return recaudacionTotal;
	}
	
	// CALCULA LA RENTABLIDIDAD NETA ENTRE 2 FECHAS DE UNA UNIDAD, costoSuperficie y costoAdicional SON VARIABLES GENERICAS PARA NO TENER DATOS HARDCODEADOS (LOS $500 | $2000 | $10)
	public double calcularRentabilidadEntreFechas(UnidadDeVenta unidad, LocalDate fechaDesde, LocalDate fechaHasta, double costoSuperficie, double costoAdicional) {
	    double rentabilidad = this.calcularRecaudacion(unidad, fechaDesde, fechaHasta); // NOS DA LA GANANCIA BRUTA
	    
	    double totalSueldos = 0;
	    for (Persona empleado : unidad.getLstPersonas()) {
	        totalSueldos += empleado.calcularSueldo(); 
	    }
	    rentabilidad -= totalSueldos;
	    
	    double canon = unidad.calcularCanon(costoSuperficie, costoAdicional);
	    rentabilidad -= canon;
	    
	    return rentabilidad;
	}
}
