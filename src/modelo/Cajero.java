package modelo;

import java.time.LocalDate;

public class Cajero extends Persona{
	
	private String turno;
	private double bonoPorAntiguedad;
	
	public Cajero(int idPersona, String nombre, String apellido, long dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double sueldoBase, String turno, double bonoPorAntiguedad) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
		this.turno = turno;
		this.bonoPorAntiguedad = bonoPorAntiguedad;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public double getBonoPorAntiguedad() {
		return bonoPorAntiguedad;
	}

	public void setBonoPorAntiguedad(double bonoPorAntiguedad) {
		this.bonoPorAntiguedad = bonoPorAntiguedad;
	}

	@Override
	public String toString() {
		return "\nCajero "+super.toString()+"[turno=" + turno + ", bonoPorAntiguedad=" + bonoPorAntiguedad + "]";
	}
	
	public double calcularSueldo() {
		// Sueldo Base + (Antigüedad * bono)
		return (this.sueldoBase + (this.validarAntiguedad(LocalDate.now())*this.bonoPorAntiguedad));
	}
	
	
	
	

}
