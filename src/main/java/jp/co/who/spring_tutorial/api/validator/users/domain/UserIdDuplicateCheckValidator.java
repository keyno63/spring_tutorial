package jp.co.who.spring_tutorial.api.validator.users.domain;

import jp.co.who.spring_tutorial.api.validator.users.User;
import jp.co.who.spring_tutorial.api.validator.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIdDuplicateCheckValidator
        implements ConstraintValidator<UserIdDuplicateCheck, User>{

    private String message;

    @Autowired
    UserService userService;

    @Override
    public void initialize(UserIdDuplicateCheck constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {
        String userId = value.getUserId();
        boolean blResult = userService.checkDuplicate(userId);

        if(!blResult) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode("userId").addConstraintViolation();
        }

        return true;
    }
}
