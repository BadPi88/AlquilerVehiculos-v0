package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Cliente {
	private static final String ER_NOMBRE = "[A-ZÁÉÍÓÚÜÑ][a-záéíóúÜñ]+([ ][A-ZÁÉÍÓÚÜÑ][a-záéíóúÜñ]+)*";
	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_TELEFONO = "[96][0-9]{8}";

	private String dni;
	private String nombre;
	private String telefono;
	
	//Lo usamos para inicializar variables
	public Cliente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}
	
	//Constructor copia 
	public Cliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}
		setNombre(cliente.getNombre());
		setDni(cliente.getDni());
		setTelefono(cliente.getTelefono());
	}
	//get y set(Con sus excepciones)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (!nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (comprobarLetraDni(dni) == false) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		}
		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
		this.telefono = telefono;
	}

	private boolean comprobarLetraDni(String dni) {
		boolean letraVerdadera = false;

		char[] letrasDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };
		char letra = dni.charAt(dni.length() - 1);
		int posicion = Integer.parseInt(dni.substring(0, dni.length() - 1));
		if (letrasDni[posicion % 23] == letra) {
			letraVerdadera = true;
		}
		return letraVerdadera;

	}

	public static Cliente getClienteConDni(String dni) {
		return new Cliente("Bob Esponj", dni, "950112233");
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}
	
	//metodo toString

	@Override
	public String toString() {
		return String.format("%s - %s (%s)", nombre, dni, telefono);
	}

}