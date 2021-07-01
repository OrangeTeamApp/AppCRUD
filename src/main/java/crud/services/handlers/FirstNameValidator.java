package crud.services.handlers;

import crud.exception.FormatDataException;
import crud.model.User;

public class FirstNameValidator implements Validator {

    private static final String REGEX_FOR_NAME = "^[a-zA-Z '.-]*$";
    private static Validator nextValidator = new LastNameValidator();

    @Override
    public void validate(User user) throws FormatDataException {
        if (user.getFirstName().matches(REGEX_FOR_NAME)) {
            if (nextValidator != null) {
                nextValidator.validate(user);
            }
        } else {
            throw new FormatDataException("First Name is incorrect: " + user.getFirstName());
        }
    }

    @Override
    public void setNext(Validator validator) {
        nextValidator = validator;
    }
}
