package app.validators;

import app.annotations.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomEmailValidator implements ConstraintValidator<Email, String> {
    private Pattern regexPattern;

    @Override
    public void initialize(Email constraintAnnotation) {
        regexPattern = Pattern.compile(constraintAnnotation.regex());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Matcher m = regexPattern.matcher(value);
        return m.find();
    }
}
