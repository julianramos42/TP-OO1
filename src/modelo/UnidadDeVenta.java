package modelo;

import java.time.LocalDate;
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
	private List<Pedido> lstPedidos;
	
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
		this.lstPedidos = new ArrayList<Pedido>();
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
	
	
	public List<Pedido> getLstPedidos() {
		return lstPedidos;
	}

	public void setLstPedidos(List<Pedido> lstPedidos) {
		this.lstPedidos = lstPedidos;
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
		
		while (plato == null && i < this.lstPlatos.size()) {
			if (this.lstPlatos.get(i).getNombre().equals(nombre)) {
				plato = this.lstPlatos.get(i);
			}
			
			i++;
		}

		return plato;
	}
	
	
	@Override
	public String toString() {
		return "UnidadDeVenta [idUnidadDeVenta=" + idUnidadDeVenta + ", nombreComercial=" + nombreComercial
				+ ", codigoUnico=" + codigoUnico + ", superficie=" + superficie + ", responsable=" + responsable
				+ ", lstPersonas=" + lstPersonas + ", lstPlatos=" + lstPlatos + ", lstPedidos=" + lstPedidos + "]";
	}

	public boolean equals(UnidadDeVenta unidad) {
		return this.codigoUnico.equals(unidad.codigoUnico);
	}
	
	// VALIDACION DEL ENUNCIADO (NO ESPECIFICA QUE VALIDAR)
	protected boolean validarCodigoUnico(String codigo) {
	    return codigo.length() == 10; 
	}
	
	public boolean agregarPlatoAlMenu(String nombre, double precio, double costo) throws Exception {
		if(encontrarPlato(nombre) != null) {
			throw new Exception("El plato ya forma parte del menú");
		}
		int id = 1;
		if(!lstPlatos.isEmpty()) {
			id = lstPlatos.get(lstPlatos.size()-1).getIdPlato()+1;
		}
		return(lstPlatos.add(new Plato(id, nombre, precio, costo)));
	}
	// CALCULARCANON CLASE ABSTRACTA, SE HACE OVERRIDE EN LAS HIJAS
	public abstract double calcularCanon(double costoSuperficie, double costoAdicional);
	
	//encontrarPedido
	public Pedido encontrarPedido(Festival festival, int id) {
		Pedido pedido = null;
		int i = 0;
		while(i < lstPedidos.size() && pedido == null) {
			if(lstPedidos.get(i).getIdPedido() == id && lstPedidos.get(i).getFestival().equals(festival)) {
				pedido = lstPedidos.get(i);
			}
			i++;
		}
		return pedido;
	}
	//agregarPedido o validarPedido -> CASO DE USO 5
	public boolean agregarPedido(Festival festival, LocalDate fecha) {
		int id = 1;
		if(!lstPedidos.isEmpty()) {
			id = lstPedidos.get(lstPedidos.size()-1).getIdPedido()+1;
		}
		return lstPedidos.add(new Pedido(id, festival, fecha));
	}
	
	
	private int calcularCantidadPedidaEnFestival(Plato plato, Festival festival) {
		int cantidad = 0;

		for (Pedido pedido : this.lstPedidos) {
			if (pedido.getFestival().equals(festival)) {
				for (ItemPlato item : pedido.getLstItemPlatos()) {
					if (item.getPlato().equals(plato)) {
						cantidad += item.getCantidad();
					}
				}
			}
		}

		return cantidad;
	}
	
	public Plato traerPlatoEstrella(Festival festival) {
		Plato platoEstrella = null;
		int mayorCantidad = 0;

		for (Plato plato : this.lstPlatos) {
			int cantidad = this.calcularCantidadPedidaEnFestival(plato, festival);

			if (cantidad > mayorCantidad) {
				mayorCantidad = cantidad;
				platoEstrella = plato;
			}
		}

		return platoEstrella;
	}
}


