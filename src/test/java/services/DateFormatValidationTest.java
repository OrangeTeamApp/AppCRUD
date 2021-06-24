package services;

import exception.FormatDataException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import services.handlers.DateFormatValidator;


public class DateFormatValidationTest {
    private final DateFormatValidator dateFormatValidator = new DateFormatValidator();
    private static final String DATE_FORMAT_YYYY_MM_DD = "1999-01-21";
    private static final String DATE_FORMAT_DD_YYYY_MM = "21-1999-01";
    private static final String DATE_FORMAT_DD_MM_YYYY = "21-01-1999";
    private static final String DATE_FORMAT_YYYY_DD_MM = "1999-21-01";
    private static final String DATE_FORMAT_MM_DD_YYYY = "01-21-1999";
    private static final String DATE_FORMAT_MM_YYYY_DD = "01-1999-21";
    private static final String NONEXISTENT_DATE_AT_ALL = "2010-02-31";
    private static final String FEBRUARY_TWENTY_NINTH_OF_NOT_LEAR_YEAR = "2021-02-29";
    private static final String FEBRUARY_TWENTY_NINTH_OF_LEAR_YEAR = "2020-02-29";


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenInvalidDateFormat_YYYY_MM_DD_whenValidate_thenValidationIsPassed() throws FormatDataException {
        dateFormatValidator.validate(DATE_FORMAT_YYYY_MM_DD);
    }

    @Test
    public void givenInvalidDateFormat_DD_YYYY_MM_whenValidate_thenValidationIsFailed() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        thrown.expectMessage("Format of date is incorrect: " + DATE_FORMAT_DD_YYYY_MM);
        dateFormatValidator.validate(DATE_FORMAT_DD_YYYY_MM);
    }

    @Test
    public void givenInvalidDateFormat_DD_MM_YYYY_whenValidate_thenValidationIsFailed() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        thrown.expectMessage("Format of date is incorrect: " + DATE_FORMAT_DD_MM_YYYY);
        dateFormatValidator.validate(DATE_FORMAT_DD_MM_YYYY);
    }

    @Test
    public void givenInvalidDateFormat_YYYY_DD_MM_whenValidate_thenValidationIsFailed() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        thrown.expectMessage("Format of date is incorrect: " + DATE_FORMAT_YYYY_DD_MM);
        dateFormatValidator.validate(DATE_FORMAT_YYYY_DD_MM);
    }

    @Test
    public void givenInvalidDateFormat_MM_YYYY_DD_whenValidate_thenValidationIsFailed() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        thrown.expectMessage("Format of date is incorrect: " + DATE_FORMAT_MM_YYYY_DD);
        dateFormatValidator.validate(DATE_FORMAT_MM_YYYY_DD);
    }

    @Test
    public void givenInvalidDateFormat_MM_DD_YYYY_whenValidate_thenValidationIsFailed() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        thrown.expectMessage("Format of date is incorrect: " + DATE_FORMAT_MM_DD_YYYY);
        dateFormatValidator.validate(DATE_FORMAT_MM_DD_YYYY);
    }

    @Test
    public void givenNonExistentDateAtAll_whenValidate_thenValidationIsFailed() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        thrown.expectMessage("Format of date is incorrect: " + NONEXISTENT_DATE_AT_ALL);
        dateFormatValidator.validate(NONEXISTENT_DATE_AT_ALL);
    }

    @Test
    public void givenExistentDateAtLeapYear_whenValidate_thenValidationIsPassed() throws FormatDataException {
        dateFormatValidator.validate(FEBRUARY_TWENTY_NINTH_OF_LEAR_YEAR);
    }

    @Test
    public void givenNonExistentDateAtNotLeapYear_whenValidate_thenValidationIsFailed() throws FormatDataException {
        thrown.expect(FormatDataException.class);
        thrown.expectMessage("Format of date is incorrect: " + FEBRUARY_TWENTY_NINTH_OF_NOT_LEAR_YEAR);
        dateFormatValidator.validate(FEBRUARY_TWENTY_NINTH_OF_NOT_LEAR_YEAR);
    }






}
