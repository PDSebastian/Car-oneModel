package ro.mycode.car.exceptions;

import ro.mycode.system.consntants.ErrorConstants;

public class InvalidModelException extends RuntimeException {
    public InvalidModelException() {
        super(ErrorConstants.INVALID_MODEL_ERROR_MESSAGE);
    }
}
