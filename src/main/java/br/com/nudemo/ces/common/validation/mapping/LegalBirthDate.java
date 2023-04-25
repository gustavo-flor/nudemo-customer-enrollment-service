package br.com.nudemo.ces.common.validation.mapping;

import br.com.nudemo.ces.common.validation.LegalBirthDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LegalBirthDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LegalBirthDate {

    String message() default "{br.com.nudemo.ces.common.validation.mapping.LegalBirthDate.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minimumAge() default 18;

}
