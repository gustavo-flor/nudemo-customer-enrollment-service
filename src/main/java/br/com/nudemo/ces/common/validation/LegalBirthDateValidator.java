package br.com.nudemo.ces.common.validation;

import br.com.nudemo.ces.common.validation.mapping.LegalBirthDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

import static java.time.LocalDate.now;
import static java.util.Objects.isNull;

public class LegalBirthDateValidator implements ConstraintValidator<LegalBirthDate, LocalDate> {

    private Integer minimumValue;

    @Override
    public void initialize(final LegalBirthDate constraintAnnotation) {
        minimumValue = constraintAnnotation.minimumAge();
    }

    @Override
    public boolean isValid(final LocalDate input, final ConstraintValidatorContext context) {
        return isNull(input) || Period.between(now(), input).getYears() >= minimumValue;
    }

}
