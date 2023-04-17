package br.com.nudemo.ces.common.validation.mapping;

import br.com.nudemo.ces.common.validation.LegalAgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LegalAgeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LegalAge {

    String message() default "{br.com.nudemo.ces.common.validation.mapping.LegalAge.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minimumValue() default 18;

}
