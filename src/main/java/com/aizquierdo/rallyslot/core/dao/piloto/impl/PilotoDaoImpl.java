package com.aizquierdo.rallyslot.core.dao.piloto.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aizquierdo.rallyslot.core.dao.piloto.PilotoDao;
import com.aizquierdo.rallyslot.core.entity.piloto.PilotoEntity;
import com.aizquierdo.rallyslot.core.mapper.piloto.PilotoMapper;

/**
 * Implementación del servicio de Piloto.
 */
@Component
public class PilotoDaoImpl implements PilotoDao {

	@Autowired
	private PilotoMapper pilotoMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PilotoEntity findById(Long pilotoId) {
		return pilotoMapper.getPiloto(pilotoId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PilotoEntity savePiloto(PilotoEntity piloto) {
		pilotoMapper.insertPiloto(piloto);
		return piloto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PilotoEntity> fetchPilotoList() {
		return pilotoMapper.getPilotos();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PilotoEntity updatePiloto(PilotoEntity piloto, Long pilotoId) {
		pilotoMapper.updatePiloto(piloto);
		return piloto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletePilotoById(PilotoEntity piloto) {
		pilotoMapper.deletePiloto(piloto);
	}

}
