package com.aizquierdo.rallyslot.core.dao.piloto;

import java.util.List;

import com.aizquierdo.rallyslot.core.entity.piloto.PilotoEntity;

/**
 * La interfaz PilotoDao.
 */
public interface PilotoDao {

	/**
	 * Busca el piloto por id
	 *
	 * @param pilotoId el identificador del piloto
	 * @return el piloto
	 */
	PilotoEntity findById(Long pilotoId);

	/**
	 * Guarda el piloto.
	 *
	 * @param piloto el piloto
	 * @return el piloto
	 */
	PilotoEntity savePiloto(PilotoEntity piloto);
	 
    /**
	 * Obtiene el listado de pilotoes.
	 *
	 * @return el listado de pilotoes
	 */
	List<PilotoEntity> fetchPilotoList();
 
    /**
	 * Actualiza el piloto.
	 *
	 * @param piloto   el piloto
	 * @param pilotoId el identificador del piloto
	 * @return el piloto
	 */
    // Update operation
	PilotoEntity updatePiloto(PilotoEntity piloto, Long pilotoId);
 
    /**
	 * Borra el piloto por su identificador
	 *
	 * @param pilotoId el identificador del piloto
	 */
    // Delete operation
	void deletePilotoById(PilotoEntity piloto);
		
}
