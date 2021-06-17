package services;

import dao.JdbcUserDao;
import dao.UserDao;
import exception.FormatDataException;
import model.User;

public class UserService {
    private static final String EMPTY_STRING = "";
    private static final String REGEX_FOR_EMAIL = "^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$";
    private static final String REGEX_FOR_DATE = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
    private static final String REGEX_FOR_LOGIN = "^[a-zA-Z][a-zA-z0-9]*$";
    private static final String REGEX_FOR_NAME = "/^[a-zA-Z '.-]*$/";


    private boolean fieldsAreRequiredValidator(User user) {
        if (user.getLogin().equals(EMPTY_STRING) || user.getPassword().equals(EMPTY_STRING)
                || user.getFirstName().equals(EMPTY_STRING) || user.getLastName().equals(EMPTY_STRING)
                || user.getBirthDate() == null) {
            return false;
        }
        return true;
    }

    private  boolean emailFormatValidator(String email) {
       return validate(email, REGEX_FOR_EMAIL);
    }

    public  boolean dateFormatValidator(String date) {
        return validate(date, REGEX_FOR_DATE);
    }

    private  boolean loginFormatValidator(String login) {
        return validate(login, REGEX_FOR_LOGIN);
    }

    private  boolean nameFormatValidator(String name) {
        return validate(name, REGEX_FOR_NAME);
    }

    private boolean validate(String parameter, String regex) {
        if (!parameter.matches(regex)) {
            return false;
        }
        return true;
    }

    public void userFieldsValidation(User user) throws FormatDataException{
        if (! fieldsAreRequiredValidator(user)) {
            throw new FormatDataException("All fields are required! Try again!");
        } else if (! emailFormatValidator(user.getEmail())) {
            throw new FormatDataException("Email is incorrect: " + user.getEmail());
        } else if (! loginFormatValidator(user.getLogin())) {
            throw new FormatDataException("Login is incorrect: " + user.getLogin());
        } else if (! nameFormatValidator(user.getFirstName())) {
            throw new FormatDataException("First Name is incorrect: " + user.getFirstName());
        } else if (! nameFormatValidator(user.getLastName())) {
            throw new FormatDataException("Last Name is incorrect: " + user.getLastName());
        }
    }





}
