package uit.thesis.assessment_mgnt.validation.validator.user;

import uit.thesis.assessment_mgnt.validation.annotation.user.ConfirmPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {
    private String message;
    private String getPassword;
    private String getConfirmPassword;

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.getPassword = constraintAnnotation.getPassword();
        this.getConfirmPassword = constraintAnnotation.getConfirmPassword();
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
        try {
            String password = (String)target.getClass().getMethod(getPassword).invoke(target);
            String confirmPassword = (String)target.getClass().getMethod(getConfirmPassword).invoke(target);

            if(password.equals(confirmPassword))
                return true;

            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            e.printStackTrace();
        }

        return false;
    }
}
