package services.handlers;

import exception.FormatDataException;
import model.User;

public class EmailValidator implements Validator {

    private static final String REGEX_FOR_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Validator nextValidator = new FirstNameValidator();

    @Override
    public void validate(User user) throws FormatDataException {
        if (user.getEmail().matches(REGEX_FOR_EMAIL)) {
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
