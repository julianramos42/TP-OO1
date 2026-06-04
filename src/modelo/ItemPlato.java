package modelo;

public class ItemPlato {

	private int idItemPlato;
	private Plato plato;
	private int cantidad;
	
	public ItemPlato(int idItemPlato, Plato plato, int cantidad) {
		this.idItemPlato = idItemPlato;
		this.plato = plato;
		this.cantidad = cantidad;
	}

	public int getIdItemPlato() {
		return idItemPlato;
	}

	public void setIdItemPlato(int idItemPlato) {
		this.idItemPlato = idItemPlato;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ItemPlato [idItemPlato=" + idItemPlato + ", plato=" + plato + ", cantidad=" + cantidad + "]";
	}
	
	public double calcularTotaPorItem() {
		return (this.getPlato().getPrecio() * cantidad);
		
	}
	

}

