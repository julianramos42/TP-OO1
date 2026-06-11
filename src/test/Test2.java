package test;

import java.time.LocalDate;


import modelo.Sistema;
import modelo.UnidadDeVenta;

public class Test2 {

	public static void main(String[] args) {

		Sistema sistema = new Sistema();

		System.out.println("===== TEST SISTEMA EPICENTRO GOURMET =====");

		// ============================================================
		// CU 1 - Altas: festivales, personal y unidades
		// ============================================================

		System.out.println("\nCU 1 - Altas");
		// ============================================================
		// ALTA DE PERSONAS
		// ============================================================
		try {
			sistema.agregarCocinero(
				"Juan",
				"Perez",
				12345678,
				LocalDate.of(1990, 5, 10),
				LocalDate.of(2020, 3, 1),
				10000,
				"Parrilla",
				"A",
				3000
			);
			sistema.agregarCajero(
				"Ana",
				"Gomez",
				87654321,
				LocalDate.of(1995, 4, 20),
				LocalDate.of(2021, 1, 1),
				10000,
				"Mañana",
				500
			);
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		} finally {
			System.out.println("\nPERSONAL: \n" + sistema.getLstPersonal());
		}
		// ============================================================
		// ALTA DE UNIDADES
		// ============================================================
		try {
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
				20
			);
			sistema.agregarFoodTruck(
					"Pancho Truck",
					"PAN1234567",
					10,
					sistema.encontrarUnidad("ABC1234567").getResponsable(),
					"BB123CC",
					false
				);
		// ============================================================
		// Asignar empleados a unidad
		// ============================================================

			try {
				sistema.encontrarUnidad("ABC1234567").agregarEmpleado(
					sistema.encontrarPersona(12345678)
				);

				sistema.encontrarUnidad("XYZ1234567").agregarEmpleado(
					sistema.encontrarPersona(87654321)
				);
				
				sistema.encontrarUnidad("PAN1234567").agregarEmpleado(
						sistema.encontrarPersona(87654321)
					);
			} catch (Exception e) {
				System.out.println("ERROR " + e.getMessage());
			}
			
		// ============================================================
		// Carga de platos
		// ============================================================

				sistema.encontrarUnidad("ABC1234567").agregarPlatoAlMenu("Hamburguesa", 15000, 2500);
				sistema.encontrarUnidad("ABC1234567").agregarPlatoAlMenu("Pizza", 14000, 2000);
				sistema.encontrarUnidad("XYZ1234567").agregarPlatoAlMenu("Empanada", 5000, 500);
				sistema.encontrarUnidad("PAN1234567").agregarPlatoAlMenu("Pancho", 1500, 500);
			
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		} finally {
			System.out.println("\nUNIDADES: \n" + sistema.getLstUnidades());
		}
		
		// ============================================================
		// ALTA DE FESTIVALES
		// ============================================================
		try {
			sistema.agregarFestival(
				"Festival Verano",
				"Verano",
				LocalDate.of(2026, 1, 1),
				LocalDate.of(2026, 1, 10),
				50,
				10,
				12
			);
			sistema.agregarFestival(
					"Festival Invierno",
					"Invierno",
					LocalDate.of(2026, 1, 1),
					LocalDate.of(2026, 1, 10),
					50,
					10,
					12
				);
			
		// ============================================================
		// Relacionar unidades con festival
		// ============================================================

			try {
				sistema.encontrarFestival("Festival Verano").agregarUnidadDeVenta(
					sistema.encontrarUnidad("ABC1234567")
				);

				sistema.encontrarFestival("Festival Verano").agregarUnidadDeVenta(
					sistema.encontrarUnidad("XYZ1234567")
				);
				sistema.encontrarFestival("Festival Verano").agregarUnidadDeVenta(
						sistema.encontrarUnidad("PAN1234567")
				);
			} catch (Exception e) {
				System.out.println("ERROR " + e.getMessage());
			}
			
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		} finally {
			System.out.println("\nFESTIVALES: \n" + sistema.getLstFestivales());
		}

		// ============================================================
		// CU 2 - Búsqueda por atributo único
		// ============================================================

		System.out.println("\nCU 2 - Búsqueda por atributo único");

