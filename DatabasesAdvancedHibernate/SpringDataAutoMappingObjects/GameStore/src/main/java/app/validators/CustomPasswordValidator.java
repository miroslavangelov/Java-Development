package app.validators;

import app.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomPasswordValidator implements ConstraintValidator<Password, String> {
    StringBuilder sb;
    private boolean digits;
    private boolean lowercase;
    private boolean uppercase;
    private int min;

    @Override
    public void initialize(Password constraintAnnotation) {
        min = constraintAnnotation.minLength();
        digits = constraintAnnotation.containsDigit();
        lowercase = constraintAnnotation.containsLowercase();
        uppercase = constraintAnnotation.containsUppercase();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        sb = new StringBuilder();

        if (password.length() < min) {
            sb.append(String.format(
                    "Length of password should be at least %d symbols%n", min));
        }
        if (lowercase && !password.matches(".*[a-z].*")) {
            sb.append("The password should contain at least one letter (a-z)").append(System.lineSeparator());
        }

        if (uppercase && !password.matches(".*[A-Z].*")) {
            sb.append("The password should contain at least one capital letter (A-Z)").append(System.lineSeparator());
        }
        if (digits && !password.matches(".*\\d.*")) {
            sb.append("The password should contain at least one digit (0-9)").append(System.lineSeparator());
        }

        if (!sb.toString().equals("")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(sb.toString().trim()).addConstraintViolation();
            return false;
        }

        return true;
    }
}
