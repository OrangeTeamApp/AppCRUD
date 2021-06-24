package services.handlers;

import exception.FormatDataException;
import model.User;

import java.time.LocalDate;

public class BirthDateValidator implements Validator{

    private static Validator nextValidator;

    @Override
    public void validate(User user) throws FormatDataException {
        if (user.getBirthDate().compareTo(LocalDate.now()) < 0) {
            if (nextValidator != null) {
                nextValidator.validate(user);
            }
        } else {
            throw new FormatDataException("Birth date is incorrect: " + user.getBirthDate());
        }
    }

    @Override
    public void setNext(Validator validator) {
        nextValidator = validator;
    }
}
