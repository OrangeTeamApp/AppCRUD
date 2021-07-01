package crud.services.handlers;

import crud.exception.FormatDataException;
import crud.model.User;

public class RequiredFieldsValidator implements Validator {

    private static final String EMPTY_STRING = "";
    private static Validator nextValidator = new EmailValidator();

    @Override
    public void validate(User user) throws FormatDataException {
        if (!user.getFirstName().equals(EMPTY_STRING) && !user.getLastName().equals(EMPTY_STRING)
                && !user.getEmail().equals(EMPTY_STRING) && user.getBirthDate() != null) {
            if (nextValidator != null) {
                nextValidator.validate(user);
            }
        } else {
            throw new FormatDataException("All fields are required! Try again!");
        }
    }

    @Override
    public void setNext(Validator validator) {
        nextValidator = validator;
    }
}
