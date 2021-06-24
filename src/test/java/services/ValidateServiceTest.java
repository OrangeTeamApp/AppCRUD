package services;

import exception.FormatDataException;
import model.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import services.handlers.UserValidator;

import java.time.LocalDate;

public class ValidateServiceTest {
    private final UserValidator validator = new UserValidator();
    private User user;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        user = new User(1L);
        user.setEmail("test@mail.ru");
        user.setFirstName("Ivan");
        user.setLastName("Abramov");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
    }

    @Test
    public void testUserFieldsValidationCorrectUserSuccess() throws FormatDataException {
        validator.validate(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithIncorrectEmail() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setEmail("incorrectmail.rururu");
        thrown.expectMessage("Email is incorrect: " + user.getEmail());
        validator.validate(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithEmptyEmail() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setEmail("");
        thrown.expectMessage("All fields are required! Try again!" + user.getEmail());
        validator.validate(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithIncorrectFirstName() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setFirstName("!@#$%^");
        thrown.expectMessage("First Name is incorrect: " + user.getFirstName());
        validator.validate(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithEmptyFirstName() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setFirstName("");
        thrown.expectMessage("Incorrect input data for user. All fields are required! Try again!");
        validator.validate(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithIncorrectLastName() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setLastName("123");
        thrown.expectMessage("Last Name is incorrect: " + user.getLastName());
        validator.validate(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithEmptyLastName() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setLastName("");
        thrown.expectMessage("Incorrect input data for user. All fields are required! Try again!");
        validator.validate(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithEmptyBirthDate() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setBirthDate(null);
        thrown.expectMessage("Incorrect input data for user. All fields are required! Try again!");
        validator.validate(user);
    }

    @Test
    public void testUserFieldsValidationFailsWithUnmatchedBirthDate() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        user.setBirthDate(LocalDate.now().plusWeeks(4L));
        thrown.expectMessage("Birth date is incorrect: " + user.getBirthDate());
        validator.validate(user);
    }

}