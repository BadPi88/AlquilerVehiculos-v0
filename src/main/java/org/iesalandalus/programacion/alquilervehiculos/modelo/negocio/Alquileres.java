package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

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

	// recorrer cliente
	private void comprobarAlquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getFechaDevolucion() == null) {
				if (alquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				if (alquiler.getTurismo().equals(turismo)) {
					throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
				}
			} else if (!alquiler.getFechaDevolucion().isBefore(fechaAlquiler)) {
				if (alquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				if (alquiler.getTurismo().equals(turismo)) {
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
				}
			}
		}
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);

	}

	public Alquiler devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler)) {
			
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		alquiler.devolver(fechaDevolucion);
		return alquiler;

	}
	// Mismo que clientes

	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {

			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		Alquiler alquilerExistente;
		int indice = coleccionAlquileres.indexOf(alquiler);
		if (coleccionAlquileres.contains(alquiler)) {
			alquilerExistente = coleccionAlquileres.get(indice);
		} else {
			alquilerExistente = null;
		}
		return alquilerExistente;

	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.remove(alquiler);
	}

}