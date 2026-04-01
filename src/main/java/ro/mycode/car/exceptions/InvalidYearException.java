package ro.mycode.car.exceptions;

import ro.mycode.system.consntants.ErrorConstants;

public class InvalidYearException extends RuntimeException {
    public InvalidYearException() {

        super(ErrorConstants.INVALID_YEAR_ERROR_MESSAGE);
    }
}
