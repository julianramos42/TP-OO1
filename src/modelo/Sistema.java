package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
	private List<Festival> lstFestivales;
	private List<UnidadDeVenta> lstUnidades;
	private List<Persona> lstPersonal;
	
	public Sistema() {
		super();
		this.lstFestivales = new ArrayList<Festival>();
		this.lstUnidades = new ArrayList<UnidadDeVenta>();
		this.lstPersonal = new ArrayList<Persona>();
	}
	
	
public List<Festival> getLstFestivales() {
		return lstFestivales;
	}


	public void setLstFestivales(List<Festival> lstFestivales) {
		this.lstFestivales = lstFestivales;
	}


	public List<UnidadDeVenta> getLstUnidades() {
		return lstUnidades;
	}


	public void setLstUnidades(List<UnidadDeVenta> lstUnidades) {
		this.lstUnidades = lstUnidades;
	}


	public List<Persona> getLstPersonal() {
		return lstPersonal;
	}


	public void setLstPersonal(List<Persona> lstPersonal) {
		this.lstPersonal = lstPersonal;
	}


	//CASO DE USO 1: BUSQUEDA POR ATRIBUTO IDENTIFICADOR ----------------------------------
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
			i++;
		}

		return festival;
	}
	
	//encontrarPersona
	public Persona encontrarPersona(long dni) {
		
		Persona persona = null;
		int i = 0;
		
		while(persona == null && i < this.lstPersonal.size()){
			
			if(this.lstPersonal.get(i).getDni() == dni) {
				persona = this.lstPersonal.get(i);
			}
			i++;
		}
		return persona;
	}
	
	
//CASO DE USO 2: AGREGAR ELEMENTOS A UNA LISTA ----------------------------------
	
	//agregarFestival
	public boolean agregarFestival(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin, double porSuperficie, double porMonataje, double porElectricidad) throws Exception {
		if(this.encontrarFestival(nombre) != null) {
			throw new Exception("El festival ya existe.");
		}
		
		int id;
		
		if(this.lstFestivales.isEmpty()) {
			id = 1;
		} else {
			id = this.lstFestivales.get(this.lstFestivales.size() - 1).getIdFestival() + 1;
		}
		
		return this.lstFestivales.add(new Festival(id, nombre, temporada, fechaInicio, fechaFin, porSuperficie, porMonataje, porElectricidad));
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
	
	//agregarCajero
	public boolean agregarCajero(String nombre, String apellido, long dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String turno, double bonoPorAntiguedad) throws Exception {
		    
		    if(this.encontrarPersona(dni) != null) {
		        throw new Exception("Ya existe una persona con ese DNI.");
		    }
		    
		    if(Period.between(fechaNacimiento, LocalDate.now()).getYears() < 18) {
		        throw new Exception("ERROR: El empleado debe ser mayor de edad.");
		    }
		    
		    int id = 1;
		    if(!this.lstPersonal.isEmpty()) {
		        id = this.lstPersonal.get(this.lstPersonal.size()-1).getIdPersona() + 1;
		    }
		    
		    
		    return this.lstPersonal.add(new Cajero(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase, turno, bonoPorAntiguedad));
		}
	
	//agregarCocinero
	public boolean agregarCocinero(String nombre, String apellido, long dni, LocalDate fechaNacimiento, LocalDate fechaIngreso, double sueldoBase, String especialidad, String categoria, double plusCategoria) throws Exception {
		if(this.encontrarPersona(dni) != null) {
			throw new Exception("Ya existe una persona con ese DNI.");
		}
		
		if(Period.between(fechaNacimiento, LocalDate.now()).getYears() < 18) {
			throw new Exception("ERROR: El empleado debe ser mayor de edad.");
		}
		
		int id = 1;
		if(!this.lstPersonal.isEmpty()) {
			id = this.lstPersonal.get(this.lstPersonal.size()-1).getIdPersona() + 1;
		}
		
		return this.lstPersonal.add(new Cocinero(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase, especialidad, categoria, plusCategoria));
	}
	
	
		
		
//CALCULOS ----------------------------------
	// CALCULA LA RECAUDACION HISTORICA DE UNA UNIDAD DE VENTA
	public double calcularRecaudacion(UnidadDeVenta unidad) {
	    double recaudacionTotal = 0;
	    
	    for (Pedido pedido : unidad.getLstPedidos()) {
	            recaudacionTotal += pedido.calcularGanancia(); //NOS DEVUELVE LA GANANCIA DE CADA PEDIDO YA CALCULADA 
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
	    
	    for (Pedido pedido : unidad.getLstPedidos()) {
	        if (!pedido.getFecha().isBefore(fechaDesde) && !pedido.getFecha().isAfter(fechaHasta)) { // SI ES IGUAL A LA UNIDAD, SI LA FECHA NO ESTA ANTES NI DESPUES (ENTONCES ESTA DENTRO O EN LOS EXTREMOS)
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

//LISTAS FILTRADAS ----------------------------------
	public List<Persona> filtroPorEdad(LocalDate inicio, LocalDate fin) {
		List<Persona> personalFiltrado = new ArrayList<Persona>();
		
		for (Persona p : this.lstPersonal) {
			
			
			if ((p.getFechaNacimiento().isEqual(inicio) || p.getFechaNacimiento().isAfter(inicio)) && 
				(p.getFechaNacimiento().isEqual(fin) || p.getFechaNacimiento().isBefore(fin))) {
				
				personalFiltrado.add(p);
			}
		}
		
		return personalFiltrado;
	}
	
//Ranking de unidades
	public List<UnidadDeVenta> rankingDeUnidades(Festival festival) throws Exception{
		if(encontrarFestival(festival.getNombre()) == null) {
			throw new Exception("El festival no existe");
		}
		//ranking de unidades
		List<UnidadDeVenta> ranking = new ArrayList<UnidadDeVenta>();
		
		//unidades y recaudacion del festival
		List<ReporteVenta> reportes = reporteRecaudacion(festival);
		ordenarPorRecaudacion(reportes);
		
		//agrega las primeras tres unidades con mayor recaudacion
		for(int i = 0; i < 3; i++) {
			ranking.add(reportes.get(i).getUnidad());
		}
		return ranking;
		
	}
	public List<Pedido> traerPedidosUnidad(UnidadDeVenta unidad){
		List<Pedido> lstPedidosPorUnidad = new ArrayList<Pedido>();
		if(encontrarUnidad(unidad.getCodigoUnico()) != null) {
			for(Pedido p : unidad.getLstPedidos()) {
					lstPedidosPorUnidad.add(p);
		}
		}
		return lstPedidosPorUnidad;
	}

//ORDENAMIENTO ----------------------------------
	
	//Ordenamiento de lista por recaudacion
	public void ordenarPorRecaudacion(List<ReporteVenta> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).getRecaudacion() < lista.get(j + 1).getRecaudacion()) {
                    ReporteVenta temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                 
                }
            }
            
        }
    }
	
	//Ordenamiento de lista por canon
	public void ordenarPorCanon(List<ReporteMayorCanon> lista) {
		int n = lista.size();
		for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).getCanon() < lista.get(j + 1).getCanon()) {
                    ReporteMayorCanon temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                 
                }
            }
            
        }
	}
	
