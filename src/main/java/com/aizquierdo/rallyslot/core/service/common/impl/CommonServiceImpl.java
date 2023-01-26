package com.aizquierdo.rallyslot.core.service.common.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aizquierdo.rallyslot.core.service.common.CommonService;

/**
 * Implementación del servicio común.
 */
@Service
public class CommonServiceImpl implements CommonService {
	
	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}

	@Override
	public <S, T> T map(S source, Class<T> targetClass) {
		return modelMapper.map(source, targetClass);
	}
	


}
