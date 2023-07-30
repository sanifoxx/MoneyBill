package com.moneybill.moneybill.annotation.null_or_not_blank;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrNotBlank {

    String message() default "The parameter must be null or must be not empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