//REPORTES ----------------------------------
	
	//Recaudacion total de un festival
			public List<ReporteVenta> reporteRecaudacion(Festival festival) throws Exception{
				if(encontrarFestival(festival.getNombre()) == null) {
					throw new Exception("El festival no existe");
				}
				List<ReporteVenta> lstReportes = new ArrayList<ReporteVenta>();
				for(UnidadDeVenta u : festival.getLstUnidades()) {
					lstReportes.add(new ReporteVenta(LocalDate.now(), u, calcularRecaudacion(u)));
				}
				
				return lstReportes;
			}
	//Recaudacion entre fechas
		public List<ReporteVenta> reporteRecaudacionEntreFechas(Festival festival, LocalDate fechaDesde, LocalDate fechaHasta) throws Exception {
			if(encontrarFestival(festival.getNombre()) == null) {
				throw new Exception("El festival no existe");
			}
			List<ReporteVenta> lstReportes = new ArrayList<ReporteVenta>();
			for(UnidadDeVenta u : festival.getLstUnidades()) {
				lstReportes.add(new ReporteVenta(LocalDate.now(), u, calcularRecaudacion(u, fechaDesde, fechaHasta)));
			}
			return lstReportes;
			
		}
	//Unidades de mayor Canon
	public List<ReporteMayorCanon> reporteMayoresCanon(Festival festival) throws Exception{
		if(encontrarFestival(festival.getNombre()) == null) {
			throw new Exception("El festival no existe");
		}
		List<ReporteMayorCanon> lstMayorCanon = new ArrayList<ReporteMayorCanon>();
		for(UnidadDeVenta u : festival.getLstUnidades()) {
			if(u instanceof PuestoDesarmable) {
				PuestoDesarmable p = (PuestoDesarmable) u;
				lstMayorCanon.add(new ReporteMayorCanon(u, p.calcularCanon(festival.getCosto().getPorSuperficie(), festival.getCosto().getPorMontaje())));
			}else {
				FoodTruck f = (FoodTruck) u;
				lstMayorCanon.add(new ReporteMayorCanon(u, f.calcularCanon(festival.getCosto().getPorSuperficie(), festival.getCosto().getPorElectricidad())));
			}
		}
		ordenarPorCanon(lstMayorCanon);
		return lstMayorCanon;
		
	}
	
		
}
