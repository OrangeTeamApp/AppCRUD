package crud.services;

import crud.exception.FormatDataException;
import crud.model.User;
import crud.services.handlers.DateFormatValidator;
import crud.services.handlers.UserValidator;
import crud.services.handlers.Validator;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private static final Validator validator = new UserValidator();
    private static final DateFormatValidator dateFormatValidator = new DateFormatValidator();

    public void validateUser(User user) throws FormatDataException {
        validator.validate(user);
        dateFormatValidator.validate(user.getBirthDate().toString());
    }
}
