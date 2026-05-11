package ro.mycode.user.exceptions;

import ro.mycode.system.consntants.ErrorConstants;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super(ErrorConstants.USER_ALREADY_EXISTS_ERROR_MESSAGE);
    }
}
