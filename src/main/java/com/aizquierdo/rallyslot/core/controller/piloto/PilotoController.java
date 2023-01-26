package com.aizquierdo.rallyslot.core.controller.piloto;

import com.aizquierdo.rallyslot.core.controller.BaseController;
import com.aizquierdo.rallyslot.core.dto.piloto.Piloto;
import com.aizquierdo.rallyslot.core.exception.EntityNotFoundException;
import com.aizquierdo.rallyslot.core.exception.InvalidEntityException;
import com.aizquierdo.rallyslot.core.service.piloto.PilotoService;
import com.aizquierdo.rallyslot.core.util.RallyslotConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * La clase del controlador de pilotos.
 */
@RestController
public class PilotoController extends BaseController {

	private static final Log LOG = LogFactory.getLog(PilotoController.class);

	@Autowired
	private PilotoService pilotoService;

	@PostMapping("/pilotos")
	public ResponseEntity<Piloto> savePiloto(@RequestBody Piloto piloto) {
		LOG.debug("Inicio de creacion de un piloto con datos:" + piloto.toString());
		ResponseEntity<Piloto> respuesta = null;
		try {
			piloto.setCreationDate(new Date());
			piloto.setCreationUser("user");
			Piloto pilotoCreado = pilotoService.savePiloto(piloto);
			respuesta = new ResponseEntity<>(pilotoCreado, HttpStatus.OK);
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al crear el piloto con los datos " + piloto.toString(), e);
			throw e;
		}
		LOG.debug("Fin de creacion de un piloto con datos:" + piloto.toString());
		return respuesta;
	}

	// Read operation
	@GetMapping("/pilotos")
	public List<Piloto> fetchPilotoList() {
		LOG.debug("Inicio de consulta de listados de piloto.");
		List<Piloto> listadoPilotos = pilotoService.fetchPilotoList();
		LOG.debug("Fin de consulta de listados de piloto.");
		return listadoPilotos;
	}

	// Update operation
	@PutMapping("/pilotos/{id}")
	public ResponseEntity<Piloto> updatePiloto(@RequestBody Piloto piloto, @PathVariable("id") Long pilotoId) {
		LOG.debug("Inicio de modificacion del piloto con identificador " + pilotoId);
		ResponseEntity<Piloto> respuesta = null;
		try {
			piloto.setModificationDate(new Date());
			piloto.setModificationUser("user");
			piloto = pilotoService.updatePiloto(piloto, pilotoId);
			respuesta = new ResponseEntity<>(piloto, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (InvalidEntityException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al modificar el piloto con identificador " + pilotoId, e);
			throw e;
		}
		LOG.debug("Fin de modificacion del piloto con identificador " + pilotoId);
		return respuesta;
	}

	// Delete operation
	@DeleteMapping("/pilotos/{id}")
	public ResponseEntity<String> deletePilotoById(@PathVariable("id") Long pilotoId) {
		LOG.debug("Fin de borrado del piloto con identificador " + pilotoId);
		ResponseEntity<String> respuesta = null;
		try {
			pilotoService.deletePilotoById(pilotoId, RallyslotConstants.USER, new Date());
			respuesta = new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al borrar el piloto con identificador " + pilotoId, e);
			throw e;
		}
		LOG.debug("Fin de borrado del piloto con identificador " + pilotoId);
		return respuesta;
	}

	/**
	 * Obtiene el piloto.
	 *
	 * @param pilotoId el identificador del piloto
	 * @return el piloto
	 */
	@GetMapping("/pilotos/{id}")
	public ResponseEntity<Piloto> getPiloto(@PathVariable("id") Long pilotoId) {
		LOG.debug("Inicio de obtencion del piloto con identificador " + pilotoId);
		ResponseEntity<Piloto> respuesta = null;
		try {
			Piloto piloto = pilotoService.getPiloto(pilotoId);
			respuesta = new ResponseEntity<>(piloto, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			LOG.debug(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Se ha producido un error al obtener el piloto con identificador " + pilotoId, e);
			throw e;
		}
		LOG.debug("Fin de obtencion del piloto con identificador " + pilotoId);
		return respuesta;
	}

}
