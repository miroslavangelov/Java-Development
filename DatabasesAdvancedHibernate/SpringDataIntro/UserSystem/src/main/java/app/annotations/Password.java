package app.annotations;

import app.annotations.validators.CustomPasswordValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Documented
@Constraint(validatedBy = CustomPasswordValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    int minLength();

    int maxLength();

    boolean containsDigit() default true;

    boolean containsLowercase() default true;

    boolean containsUppercase() default true;

    boolean containsSpecialSymbol() default true;
}
