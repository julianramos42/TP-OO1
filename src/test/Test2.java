package test;

import java.time.LocalDate;
import java.util.List;

import modelo.Persona;
import modelo.Plato;
import modelo.ReporteMayorCanon;
import modelo.ReporteVenta;
import modelo.Sistema;
import modelo.UnidadDeVenta;

public class Test2 {

	public static void main(String[] args) {

		try {
			Sistema sistema = new Sistema();

			System.out.println("===== TEST SISTEMA EPICENTRO GOURMET =====");

			// ============================================================
			// CU 1 - Altas: festivales, personal y unidades
			// ============================================================

			System.out.println("\nCU 1 - Altas");

			sistema.agregarFestival(
				"Festival Verano",
				"Verano",
				LocalDate.of(2026, 1, 1),
				LocalDate.of(2026, 1, 10),
				500,
				10,
				2000
			);

			sistema.agregarCocinero(
				"Juan",
				"Perez",
				12345678,
				LocalDate.of(1990, 5, 10),
				LocalDate.of(2020, 3, 1),
				100000,
				"Parrilla",
				"A",
				30000
			);

			sistema.agregarCajero(
				"Ana",
				"Gomez",
				87654321,
				LocalDate.of(1995, 4, 20),
				LocalDate.of(2021, 1, 1),
				100000,
				"Mañana",
				5000
			);



			sistema.agregarFoodTruck(
				"Burger Truck",
				"ABC1234567",
				20,
				sistema.encontrarPersona(12345678),
				"AA123BB",
				true
			);

			sistema.agregarPuestoDesarmable(
				"Puesto Pizza",
				"XYZ1234567",
				30,
				sistema.encontrarPersona(87654321),
				3,
				60
			);

			System.out.println("Festivales: " + sistema.getLstFestivales().size());
			System.out.println("Personal: " + sistema.getLstPersonal().size());
			System.out.println("Unidades: " + sistema.getLstUnidades().size());

			// ============================================================
			// CU 2 - Búsqueda por atributo único
			// ============================================================

			System.out.println("\nCU 2 - Búsqueda por atributo único");

			System.out.println("Persona encontrada: " + sistema.encontrarPersona(87654321).toString());
			System.out.println("Unidad encontrada: " + sistema.encontrarUnidad("ABC1234567").toString());
			System.out.println("Unidad encontrada: " + sistema.encontrarFestival("Festival Verano").toString());

			// ============================================================
			// CU 3 - Cálculo de canon
			// ============================================================

			System.out.println("\nCU 3 - Cálculo de canon");


			System.out.println("Canon FoodTruck: " + sistema.encontrarUnidad("ABC1234567").calcularCanon(500, 2000));
			System.out.println("Canon Puesto: " + sistema.encontrarUnidad("XYZ1234567").calcularCanon(500, 10));

			// ============================================================
			// CU 4 - Liquidación de haberes
			// ============================================================

			System.out.println("\nCU 4 - Liquidación de haberes");

			System.out.println("Sueldo cocinero: " + sistema.encontrarPersona(12345678).calcularSueldo());
			System.out.println("Sueldo cajero: " + sistema.encontrarPersona(87654321).calcularSueldo());

			// ============================================================
			// Relacionar unidades con festival
			// ============================================================


			sistema.encontrarFestival("Festival Verano").agregarUnidadDeVenta(sistema.encontrarUnidad("ABC1234567"));
			sistema.encontrarFestival("Festival Verano").agregarUnidadDeVenta(sistema.encontrarUnidad("XYZ1234567"));

			// ============================================================
			// CU 5 - Registro de pedido
			// ============================================================

			System.out.println("\nCU 5 - Registro de pedido");

			sistema.encontrarUnidad("ABC1234567").agregarPedido(sistema.encontrarFestival("Festival Verano"), LocalDate.of(2026, 1, 2));
			sistema.encontrarUnidad("ABC1234567").agregarPedido(sistema.encontrarFestival("Festival Verano"), LocalDate.of(2026, 1, 3));
			sistema.encontrarUnidad("XYZ1234567").agregarPedido(sistema.encontrarFestival("Festival Verano"), LocalDate.of(2026, 1, 2));

			System.out.println("Pedidos Burger Truck: " + sistema.encontrarUnidad("ABC1234567").getLstPedidos().size());
			System.out.println("Pedidos Puesto Pizza: " + sistema.encontrarUnidad("XYZ1234567").getLstPedidos().size());

			// ============================================================
			// Carga de platos e items para probar los casos siguientes
			// ============================================================

			sistema.encontrarUnidad("ABC1234567").agregarPlatoAlMenu("Hamburguesa", 5000, 2500);
			sistema.encontrarUnidad("ABC1234567").agregarPlatoAlMenu("Pizza", 4000, 2000);
			sistema.encontrarUnidad("XYZ1234567").agregarPlatoAlMenu("Empanada", 1000, 500);

			Plato hamburguesa = sistema.encontrarUnidad("ABC1234567").encontrarPlato("Hamburguesa");
			Plato pizza = sistema.encontrarUnidad("ABC1234567").encontrarPlato("Pizza");
			Plato empanada = sistema.encontrarUnidad("XYZ1234567").encontrarPlato("Empanada");

			sistema.encontrarUnidad("ABC1234567").encontrarPedido(sistema.encontrarFestival("Festival Verano"), 1).agregarItemPlato(hamburguesa, 2);
			sistema.encontrarUnidad("ABC1234567").encontrarPedido(sistema.encontrarFestival("Festival Verano"), 1).agregarItemPlato(pizza, 1);
			sistema.encontrarUnidad("ABC1234567").encontrarPedido(sistema.encontrarFestival("Festival Verano"), 2).agregarItemPlato(hamburguesa, 3);

			sistema.encontrarUnidad("XYZ1234567").encontrarPedido(sistema.encontrarFestival("Festival Verano"), 1).agregarItemPlato(empanada, 10);

			// ============================================================
			// CU 6 - Reporte de recaudación
			// ============================================================

			System.out.println("\nCU 6 - Reporte de recaudación");

			List<ReporteVenta> reportes = sistema.reporteRecaudacion(sistema.encontrarFestival("Festival Verano"));

			System.out.println("Cantidad de reportes: " + reportes.size());

			for (ReporteVenta reporte : reportes) {
				System.out.println(
					reporte.getUnidad().getNombreComercial() 
					+ " - Recaudación: " 
					+ reporte.getRecaudacion()
				);
			}

			// ============================================================
			// CU 7 - Filtro de personal por edad
			// ============================================================

			System.out.println("\nCU 7 - Filtro de personal por edad");

			List<Persona> personalFiltrado = sistema.filtroPorEdad(
				LocalDate.of(1990, 1, 1),
				LocalDate.of(2000, 12, 31)
			);

			System.out.println("Cantidad filtrada: " + personalFiltrado.size());

			for (Persona persona : personalFiltrado) {
				System.out.println(persona.getNombre() + " " + persona.getApellido());
			}

			// ============================================================
			// Asignar empleados a unidad
			// ============================================================

			sistema.encontrarUnidad("ABC1234567").agregarEmpleado(sistema.encontrarPersona(12345678));
			sistema.encontrarUnidad("ABC1234567").agregarEmpleado(sistema.encontrarPersona(87654321));

			// ============================================================
			// CU 8 - Rentabilidad neta
			// ============================================================

			System.out.println("\nCU 8 - Rentabilidad neta");

			double rentabilidad = sistema.calcularRentabilidadNeta(sistema.encontrarUnidad("ABC1234567"), 500, 2000);

			System.out.println("Rentabilidad neta Burger Truck: " + rentabilidad);

			// ============================================================
			// CU 9 - Rentabilidad neta entre fechas
			// ============================================================

			System.out.println("\nCU 9 - Rentabilidad neta entre fechas");

			double rentabilidadFechas = sistema.calcularRentabilidadEntreFechas(
					sistema.encontrarUnidad("ABC1234567"),
				LocalDate.of(2026, 1, 1),
				LocalDate.of(2026, 1, 31),
				500,
				2000
			);

			System.out.println("Rentabilidad entre fechas: " + rentabilidadFechas);

			// ============================================================
			// CU 10 - Ranking de unidades
			// ============================================================

			System.out.println("\nCU 10 - Ranking de unidades");

			sistema.agregarFoodTruck(
				"Pancho Truck",
				"PAN1234567",
				10,
				sistema.encontrarUnidad("ABC1234567").getResponsable(),
				"BB123CC",
				false
			);

			UnidadDeVenta panchoTruck = sistema.encontrarUnidad("PAN1234567");

			sistema.encontrarFestival("Festival Verano").agregarUnidadDeVenta(panchoTruck);

			panchoTruck.agregarPlatoAlMenu("Pancho", 1500, 500);
			panchoTruck.agregarPedido(sistema.encontrarFestival("Festival Verano"), LocalDate.of(2026, 1, 4));
			panchoTruck.encontrarPedido(sistema.encontrarFestival("Festival Verano"), 1).agregarItemPlato(
				panchoTruck.encontrarPlato("Pancho"),
				1
			);

			List<UnidadDeVenta> ranking = sistema.rankingDeUnidades(sistema.encontrarFestival("Festival Verano"));

			for (UnidadDeVenta unidad : ranking) {
				System.out.println(unidad.getNombreComercial());
			}

			// ============================================================
			// CU 11 - Plato estrella
			// ============================================================

			System.out.println("\nCU 11 - Plato estrella");

			Plato platoEstrella = sistema.encontrarUnidad("ABC1234567").traerPlatoEstrella(sistema.encontrarFestival("Festival Verano"));

			System.out.println("Plato estrella esperado: Hamburguesa");
			System.out.println("Plato estrella obtenido: " + platoEstrella.getNombre());

			// ============================================================
			// CU 12 - Auditoría de personal del festival
			// ============================================================

			System.out.println("\nCU 12 - Auditoría de personal del festival");

			List<Persona> personalFestival = sistema.encontrarFestival("Festival Verano").auditoriaPersonal();

			System.out.println("Cantidad de personal: " + personalFestival.size());

			for (Persona persona : personalFestival) {
				System.out.println(persona.getNombre() + " " + persona.getApellido());
			}

			// ============================================================
			// CU 13 - Unidades con mayor canon
			// ============================================================

			System.out.println("\nCU 13 - Unidades con mayor canon");

			List<ReporteMayorCanon> mayoresCanon = sistema.reporteMayoresCanon(sistema.encontrarFestival("Festival Verano"));

			for (ReporteMayorCanon reporte : mayoresCanon) {
				System.out.println(
					reporte.getUnidad().getNombreComercial() 
					+ " - Canon: " 
					+ reporte.getCanon()
				);
			}

			System.out.println("\n===== FIN DE TESTS =====");

		} catch (Exception e) {
			System.out.println("\nERROR EN EL TEST");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}