package com.aizquierdo.rallyslot.core.common;

import com.aizquierdo.rallyslot.core.util.RallyslotConstants;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, Object> {
	private List<EnumBase> acceptedValues;

	@Override
	public void initialize(ValueOfEnum annotation) {
		acceptedValues = Stream.of(annotation.enumClass().getEnumConstants()).collect(Collectors.toList());
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean esValido = Boolean.FALSE;
		if (value == null || CollectionUtils.isEmpty(acceptedValues)) {
			return esValido;
		}

		EnumBase enumBase = acceptedValues.get(RallyslotConstants.ZERO);

		return enumBase.buscaPorValidacion(value) != null;
	}
}
