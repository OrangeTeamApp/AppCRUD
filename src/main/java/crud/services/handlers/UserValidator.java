package crud.services.handlers;

import crud.exception.FormatDataException;
import crud.model.User;

public class UserValidator implements Validator {

    private static Validator nextValidator = new RequiredFieldsValidator();

    @Override
    public void validate(User user) throws FormatDataException {
        if (user != null) {
            if (nextValidator != null) {
                nextValidator.validate(user);
            }
        } else {
            throw new FormatDataException("Email is incorrect: " + user.getEmail());
        }
    }

    @Override
    public void setNext(Validator validator) {
        nextValidator = validator;
    }
}
