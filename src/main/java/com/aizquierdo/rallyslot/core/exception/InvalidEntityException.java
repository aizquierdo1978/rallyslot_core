package com.aizquierdo.rallyslot.core.exception;

import java.util.List;

public class InvalidEntityException extends RuntimeException {	

	private static final long serialVersionUID = 2082862875646043577L;

	private List<String> errors;

	/**
	 * Instancia una nueva excepción.
	 */
	public InvalidEntityException() {
		super();
	}

	/**
	 * Instancia una nueva excepción.
	 *
	 * @param message el mensaje
	 * @param cause   la causa
	 */
	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instancia una nueva excepción.
	 *
	 * @param message el mensaje
	 */
	public InvalidEntityException(String message) {
		super(message);
	}


	/**
	 * Instancia una nueva excepción.
	 *
	 * @param message el mensaje
	 * @param errors  el listado de errores
	 */
	public InvalidEntityException(String message, List<String> errors) {
		super(message);
		this.errors = errors;
	}

	/**
	 * Instancia una nueva excepción.
	 *
	 * @param errors el listado de errores
	 */
	public InvalidEntityException(List<String> errors) {
		super();
		this.errors = errors;
	}

	/**
	 * Obtiene el listado de errores.
	 *
	 * @return el listado de errores
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * Establece el listado de errores.
	 *
	 * @param errors nuevo listado de errores
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
