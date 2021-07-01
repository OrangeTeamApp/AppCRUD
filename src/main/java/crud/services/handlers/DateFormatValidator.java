package crud.services.handlers;

import crud.exception.FormatDataException;

public class DateFormatValidator {

    private static final String REGEX_FOR_DATE = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

    public void validate (String date) throws FormatDataException {
        if (!date.matches(REGEX_FOR_DATE)) {
            throw new FormatDataException("Format of date is incorrect: " + date);
        }
    }
}
