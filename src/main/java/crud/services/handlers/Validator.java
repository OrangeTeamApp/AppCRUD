package crud.services.handlers;

import crud.exception.FormatDataException;
import crud.model.User;

public interface Validator {

    void validate(User user) throws FormatDataException;

    void setNext(Validator validator);
}
