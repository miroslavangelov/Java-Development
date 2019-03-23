package app.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtil {
    private Validator validator;

    public ValidatorUtil() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <E> boolean isValid(E object) {
        System.out.println(this.validator.validate(object).size());
        for(ConstraintViolation e:this.validator.validate(object) ) {
            System.out.println(e.getMessage());
        }
        return this.validator.validate(object).size() == 0;
    }

    public <E> Set<ConstraintViolation<E>> violations(E object) {
        return this.validator.validate(object);
    }
}
