package br.com.nudemo.ces.common.validation;

import br.com.nudemo.ces.common.util.StringUtil;
import br.com.nudemo.ces.common.validation.mapping.FullName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullNameValidator implements ConstraintValidator<FullName, String> {

    private Pattern fullNamePattern;

    @Override
    public void initialize(final FullName constraintAnnotation) {
        fullNamePattern = Pattern.compile(constraintAnnotation.regexp());
    }

    @Override
    public boolean isValid(final String input, final ConstraintValidatorContext context) {
        return Optional.ofNullable(input)
                .map(String::trim)
                .map(StringUtil::removeAccents)
                .map(value -> fullNamePattern.matcher(value))
                .map(Matcher::matches)
                .orElse(true);
    }

}
