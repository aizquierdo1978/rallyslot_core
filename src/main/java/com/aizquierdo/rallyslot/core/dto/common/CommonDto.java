package com.aizquierdo.rallyslot.core.dto.common;


import java.io.Serializable;
import java.util.Date;

/**
 * La clase CommonDTO.
 */
public class CommonDto implements Serializable{
	
	private static final long serialVersionUID = 4660877437464343165L;

	private Date creationDate;
    
    private String creationUser;

	private Date modificationDate;

	private String modificationUser;

	private Date deleteDate;

	private String deleteUser;

	/**
	 * Obtiene la fecha de creación.
	 *
	 * @return la fecha de creación
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Establece la fecha de creación.
	 *
	 * @param creationDate nuevo creation date
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Obtiene el usuario que crea.
	 *
	 * @return el usuario que crea
	 */
	public String getCreationUser() {
		return creationUser;
	}

	/**
	 * Establece el usuario que crea.
	 *
	 * @param creationUser nuevo creation user
	 */
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	/**
	 * Obtiene la fecha de modificationicación.
	 *
	 * @return la fecha de modificationicación
	 */
	public Date getModificationDate() {
		return modificationDate;
	}

	/**
	 * Establece la fecha de modificationicación.
	 *
	 * @param modificationDate nuevo modification date
	 */
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	/**
	 * Obtiene el usuario que modificationica.
	 *
	 * @return el usuario que modificationica
	 */
	public String getModificationUser() {
		return modificationUser;
	}

	/**
	 * Establece el usuario que modificationica.
	 *
	 * @param modificationUser nuevo modification user
	 */
	public void setModificationUser(String modificationUser) {
		this.modificationUser = modificationUser;
	}

	/**
	 * Obtiene la fecha de baja.
	 *
	 * @return la fecha de baja
	 */
	public Date getDeleteDate() {
		return deleteDate;
	}

	/**
	 * Establece la fecha de baja.
	 *
	 * @param deleteDate nuevo delete date
	 */
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	/**
	 * Obtiene el usuario que da de baja.
	 *
	 * @return el usuario que da de baja
	 */
	public String getDeleteUser() {
		return deleteUser;
	}

	/**
	 * Establece el usuario que da de baja.
	 *
	 * @param deleteUser nuevo delete user
	 */
	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

}
