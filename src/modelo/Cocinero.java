package modelo;

import java.time.LocalDate;

public class Cocinero extends Persona{
	
	private String especialidad;
	private String categoria;
	private double plusCategoria;
	
	public Cocinero(int idPersona, String nombre, String apellido, long dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double sueldoBase, String especialidad, String categoria, double plusCategoria) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
		this.especialidad = especialidad;
		this.categoria = categoria;
		this.plusCategoria = plusCategoria;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPlusCategoria() {
		return plusCategoria;
	}

	public void setPlusCategoria(double plusCategoria) {
		this.plusCategoria = plusCategoria;
	}

	@Override
	public String toString() {
		return "\nCocinero "+super.toString()+"[especialidad=" + especialidad + ", categoria=" + categoria + ", plusCategoria="
				+ plusCategoria + "]";
	}
	
	public double calcularPlus() {
		return (this.plusCategoria);
	}
	
	public double calcularSueldo() {
		
		return (this.sueldoBase + this.calcularPlus());
	}

}
