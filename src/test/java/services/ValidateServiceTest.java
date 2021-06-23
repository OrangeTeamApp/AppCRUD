package services;

import exception.FormatDataException;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

public class ValidateServiceTest {
    private final ValidateService validateService = new ValidateService();
    private User user;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        user = new User(1L);
        user.setEmail("test@mail.ru");
        user.setFirstName("Name");
        user.setLastName("LastName");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
    }

    @Test
    public void testUserFieldsValidationCorrectUserSuccess() throws FormatDataException {
        validateService.userFieldsValidation(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithIncorrectEmail() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setEmail("incorrectmail.rururu");
        thrown.expectMessage("Email is incorrect: " + user.getEmail());
        validateService.userFieldsValidation(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithEmptyEmail() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setEmail("");
        thrown.expectMessage("Email is incorrect: " + user.getEmail());
        validateService.userFieldsValidation(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithIncorrectFirstName() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setFirstName("!@#$%^");
        thrown.expectMessage("First Name is incorrect: " + user.getFirstName());
        validateService.userFieldsValidation(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithEmptyFirstName() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setFirstName("");
        thrown.expectMessage("Incorrect input data for user. All fields are required! Try again!");
        validateService.userFieldsValidation(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithIncorrectLastName() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setLastName("123");
        thrown.expectMessage("Last Name is incorrect: " + user.getLastName());
        validateService.userFieldsValidation(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithEmptyLastName() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setLastName("");
        thrown.expectMessage("Incorrect input data for user. All fields are required! Try again!");
        validateService.userFieldsValidation(user);
    }

    @Test
    public void testDateFormatValidatorFailsWithIncorrectDate() {
        ValidateService validateService = new ValidateService();
        user.setBirthDate(LocalDate.of(2025, 1, 1));
        boolean isDateCorrect = validateService.dateIsNotFutureData(user.getBirthDate());
        Assert.assertFalse(isDateCorrect);
    }

    @Test
    public void testDateFormatValidatorSuccess() {
        ValidateService validateService = new ValidateService();
        boolean isDateCorrect = validateService.dateFormatValidator(String.valueOf(user.getBirthDate()));
        Assert.assertTrue(isDateCorrect);
    }
}