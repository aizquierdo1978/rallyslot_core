package com.aizquierdo.rallyslot.core.service.common;

import java.util.List;

/**
 * The Interface ClubService.
 */
public interface CommonService {

	/**
	 * Mapea una lista.
	 *
	 * @param <S>         el tipo genérico de la lista de entrada
	 * @param <T>         el tipo genérico de la lista de salida
	 * @param source      la lista origen
	 * @param targetClass la clase destino
	 * @return lista parseada
	 */
	<S, T> List<T> mapList(List<S> source, Class<T> targetClass);

	/**
	 * Mapea una objeto.
	 *
	 * @param <S>         el tipo genérico de entrada
	 * @param <T>         el tipo genérico de salida
	 * @param source      el objeto origen
	 * @param targetClass la clase destino
	 * @return lista parseada
	 */
	<S, T> T map(S source, Class<T> targetClass);
}
