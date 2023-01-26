package com.aizquierdo.rallyslot.core.service.piloto;

import java.util.Date;
import java.util.List;

import com.aizquierdo.rallyslot.core.dto.piloto.Piloto;

/**
 * La interfaz de servicio de pilotos.
 */
public interface PilotoService {

	/**
	 * Guarda el piloto.
	 *
	 * @param piloto el piloto
	 * @return el piloto
	 */
	Piloto savePiloto(Piloto piloto);
	 
    /**
	 * Obtiene el listado de pilotos.
	 *
	 * @return el listado de pilotos
	 */
	List<Piloto> fetchPilotoList();
 
    /**
	 * Actualiza un piloto.
	 *
	 * @param piloto   el piloto
	 * @param pilotoId el identificador del piloto
	 * @return el piloto
	 */
	Piloto updatePiloto(Piloto piloto, Long pilotoId);
 
    /**
	 * Borra un piloto por su id.
	 *
	 * @param pilotoId el identificador del piloto
	 */
	void deletePilotoById(Long pilotoId, String usuario, Date date);

	/**
	 * Obtiene el piloto.
	 *
	 * @param piloto   el piloto
	 * @param pilotoId el identificador del piloto
	 * @return el piloto
	 */
	Piloto getPiloto(Long pilotoId);
		
}
