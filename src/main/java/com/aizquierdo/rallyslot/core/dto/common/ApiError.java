package com.aizquierdo.rallyslot.core.dto.common;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * La clase ApiError.
 */
public class ApiError implements Serializable {

	private static final long serialVersionUID = -5876489941972896882L;

	private String message;

	private List<String> errors;

	/**
	 * Constructor.
	 *
	 * @param message el mensaje
	 */
	public ApiError(String message) {
		super();
		this.message = message;
	}

	/**
	 * Constructor.
	 *
	 * @param message el mensaje
	 * @param errors  el listado de errores
	 */
	public ApiError(String message, List<String> errors) {
		super();
		this.message = message;
		this.errors = errors;
	}

	/**
	 * Constructor.
	 *
	 * @param message el mensaje
	 * @param error   el error
	 */
	public ApiError(String message, String error) {
		super();
		this.message = message;
		errors = Arrays.asList(error);
	}

	/**
	 * Obtiene el mensaje.
	 *
	 * @return el mensaje
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Establece el mensaje.
	 *
	 * @param message nuevo mensaje
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * @param errors nuevo errors
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
