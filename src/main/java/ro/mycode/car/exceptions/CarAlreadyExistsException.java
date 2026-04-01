package ro.mycode.car.exceptions;

import ro.mycode.system.consntants.ErrorConstants;


public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException() {
        super(ErrorConstants.CAR_ALREADY_EXISTS_ERROR_MESSAGE);
    }
}
