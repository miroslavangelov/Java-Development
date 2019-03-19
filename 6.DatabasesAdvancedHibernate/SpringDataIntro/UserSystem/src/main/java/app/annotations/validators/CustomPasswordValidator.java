package app.annotations.validators;

import app.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomPasswordValidator implements ConstraintValidator<Password, String> {
    StringBuilder sb;
    private int max;
    private boolean digits;
    private boolean lowercase;
    private boolean uppercase;
    private int min;
    private boolean specialSymbol;

    @Override
    public void initialize(Password constraintAnnotation) {
        min = constraintAnnotation.minLength();
        max = constraintAnnotation.maxLength();
        digits = constraintAnnotation.containsDigit();
        lowercase = constraintAnnotation.containsLowercase();
        uppercase = constraintAnnotation.containsUppercase();
        specialSymbol = constraintAnnotation.containsSpecialSymbol();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        sb = new StringBuilder();

        if (password.length() < min || password.length() > max) {
            sb.append(String.format(
                    "Length of password should be between %d and %d symbols%n", min, max));
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
        if (specialSymbol && !password.matches(".*[!@#$%^&*()_+<>?].*")) {
            sb.append("The password should contain at least one special symbol (!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)").append(System.lineSeparator());
        }

        if (!sb.toString().equals("")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(sb.toString().trim()).addConstraintViolation();
            return false;
        }

        return true;
    }
}
