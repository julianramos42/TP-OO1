<div align="center">
  <h1>☕ Sistema de gestión: "Epicentro Gourmet"</h1>
  <p><i>Laboratorio de trabajos prácticos de Orientación a Objetos I</i></p>

  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  </div>

<br>

> Estudiantes: Ramos Julián, Enrique Leandro Ariel, Liberatori Gonzalo, Martinez Martina.
>
> Equipo docente: Esp. Lic. Scordamaglia Ezequiel, Mg. Lic. Vranic Alejandra, Lic. Borea Nicolás, Lic. Bustos Rita.
>

## 📖 Descripción
El proyecto diseña y desarrola un sistema de gestión integral para el centro de convenciones y predio ferial "Epicentro Gourmet". 
El objetivo principal es optimizar la administración de sus festivales gastronómicos anuales, controlando las unidades de venta, el personal asignado y el rendimiento económico de cada jornada.

## 🏗️ Arquitectura y Clases Principales
La solución se estructura bajo una arquitectura orientada a objetos basada en los siguientes clases principales:

Sistema: Es la clase de administración y gestión de todas las listas del sistema.

Festival: Registro de los eventos y costos.

Unidades de Venta: Locales comerciales con un código único que gestionan su propio menú y staff. Se dividen en dos subclases con atributos adicionales: FoodTrucks y Puestos Desarmables

Personal: Control de los empleados del predio que se extiende en dos subclases: Cocinero y Cajero.

Control económico (Plato, Pedidos, Recaudación): Cumplen el rol de gestión de transacciones de cada festival generando registros con fechas específicas.

### 📂 Estructura del Repositorio
```
src/
  └── java/
    └── Tp-OO1/
      ├── model/       # Clases
      ├── test/  # Test de casos de uso
      └── 
```
><div align="center"> Primer cuatrimestre de 2026 - Universidad Nacional de Lanús.
>
