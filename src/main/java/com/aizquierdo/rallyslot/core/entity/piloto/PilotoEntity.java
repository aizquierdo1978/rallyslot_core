package com.aizquierdo.rallyslot.core.entity.piloto;


import java.io.Serializable;

import com.aizquierdo.rallyslot.core.entity.common.CommonEntity;

/**
 * La clase Piloto.
 */
public class PilotoEntity extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -4243781575432440180L;

	private Long pilotoId;
    
	private String pilotoName;

	/**
	 * Obtiene el identificador del piloto.
	 *
	 * @return el identificador del piloto
	 */
	public Long getPilotoId() {
		return pilotoId;
	}

	/**
	 * Establece el identificador del piloto.
	 *
	 * @param pilotoId nuevo piloto id
	 */
	public void setPilotoId(Long pilotoId) {
		this.pilotoId = pilotoId;
	}

	/**
	 * Obtiene el el nombre del piloto.
	 *
	 * @return el el nombre del piloto
	 */
	public String getPilotoName() {
		return pilotoName;
	}

	/**
	 * Establece el el nombre del piloto.
	 *
	 * @param pilotoName nuevo el nombre del piloto
	 */
	public void setPilotoName(String pilotoName) {
		this.pilotoName = pilotoName;
	}
}
