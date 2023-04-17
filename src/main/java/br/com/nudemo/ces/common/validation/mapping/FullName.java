package br.com.nudemo.ces.common.validation.mapping;

import br.com.nudemo.ces.common.validation.FullNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FullNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FullName {

    String message() default "{br.com.nudemo.ces.common.validation.mapping.FullName.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regexp() default "^[a-zA-Z]{2,}(?: [a-zA-Z]+)+$";

}
