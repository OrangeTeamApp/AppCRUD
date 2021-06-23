package services.handlers;

import exception.FormatDataException;
import model.User;

public interface Validator {

    void validate(User user) throws FormatDataException;

    void setNext(Validator validator);
}
