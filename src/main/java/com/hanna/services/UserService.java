package com.hanna.services;


import com.hanna.dao.JdbcUserDao;
import com.hanna.dao.UserDao;
import com.hanna.model.User;

public class UserService {
    private UserDao userDao = new JdbcUserDao();
    private static final String EMPTY_STRING = "";
    private static final String REGEX_FOR_EMAIL = "^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$";
    private static final String REGEX_FOR_DATE = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";


    public boolean fieldsAreRequiredValidator(User user) {
        if (user.getLogin().equals(EMPTY_STRING) || user.getPassword().equals(EMPTY_STRING)
                || user.getFirstName().equals(EMPTY_STRING) || user.getLastName().equals(EMPTY_STRING)
                || user.getBirthDate() == null) {
            return false;
        }
        return true;
    }

    public  boolean emailFormatValidator(String email) {
        if (!email.matches(REGEX_FOR_EMAIL)) {
            return false;
        }
        return true;
    }

    public  boolean dateFormatValidator(String date) {
        if (!date.matches(REGEX_FOR_DATE)) {
            return false;
        }
        return true;
    }

    public String userFieldsValidation(User user) {
        String errorMessage = null;
        if(user == null) {
            errorMessage = "Invalid date format";
        } else if (!emailFormatValidator(user.getEmail())) {
            errorMessage = "Invalid email format";
        }  else if(!fieldsAreRequiredValidator(user)) {
            errorMessage = "All fields are required!";
        }
        return  errorMessage;
    }





}
