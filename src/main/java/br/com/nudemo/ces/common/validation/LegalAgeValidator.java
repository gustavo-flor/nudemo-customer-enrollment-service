package br.com.nudemo.ces.common.validation;

import br.com.nudemo.ces.common.validation.mapping.LegalAge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class LegalAgeValidator implements ConstraintValidator<LegalAge, Integer> {

    private Integer minimumValue;

    @Override
    public void initialize(LegalAge constraintAnnotation) {
        minimumValue = constraintAnnotation.minimumValue();
    }

    @Override
    public boolean isValid(final Integer input, final ConstraintValidatorContext context) {
        return isNull(input) || input >= minimumValue;
    }

}
