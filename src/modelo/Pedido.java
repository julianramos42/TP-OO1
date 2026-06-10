package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private int idPedido;
	private Festival festival;
	private LocalDate fecha;
	List<ItemPlato> lstItemPlatos;
	
	public Pedido(int idPedido, Festival festival, LocalDate fecha) {
		this.idPedido = idPedido;
		this.festival = festival;
		this.fecha = fecha;
		this.lstItemPlatos = new ArrayList<ItemPlato>();
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public List<ItemPlato> getLstItemPlatos() {
		return lstItemPlatos;
	}

	public void setLstItemPlatos(List<ItemPlato> lstItemPlatos) {
		this.lstItemPlatos = lstItemPlatos;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + " festival=" + festival + ", fecha=" + fecha + ", lstItemPlatos=" + lstItemPlatos + "]";
	}
	public boolean equals(Pedido pedido){
		return(this.getIdPedido() == pedido.getIdPedido());
	}
	
	public double calcularTotal() {
		double total = 0;
		for(ItemPlato i : lstItemPlatos) {
			total += i.calcularTotaPorItem();
		}
		return total;
	}
	
	public double calcularGanancia() {
		double costos = 0;
		for(ItemPlato i : lstItemPlatos) {
			costos += i.getPlato().getCosto() * i.getCantidad();
		}
		return(this.calcularTotal() - costos);
	}
	public boolean agregarItemPlato(Plato plato, int cantidad) throws Exception {
		if(plato == null) {
			throw new Exception("El plato no forma parte del menú de esta unidad");
		}
		if(cantidad <= 0) {
			throw new Exception("La cantidad del plato no puede ser menor a 1");
		}
		int id = 1;
		if(!lstItemPlatos.isEmpty()) {
			id = lstItemPlatos.get(lstItemPlatos.size()-1).getIdItemPlato()+1;
		}
		return lstItemPlatos.add(new ItemPlato(id, plato, cantidad));
	}
	

}
