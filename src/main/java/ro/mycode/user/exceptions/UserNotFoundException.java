package ro.mycode.user.exceptions;

import ro.mycode.system.consntants.ErrorConstants;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(ErrorConstants.USER_NOT_FOUND_ERROR_MESSAGE);
    }
}