		try {
			System.out.println("Persona encontrada: " + sistema.encontrarPersona(87654321));
			System.out.println("Unidad encontrada: " + sistema.encontrarUnidad("ABC1234567"));
			System.out.println("Festival encontrado: " + sistema.encontrarFestival("Festival Verano"));
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		
		// ============================================================
		// CU 3 - Cálculo de canon
		// ============================================================

		System.out.println("\nCU 3 - Cálculo de canon");

		try {
			System.out.println("Canon FoodTruck: " + sistema.encontrarUnidad("ABC1234567").calcularCanon(
				sistema.encontrarFestival("Festival Verano").getCosto().getPorSuperficie(),
				sistema.encontrarFestival("Festival Verano").getCosto().getPorElectricidad()
			));

			System.out.println("Canon Puesto: " + sistema.encontrarUnidad("XYZ1234567").calcularCanon(
				sistema.encontrarFestival("Festival Verano").getCosto().getPorSuperficie(),
				sistema.encontrarFestival("Festival Verano").getCosto().getPorMontaje()
			));
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		// ============================================================
		// CU 4 - Liquidación de haberes
		// ============================================================

		System.out.println("\nCU 4 - Liquidación de haberes");

		try {
			System.out.println("Sueldo cocinero: " + sistema.encontrarPersona(12345678).calcularSueldo());
			System.out.println("Sueldo cajero: " + sistema.encontrarPersona(87654321).calcularSueldo());
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		// ============================================================
		// CU 5 - Registro de pedido
		// ============================================================

		System.out.println("\nCU 5 - Registro de pedido");

		try {
			sistema.encontrarUnidad("ABC1234567").agregarPedido(
				sistema.encontrarFestival("Festival Verano"),
				LocalDate.of(2026, 1, 2)
			);

			sistema.encontrarUnidad("ABC1234567").agregarPedido(
				sistema.encontrarFestival("Festival Verano"),
				LocalDate.of(2026, 1, 3)
			);

			sistema.encontrarUnidad("XYZ1234567").agregarPedido(
				sistema.encontrarFestival("Festival Verano"),
				LocalDate.of(2026, 1, 2)
			);
			sistema.encontrarUnidad("PAN1234567").agregarPedido(
					sistema.encontrarFestival("Festival Verano"),
					LocalDate.of(2026, 1, 4)
			);
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		// ============================================================
		// Carga de ítems a pedidos
		// ============================================================

		System.out.println("\nCarga de items a pedidos");

		try {
			sistema.encontrarUnidad("ABC1234567")
				.encontrarPedido(sistema.encontrarFestival("Festival Verano"), 1)
				.agregarItemPlato(
					sistema.encontrarUnidad("ABC1234567").encontrarPlato("Hamburguesa"),
					2
				);

			sistema.encontrarUnidad("ABC1234567")
				.encontrarPedido(sistema.encontrarFestival("Festival Verano"), 1)
				.agregarItemPlato(
					sistema.encontrarUnidad("ABC1234567").encontrarPlato("Pizza"),
					1
				);

			sistema.encontrarUnidad("ABC1234567")
				.encontrarPedido(sistema.encontrarFestival("Festival Verano"), 2)
				.agregarItemPlato(
					sistema.encontrarUnidad("ABC1234567").encontrarPlato("Hamburguesa"),
					3
				);

			sistema.encontrarUnidad("XYZ1234567")
				.encontrarPedido(sistema.encontrarFestival("Festival Verano"), 1)
				.agregarItemPlato(
					sistema.encontrarUnidad("XYZ1234567").encontrarPlato("Empanada"),
					10
				);

			sistema.encontrarUnidad("PAN1234567")
				.encontrarPedido(sistema.encontrarFestival("Festival Verano"), 1)
				.agregarItemPlato(
					sistema.encontrarUnidad("PAN1234567").encontrarPlato("Pancho"),
					1
				);
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		} finally {
			System.out.println("Pedidos de " + sistema.encontrarUnidad("ABC1234567").getNombreComercial() + " "+ sistema.encontrarUnidad("ABC1234567").getLstPedidos());
			System.out.println("Pedidos de " + sistema.encontrarUnidad("XYZ1234567").getNombreComercial() + " "+ sistema.encontrarUnidad("XYZ1234567").getLstPedidos());
			System.out.println("Pedidos de " + sistema.encontrarUnidad("PAN1234567").getNombreComercial() + " "+ sistema.encontrarUnidad("PAN1234567").getLstPedidos());
		}

		// ============================================================
		// CU 6 - Reporte de recaudación
		// ============================================================

		System.out.println("\nCU 6 - Reporte de recaudación");

		try {
			System.out.println("Cantidad de reportes: " + sistema.reporteRecaudacion(
				sistema.encontrarFestival("Festival Verano")
			).size());

			System.out.println(sistema.reporteRecaudacion(sistema.encontrarFestival("Festival Verano")));
			
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		// ============================================================
		// CU 7 - Filtro de personal por edad
		// ============================================================

		System.out.println("\nCU 7 - Filtro de personal por edad");

		try {
			System.out.println("Cantidad de empleados encontrados: " + sistema.filtroPorEdad(
				LocalDate.of(1990, 1, 1),
				LocalDate.of(2000, 12, 31)
			).size());

			System.out.println(sistema.filtroPorEdad(LocalDate.of(1990, 1, 1), LocalDate.of(2000, 12, 31)));
			
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}


		// ============================================================
		// CU 8 - Rentabilidad neta
		// ============================================================

		System.out.println("\nCU 8 - Rentabilidad neta");

		try {
			System.out.println("Rentabilidad neta de "+ sistema.encontrarUnidad("ABC1234567").getNombreComercial() + " $" + sistema.calcularRentabilidadNeta(
				sistema.encontrarUnidad("ABC1234567"),
				sistema.encontrarFestival("Festival Verano").getCosto().getPorSuperficie(),
				sistema.encontrarFestival("Festival Verano").getCosto().getPorElectricidad()
			));
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		// ============================================================
		// CU 9 - Rentabilidad neta entre fechas
		// ============================================================

		System.out.println("\nCU 9 - Rentabilidad neta entre fechas");

		try {
			System.out.println("Rentabilidad entre fechas: $" + sistema.calcularRentabilidadEntreFechas(
				sistema.encontrarUnidad("ABC1234567"),
				LocalDate.of(2026, 1, 1),
				LocalDate.of(2026, 1, 31),
				sistema.encontrarFestival("Festival Verano").getCosto().getPorSuperficie(),
				sistema.encontrarFestival("Festival Verano").getCosto().getPorElectricidad()
			));
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		// ============================================================
		// CU 10 - Ranking de unidades
		// ============================================================

		System.out.println("\nCU 10 - Ranking de unidades");

		try {
			for (UnidadDeVenta unidad : sistema.rankingDeUnidades(sistema.encontrarFestival("Festival Verano"))) {
				System.out.println(unidad.getNombreComercial());
			}
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		// ============================================================
		// CU 11 - Plato estrella
		// ============================================================

		System.out.println("\nCU 11 - Plato estrella");

		try {
			System.out.println("Plato estrella esperado: Hamburguesa");

			System.out.println("Plato estrella obtenido: " + sistema.encontrarUnidad("ABC1234567")
				.traerPlatoEstrella(sistema.encontrarFestival("Festival Verano"))
				.getNombre()
			);
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		// ============================================================
		// CU 12 - Auditoría de personal del festival
		// ============================================================

		System.out.println("\nCU 12 - Auditoría de personal del festival");

		try {
			System.out.println("Cantidad de personal: " + sistema.encontrarFestival("Festival Verano")
				.auditoriaPersonal()
				.size()
			);

				System.out.println(sistema.encontrarFestival("Festival Verano").auditoriaPersonal());
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		// ============================================================
		// CU 13 - Unidades con mayor canon
		// ============================================================

		System.out.println("\nCU 13 - Unidades con mayor canon");

		try {
				System.out.println(sistema.reporteMayoresCanon(sistema.encontrarFestival("Festival Verano")));
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}

		System.out.println("\n===== FIN DE TESTS =====");
		
		System.out.println("\n===== TEST CON ERRORES =====");
		// ============================================================
		// FESTIVAL EXISTENTE
		// ============================================================
		try {
			sistema.agregarCocinero(
					"Juan",
					"Perez",
					12345678,
					LocalDate.of(1990, 5, 10),
					LocalDate.of(2020, 3, 1),
					10000,
					"Parrilla",
					"A",
					3000
				);
		}catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}
		// ============================================================
		// UNIDAD EXISTENTE
		// ============================================================
		try {
			sistema.agregarFoodTruck(
					"Burger Truck",
					"ABC1234567",
					20,
					sistema.encontrarPersona(12345678),
					"AA123BB",
					true
				);
		}catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}
		// ============================================================
		// PERSONA EXISTENTE
		// ============================================================
		try {
			sistema.agregarFestival(
					"Festival Verano",
					"Verano",
					LocalDate.of(2026, 1, 1),
					LocalDate.of(2026, 1, 10),
					50,
					10,
					12
				);
		}catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}
		
		// ============================================================
		// PEDIR PLATO QUE NO ESTA EN EL MENÚ
		// ============================================================
		
		try {
			sistema.encontrarUnidad("ABC1234567")
			.encontrarPedido(sistema.encontrarFestival("Festival Verano"), 1)
			.agregarItemPlato(
				sistema.encontrarUnidad("ABC1234567").encontrarPlato("Nuggets"),
				2
			);
		}catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
		}
	}
}