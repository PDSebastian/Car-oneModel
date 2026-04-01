package ro.mycode.car.exceptions;

import ro.mycode.system.consntants.ErrorConstants;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(){

        super(ErrorConstants.CAR_NOT_FOUND_ERROR_MESSAGE);
    }
}
