package ro.mycode.user.exceptions;

import ro.mycode.system.consntants.ErrorConstants;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_ERROR_MESSAGE);
    }
}
