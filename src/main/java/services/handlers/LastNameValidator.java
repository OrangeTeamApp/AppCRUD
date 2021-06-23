package services.handlers;

import exception.FormatDataException;
import model.User;

public class LastNameValidator implements Validator {

    private static final String REGEX_FOR_NAME = "^[a-zA-Z '.-]*$";
    private static Validator nextValidator;

    @Override
    public void validate(User user) throws FormatDataException {
        if (user.getLastName().matches(REGEX_FOR_NAME)) {
            if (nextValidator != null) {
                nextValidator.validate(user);
            }
        } else {
            throw new FormatDataException("Last Name is incorrect: " + user.getLastName());
        }
    }

    @Override
    public void setNext(Validator validator) {
        nextValidator = validator;
    }
}
