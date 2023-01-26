package com.aizquierdo.rallyslot.core.service.piloto.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aizquierdo.rallyslot.core.dao.piloto.PilotoDao;
import com.aizquierdo.rallyslot.core.dto.piloto.Piloto;
import com.aizquierdo.rallyslot.core.entity.piloto.PilotoEntity;
import com.aizquierdo.rallyslot.core.exception.EntityNotFoundException;
import com.aizquierdo.rallyslot.core.exception.InvalidEntityException;
import com.aizquierdo.rallyslot.core.service.common.impl.CommonServiceImpl;
import com.aizquierdo.rallyslot.core.service.piloto.PilotoService;

/**
 * Implementación del servicio de pilotoes.
 */
@Service
public class PilotoServiceImpl extends CommonServiceImpl implements PilotoService {
	
	@Autowired
	private PilotoDao pilotoDao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Piloto savePiloto(Piloto piloto) {
		// Se valida y se parsea
		PilotoEntity pilotoEntity = validateData(piloto);
		// Se crea el piloto
		PilotoEntity createdPiloto = pilotoDao.savePiloto(pilotoEntity);
		return map(createdPiloto, Piloto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Piloto> fetchPilotoList() {
		List<PilotoEntity> pilotoEntityList = pilotoDao.fetchPilotoList();
		return mapList(pilotoEntityList, Piloto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Piloto updatePiloto(Piloto piloto, Long pilotoId) {
		// Se valida el piloto y se parsea
		PilotoEntity pilotoEntity = validateData(piloto, pilotoId);
		// Se actualiza
		pilotoDao.updatePiloto(pilotoEntity, pilotoId);
		// Obtenemos los nuevos valores del piloto
		PilotoEntity updatedPiloto = pilotoDao.findById(pilotoId);

		return map(updatedPiloto, Piloto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletePilotoById(Long pilotoId, String usuario, Date date) {
		// Validamos si existe el piloto
		existsPiloto(pilotoId);
		PilotoEntity piloto = new PilotoEntity();
		piloto.setPilotoId(pilotoId);
		piloto.setDeleteUser(usuario);
		piloto.setDeleteDate(date);
		pilotoDao.deletePilotoById(piloto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Piloto getPiloto(Long pilotoId) {
		PilotoEntity piloto = pilotoDao.findById(pilotoId);
		if (piloto == null) {
			throw new EntityNotFoundException("No existe el piloto con el identificador: " + pilotoId);
		}
		return map(piloto, Piloto.class);
	}

	/**
	 * Si existe el piloto.
	 *
	 * @param pilotoId el identificador del piloto
	 * @return true, si existe
	 */
	private boolean existsPiloto(Long pilotoId) {
		if (pilotoDao.findById(pilotoId) == null) {
			throw new EntityNotFoundException("No existe el piloto con el identificador: " + pilotoId);
		}
		return Boolean.TRUE;
	}

	/**
	 * Valida los datos.
	 *
	 * @param piloto   el piloto
	 * @param pilotoId el identificador del piloto
	 * @return la entidad del piloto
	 */
	private PilotoEntity validateData(Piloto piloto, Long pilotoId) {
		// Validamos si existe el piloto
		existsPiloto(pilotoId);

		if (!pilotoId.equals(piloto.getPilotoId())) {
			throw new InvalidEntityException("El piloto de la ruta es distinto al del objeto");
		}

		return validateData(piloto);
	}

	/**
	 * Valida los datos.
	 *
	 * @param piloto la piloto
	 * @return la entidad de la piloto
	 */
	private PilotoEntity validateData(Piloto piloto) {
		if (piloto.getPilotoName() == null) {
			throw new InvalidEntityException("No ha informado el piloto");
		}

		return map(piloto, PilotoEntity.class);
	}

}
