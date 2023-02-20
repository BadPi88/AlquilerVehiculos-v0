package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Alquileres {

	private List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}

	// Devolver una lista de los Alquileres para el cliente indicado
	public List<Alquiler> get(Cliente cliente) {
		List<Alquiler> listaAlquileresCliente = new ArrayList<>();
		for (Alquiler elemento : coleccionAlquileres) {
			if (elemento.getCliente().equals(cliente)) {
				listaAlquileresCliente.add(elemento);
			}
		}
		return listaAlquileresCliente;
	}

	// Devolver lista de los Alquileres para el turismo
	public List<Alquiler> get(Turismo turismo) {
		List<Alquiler> listaAlquileresTurismo = new ArrayList<>();
		for (Alquiler elemento : coleccionAlquileres) {
			if (elemento.getTurismo().equals(turismo)) {
				listaAlquileresTurismo.add(elemento);
			}
		}
		return listaAlquileresTurismo;
	}

	public int getCantidad() {
		return coleccionAlquileres.size();
	}

}